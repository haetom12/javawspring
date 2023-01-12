<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>adLeft.jsp</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
  <style>
		.accordion {
		  background-color: #eee;
		  color: #444;
		  cursor: pointer;
		  width: 100%;
		  height: 50px;
		  border: none;
		  text-align: left;
		  outline: none;
		  font-size: 15px;
		  transition: 0.4s;
		}
		
		.active, .accordion:hover {
		  background-color: #ccc;
		}
		
		.accordion:after {
		  content: '\002B';
		  color: #777;
		  font-weight: bold;
		  float: right;
		  margin-left: 5px;
		}
		
		.active:after {
		  content: "\2212";
		}
		
		.panel {
		  padding: 0 18px;
		  background-color: white;
		  max-height: 0;
		  overflow: hidden;
		  transition: max-height 0.2s ease-out;
		}
	</style>

	
	
</head>

<body style="background-color: gray">
<p><br/></p>
<div class="container">
  <h5>관리자메뉴</h5>
  <hr/>
  <p>
    <a href="${ctp}/" target="_top">메인홈으로</a>
  </p>
  <hr/>
  <p>
    <a href="${ctp}/admin/adMain" target="_top">관리자홈으로</a>
  </p>
  <hr/>
  <p>
    <a href="#">방명록리스트</a>
  </p>
  <hr/>
  <p>
    <a href="${ctp}/admin/member/adminMemberList" target="adminContent">회원리스트</a>
  </p>
  <hr />
  <button class="accordion">Section 1</button>
		<div class="panel">
		  <p style="margin-top: 10px; border-bottom: 1px solid; black;"><a href="${ctp}/admin/adminboardList" target="adminContent">게시글 삭제처리</a></p>
		  <p style="border-bottom: 1px solid; black;"><a href="#">메뉴2</a></p>
		  <p style="border-bottom: 1px solid; black;"><a href="#">메뉴3</a></p>
		  <p style="border-bottom: 1px solid; black;"><a href="#">메뉴4</a></p>
		</div>
  <p>
  <hr/>
    <a href="${ctp}/admin/file/fileList" target="adminContent">임시파일</a>
  </p>
</div>
<p><br/></p>
	<script>
		var acc = document.getElementsByClassName("accordion");
		var i;
		
		for (i = 0; i < acc.length; i++) {
		  acc[i].addEventListener("click", function() {
		    this.classList.toggle("active");
		    var panel = this.nextElementSibling;
		    if (panel.style.maxHeight) {
		      panel.style.maxHeight = null;
		    } else {
		      panel.style.maxHeight = panel.scrollHeight + "px";
		    } 
		  });
		}
  </script>
</body>
</html>