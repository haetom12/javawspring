<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>fileList.jsp</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
  <script>
	  // 전체선택
	  $(function(){
	  	$("#checkAll").click(function(){
	  		if($("#checkAll").prop("checked")) {
		    		$(".chk").prop("checked", true);
	  		}
	  		else {
		    		$(".chk").prop("checked", false);
	  		}
	  	});
	  });
	  
	  // 선택항목 반전
	  $(function(){
	  	$("#reversekAll").click(function(){
	  		$(".chk").prop("checked", function(){
	  			return !$(this).prop("checked");
	  		});
	  	});
	  });
	  
	  // 선택항목 삭제하기(ajax처리하기)
	  function selectDelCheck() {
	  	if(photoForm.chk.length == 0) {
	  		alert('삭제할 게시물을 선택하세요.');
	  		return false;
	  	}
	  	
	  	let ans = confirm("선택된 모든 게시물을 삭제 하시겠습니까?");
	  	if(!ans) return false;
	  	let delItems = "";
	  	for(let i=0; i<photoForm.chk.length; i++) {
	  		if(photoForm.chk[i].checked == true) delItems += photoForm.chk[i].value + "/";
	  	}
			
	  	$.ajax({
	  		type : "post",
	  		url  : "${ctp}/admin/photoViewDeleteAll",
	  		data : {delItems : delItems},
	  		success:function(res) {
	  			if(res == "1") {
	  				alert("삭제완료");
	  			  location.reload();
	  			}
	  			else {
	  				alert("삭제실패");
	  			}
	  		},
	  		error  :function() {
	  			alert("전송오류!!");
	  		}
	  	});
	  }
	
	  // 선택 삭제처리
	  function delCheck() {
	  	let ans = confirm("선택된 파일을 삭제하시겠습니까?");
	  	if(!ans) return false;
	  }
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2>서버 파일 리스트</h2>
  <hr/>
  <p>서버의 파일 경로 : ${ctp}/data/ckeditor/~~~파일명</p>
  <hr/>
  <form name="photoForm">
	  <table class="table table-hover text-center">
	    <tr>
	      <td colspan="4">
	        <input type="checkbox" id="checkAll" onclick="checkAllCheck()"/>전체선택/해제 &nbsp;&nbsp;
	        <input type="checkbox" id="reversekAll" onclick="reverseAllCheck()"/>선택반전 &nbsp;&nbsp;
	        <input type="button" value="선택항목삭제" onclick="selectDelCheck()" class="btn btn-danger btn-sm"/>
	      </td>
	    </tr>
	    <tr class="table-dark text-dark">
	      <th>선택</th><th>번호</th><th>파일명</th><th>그림파일</th>
	    </tr>
		  <c:forEach var="file" items="${files}" varStatus="st">
		    <tr>
		      <td><input type="checkbox" name="chk" class="chk" value="${file}"/></td>
		      <td>${st.count}</td>
		      <td>${file}</td>
		      <td><img src="${ctp}/data/ckeditor/${file}" width="150px"/></td>
		    </tr>
		  </c:forEach>
	  </table>
  </form>
</div>
<p><br/></p>
</body>
</html>