package com.spring.javawspring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.javawspring.dao.BoardDAO;
import com.spring.javawspring.vo.BoardReplyVO;
import com.spring.javawspring.vo.BoardVO;
import com.spring.javawspring.vo.GoodVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO boardDAO;

	@Override
	public List<BoardVO> getBoardList(int startIndexNo, int pageSize, String search, String searchString) {
		return boardDAO.getBoardList(startIndexNo, pageSize, search, searchString);
	}

	@Override
	public int setBoardInput(BoardVO vo) {
		return boardDAO.setBoardInput(vo);
	}

	@Override
	public BoardVO getBoardContent(int idx) {
		return boardDAO.getBoardContent(idx);
	}

	@Override
	public void setBoardReadNum(int idx) {
		boardDAO.setBoardReadNum(idx);
		
	}

	@Override
	public void setBoGood(int idx) {
		boardDAO.setBoGood(idx);
	}

	@Override
	public GoodVO getBoardGoodCheck(int partIdx, String part, String mid) {
		return boardDAO.getBoardGoodCheck(partIdx, part, mid);
	}

	@Override
	public ArrayList<BoardVO> getPreNext(int idx) {
		return boardDAO.getPreNext(idx);
	}

	@Override
	public void imgCheck(String content) {
		// 		 0         1         2         3         4         5         6         7         8         
		//     012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789
		//<img src="/javawspring/data/ckeditor/230111121412_4.jpg" style="height:183px; width:275px" />
		// content안에 그림파일이 존재할때만 작업을 수행 할 수 있도록 한다.
		if(content.indexOf("src=\"/") == -1) return;
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/data/ckeditor/");
		
		int position = 32;
		String nextImg = content.substring(content.indexOf("src=\"/") + position);
		boolean sw =true;
		
		while(sw) {
			String imgFile = nextImg.substring(0,nextImg.indexOf("\""));
			
			String origFilePath = uploadPath + imgFile;
			String copyFilePath = uploadPath + "board/" + imgFile;
			
			fileCopyCheck(origFilePath,copyFilePath); // board 폴더에 파일을 복사하고자 한다.
			
			if(nextImg.indexOf("src=\"/") == -1) {
				sw = false;
			}
			else {
				nextImg = nextImg.substring(nextImg.indexOf("src=\"/") + position);
			}
		}
	}

	private void fileCopyCheck(String originalFilePath, String copyFilePath) {
		File origFile = new File(originalFilePath);
		File copyFile = new File(copyFilePath);
		
		try {
			FileInputStream fis = new FileInputStream(origFile);
			FileOutputStream fos = new FileOutputStream(copyFile);
			
			byte[] buffer = new byte[2048];
			int cnt = 0;
			while((cnt = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, cnt);
			}
			fos.flush();
			fos.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void setBoardDeleteOk(int idx) {
		boardDAO.setBoardDeleteOk(idx);
	}

	@Override
	public void imgDelete(String content) {
		//	   0         1         2         3         4         5         6         7         8         
		//     012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789
		//<img src="/javawspring/data/ckeditor/board/230111121412_4.jpg" style="height:183px; width:275px" />
		// content안에 그림파일이 존재할때만 작업을 수행 할 수 있도록 한다.
		if(content.indexOf("src=\"/") == -1) return;
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/data/ckeditor/board/");
		
		int position = 38;
		String nextImg = content.substring(content.indexOf("src=\"/") + position);
		boolean sw =true;
		
		while(sw) {
			String imgFile = nextImg.substring(0,nextImg.indexOf("\""));
			
			String origFilePath = uploadPath + imgFile;
			
			fileDelete(origFilePath);
			
			if(nextImg.indexOf("src=\"/") == -1) {
				sw = false;
			}
			else {
				nextImg = nextImg.substring(nextImg.indexOf("src=\"/") + position);
			}
		}
	}

	private void fileDelete(String origFilePath) {
		File delFile = new File(origFilePath);
		if(delFile.exists()) delFile.delete();
		
	}

	@Override
	public void imgCheckUpdate(String content) {
		//  0         1         2         3         4         5         6
		//      01234567890123456789012345678901234567890123456789012345678901234567890
		// <img src="/javawspring/data/ckeditor/board/230111121324_green2209J_06.jpg" style="height:967px; width:1337px" /></p>
		// <img src="/javawspring/data/ckeditor/230111121324_green2209J_06.jpg" style="height:967px; width:1337px" /></p>
		// content안에 그림파일이 존재할때만 작업을 수행 할수 있도록 한다.(src="/_____~~)
		if(content.indexOf("src=\"/") == -1) return;
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/data/ckeditor/board/");
		
		int position = 38;
		String nextImg = content.substring(content.indexOf("src=\"/") + position);
		boolean sw = true;
		
		while(sw) {
			String imgFile = nextImg.substring(0, nextImg.indexOf("\""));
			
			String origFilePath = uploadPath + imgFile;
			String copyFilePath = request.getSession().getServletContext().getRealPath("/resources/data/ckeditor/" + imgFile);
			
			fileCopyCheck(origFilePath, copyFilePath);  // board폴더에 파일을 복사하고자 한다.
			
			if(nextImg.indexOf("src=\"/") == -1) {
				sw = false;
			}
			else {
				nextImg = nextImg.substring(nextImg.indexOf("src=\"/") + position);
			}
		}
	}

	@Override
	public void setBoardUpdateOk(BoardVO vo) {
		boardDAO.setBoardUpdateOk(vo);
	}

	@Override
	public void getGoodInsert(int idx, String part, String mid) {
		boardDAO.getGoodInsert(idx, part, mid);
	}

	@Override
	public void setGoodDelete(int partIdx, String part, String mid) {
		boardDAO.setGoodDelete(partIdx,part, mid);
	}

	@Override
	public void setBoardReplyInput(BoardReplyVO replyVo) {
		boardDAO.setBoardReplyInput(replyVo);
	}

	@Override
	public List<BoardReplyVO> getBoardReply(int idx) {
		return boardDAO.getBoardReply(idx);
	}

	@Override
	public void setboReplyDeleteOk(int idx) {
		boardDAO.setboReplyDeleteOk(idx);
	}

	@Override
	public String getMaxLevelOrder(int boardIdx) {
		return boardDAO.getMaxLevelOrder(boardIdx);
	}

	@Override
	public void setLevelOrderPlusUpdate(BoardReplyVO replyVo) {
		boardDAO.setLevelOrderPlusUpdate(replyVo);
	}

	@Override
	public void setBoardReplyInput2(BoardReplyVO replyVo) {
		boardDAO.setBoardReplyInput2(replyVo);
	}

	@Override
	public void setUpdateReply(BoardReplyVO replyVo) {
		boardDAO.setUpdateReply(replyVo);
	}

	@Override
	public int getBoardNewCount() {
		return boardDAO.getBoardNewCount();
	}

	@Override
	public int getMemberCount() {
		return boardDAO.getMemberCount();
	}

	@Override
	public List<BoardReplyVO> getBoardReply2(int startIndexNo, int pageSize, String search, String searchString, int idx) {
		return boardDAO.getBoardReply2(startIndexNo, pageSize, search, searchString, idx);
	}
}
