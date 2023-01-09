<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>title</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
  
<script>
	'use strict';
	function idCheck() {
		let mid = myform.mid.value;
		if(mid.trim() == "") {
			alert("성명을 입력하세요!");
			myform.mid.focus();
			return false;
		}
		
		$.ajax({
			type : "post",
			url  : "${ctp}/study/ajax/ajaxTest3_1",
			data : {mid : mid},
			success:function(vo) {
				let str = '<b>전송결과</b><hr/>';
				if(vo != '') {
					str += '성명 : ' + vo.name + '<br>';
					str += '이메일 : ' + vo.email + '<br>';
					str += '홈페이지 : ' + vo.homePage + '<br>';
					str += '방문IP : ' + vo.hostIp + '<br>';
				}
				else {
					str += '<font color="red"><b>찾는 차료가 없습니다.</b></font>';
				}
				$("#demo").html(str);
			},
			error : function() {
				alert("전송오류!");
			}
		});
	}
	function nameCheck() {
		let mid = myform.mid.value;
		let search = myform.search.value;
		if(mid.trim() == "") {
			alert("성명를 입력하세요!");
			myform.mid.focus();
			return false;
		}
		
		$.ajax({
			type : "post",
			url  : "${ctp}/study/ajax/ajaxTest3_2",
			data : {
					mid : mid,
					search : search
			},
			success:function(vos) {
				let str = '<b>전송결과</b><hr/>';
				if(vos != '') {
					str += '<table class="table table-hover">'
					str += '<tr class="table-dark text-dark	text-center">';
					str += '<th>성명</th><th>이메일</th><th>홈페이지</th><th>방문소감</th><th>방문IP</th>';
					str += '</tr>';
					for(let i=0; i<vos.length; i++){  //자바스크립트이기때문에 foreach가 아니다
						str += '<tr class="text-center">';
						str += '<td>' + vos[i].name + '</td>';
						str += '<td>' + vos[i].email + '</td>';
						str += '<td>' + vos[i].homePage + '</td>';
						str += '<td>' + vos[i].content + '</td>';
						str += '<td>' + vos[i].hostIp + '</td>';
						str += '</tr>';
					}
					str += '</table>';
				}
				else {
					str += '<font color="red"><b>찾는 차료가 없습니다.</b></font>';
				}
				$("#demo").html(str);
			},
			error : function() {
				alert("전송오류!");
			}
		});
	}
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
  <h2>aJax를 활용한 '방명록 이름' 검색하기</h2>
  <hr />
  <form name="myform">
  	<p>
			<select name="search" style="width:12%;" class="form-control">
	      <option value="name">성 명</option>
	      <option value="email">이메일</option>
	      <option value="content">내용</option>
	    </select>
  		<input type="text" name="mid" autofocus />
  		<input type="button" value="성명일치검색" onclick="idCheck()" class="btn btn-success" /> &nbsp;
  		<input type="button" value="성명부분일치검색" onclick="nameCheck()" class="btn btn-success" /> &nbsp;
  		<input type="reset" value="다시입력"class="btn btn-warning" /> &nbsp;
  		<input type="button" value="돌아가기" onclick="location.href='${ctp}/study/ajax/ajaxMenu';" class="btn btn-secondary" /> &nbsp;
  	</p>
  </form>
  <div id="demo"></div>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>