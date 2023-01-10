package com.spring.javawspring.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.javawspring.common.JavaspringProvide;
import com.spring.javawspring.dao.MemberDAO;
import com.spring.javawspring.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDAO memberDAO;

	@Override
	public MemberVO getMemberIdCheck(String mid) {
		return memberDAO.getMemberIdCheck(mid);
	}

	@Override
	public MemberVO getMemberNickNameCheck(String nickName) {
		return memberDAO.getMembernickNameCheck(nickName);
	}

	@Override
	public int setMemberJoinOk(MultipartFile fName, MemberVO vo) {
		// 업로드 된 사진을 서버 파일시스템에 저장시켜준다.
		int res = 0;
		try {
			String oFileName = fName.getOriginalFilename();
			if(oFileName.equals("")) {
				vo.setPhoto("noimage.jpg");
			}
			else {
				UUID uid = UUID.randomUUID();
				String saveFileName = uid + "_" + oFileName;
				
				JavaspringProvide ps = new JavaspringProvide();
				ps.writeFile(fName,saveFileName, "member");
				vo.setPhoto(saveFileName);
			}
			memberDAO.setMemberJoinOk(vo);
			res = 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	// 사진 업데이트
	@Override
	public int setMemberUpdateOk(MultipartFile fName, MemberVO vo, String pastPhoto) {
		int res = 0;
		String oFileName = fName.getOriginalFilename();
		UUID uid = UUID.randomUUID();
		String saveFileName = "";
		if(oFileName.equals("")) {
			saveFileName = vo.getPhoto();
			res = 1;
			return res;
		}
		else {
			saveFileName = uid + "_" + oFileName;
		}
		JavaspringProvide ps = new JavaspringProvide();
		try {
				ps.deleteFile(fName,saveFileName,pastPhoto, "member");
				vo.setPhoto(saveFileName);
				memberDAO.setMemberUpdate(vo);
				res = 1;				
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	// 로그인시 포인트 처리
	@Override
	public void setMemberVisitProcess(MemberVO vo) {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strNow = sdf.format(now);
		
		// 오늘 처음 방문시는 오늘 방문카운트(todayCnt)를 0으로 셋팅한다. 
		if(!vo.getLastDate().substring(0,10).equals(strNow)) {
			//memberDAO.setTodayCntUpdate(vo.getMid());
			vo.setTodayCnt(0);
		}
		
		int todayCnt = vo.getTodayCnt() + 1;

		int nowTodayPoint = 0;
		if(vo.getTodayCnt() >= 5) {
			nowTodayPoint = vo.getPoint();
		}
		else {
			nowTodayPoint = vo.getPoint() + 10;
		}
		// 오늘 재방문이라면 '총방문수','오늘방문수','포인트' 누적처리
		memberDAO.setMemTotalUpdate(vo.getMid(), nowTodayPoint, todayCnt);
	}
	// 멤버 총 숫자 가져오기
	@Override
	public int totRecCnt() {
		return memberDAO.totRecCnt();
	}

	// 멤버 리스트 가져오기
	@Override
	public ArrayList<MemberVO> getMemberList(int startIndexNo, int pageSize) {
		return memberDAO.getMemberList(startIndexNo, pageSize);
	}

	// 검색한 회원 수 가져오기
	@Override
	public int totSearchRecCnt(String search, String searchString) {
		return memberDAO.totSearchCnt(search,searchString);
	}

	// 검색한 회원 리스트 가져오기
	@Override
	public ArrayList<MemberVO> getMemberSearch(int startIndexNo, int pageSize, String search, String searchString) {
		return memberDAO.getMemberSearch(startIndexNo, pageSize, search, searchString);
	}

	// 선택한 회원 정보보기
	@Override
	public MemberVO getMemberInfo(String mid) {
		return memberDAO.getMemberIdCheck(mid);
	}

	@Override
	public MemberVO getFindMid(String name, String nickName) {
		return memberDAO.getMemberMid(name,nickName);
	}

	@Override
	public void setMemberDelete(String mid) {
		memberDAO.getMemberDelete(mid);
	}

	@Override
	public int totTermRecCnt(String search, String searchString) {
		return memberDAO.totTermRecCnt(search,searchString);
	}

	@Override
	public ArrayList<MemberVO> getTermMemberList(int startIndexNo, int pageSize, String search, String searchString) {
		return memberDAO.getTermMemberList(startIndexNo, pageSize,search,searchString);
	}

	@Override
	public void setMemberPwdUpdate(String mid, String pwd) {
		memberDAO.setMemberPwdUpdate(mid, pwd);
	}

}
