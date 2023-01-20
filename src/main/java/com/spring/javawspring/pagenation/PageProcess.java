package com.spring.javawspring.pagenation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.javawspring.dao.BoardDAO;
import com.spring.javawspring.dao.GuestDAO;
import com.spring.javawspring.dao.MemberDAO;
import com.spring.javawspring.dao.PdsDAO;
import com.spring.javawspring.dao.WebMessageDAO;

@Service
public class PageProcess {
	@Autowired
	GuestDAO guestDAO;
	
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	BoardDAO boardDAO;
	
	@Autowired
	PdsDAO pdsDAO;
	
	@Autowired
	WebMessageDAO webMessageDAO;
	
	public PageVO totRecCnt(int pag, int pageSize, String section, String search, String searchString) {
		PageVO pageVO = new PageVO();
		int totRecCnt = 0;
		
		if(section.equals("member")) {
		 	totRecCnt = memberDAO.totSearchCnt(search,searchString);			
		}
		else if(section.equals("guest")) {
			totRecCnt = guestDAO.totSearchCnt(search,searchString);			
		}
		else if(section.equals("board")) {
			totRecCnt = boardDAO.totSearchCnt(search,searchString);			
		}
		else if(section.equals("pds")) {
			totRecCnt = pdsDAO.totRecCnt(search);
		}
		
		else if(section.equals("webMessage")) {
			String mid = search;
			int mSw = Integer.parseInt(searchString);
			totRecCnt = webMessageDAO.totRecCnt(mid,mSw);
		}

		int totPage = (totRecCnt % pageSize) ==0 ? totRecCnt / pageSize : (totRecCnt / pageSize) +1;	//4. 총 페이지 건수를 구한다.
		int startIndexNo = (pag-1) * pageSize; //5. 현재페이지의 시작 인덱스번호를 구한다.
		int curScrStartNo = totRecCnt - startIndexNo;		//6. 현재 화면에 보여주는 시작번호를 구한다.
		
		int blockSize = 3;  // 1. 블록의 크기를 결정한다.(여기선 3으로 지정)		
		int curBlock = (pag - 1) / blockSize;
		int lastBlock = (totPage - 1) / blockSize;	// 3. 마지막블록을 구한다.
		
		pageVO.setPag(pag);
		pageVO.setPageSize(pageSize);
		pageVO.setTotRecCnt(totRecCnt);
		pageVO.setTotPage(totPage);
		pageVO.setStartIndexNo(startIndexNo);
		pageVO.setCurScrStartNo(curScrStartNo);
		pageVO.setBlockSize(blockSize);
		pageVO.setCurBlock(curBlock);
		pageVO.setLastBlock(lastBlock);
		
		pageVO.setPart(search);
		
		
		return pageVO;
	}

	public PageVO totReplyRecCnt(int replyPag, int replyPageSize, String section, String search, String searchString, int idx) {
		PageVO pageVO = new PageVO();
		int totRecCnt = 0;
		
		if(section.equals("boardReply")) {
			totRecCnt = boardDAO.totboardReplyCnt(search,searchString, idx);	
		  System.out.println("댓글 숫자" + totRecCnt);
		}
		int totPage = (totRecCnt % replyPageSize) ==0 ? totRecCnt / replyPageSize : (totRecCnt / replyPageSize) +1;	//4. 총 페이지 건수를 구한다.
		int startIndexNo = (replyPag-1) * replyPageSize; //5. 현재페이지의 시작 인덱스번호를 구한다.
		int curScrStartNo = totRecCnt - startIndexNo;		//6. 현재 화면에 보여주는 시작번호를 구한다.
		
		int blockSize = 3;  // 1. 블록의 크기를 결정한다.(여기선 3으로 지정)		
		int curBlock = (replyPag - 1) / blockSize;
		int lastBlock = (totPage - 1) / blockSize;	// 3. 마지막블록을 구한다.
		
		pageVO.setPag(replyPag);
		pageVO.setPageSize(replyPageSize);
		pageVO.setTotRecCnt(totRecCnt);
		pageVO.setTotPage(totPage);
		pageVO.setStartIndexNo(startIndexNo);
		pageVO.setCurScrStartNo(curScrStartNo);
		pageVO.setBlockSize(blockSize);
		pageVO.setCurBlock(curBlock);
		pageVO.setLastBlock(lastBlock);
		return pageVO;
	}
	
	
	
	
	
}
