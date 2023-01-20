package com.spring.javawspring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.javawspring.vo.GuestVO;
import com.spring.javawspring.vo.KakaoAddressVO;
import com.spring.javawspring.vo.MemberVO;
import com.spring.javawspring.vo.TransactionVO;
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

	public KakaoAddressVO getKakaoAddressName(String address);

	public void setKakaoAddressName(KakaoAddressVO vo);

	public List<KakaoAddressVO> getAddressNameList();

	public void setkakaoEx2Delete(String address);

	public List<KakaoAddressVO> getDistanceList();

	public void setTransInput1(TransactionVO vo);

	public void setTransInput2(TransactionVO vo);

	public List<TransactionVO> setTransList();

	public void setTransInput(TransactionVO vo);

}
