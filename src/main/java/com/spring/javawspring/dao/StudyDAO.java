package com.spring.javawspring.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.javawspring.vo.GuestVO;
import com.spring.javawspring.vo.MemberVO;
import com.spring.javawspring.vo.qrCodeVO;

public interface StudyDAO {

	public GuestVO getGuestMid(@Param("name") String mid);

	public ArrayList<GuestVO> getGuestNames(@Param("name") String mid, @Param("search") String search);

	public ArrayList<MemberVO> getMemEmail();

	public void setFoodInfo(@Param("name") String name, @Param("price") int price, @Param("ingredient") String ingredient , @Param("idx") String uid, @Param("qrCode") String qrCodeName);

	public qrCodeVO getQrCodeInfo(@Param("idx") String fIdx);

}
