<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>boList.jsp</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
  <script>
    'use strict';
    function pageCheck() {
    	let pageSize = document.getElementById("pageSize").value;
    	location.href = "${ctp}/admin/adminboardList?pageSize="+pageSize+"&pag=${pageVo.pag}";
    }
    
    function searchCheck() {
    	let searchString = $("#searchString").val();
    	
    	if(searchString.trim() == "") {
    		alert("찾고자 하는 검색어를 입력하세요!");
    		searchForm.searchString.focus();
    	}
    	else {
    		searchForm.submit();
    	}
    }
    
    // 전체선택
	  $(function(){
	  	$("#checkAll").click(function(){
	  		if($("#checkAll").prop("checked")) {
		    		$(".check").prop("checked", true);
	  		}
	  		else {
		    		$(".check").prop("checked", false);
	  		}
	  	});
	  });
	  
	  // 선택항목 반전
	  $(function(){
	  	$("#reversekAll").click(function(){
	  		$(".check").prop("checked", function(){
	  			return !$(this).prop("checked");
	  		});
	  	});
	  });
	  
	  // 선택항목 삭제하기(ajax처리하기)
	  function selectDelCheck() {
	  	if(boardForm.check.length == 0) {
	  		alert('삭제할 게시물을 선택하세요.');
	  		return false;
	  	}
	  	
	  	let ans = confirm("선택된 모든 게시물을 삭제 하시겠습니까?");
	  	if(!ans) return false;
	  	let delItems = "";
	  	for(let i=0; i<boardForm.check.length; i++) {
	  		if(boardForm.check[i].checked == true) delItems += boardForm.check[i].value + "/";
	  	}
			
	  	$.ajax({
	  		type : "post",
	  		url  : "${ctp}/admin/boardDeleteAll",
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
    
    
    
  </script>
</head>
<body>
<p><br/></p>
<div class="container">
  <h2 class="text-center">게 시 판 리 스 트</h2>
  <br/>
  <table class="table table-borderless">
    <tr>
      <td class="text-left p-0">
        	<input type="checkbox" id="checkAll" onclick="checkAllCheck()"/>전체선택/해제 &nbsp;&nbsp;
	        <input type="checkbox" id="reversekAll" onclick="reverseAllCheck()"/>선택반전 &nbsp;&nbsp;
	        <input type="button" value="선택항목삭제" onclick="selectDelCheck()" class="btn btn-danger btn-sm"/>
      </td>
      <td class="text-right p-0">
        <select name="pageSize" id="pageSize" onchange="pageCheck()">
          <option value="5"  ${pageSize==5  ? 'selected' : ''}>5건</option>
          <option value="10" ${pageSize==10 ? 'selected' : ''}>10건</option>
          <option value="15" ${pageSize==15 ? 'selected' : ''}>15건</option>
          <option value="20" ${pageSize==20 ? 'selected' : ''}>20건</option>
        </select>
      </td>
    </tr>
  </table>
  <form name="boardForm">
	  <table class="table table-hover text-center">
	    <tr class="table-dark text-dark">
	      <th>선택</th>
	      <th>글번호</th>
	      <th>글제목</th>
	      <th>글쓴이</th>
	      <th>글쓴날짜</th>
	      <th>조회수</th>
	      <th>좋아요</th>
	    </tr>
	    <c:set var="curScrStartNo" value="${pageVo.curScrStartNo}"/>
	    <c:forEach var="vo" items="${vos}">
	    	<tr>
	    	  <td><input type="checkbox" name="check" class="check" value="${vo.idx}"/></td>
	    	  <td>${curScrStartNo}</td>
	    	  <td class="text-left">
	    	    <a href="${ctp}/board/boardContent?idx=${vo.idx}&pageSize=${pageVo.pageSize}&pag=${pageVo.pag}">${vo.title}</a>
	    	    <c:if test="${vo.replyCount != 0}">(${vo.replyCount})</c:if>
	    	    <c:if test="${vo.hour_diff <= 24}"><img src="${ctp}/images/new.gif"/></c:if>
	    	  </td>
	    	  <td>${vo.nickName}</td>
	    	  <td>
	    	    <!-- 1일(24시간)이 지난것은 날짜만표시, 1일(24시간)이내것은 시간을 표시하되, 24시간 이내중 현재시간보다 이후시간은 날짜와 시간을 함께 표시 -->
	    	    <c:if test="${vo.hour_diff >= 24}">${fn:substring(vo.WDate,0,10)}</c:if>
	    	    <c:if test="${vo.hour_diff < 24}">
	    	      ${vo.day_diff > 0 ? fn:substring(vo.WDate,0,16) : fn:substring(vo.WDate,11,19)}
	    	    </c:if>
	    	  </td>
	    	  <td>${vo.readNum}</td>
	    	  <td>${vo.good}</td>
	    	</tr>
	    	<c:set var="curScrStartNo" value="${curScrStartNo-1}"/>
	    </c:forEach>
	    <tr><td colspan="6" class="m-0 p-0"></td></tr>
	  </table>
  </form>
</div>

<!-- 블록 페이지 시작 -->
<div class="text-center">
  <ul class="pagination justify-content-center">
    <c:if test="${pageVo.pag > 1}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/admin/adminboardList?search=${search}&searchString=${searchString}&pag=1">첫페이지</a></li>
    </c:if>
    <c:if test="${pageVo.curBlock > 0}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/admin/adminboardList?search=${search}&searchString=${searchString}&pag=${(pageVo.curBlock-1)*pageVo.blockSize + 1}">이전블록</a></li>
    </c:if>
    <c:forEach var="i" begin="${(pageVo.curBlock)*pageVo.blockSize + 1}" end="${(pageVo.curBlock)*pageVo.blockSize + pageVo.blockSize}" varStatus="st">
      <c:if test="${i <= pageVo.totPage && i == pageVo.pag}">
    		<li class="page-item active"><a class="page-link bg-secondary border-secondary" href="${ctp}/admin/adminboardList?search=${search}&searchString=${searchString}&pag=${i}">${i}</a></li>
    	</c:if>
      <c:if test="${i <= pageVo.totPage && i != pageVo.pag}">
    		<li class="page-item"><a class="page-link text-secondary" href="${ctp}/admin/adminboardList?search=${search}&searchString=${searchString}&pag=${i}">${i}</a></li>
    	</c:if>
    </c:forEach>
    <c:if test="${pageVo.curBlock < pageVo.lastBlock}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/admin/adminboardList?search=${search}&searchString=${searchString}&pag=${(pageVo.curBlock+1)*pageVo.blockSize + 1}">다음블록</a></li>
    </c:if>
    <c:if test="${pageVo.pag < pageVo.totPage}">
      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/admin/adminboardList?search=${search}&searchString=${searchString}&pag=${pageVo.totPage}">마지막페이지</a></li>
    </c:if>
  </ul>
</div>
<!-- 블록 페이지 끝 -->
<br/>
<!-- 검색기 처리 시작  -->
<div class="container text-center">
  <form name="searchForm">
    <b>검색 : </b>
    <select name="search">
      <option value="title">글제목</option>
      <option value="nickName">글쓴이</option>
      <option value="content">글내용</option>
    </select>
    <input type="text" name="searchString" id="searchString"/>
    <input type="button" value="검색" onclick="searchCheck()" class="btn btn-secondary btn-sm"/>
    <input type="button" value="전체검색" onclick="location.href='${ctp}/admin/adminboardList';" class="btn btn-secondary btn-sm"/>
    <input type="hidden" name="pag" value="${pag}"/>
    <input type="hidden" name="pageSize" value="${pageSize}"/>
  </form>
</div>
<!-- 검색기 처리 끝  -->
<p><br/></p>
</body>
</html>