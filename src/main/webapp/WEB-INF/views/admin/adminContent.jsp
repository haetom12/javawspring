<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>adContent.jsp</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
  <script>
    'use strict';
    
    function midSearch() {
      let mid = myform.mid.value;
      if(mid.trim() == "") {
    	  alert("아이디를 입력하세요!");
    	  myform.mid.focus();
      }
      else {
    	  myform.submit();
      }
    }
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h5>관리자 홈 화면</h5>
  <hr/>
  <p>
    신규 가입자 :
  </p>
  <hr/>
  <p><br/></p>
		<div class="w3-row-padding w3-margin-bottom">
	    <div class="w3-quarter">
	      <div class="w3-container w3-red w3-padding-16">
	        <div class="w3-left"><i class="fa fa-comment w3-xxxlarge"></i></div>
	        <div class="w3-right">
	        </div>
	        <div class="w3-clear"></div>
	        <h3>새글 갯수 <font color="blue">${newBoardCnt}</font>개</h3>
	      </div>
	    </div>
	    <div class="w3-quarter">
	      <div class="w3-container w3-blue w3-padding-16">
	        <div class="w3-left"><i class="fa fa-eye w3-xxxlarge"></i></div>
	        <div class="w3-right">
	        </div>
	        <div class="w3-clear"></div>
	        <h3>신규 가입 회원 <font color="blue">${newMemberCnt}</font>명</h3>
	      </div>
	    </div>
	  </div>
<!-- 블록 페이지 끝 -->
<p><br/></p>
  
</div>
<p><br/></p>
</body>
</html>