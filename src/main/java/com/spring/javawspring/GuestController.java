package com.spring.javawspring;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.javawspring.pagenation.PageProcess;
import com.spring.javawspring.pagenation.PageVO;
import com.spring.javawspring.service.GuestService;
import com.spring.javawspring.vo.GuestVO;

@Controller
@RequestMapping("/guest")
public class GuestController {
	
	@Autowired
	GuestService guestService;
	
	@Autowired 
	PageProcess pageProcess;
	
	/*
	@RequestMapping(value = "/guestList", method = RequestMethod.GET)
	public String guestListGet(Model model,
			@RequestParam(name="pag", defaultValue = "1", required = false) int pag, 
			@RequestParam(name="pageSize", defaultValue = "5", required = false) int pageSize) {	
	  // 1. 페이지(pag)를 결정한다
		int totRecCnt = guestService.totRecCnt();  //3. 총 레코드 건수를 구한다.
		int totPage = (totRecCnt % pageSize) ==0 ? totRecCnt / pageSize : (totRecCnt / pageSize) +1;	//4. 총 페이지 건수를 구한다.
		int startIndexNo = (pag-1) * pageSize; //5. 현재페이지의 시작 인덱스번호를 구한다.
		int curScrStartNo = totRecCnt - startIndexNo;		//6. 현재 화면에 보여주는 시작번호를 구한다.
		
		
		int blockSize = 3;  // 1. 블록의 크기를 결정한다.(여기선 3으로 지정)		
		int curBlock = (pag - 1) / blockSize;
		int lastBlock = (totPage - 1) / blockSize;	// 3. 마지막블록을 구한다.
		
//		ArrayList<GuestVO> vos = dao.getGuestList();
		ArrayList<GuestVO> vos = guestService.getGuestList(startIndexNo, pageSize);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pag", pag);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("totPage", totPage);
		model.addAttribute("curScrStartNo", curScrStartNo);
		model.addAttribute("blockSize", blockSize);
		model.addAttribute("curBlock", curBlock);
		model.addAttribute("lastBlock", lastBlock);
	
		return "guest/guestList";
	}
		*/
	// 페이지 네이션 이용하기 (리스트 검색 둘다 이쪽으로)
	@RequestMapping(value = "/guestList", method = RequestMethod.GET)
	public String guestListGet(Model model,
			@RequestParam(name = "search" , defaultValue = "", required = false) String search,
			@RequestParam(name = "searchString" , defaultValue = "", required = false) String searchString,
			@RequestParam(name="pag", defaultValue = "1", required = false) int pag, 
			@RequestParam(name="pageSize", defaultValue = "5", required = false) int pageSize) {	
		PageVO pageVo = pageProcess.totRecCnt(pag, pageSize, "guest",search,searchString);  //3. 총 레코드 건수를 구한다.
		
		System.out.println("pageSize :" + pageSize);
		System.out.println("검색어" + searchString);
		System.out.println("총 개수"+ pageVo.getTotRecCnt());

		ArrayList<GuestVO> vos = guestService.getTermGuestList(pageVo.getStartIndexNo(), pageVo.getPageSize(),search,searchString);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("search", search);
		model.addAttribute("searchString", searchString);
	
		return "guest/guestList";
	}
	
	
	/*
	@RequestMapping(value = "/guestSearch", method = RequestMethod.POST)
	public String guestListPost(Model model, String search, String searchString,
			@RequestParam(name="pag", defaultValue = "1", required = false) int pag) {
	// 1. 페이지(pag)를 결정한다.
			int pageSize = 3;  //2. 한 페이지의 분량을 결정한다.
			int totRecCnt = guestService.totSearchCnt(search,searchString);  //3. 총 레코드 건수를 구한다.
			int totPage = (totRecCnt % pageSize) ==0 ? totRecCnt / pageSize : (totRecCnt / pageSize) +1;	//4. 총 페이지 건수를 구한다.
			int startIndexNo = (pag-1) * pageSize; //5. 현재페이지의 시작 인덱스번호를 구한다.
			int curScrStartNo = totRecCnt - startIndexNo;		//6. 현재 화면에 보여주는 시작번호를 구한다.
			
			
			int blockSize = 3;  // 1. 블록의 크기를 결정한다.(여기선 3으로 지정)		
			int curBlock = (pag - 1) / blockSize;
			int lastBlock = (totPage - 1) / blockSize;	// 3. 마지막블록을 구한다.
			
			ArrayList<GuestVO> vos = guestService.setguestSearch(startIndexNo, pageSize,search,searchString);
			model.addAttribute("vos", vos);
			model.addAttribute("pag", pag);
			model.addAttribute("totPage", totPage);
			model.addAttribute("curScrStartNo", curScrStartNo);
			model.addAttribute("blockSize", blockSize);
			model.addAttribute("curBlock", curBlock);
			model.addAttribute("lastBlock", lastBlock);
		return "guest/guestList";
	}
*/
	@RequestMapping(value = "/guestInput", method = RequestMethod.GET)
	public String guestInputGet() {
		return "guest/guestInput";
	}
	
	@RequestMapping(value = "/guestInput", method = RequestMethod.POST)
	public String guestInputPost(GuestVO vo) {
		guestService.setGuestInput(vo);
		return "redirect:/msg/guestInputOk";
	}
	
	@RequestMapping(value = "/guestDelete", method = RequestMethod.GET)
	public String guestDeleteGet(int idx) {
		guestService.guestDelete(idx);
		return "redirect:/msg/guestDeleteOk";
	}
	@RequestMapping(value = "/guestUpdate", method = RequestMethod.GET)
	public String guestUpdateGet(Model model ,int idx) {
		GuestVO vo = guestService.guestUpdate(idx);
		model.addAttribute("vo",vo);
		return "guest/guestUpdate";
	}
	@RequestMapping(value = "/guestUpdate", method = RequestMethod.POST)
	public String guestUpdatePost(GuestVO vo) {
		guestService.guestUpdateOk(vo);
		return "redirect:/msg/guestUpdateOk";
	}
}
