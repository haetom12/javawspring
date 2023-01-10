package com.spring.javawspring.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.javawspring.vo.GuestVO;

public interface GuestDAO {

	public ArrayList<GuestVO> getGuestList(@Param("startIndexNo") int startIndexNo,@Param("pageSize") int pageSize);

	public void setGuestInput(@Param("vo") GuestVO vo);

	public int totRecCnt();

	public void guestDelete(@Param("idx") int idx);

	public int totSearchCnt(@Param("search") String search, @Param("searchString") String searchString);

	public ArrayList<GuestVO> getGuestSearch(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize, @Param("search") String search, @Param("searchString") String searchString);

	public GuestVO guestUpdate(@Param("idx") int idx);

	public void guestUpdateOk(@Param("vo") GuestVO vo);

	public ArrayList<GuestVO> getTermGuestList(@Param("startIndexNo") int startIndexNo,@Param("pageSize") int pageSize,@Param("search") String search,@Param("searchString") String searchString);

}
