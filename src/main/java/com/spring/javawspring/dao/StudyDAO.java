package com.spring.javawspring.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.javawspring.vo.GuestVO;
import com.spring.javawspring.vo.KakaoAddressVO;
import com.spring.javawspring.vo.MemberVO;
import com.spring.javawspring.vo.TransactionVO;
import com.spring.javawspring.vo.qrCodeVO;

public interface StudyDAO {

	public GuestVO getGuestMid(@Param("name") String mid);

	public ArrayList<GuestVO> getGuestNames(@Param("name") String mid, @Param("search") String search);

	public ArrayList<MemberVO> getMemEmail();

	public void setFoodInfo(@Param("name") String name, @Param("price") int price, @Param("ingredient") String ingredient , @Param("idx") String uid, @Param("qrCode") String qrCodeName);

	public qrCodeVO getQrCodeInfo(@Param("idx") String fIdx);

	public KakaoAddressVO getKakaoAddressName(@Param("address") String address);

	public void setKakaoAddressName(@Param("vo") KakaoAddressVO vo);

	public List<KakaoAddressVO> getAddressNameList();

	public void setkakaoEx2Delete(@Param("address") String address);

	public List<KakaoAddressVO> getKakaoList();

	public void setTransInput1(@Param("vo") TransactionVO vo);
	
	public void setTransInput2(@Param("vo") TransactionVO vo);

	public List<TransactionVO> setTransList();

	public void setTransInput(@Param("vo") TransactionVO vo);

}
