package com.spring.javawspring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {
	
	@RequestMapping(value="/msg/{msgFlag}", method=RequestMethod.GET)
	public String msgGet(@PathVariable String msgFlag, Model model,
			@RequestParam(value="mid", defaultValue = "", required = false) String mid,
			@RequestParam(value="flag", defaultValue = "", required = false) String flag) {
		System.out.println("mid : "+mid);
		
		if(msgFlag.equals("memberLoginOk")) {
			model.addAttribute("msg", mid + "님 로그인 되었습니다.");
			model.addAttribute("url", "member/memberMain");
		}
		if(msgFlag.equals("memberLogout")) {
			model.addAttribute("msg", mid + "님 로그아웃 되었습니다.");
			model.addAttribute("url", "member/memberLogin");
		}
		else if(msgFlag.equals("memberLoginNo")) {
			model.addAttribute("msg", "로그인 실패~~~");
			model.addAttribute("url", "member/memberLogin");
		}
		else if(msgFlag.equals("guestInputOk")) {
			model.addAttribute("msg", "방명록이 입력되었습니다!");
			model.addAttribute("url", "guest/guestList");
		}
		else if(msgFlag.equals("guestDeleteOk")) {
			model.addAttribute("msg", "방명록이 삭제되었습니다!");
			model.addAttribute("url", "guest/guestList");
		}
		else if(msgFlag.equals("guestUpdateOk")) {
			model.addAttribute("msg", "방명록이 수정되었습니다!");
			model.addAttribute("url", "guest/guestList");
		}
		else if(msgFlag.equals("memberJoinOk")) {
			model.addAttribute("msg", "회원가입 완료되었습니다!");
			model.addAttribute("url", "member/memberLogin");
		}
		else if(msgFlag.equals("memberJoinNo")) {
			model.addAttribute("msg", "회원가입 실패!!!");
			model.addAttribute("url", "member/memberJoin");
		}
		else if(msgFlag.equals("memberIdCheckNo")) {
			model.addAttribute("msg", "중복된 아이디 입니다!");
			model.addAttribute("url", "member/memberJoin");
		}
		else if(msgFlag.equals("memberNickNameCheckNo")) {
			model.addAttribute("msg", "중복된 닉네임입니다!");
			model.addAttribute("url", "member/memberJoin");
		}
		else if(msgFlag.equals("adminNo")) {
			model.addAttribute("msg", "관리자가 아닙니다!");
			model.addAttribute("url", "member/memberLogin");
		}
		else if(msgFlag.equals("memberNo")) {
			model.addAttribute("msg", "로그인후 사용하세요");
			model.addAttribute("url", "member/memberLogin");
		}
		else if(msgFlag.equals("levelCheckNo")) {
			model.addAttribute("msg", "등급을 확인하세요");
			model.addAttribute("url", "member/memberMain");
		}
		else if(msgFlag.equals("mailSendOk")) {
			model.addAttribute("msg", "메일 전송이 완료되었습니다!");
			model.addAttribute("url", "study/mail/mailForm");
		}
		else if(msgFlag.equals("mailSendOk")) {
			model.addAttribute("msg", "메일 전송이 완료되었습니다!");
			model.addAttribute("url", "study/mail/mailForm");
		}
		else if(msgFlag.equals("memberDelNo")) {
			model.addAttribute("msg", "비밀번호가 틀립니다!");
			model.addAttribute("url", "member/memberDelete");
		}
		else if(msgFlag.equals("memberDelOk")) {
			model.addAttribute("msg", "회원탈퇴 처리 되었습니다!");
			model.addAttribute("url", "member/memberLogout");
		}
		else if(msgFlag.equals("memberImsiPwdOk")) {
			model.addAttribute("msg", "임시비밀번호가 전송되었습니다!");
			model.addAttribute("url", "member/memberLogin");
		}
		else if(msgFlag.equals("memberImsiPwdNo")) {
			model.addAttribute("msg", "아이디와 이메일 주소를 확인해주세요!");
			model.addAttribute("url", "member/memberPwdSearch");
		}
		else if(msgFlag.equals("fileUploadOk")) {
			model.addAttribute("msg", "파일이 업로드 되었습니다!!");
			model.addAttribute("url", "study/fileUpload/fileUploadForm");
		}
		else if(msgFlag.equals("fileUploadNo")) {
			model.addAttribute("msg", "파일 업로드 실패!!");
			model.addAttribute("url", "study/fileUpload/fileUploadForm");
		}
		else if(msgFlag.equals("memberUpdatePwdOk")) {
			model.addAttribute("msg", "비밀번호 변경이 성공하였습니다");
			model.addAttribute("url", "member/memberMain");
		}
		else if(msgFlag.equals("memberUpdatePwdNo")) {
			model.addAttribute("msg", "비밀번호 변경 실패!!");
			model.addAttribute("url", "member/memUpdatePwd");
		}
		else if(msgFlag.equals("memberPwdCheckOk")) {
			model.addAttribute("msg", "비밀번호가 확인되었습니다!");
			model.addAttribute("url", "member/memberUpdate");
		}
		else if(msgFlag.equals("memberPwdCheckNo")) {
			model.addAttribute("msg", "비밀번호가 틀립니다!");
			model.addAttribute("url", "member/memberPwdCheck");
		}
		else if(msgFlag.equals("memberUpdateNickNameCheckNo")) {
			model.addAttribute("msg", "이미 존재하는 닉네임입니다!!");
			model.addAttribute("url", "member/memberUpdate");
		}
		else if(msgFlag.equals("memberUpdateOk")) {
			model.addAttribute("msg", "회원수정이 완료되었습니다!");
			model.addAttribute("url", "member/memberMain");
		}
		else if(msgFlag.equals("memberUpdateNo")) {
			model.addAttribute("msg", "회원수정 실패!!");
			model.addAttribute("url", "member/memberUpdate");
		}
		else if(msgFlag.equals("boardInputOk")) {
			model.addAttribute("msg", "게시글이 등록되었습니다!");
			model.addAttribute("url", "board/boardList");
		}
		else if(msgFlag.equals("boardInputNo")) {
			model.addAttribute("msg", "게시글이 등록실패!");
			model.addAttribute("url", "board/boardList");
		}
		else if(msgFlag.equals("boardDeleteOk")) {
			model.addAttribute("msg", "게시글이 삭제되었습니다!!");
			model.addAttribute("url", "board/boardList"+flag);
		}
		else if(msgFlag.equals("boardUpdateOk")) {
			model.addAttribute("msg", "게시글이 수정되었습니다!!");
			model.addAttribute("url", "board/boardList"+flag);
		}
		else if(msgFlag.equals("pdsInputOk")) {
			model.addAttribute("msg", "자료실에 파일이 등록되었습니다!!");
			model.addAttribute("url", "pds/pdsList");
		}
		else if(msgFlag.equals("wmMemberIdNo")) {
			model.addAttribute("msg", "가입된 회원이 아닙니다!!");
			model.addAttribute("url", "webMessage/webMessage?mSw=0");
		}
		else if(msgFlag.equals("wmInputOk")) {
			model.addAttribute("msg", "메세지 전송 완료!!");
			model.addAttribute("url", "webMessage/webMessage?mSw=1");
		}
		else if(msgFlag.equals("wmDeleteAll")) {
			model.addAttribute("msg", "휴지통 비우기 성공!");
			model.addAttribute("url", "webMessage/webMessage?mSw=1");
		}

		
		return "include/message";
	}
}
