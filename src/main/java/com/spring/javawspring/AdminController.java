package com.spring.javawspring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.javawspring.pagenation.PageProcess;
import com.spring.javawspring.pagenation.PageVO;
import com.spring.javawspring.service.AdminService;
import com.spring.javawspring.service.BoardService;
import com.spring.javawspring.service.MemberService;
import com.spring.javawspring.vo.BoardVO;
import com.spring.javawspring.vo.MemberVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired	
	PageProcess pageProcess;
	
	@Autowired
	JavaMailSender mailSender;

	@Autowired
	MemberService memberService;
	
	@Autowired
	BoardService boardService;
	
	// 어드민 메인
	@RequestMapping(value = "/adminMain", method = RequestMethod.GET)
	public String adminMainGet(Model model) {
		return "admin/adminMain";
	}
	
	// 어드민 메인
	@RequestMapping(value = "/adminLeft", method = RequestMethod.GET)
	public String adminLeftGet() {
		
		return "admin/adminLeft";
	}
	
	// 어드민 메인
	@RequestMapping(value = "/adminContent", method = RequestMethod.GET)
	public String adminContentGet(Model model) {
		int newBoardCnt = boardService.getBoardNewCount();
		int newMemberCnt = boardService.getMemberCount();
		
		// 최신 게시판(24시간내)
		model.addAttribute("newBoardCnt",newBoardCnt);
		// 신규회원(24시간내)
		model.addAttribute("newMemberCnt",newMemberCnt);
		return "admin/adminContent";
	}
	
	// 어드민 멤버 리스트
	@RequestMapping(value = "/member/adminMemberList", method = RequestMethod.GET)
	public String adminMemListGet(Model model,
		@RequestParam(name = "search" , defaultValue = "", required = false) String search,
		@RequestParam(name = "searchString" , defaultValue = "", required = false) String searchString,
		@RequestParam(name = "pag" , defaultValue = "1", required = false) int pag,
		@RequestParam(name = "pageSize" , defaultValue = "5", required = false) int pageSize) {
	
		PageVO pageVo = pageProcess.totRecCnt(pag, pageSize, "member", search ,searchString);  //3. 총 레코드 건수를 구한다.
	
		ArrayList<MemberVO> vos = memberService.getTermMemberList(pageVo.getStartIndexNo(), pageSize,search,searchString);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("search", search);
		model.addAttribute("searchString", searchString);
		return "admin/member/adminMemberList";
	}
	
	// 회원 등급 변경하기
	@ResponseBody
	@RequestMapping(value = "member/adMemberLevel", method = RequestMethod.POST)
	public String adminMemberLevelPost(int idx, int level) {
		int res = adminService.setMemberLevelCheck(idx, level);
		
		return res+"";
	}
	// 회원 탈퇴하기
	@ResponseBody
	@RequestMapping(value = "member/adminMemberDel", method = RequestMethod.POST)
	public String adminMemberDelPost(int idx) {
		int res = adminService.setMemberDelete(idx);
		
		return res+"";
	}
	
//어드민 메인
	@RequestMapping(value = "/adminboardList", method = RequestMethod.GET)
	public String boardListGet(Model model,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pagSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(name = "search" , defaultValue = "", required = false) String search,
			@RequestParam(name = "searchString" , defaultValue = "", required = false) String searchString) {
		
		PageVO pageVo = pageProcess.totRecCnt(pag, pageSize, "board", search, searchString);
		
		List<BoardVO> vos = boardService.getBoardList(pageVo.getStartIndexNo(), pageSize,search,searchString);
		
		model.addAttribute("vos",vos);
		model.addAttribute("pageVo",pageVo);
		
		return "admin/board/adminBoardList";
	}
	
	

	// ckeditor 폴거의 파일 리스트 보여주기
	@RequestMapping(value = "/file/fileList", method = RequestMethod.GET)
	public String fileListGet(HttpServletRequest request, Model model) {
		String realPath = request.getRealPath("/resources/data/ckeditor/");
		
		String[] files = new File(realPath).list(); // 해당폴더에 들어가있는 파일의 리스트들을 담아준다
		
		model.addAttribute("files", files);
		
		return "admin/file/fileList";
	}
	// ckeditor 폴거의 파일 리스트 보여주기
	
	
	// 껍데기 사진 삭제하기
	@ResponseBody
	@RequestMapping(value = "/photoViewDeleteAll", method = RequestMethod.POST)
	public String photoViewDeleteAllPost(HttpServletRequest request, Model model, String delItems) {
		String realPath = request.getRealPath("/resources/data/ckeditor/");
		int res = 0;
		String[] delfile = delItems.split("/");
		
		for(int i=0; i<delfile.length; i++) {
//			System.out.println("파일 이름 :" + delfile[i]);
			String origFilePath = realPath + delfile[i];
			File deleteFile = new File(origFilePath);
			
			if(deleteFile.exists()) deleteFile.delete();
			res = 1;
		}
		return res+"";
	}
	// 선택한 게시글 삭제하기
	@ResponseBody
	@RequestMapping(value = "/boardDeleteAll", method = RequestMethod.POST)
	public String boardDeleteAllPost(HttpServletRequest request, Model model, String delItems) {
		int res = 0;
		
		res = adminService.setAdminBoardDelete(delItems);
		
		return res+"";
	}
	
}
