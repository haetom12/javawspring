package com.spring.javawspring.service;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.spring.javawspring.vo.GuestVO;
import com.spring.javawspring.vo.MemberVO;
import com.spring.javawspring.vo.qrCodeVO;

public interface StudyService {

	public String[] getCityStringArr(String dodo);

	public ArrayList<String> getCityArrayListArr(String dodo);

	public GuestVO getGuestMid(String mid);

	public ArrayList<GuestVO> getGuestNames(String mid, String search);

	public ArrayList<MemberVO> getMemEmail();

	public int fileUpload(MultipartFile fName);

	public void getCalendar();

	public String qrCreate(String mid, String moveFlag, String realPath);

	public void setFoodInfo(String name, int price, String ingredient , String uid, String qrCodeName);

	public String qrCreate2(String name, int price, String ingredient, String realPath);

	public qrCodeVO getQrCodeInfo(String fIdx);

}
