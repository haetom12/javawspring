package com.spring.javawspring;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = {"/","/h"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	// ckeditor 사용시 파일 올릴때 객체 3개 필요함!!!!
	@RequestMapping(value = "/imageUpload")
	public void imageUploadGet(MultipartFile upload, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String oFileName = upload.getOriginalFilename();
		
		Date date = new Date();
	  SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss"); // 날짜변환
	  oFileName = sdf.format(date) + "_" + oFileName;
		
	  byte[] bytes = upload.getBytes(); 
	  
	  
	  // ckeditor에서 올린 파일을, 서버 파일시스템에 실제로 저장할 경로를 결정한다.
//	  String realPath = request.getRealPath("");
	  String realPath = request.getSession().getServletContext().getRealPath("/resources/data/ckeditor/");
	  OutputStream os = new FileOutputStream(new File(realPath + oFileName)); // 2줄을 한줄로
	  // 서버에 읽을떄 inputstream , 서버에서 서버로 저장할땐 outputstream
	  os.write(bytes);
	  
	  
	  // 서버 파일시스템에 저장되어 있는 파일을 브라우저 편집 화면에 보여주기 위한 작업
	  PrintWriter out = response.getWriter();
	  String fileUrl = request.getContextPath() + "/data/ckeditor/" + oFileName;
	  out.println("{\"originalFilename\" : \""+oFileName+"\", \"uploaded\" : 1, \"url\" : \""+fileUrl+"\"}");
	  
	  out.flush(); //보내고 남은 바이트들도 다 보내기
	  os.close();
	}
}

