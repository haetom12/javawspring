package com.spring.javawspring;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.javawspring.pagenation.PageProcess;
import com.spring.javawspring.pagenation.PageVO;
import com.spring.javawspring.service.BoardService;
import com.spring.javawspring.service.MemberService;
import com.spring.javawspring.vo.BoardReplyVO;
import com.spring.javawspring.vo.BoardVO;
import com.spring.javawspring.vo.GoodVO;
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
		// content에 이미지가 저장되어 있다면, 저장된 이미지만 골라서 /resources/data/board/폴더에 저장시켜준다.
		boardService.imgCheck(vo.getContent());
		
		// 이미지 복사작업이 끝나면, board폴더에 실제로 저장된 파일명을 DB에 저장시켜준다. [/resources/data/board/ == >>/resources/data/board/]
		vo.setContent(vo.getContent().replace("/data/ckeditor/","/data/ckeditor/board/"));
		
		int res = boardService.setBoardInput(vo); // 성공하면 1넘어옴
		
		if(res == 1) return "redirect:/msg/boardInputOk";
		else return "redirect:/msg/boardInputNo";
		
	}
	
	// 글 조회수 1회 증가시키기.(조회수 중복방지처리 - 세션 사용 : 'board+고유번호'를 객체배열에 추가시킨다.)
	@RequestMapping(value = "/boardContent", method = RequestMethod.GET)
	public String boardContentPost(Model model, int idx, int pag, int pageSize, HttpServletRequest request) {
		BoardVO vo = boardService.getBoardContent(idx); // 성공하면 1넘어옴
		
		// 글 조회수 1회 증가시키기
		HttpSession session = request.getSession();
		ArrayList<String> contentIdx = (ArrayList) session.getAttribute("sContentIdx");
		if(contentIdx == null) {
		  contentIdx = new ArrayList<String>();
		}
		String imsiContentIdx = "board" + idx;
		if(!contentIdx.contains(imsiContentIdx)) {
			boardService.setBoardReadNum(idx);
			contentIdx.add(imsiContentIdx);
		}
		session.setAttribute("sContentIdx", contentIdx);
		
		// 해당글에 좋아요 버튼을 클릭하였었다면 '좋아요세션'에 아이디를 저장시켜두었기에 찾아서 있다면 sSw값을 1로 보내어 하트색을 빨강색으로 변경유지하게한다.
			ArrayList<String> goodIdx = (ArrayList) session.getAttribute("sGoodIdx");
			if(goodIdx == null) {
				goodIdx = new ArrayList<String>();
			}
			String imsiGoodIdx = "boardGood" + idx;
			if(goodIdx.contains(imsiGoodIdx)) {
				session.setAttribute("sSw", "1");		// 로그인 사용자가 이미 좋아요를 클릭한 게시글이라면 빨강색으로 표시가히위해 sSW에 1을 전송하고 있다.
			}
			else {
				session.setAttribute("sSw", "0");
			}
		
		model.addAttribute("vo", vo);
		model.addAttribute("pag", pag);
		model.addAttribute("pageSize", pageSize);
		
		//-------------------------------------------------------------------------------------
		
		// DB에서 현재 게시글에 '좋아요'가 체크되어있는지를 알아오자.
		String mid = (String) session.getAttribute("sMid");
		GoodVO goodVo = boardService.getBoardGoodCheck(idx,"board",mid);
		
		model.addAttribute("goodVo",goodVo);
		model.addAttribute("mid",mid);
		
		
		// 이전글 / 다음글 가져오기
		ArrayList<BoardVO> pnVos = boardService.getPreNext(idx);
		
		model.addAttribute("pnVos",pnVos);
		
		// 댓글 가져오기(replyVos)
		List<BoardReplyVO> replyVos = boardService.getBoardReply(idx);
		
		model.addAttribute("replyVos",replyVos);
		return "board/boardContent";
	}
	
	// 종아요 처리
	@ResponseBody
	@RequestMapping(value = "/boardGoodCheck", method = RequestMethod.POST)
	public String boardGoodCheckPost(HttpSession session, int idx, String mid) {
		//System.out.println("sw : " + goodSw);
		GoodVO goodVo = boardService.getBoardGoodCheck(idx,"board",mid);
		String res ="";

		// 해당글이 DB에 없으면 생성
		if(goodVo == null) {
			boardService.getGoodInsert(idx,"board",mid);
		}
	// 해당글이 DB에 있으면 삭제
		else {
			boardService.setGoodDelete(idx,"board",mid);
		}
		return res;
	}

	
	// 좋아요 처리
	@ResponseBody
	@RequestMapping(value = "/boardGood", method = RequestMethod.POST)
	public String boardGoodPost(Model model, int idx, int pag, int pageSize, HttpServletRequest request) {
		
		// 좋아요수 증가처리하기
		String sw = "0"; // 처음 0으로 셋팅하고, 처음 좋아요 버튼을 누르면 '1'을 돌려준다., 이미 '좋아요'를 한번 눌렀으면 '0'으로 sw값을 보내준다.
		HttpSession session = request.getSession();
		ArrayList<String> goodIdx = (ArrayList) session.getAttribute("sGoodIdx");
		if(goodIdx == null) {
			goodIdx = new ArrayList<String>();
		}
		String imsiGoodIdx = "boardGood" + idx;
		if(!goodIdx.contains(imsiGoodIdx)) {
			boardService.setBoGood(idx);
			goodIdx.add(imsiGoodIdx);
			sw = "1";	// 처음으로 좋아요 버튼을 클릭하였기에 '1'을 반환
		}
		session.setAttribute("sGoodIdx", goodIdx);
		session.setAttribute("idx", idx);
		session.setAttribute("pag", pag);
		session.setAttribute("pageSize", pageSize);
		
		return sw;
	}
	
	// 게시글 삭제하기...
	@RequestMapping(value = "/boardDeleteOk", method = RequestMethod.GET)
	public String boardDeleteOkGet(int idx,int pag, int pageSize, Model model) {
		// 게시글에 사진이 존재한다면 서버에 있는 사진파일을 먼저 삭제한다.
		BoardVO vo = boardService.getBoardContent(idx);
		if(vo.getContent().indexOf("src=\"/") != -1) boardService.imgDelete(vo.getContent()); 

		// DB에서 실제로 존재하는 게시글을 삭제처리한다.
		boardService.setBoardDeleteOk(idx);
		
		model.addAttribute("flag","?pag="+pag+"&pageSize="+pageSize);
		
		return "redirect:/msg/boardDeleteOk";
	}
	
	// 게시글 수정폼으로 이동
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.GET)
	public String boardUpdateGet(int idx,int pag, int pageSize, Model model) {
		BoardVO vo = boardService.getBoardContent(idx);
		
		//수정창으로 이동시에는 먼저 원본파일에 그림파일이 있다면, 현재폴더(board)의 그림파일을 ckedidor폴더로 복사시켜둔다.
		if(vo.getContent().indexOf("src=\"/") != -1) boardService.imgCheckUpdate(vo.getContent()); 
		
		model.addAttribute("vo",vo);
		model.addAttribute("pag",pag);
		model.addAttribute("pageSize",pageSize);
		
		return "board/boardUpdate";
	}
	
	// 변경된 내용 수정처리(그림포함)
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public String boardUpdatePost(BoardVO vo, int pag, int pageSize, Model model) {
		// 수정된 자료가 원본자료와 완전히 동일하다면 수정할 필요가 없기에, DB에 저장된 원본 content자료를 불러와서 비료해준다.
		BoardVO origVo = boardService.getBoardContent(vo.getIdx());
		
		// content의 내용이 조금이라도 변경된것이 있다면 아래 내용을 수행처리시킨다.
		if(!origVo.getContent().equals(vo.getContent())) {
			//실제로 수정하기버튼을 클릭하게 되면 기존의 board폴더에 저장된 현재 content의 그림파일을 모두를 삭제시킨다.
			if(origVo.getContent().indexOf("src=\"/") != -1) boardService.imgDelete(origVo.getContent());
			
			// vo.GetContent()에 들어있는 파일의 경로는 'ckeditor/board'경로를 'ckeditor' 변경시켜줘야한다.
			vo.setContent(vo.getContent().replace("/data/ckeditor/board/","/data/ckeditor/"));
			
			// 앞의 모든준비가 끝나면, 파일을 처음 업로드한것과 같은 작업을 처리한다.
			// 이 작업은 처음 게시글을 올릴때의 파일복사 작업과 동일한 작업이다.
			boardService.imgCheck(vo.getContent());
			
			// 파일 업로드가 끝나면 다시 경로를 수정한다. 'ckeditor' 경로를 'ckeditor/board' 변경시켜줘야한다. (즉, vo.setContent() 처리한다.)
			vo.setContent(vo.getContent().replace("/data/ckeditor/","/data/ckeditor/board/"));
			
		}
		
		// 잘 정비된 vo를 DB에 Update시켜준다.
		boardService.setBoardUpdateOk(vo);
	
		model.addAttribute("flag","?pag="+pag+"&pageSize="+pageSize);
		
		return "redirect:/msg/boardUpdateOk";
	}
	
	// 댓글 달기
	@ResponseBody
	@RequestMapping(value = "/boardReplyInput" , method = RequestMethod.POST)
	public String boardReplyInput(BoardReplyVO replyVo) {
		int levelOrder = 0;
		String strLevelOrder =  boardService.getMaxLevelOrder(replyVo.getBoardIdx()); // max, min은 없으면 null이 오고 count = 0이 옴
		if(strLevelOrder != null) levelOrder = Integer.parseInt(strLevelOrder)+1;
		
		replyVo.setLevelOrder(levelOrder);
		
		boardService.setBoardReplyInput(replyVo);
		
		return "1";
	}

	
	// 대댓글 달기
	@ResponseBody
	@RequestMapping(value = "/boardReplyInput2" , method = RequestMethod.POST)
	public String boardReplyInput2Post(BoardReplyVO replyVo) {
		
		boardService.setLevelOrderPlusUpdate(replyVo);
		replyVo.setLevel(replyVo.getLevel()+1);
		replyVo.setLevelOrder(replyVo.getLevelOrder()+1);
		
		boardService.setBoardReplyInput2(replyVo);
		
		System.out.println("옘병"+replyVo);
		
		return "";
	}
	
	
	// 댓글 삭제
	@ResponseBody
	@RequestMapping(value = "/boReplyDeleteOk" , method = RequestMethod.POST)
	public String boReplyDeleteOkPost(int idx) {
		
		boardService.setboReplyDeleteOk(idx);
		
		return "";
	}
	
	// 댓글 삭제
	@ResponseBody
	@RequestMapping(value = "/boardUpdateCheck2" , method = RequestMethod.POST)
	public String boardUpdateCheck2Post(BoardReplyVO replyVo) {
		System.out.println("vo : " + replyVo);
		boardService.setUpdateReply(replyVo);
		
		return "";
	}
	

}
