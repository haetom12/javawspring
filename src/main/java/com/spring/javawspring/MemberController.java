package com.spring.javawspring;

import java.util.ArrayList;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.javawspring.pagenation.PageProcess;
import com.spring.javawspring.pagenation.PageVO;
import com.spring.javawspring.service.MemberService;
import com.spring.javawspring.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired	
	PageProcess pageProcess;
	
	@Autowired
	JavaMailSender mailSender;

	@RequestMapping(value = "/memberLogin", method = RequestMethod.GET)
	public String memberLoginGet(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("cMid")) {
				request.setAttribute("mid", cookies[i].getValue());
				break;
			}
		}
		return "member/memberLogin";
	}
	@RequestMapping(value = "/memberLogin", method = RequestMethod.POST)
	public String memberLoginPost(HttpServletRequest request, HttpServletResponse response, HttpSession session ,
			@RequestParam(name="mid", defaultValue = "", required = false) String mid, 
			@RequestParam(name="pwd", defaultValue = "", required = false) String pwd, 
			@RequestParam(name="idCheck", defaultValue = "", required = false) String idCheck) { // null값 처리 안하면 오류남!!!!!!!

	  MemberVO vo = memberService.getMemberIdCheck(mid);
		if(vo != null && passwordEncoder.matches(pwd, vo.getPwd()) && vo.getUserDel().equals("NO")) {
			// 회원 인증처리된 경우 수행할 내용? session에 필요한 자료를 저장, 쿠키값처리, 그날 방문자수 1증가(방문포인트도 증가!)
			String strLevel = "";
			if(vo.getLevel() == 0) strLevel = "관리자";
			else if(vo.getLevel() == 1) strLevel = "운영자";
			else if(vo.getLevel() == 2) strLevel = "우수회원";
			else if(vo.getLevel() == 3) strLevel = "정회원";
			else if(vo.getLevel() == 4) strLevel = "준회원";
			
			session.setAttribute("sStrLevel", strLevel);
			session.setAttribute("sLevel", vo.getLevel());
			session.setAttribute("sMid", mid);
			session.setAttribute("sNickName", vo.getNickName());
			
			 if(idCheck.equals("on")) {
				 	Cookie cookieMid = new Cookie("cMid", mid);
					cookieMid.setMaxAge(60*60*24*7);  // 쿠키 만료시간을 7일..
					response.addCookie(cookieMid);
			 }
			 else {
				 Cookie[] cookies = request.getCookies();
				 for(int i=0; i<cookies.length; i++) {
					if(cookies[i].getName().equals("cMid")) {
						cookies[i].setMaxAge(60*60*24*7);  // 쿠키 만료시간을 7일..
						response.addCookie(cookies[i]);
					}
				 }
			 }
			 // 로그인한 사용자의 오늘 방문횟수(포인트) 누적...!!!(서비스에서 실행)!!
			 memberService.setMemberVisitProcess(vo);
			return "redirect:/msg/memberLoginOk?mid="+mid;
		}
		// 로그인 실패
		else {
			return "redirect:/msg/memberLoginNo";
		}
	}
	
	// 로그아웃
	@RequestMapping(value = "/memberLogout", method = RequestMethod.GET)
	public String adminLogoutGet(HttpSession session) {
		String mid = (String) session.getAttribute("sMid");
		session.invalidate();
		
		return "redirect:/msg/memberLogout?mid="+mid;
	}
	
	@RequestMapping(value = "/memberMain", method = RequestMethod.GET)
	public String memberMainGet(HttpSession session, Model model) {
		String mid = (String) session.getAttribute("sMid");
		
		MemberVO vo = memberService.getMemberIdCheck(mid);
		
		model.addAttribute("vo",vo);
		
		return "member/memberMain";
	}

	
	// 회원가입폼
	@RequestMapping(value = "/memberJoin", method = RequestMethod.GET)
	public String memberJoinGet() {
		return "member/memberJoin";
	}
	
	// 회원가입처리
	@RequestMapping(value = "/memberJoin", method = RequestMethod.POST)
	public String memberJoinPost(MultipartFile fName, MemberVO vo) {
		//System.out.println("memberVo : " + vo);
		// 아이디 체크
		if(memberService.getMemberIdCheck(vo.getMid()) != null) {
			return "redirect:/msg/memberIdCheckNo";
		}
		// 닉네임 중복 체크
		else if(memberService.getMemberNickNameCheck(vo.getNickName()) != null) {
			return "redirect:/msg/memberNickNameCheckNo";
		}
		
		// 비밀번호 암호화
		vo.setPwd(passwordEncoder.encode(vo.getPwd())); 
		
		// 체크가 완료되면 사진파일 업로드후, vo에 담긴 자료를 DB에 저장시켜준다. (회원 가입) - 서비스객체에서 수행처리했다.
		int res = memberService.setMemberJoinOk(fName,vo);
		if(res == 1) return "redirect:/msg/memberJoinOk"; // 정상처리가 되면 true == 1이 자동으로 넘어옴
		else return "redirect:/msg/memberJoinNo";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/memberIdCheck", method = RequestMethod.POST)
	public String memberIdCheckPost(String mid) {
		String res = "0";
		MemberVO vo = memberService.getMemberIdCheck(mid);
		
		if(vo != null) res = "1";
		
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/membernickNameCheck", method =  RequestMethod.POST)
	public String membernickNameCheckPost(String nickName) {
		String res = "0";
		MemberVO vo = memberService.getMemberNickNameCheck(nickName);
		
		if(vo != null) res = "1";
		
		return res;
	}
	
	//선택한 회원 세부정보 보기
	@RequestMapping(value = "/memInfor", method =  RequestMethod.GET)
	public String memInforGet(Model model, String mid, 
		@RequestParam(name = "pag" , defaultValue = "1", required = false) int pag) {
		MemberVO vo = memberService.getMemberInfo(mid);
		
		model.addAttribute("vo", vo);
		model.addAttribute("pag", pag);
		
		return "member/memberInfo";
	}
	
	
//	// 멤버 리스트 가져오기
//	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
//	public String memberListGet(Model model,
//			@RequestParam(name = "pag" , defaultValue = "1", required = false) int pag,
//			@RequestParam(name = "pageSize" , defaultValue = "3", required = false) int pageSize) {
//		
//		int totRecCnt = memberService.totRecCnt();  //3. 총 레코드 건수를 구한다.
//		
//		PageVO pageVO = pageProcess.getPageCnt(pag, pageSize, totRecCnt);
// 		
//		ArrayList<MemberVO> vos = memberService.getMemberList(pageVO.getStartIndexNo(), pageSize);
//		
//		model.addAttribute("vos", vos);
//		model.addAttribute("pageVO", pageVO);
//		
//		return "member/memberList";
//	}
//	// 검색한 멤버 리스트 가져오기
//	@RequestMapping(value = "/memberSearch", method = RequestMethod.POST)
//	public String memberSearchGet(Model model, String search, String searchString,
//			@RequestParam(name = "pag" , defaultValue = "1", required = false) int pag,
//			@RequestParam(name = "pageSize" , defaultValue = "3", required = false) int pageSize) {
//		
//		int totRecCnt = memberService.totSearchRecCnt(search,searchString);  //3. 총 레코드 건수를 구한다.
//		
//		PageVO pageVO = pageProcess.getPageCnt(pag, pageSize, totRecCnt);
//		
//		ArrayList<MemberVO> vos = memberService.getMemberSearch(pageVO.getStartIndexNo(), pageSize, search, searchString);
//		
//		model.addAttribute("vos", vos);
//		model.addAttribute("vosSize", vos.size());
//		model.addAttribute("search", search);
//		model.addAttribute("searchString", searchString);
//		model.addAttribute("pageVO", pageVO);
//		
//		return "member/memberSearch";
//	}
	/*
	// 전체 리스트와 검색 리스트를 하나의 메소드로 처리
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public String memberListGet(Model model,
			@RequestParam(name = "search" , defaultValue = "", required = false) String search,
			@RequestParam(name = "searchString" , defaultValue = "", required = false) String searchString,
			@RequestParam(name = "pag" , defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize" , defaultValue = "1", required = false) int pageSize) {
		
		int totRecCnt = memberService.totTermRecCnt(search,searchString);  //3. 총 레코드 건수를 구한다.
		
		PageVO pageVO = pageProcess.getPageCnt(pag, pageSize, totRecCnt);
		
		ArrayList<MemberVO> vos = memberService.getTermMemberList(pageVO.getStartIndexNo(), pageSize,search,searchString);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("search", search);
		model.addAttribute("searchString", searchString);
		model.addAttribute("totRecCnt", totRecCnt);
		
		return "member/memberList";
	}
	*/
	
	// Pagination 이용하기 !!!!!!!!
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public String memberListGet(Model model,
			@RequestParam(name = "search" , defaultValue = "", required = false) String search,
			@RequestParam(name = "searchString" , defaultValue = "", required = false) String searchString,
			@RequestParam(name = "pag" , defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize" , defaultValue = "3", required = false) int pageSize) {
		
		PageVO pageVo = pageProcess.totRecCnt(pag, pageSize, "member", search ,searchString);  //3. 총 레코드 건수를 구한다.

//		PageVO pageVO = pageProcess.getPageCnt(pag, pageSize, totRecCnt);
		
		ArrayList<MemberVO> vos = memberService.getTermMemberList(pageVo.getStartIndexNo(), pageSize,search,searchString);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("search", search);
		model.addAttribute("searchString", searchString);
		
		return "member/memberList";
	}
	
	
	// 아이디 찾기 폼 이동
	@RequestMapping(value = "/memberIdSearch", method = RequestMethod.GET)
	public String memberIdSearchGet() {
		return "member/memberIdSearch";
	}
	
	// 아이디 찾기 폼 이동
	@RequestMapping(value = "/memberIdSearch", method = RequestMethod.POST)
	public String memberIdSearchPost(Model model, String nickName, String name) {
		MemberVO vo = memberService.getFindMid(name,nickName);
		String mid = vo.getMid();
		mid = mid.replace(mid.substring(2,4), "**");
		model.addAttribute("res",mid);
		
		return "member/memFindResult";
	}
	
	// 회원 탈퇴 폼 이동
	@RequestMapping(value = "/memberDelete", method = RequestMethod.GET)
	public String memberDeleteGet() {
		return "member/memberDelete";
	}
	
	//회원 탈퇴
	@RequestMapping(value = "/memberDelete", method = RequestMethod.POST)
	public String memberDeletePost(HttpSession session, Model model, String pwd) {
		String mid = (String) session.getAttribute("sMid");
		MemberVO vo = memberService.getMemberIdCheck(mid);
		
    if(!passwordEncoder.matches(pwd, vo.getPwd())) {
      return "redirect:/msg/memberDelNo";
    }
    else {
    	memberService.setMemberDelete(mid);
    	return "redirect:/msg/memberDelOk";
    }		
	}
	
	// 비밀번호 찾기 발급 폼
	@RequestMapping(value = "/memberPwdSearch", method = RequestMethod.GET)
	public String memberPwdSearchGet() {
		return "member/memberPwdSearch";
	}
	
	// 비밀번호 찾기를 위한 임시비밀번호 처리(임시비밀번호를 생성시켜 메일로 보내기)
	@RequestMapping(value = "/memberPwdSearch", method = RequestMethod.POST)
	public String memberPwdSearchPost(String mid, String toMail) {
		MemberVO vo = memberService.getMemberIdCheck(mid);
		if(vo.getEmail().equals(toMail)) {
			// 회원정보가 맞다면 임시비밀번호를 발급받는다.
			UUID uid = UUID.randomUUID();
			String pwd = uid.toString().substring(0,8);
			
			// 발급받은 임시비밀번호를 암호화 처리시켜서 DB에 저장한다.
			memberService.setMemberPwdUpdate(mid,passwordEncoder.encode(pwd));
			
			// 임시비밀번호를 메일로 전송처리한다.

			String res = mailSend(toMail, pwd);
			
			if(res.equals("1")) return "redirect:/msg/memberImsiPwdOk";
			else return "redirect:/msg/memberImsiPwdNo";
		}
		
		else {
			return "redirect:/msg/memberImsiPwdNo";
		}
	}
	
	public String mailSend(String toMail, String pwd) {
		try {
			String title = "임시 비밀번호가 발급되었습니다";
			String content = "";
			
			// 메일을 전송하기 위한 객체 : MimeMessage() , MimeMessageHelper()
			MimeMessage message = mailSender.createMimeMessage(); // 타입변환
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8"); //보관함에 저장하는곳
			
			// 메일보관함에 회원이 보내온 메세지들을 모두 저장시킨다.
			messageHelper.setTo(toMail);
			messageHelper.setSubject(title);
			messageHelper.setText(content);
			
			// 메세지 보관함의 내용(content)에 필요한 정보를 추가로 담아서 전송시킬수 있도록 한다.
			content += "<br><hr><h3>연습으로 보냄</h3>";
			content += "<br><hr><h3>아래 주소로 로그인하셔서 비밀번호를 변경하시기 바랍니다</h3>";
			content += "<br><hr><h3>신규 비밀번호는 <font color='red'>"+pwd+"</font></h3> 입니다.";
			content += "<p><img src=\"cid:main.jpg\"  width='500px'></p>";
			content += "<hr>";
			messageHelper.setText(content, true);
			
			// 본문에 기재된 그림파일의 경로를 따로 표시시켜 준다. 그리고, 보관함에 다시 저장시켜준다.
			FileSystemResource file = new FileSystemResource("D:\\JavaWorkspace\\springframework\\works\\javawspring\\src\\main\\webapp\\resources\\images\\main.jpg");
			messageHelper.addInline("main.jpg", file); //일반그림은 inline	
			
			// 메일 전송하기
			mailSender.send(message);
			
			
			return "1";
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return "0";
	}
	
	
	// 비밀번호 변경 폼
	@RequestMapping(value = "/memUpdatePwd", method = RequestMethod.GET)
	public String memUpdatePwdGet(HttpSession session, Model model) {
		String mid = (String) session.getAttribute("sMid");
		MemberVO vo = memberService.getMemberIdCheck(mid);
		
		model.addAttribute("vo",vo);
		
		return "member/memUpdatePwd";
	}
	
	// 비밀번호 변경 하기 
	@RequestMapping(value = "/memUpdatePwd", method = RequestMethod.POST)
	public String memUpdatePwdPost(HttpSession session, String oldPwd, String newPwd, String rePwd) {
		String mid = (String) session.getAttribute("sMid");
		MemberVO vo = memberService.getMemberIdCheck(mid);
		
		if(passwordEncoder.matches(oldPwd, vo.getPwd())) {
			memberService.setMemberPwdUpdate(mid, passwordEncoder.encode(newPwd));
			return "redirect:/msg/memberUpdatePwdOk";
		}
		else {
			return "redirect:/msg/memberUpdatePwdNo";
			
		}
	}
	
	// 회원정보 가기전 비밀번호 확인 폼
	@RequestMapping(value = "/memberPwdCheck", method = RequestMethod.GET)
	public String memberPwdCheckGet() {
		return "member/memberPwdCheck";
	}
	
	// 비밀번호 확인후 회원정보 변경 창
	@RequestMapping(value = "/memberPwdCheck", method = RequestMethod.POST)
	public String memberPwdCheckPost(String mid,String pwd) {
		MemberVO vo = memberService.getMemberIdCheck(mid);
		
		if(passwordEncoder.matches(pwd, vo.getPwd())) {
			return "redirect:/msg/memberPwdCheckOk";
		}
		else {
			return "redirect:/msg/memberPwdCheckNo";
		}
	}
	
	// 회원정보 변경 창
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.GET)
	public String memberUpdateGet(Model model,HttpSession session) {
		String mid = (String)session.getAttribute("sMid");
		MemberVO vo = memberService.getMemberIdCheck(mid);
		
		String[] email = vo.getEmail().split("@");
		model.addAttribute("email1", email[0]);
		model.addAttribute("email2", email[1]);
		
		// 전화번호 분리(-)
		String[] tel = vo.getTel().split("-");
		if(tel[1].equals(" ")) tel[1] = "";
		if(tel[2].equals(" ")) tel[2] = "";
		model.addAttribute("tel1", tel[0]);
		model.addAttribute("tel2", tel[1]);
		model.addAttribute("tel3", tel[2]);
		
		// 주소분리("/")
		String[] address = vo.getAddress().split("/");
		if(address[0].equals(" ")) address[0] = "";
		if(address[1].equals(" ")) address[1] = "";
		if(address[2].equals(" ")) address[2] = "";
		if(address[3].equals(" ")) address[3] = "";
		model.addAttribute("postcode", address[0]);
		model.addAttribute("roadAddress", address[1]);
		model.addAttribute("detailAddress", address[2]);
		model.addAttribute("extraAddress", address[3]);
		model.addAttribute("hobby", vo.getHobby());
		model.addAttribute("birthday", vo.getBirthday().subSequence(0, 10));
		
		
		model.addAttribute("vo",vo);
		return "member/memberUpdate";
	}
	
	// 회원정보 변경 후 저장 처리
	@RequestMapping(value = "/memberUpdate", method = RequestMethod.POST)
	public String memberUpdatePost(MultipartFile fName, MemberVO vo, String pastPhoto, HttpSession session) {
		String mid = (String)session.getAttribute("sMid");
		vo.setMid(mid);
		// 체크가 완료되면 사진파일 업로드후, vo에 담긴 자료를 DB에 저장시켜준다. (회원 가입) - 서비스객체에서 수행처리했다.
		int res = memberService.setMemberUpdateOk(fName,vo,pastPhoto);
		if(res == 1) return "redirect:/msg/memberUpdateOk"; // 정상처리가 되면 true == 1이 자동으로 넘어옴
		else return "redirect:/msg/memberUpdateNo";
	}
	
	
}
