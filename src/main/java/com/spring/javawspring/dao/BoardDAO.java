package com.spring.javawspring.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.javawspring.vo.BoardReplyVO;
import com.spring.javawspring.vo.BoardVO;
import com.spring.javawspring.vo.GoodVO;

public interface BoardDAO {

	public List<BoardVO> getBoardList(@Param("startIndexNo")int startIndexNo,@Param("pageSize") int pageSize,@Param("search") String search,@Param("searchString") String searchString);

	public int totSearchCnt(@Param("search") String search,@Param("searchString") String searchString);

	public int setBoardInput(@Param("vo") BoardVO vo);

	public BoardVO getBoardContent(@Param("idx") int idx);

	public void setBoardReadNum(@Param("idx") int idx);

	public void setBoGood(@Param("idx") int idx);

	public GoodVO getBoardGoodCheck(@Param("partIdx") int partIdx,@Param("part") String part,@Param("mid") String mid);

	public ArrayList<BoardVO> getPreNext(@Param("idx") int idx);

	public void setBoardDeleteOk(@Param("idx") int idx);

	public void setBoardUpdateOk(@Param("vo") BoardVO vo);

	public void getGoodInsert(@Param("partIdx") int idx,@Param("part") String part, @Param("mid") String mid);

	public void setGoodDelete(@Param("partIdx") int partIdx, @Param("part") String part, @Param("mid") String mid);

	public void setBoardReplyInput(@Param("replyVo") BoardReplyVO replyVo);

	public List<BoardReplyVO> getBoardReply(@Param("idx") int idx);

	public void setboReplyDeleteOk(@Param("idx") int idx);

	public String getMaxLevelOrder(@Param("boardIdx") int boardIdx);

	public void setLevelOrderPlusUpdate(@Param("replyVo") BoardReplyVO replyVo);

	public void setBoardReplyInput2(@Param("replyVo") BoardReplyVO replyVo);

	public void setUpdateReply(@Param("replyVo") BoardReplyVO replyVo);

	public int getBoardNewCount();

	public int getMemberCount();

	public void setAdminBoardDelete(@Param("idx") int idx);

	public int totboardReplyCnt(@Param("search") String search, @Param("searchString") String searchString, @Param("boardIdx")  int idx);

	public List<BoardReplyVO> getBoardReply2(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize, @Param("search") String search, @Param("searchString") String searchString, @Param("boardIdx") int idx);




}