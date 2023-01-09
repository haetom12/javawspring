package com.spring.javawspring.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

public class JavaspringProvide {
	public int fileUpload(MultipartFile fName, String flag) {
		int res = 0;
		try {
			UUID uid = UUID.randomUUID();
			String oFileName = fName.getOriginalFilename();
			String saveFileName = uid + "_" + oFileName;
			
			writeFile(fName,saveFileName, flag);
			res = 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	public void writeFile(MultipartFile fName, String saveFileName, String flag) throws IOException {
		byte[] data = fName.getBytes();
		// service에서 request객체를 사용하기 위해서 형변환 및 여러가지
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String realPath = "";
	  realPath = request.getSession().getServletContext().getRealPath("/resources/"+flag+"/");
		
		
		FileOutputStream fos = new FileOutputStream(realPath + saveFileName);
		fos.write(data);
		fos.close();
	}

	// 사진 삭제 후 수정
	public void deleteFile(MultipartFile fName, String saveFileName, String oldFileName, String flag) throws IOException {
		byte[] data = fName.getBytes();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String realPath = "";
	  realPath = request.getSession().getServletContext().getRealPath("/resources/"+flag+"/");
	  if(!oldFileName.equals("noimage.jpg")) {
	  	File file = new File(realPath + oldFileName);
	  	if(file.exists()) file.delete();
	  }
		FileOutputStream fos = new FileOutputStream(realPath + saveFileName);
		fos.write(data);
		fos.close();
	}
}
