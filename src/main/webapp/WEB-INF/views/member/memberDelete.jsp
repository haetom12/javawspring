<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>title</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
	<style>
			body {
		  font-family: 'IBM Plex Sans KR', sans-serif;
		  text-align: center;
		}
		
		/* Center website */
		.w3-container .w3-card-4 {
			text-align: center;
		  margin: 0px auto;
		  margin-top: 3%;
		  height: 300px;
		}
		.pwdbtn {
			margin-bottom: 5px;	
		}
			
	
	</style>
  <script>
    'use strict';
		function deleteCheck() {
    	let pwd = memform.pwd.value;
    	

    	if(pwd.trim() == "") {
    		alert("비밀번호를 입력하세요!");
    		memform.pwd.focus();
    	}
    	else {
    		console.log("통과?");
    		memform.submit();
    	}
    }
  </script>
  
  
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />

<div class="w3-container">
<form name="memform" method="post">
  <div class="w3-card-4" style="width:30%">
    <header class="w3-container w3-blue">
      <h3>계정 탈퇴</h3>
    </header>
    <div class="w3-container">
      <h3>계정 탈퇴를 진행하시려면 <br />
      <font color="blue">비밀번호</font>를 입력하세요</h3>
      <hr>
      <input class="w3-input w3-border" id="pwd" name="pwd" type="password" placeholder="비밀번호를 입력하세요" autofocus required>
    </div>
    <br />
    <input type="button" class="w3-button w3-block w3-red pwdbtn" onclick="deleteCheck()" value="계정 탈퇴하기"/>
    <!-- <button class="w3-button w3-block w3-red pwdbtn" onclick="checkmem()">계정 탈퇴하기</button> -->
    <button class="w3-button w3-block w3-yellow" onclick="location.href='${ctp}/Mypage.mem';">뒤로 가기</button>
  </div>
</form>
</div>
<p><br/></p>
<p><br/></p>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>