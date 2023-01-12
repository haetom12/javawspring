package com.spring.javawspring.service;

import java.util.ArrayList;
import java.util.List;

import com.spring.javawspring.vo.BoardReplyVO;
import com.spring.javawspring.vo.BoardVO;
import com.spring.javawspring.vo.GoodVO;

public interface BoardService {

	public List<BoardVO> getBoardList(int startIndexNo, int pageSize, String search, String searchString);

	public int setBoardInput(BoardVO vo);

	public BoardVO getBoardContent(int idx);

	public void setBoardReadNum(int idx);

	public void setBoGood(int idx);

	public GoodVO getBoardGoodCheck(int partIdx, String part, String mid);

	public ArrayList<BoardVO> getPreNext(int idx);

	public void imgCheck(String content);

	public void setBoardDeleteOk(int idx);

	public void imgDelete(String content);

	public void imgCheckUpdate(String content);

	public void setBoardUpdateOk(BoardVO vo);

	public void getGoodInsert(int idx, String part, String mid);

	public void setGoodDelete(int partIdx, String part, String mid2);

	public void setBoardReplyInput(BoardReplyVO replyVo);

	public List<BoardReplyVO> getBoardReply(int idx);

	public void setboReplyDeleteOk(int idx);

	public String getMaxLevelOrder(int boardIdx);

	public void setLevelOrderPlusUpdate(BoardReplyVO replyVo);

	public void setBoardReplyInput2(BoardReplyVO replyVo);

	public void setUpdateReply(BoardReplyVO replyVo);

	public int getBoardNewCount();

	public int getMemberCount();

	public List<BoardReplyVO> getBoardReply2(int startIndexNo, int pageSize, String search, String searchString, int idx);
	

}
