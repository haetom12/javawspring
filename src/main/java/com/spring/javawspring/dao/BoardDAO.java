package com.spring.javawspring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.javawspring.vo.BoardVO;

public interface BoardDAO {

	public List<BoardVO> getBoardList(@Param("startIndexNo")int startIndexNo,@Param("pageSize") int pageSize,@Param("search") String search,@Param("searchString") String searchString);

	public int totSearchCnt(@Param("search") String search,@Param("searchString") String searchString);

	public int setBoardInput(@Param("vo") BoardVO vo);

	public BoardVO getBoardContent(@Param("idx") int idx);

	public void setBoardReadNum(@Param("idx") int idx);


}
