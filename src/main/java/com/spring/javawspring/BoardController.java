package com.spring.javawspring;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.javawspring.pagenation.PageProcess;
import com.spring.javawspring.pagenation.PageVO;
import com.spring.javawspring.service.BoardService;
import com.spring.javawspring.service.MemberService;
import com.spring.javawspring.vo.BoardVO;
import com.spring.javawspring.vo.MemberVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired	
	PageProcess pageProcess;
	
	@Autowired
	MemberService memberService;
	
	// 어드민 메인
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
	public String boardListGet(Model model,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pagSize", defaultValue = "5", required = false) int pageSize,
			@RequestParam(name = "search" , defaultValue = "", required = false) String search,
			@RequestParam(name = "searchString" , defaultValue = "", required = false) String searchString) {
		
		PageVO pageVo = pageProcess.totRecCnt(pag, pageSize, "board", search, searchString);
		
		List<BoardVO> vos = boardService.getBoardList(pageVo.getStartIndexNo(), pageSize,search,searchString);
		
		model.addAttribute("vos",vos);
		model.addAttribute("pageVo",pageVo);
		
		return "board/boardList";
	}
	// 글쓰기 폼
	@RequestMapping(value = "/boardInput", method = RequestMethod.GET)
	public String boardInputGet(Model model,int pag, int pageSize, HttpSession session) {
		String mid = (String) session.getAttribute("sMid");
		
		MemberVO vo = memberService.getMemberIdCheck(mid);
		
		model.addAttribute("email",vo.getEmail());
		model.addAttribute("homePage",vo.getHomePage());
		model.addAttribute("pag",pag);
		model.addAttribute("pageSize",pageSize);
		
		return "board/boardInput";
	}
	// 글쓰기 처리
	@RequestMapping(value = "/boardInput", method = RequestMethod.POST)
	public String boardInputPost(BoardVO vo) {
		int res = boardService.setBoardInput(vo); // 성공하면 1넘어옴
		
		if(res == 1) return "redirect:/msg/boardInputOk";
		else return "redirect:/msg/boardInputNo";
		
	}
	
	// 글 확인하러 가기
	@RequestMapping(value = "/boardContent", method = RequestMethod.GET)
	public String boardContentPost(Model model, int idx, int pag, int pageSize) {
		BoardVO vo = boardService.getBoardContent(idx); // 성공하면 1넘어옴
		
		boardService.setBoardReadNum(idx);
		
		model.addAttribute("vo", vo);
		model.addAttribute("pag", pag);
		model.addAttribute("pageSize", pageSize);
		
		return "board/boardContent";
		
	}
	
}
