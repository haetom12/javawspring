package com.spring.javawspring.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.spring.javawspring.vo.BoardReplyVO;
import com.spring.javawspring.vo.BoardVO;
import com.spring.javawspring.vo.GoodVO;

public class BoardDAOTest {

	@Test
	public void testGetBoardList() {
		BoardDAO dao = new BoardDAO() {
			
			@Override
			public int totboardReplyCnt(String search, String searchString, int idx) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int totSearchCnt(String search, String searchString) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void setboReplyDeleteOk(int idx) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setUpdateReply(BoardReplyVO replyVo) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setLevelOrderPlusUpdate(BoardReplyVO replyVo) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setGoodDelete(int partIdx, String part, String mid) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setBoardUpdateOk(BoardVO vo) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setBoardReplyInput2(BoardReplyVO replyVo) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setBoardReplyInput(BoardReplyVO replyVo) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setBoardReadNum(int idx) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public int setBoardInput(BoardVO vo) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void setBoardDeleteOk(int idx) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setBoGood(int idx) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setAdminBoardDelete(int idx) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public ArrayList<BoardVO> getPreNext(int idx) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getMemberCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getMaxLevelOrder(int boardIdx) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void getGoodInsert(int idx, String part, String mid) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public List<BoardReplyVO> getBoardReply2(int startIndexNo, int pageSize, String search, String searchString,
					int idx) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<BoardReplyVO> getBoardReply(int idx) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getBoardNewCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public List<BoardVO> getBoardList(int startIndexNo, int pageSize, String search, String searchString) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public GoodVO getBoardGoodCheck(int partIdx, String part, String mid) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public BoardVO getBoardContent(int idx) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		List<BoardVO> vos = dao.getBoardList(0, 10, "", "");
		System.out.println("vos : " + vos);
	}

}
