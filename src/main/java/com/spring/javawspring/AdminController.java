package com.spring.javawspring;

import java.util.ArrayList;

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
import com.spring.javawspring.service.MemberService;
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
	
	// 어드민 메인
	@RequestMapping(value = "/adminMain", method = RequestMethod.GET)
	public String adminMainGet() {
		
		return "admin/adminMain";
	}
	
	// 어드민 메인
	@RequestMapping(value = "/adminLeft", method = RequestMethod.GET)
	public String adminLeftGet() {
		
		return "admin/adminLeft";
	}
	
	// 어드민 메인
	@RequestMapping(value = "/adminContent", method = RequestMethod.GET)
	public String adminContentGet() {
		
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
	
}
