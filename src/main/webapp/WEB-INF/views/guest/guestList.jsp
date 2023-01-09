<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("newLine", "\n"); %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>guestList.jsp</title>
  
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
  <style>
		body{margin-top:20px;
			background:#eee;
			color:#284866!important;
		}

		.white-bg {
			background-color: #ffffff;
		}
		.page-heading {
			border-top: 0;
			padding: 0 10px 20px 10px;
		}

		.forum-post-container .media {
			margin: 10px 10px 10px 10px;
			padding: 20px 10px 20px 10px;
			border-bottom: 1px solid #f1f1f1;
		}
		.forum-avatar {
			float: left;
			margin-right: 20px;
			text-align: center;
			width: 110px;
		}
		.forum-avatar .img-circle {
			height: 48px;
			width: 48px;
		}
		.author-info {
			color: #676a6c;
			font-size: 11px;
			margin-top: 5px;
			text-align: center;
		}
		.forum-post-info {
			padding: 9px 12px 6px 12px;
			background: #f9f9f9;
			border: 1px solid #f1f1f1;
		}
		.media-body > .media {
			background: #f9f9f9;
			border-radius: 3px;
			border: 1px solid #f1f1f1;
		}
		.forum-post-container .media-body .photos {
			margin: 10px 0;
		}
		.forum-photo {
			max-width: 140px;
			border-radius: 3px;
		}
		.media-body > .media .forum-avatar {
			width: 70px;
			margin-right: 10px;
		}
		.media-body > .media .forum-avatar .img-circle {
			height: 38px;
			width: 38px;
		}
		.mid-icon {
			font-size: 66px;
		}
		.forum-item {
			margin: 10px 0;
			padding: 10px 0 20px;
			border-bottom: 1px solid #f1f1f1;
		}
		.views-number {
			font-size: 18px;
			line-height: 18px;
			font-weight: 400;
		}
		.forum-container,
		.forum-post-container {
			padding: 10px !important;
		}
		.forum-item small {
			color: #999;
		}
		.forum-item .forum-sub-title {
			color: #999;
			margin-left: 50px;
		}
		.forum-title {
			margin: 15px 0 15px 0;
		}
		.forum-info {
			text-align: center;
		}
		.forum-desc {
			color: #999;
		}
		.forum-icon {
			float: left;
			width: 30px;
			margin-right: 20px;
			text-align: center;
		}
		a.forum-item-title {
			color: inherit;
			display: block;
			font-size: 18px;
			font-weight: 600;
		}
		a.forum-item-title:hover {
			color: inherit;
		}
		.forum-icon .fa {
			font-size: 30px;
			margin-top: 8px;
			color: #9b9b9b;
		}
		.forum-item.active .fa {
			color: #1ab394;
		}
		.forum-item.active a.forum-item-title {
			color: #1ab394;
		}
		@media (max-width: 992px) {
			.forum-info {
				margin: 15px 0 10px 0;
				/* Comment this is you want to show forum info in small devices */
				display: none;
			}
			.forum-desc {
				float: none !important;
			}
		}





		.ibox {
			clear: both;
			margin-bottom: 25px;
			margin-top: 0;
			padding: 0;
		}
		.ibox.collapsed .ibox-content {
			display: none;
		}
		.ibox.collapsed .fa.fa-chevron-up:before {
			content: "\f078";
		}
		.ibox.collapsed .fa.fa-chevron-down:before {
			content: "\f077";
		}
		.ibox:after,
		.ibox:before {
			display: table;
		}
		.ibox-title {
			-moz-border-bottom-colors: none;
			-moz-border-left-colors: none;
			-moz-border-right-colors: none;
			-moz-border-top-colors: none;
			background-color: #ffffff;
			border-color: #e7eaec;
			border-image: none;
			border-style: solid solid none;
			border-width: 3px 0 0;
			color: inherit;
			margin-bottom: 0;
			padding: 14px 15px 7px;
			min-height: 48px;
		}
		.ibox-content {
			background-color: #ffffff;
			color: inherit;
			padding: 15px 20px 20px 20px;
			border-color: #e7eaec;
			border-image: none;
			border-style: solid solid none;
			border-width: 1px 0;
		}
		.ibox-footer {
			color: inherit;
			border-top: 1px solid #e7eaec;
			font-size: 90%;
			background: #ffffff;
			padding: 10px 15px;
		}

		.message-input {
			height: 90px !important;
		}
		.form-control, .single-line {
			background-color: #FFFFFF;
			background-image: none;
			border: 1px solid #e5e6e7;
			border-radius: 1px;
			color: inherit;
			display: block;
			padding: 6px 12px;
			transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
			width: 100%;
			font-size: 14px;
		}
		.text-navy {
			color: #1ab394;
		}
		.mid-icon {
			font-size: 66px !important;
		}
		.m-b-sm {
			margin-bottom: 10px;
		}
	</style>
  <script>
    'use strict';
    function pageCheck() {
    	let pageSize = document.getElementById("pageSize").value;
    	location.href = "${ctp}/guest/guestList.?pageSize="+pageSize+"&pag=1";
    }
    
    function delCheck(idx) {
    	let ans = confirm("정말로 삭제하시겠습니까?");
    	if(ans) location.href="${ctp}/guest/guestDelete?idx="+idx;
    }
    
  	function fCheck() {
  	  	let searchString = document.getElementById("searchString").value;
  	  	
  	  	if(searchString.trim()=="") {
  	  		alert("검색어를 입력하세요!");
  	  		document.getElementById("searchString").focus();
  	  	}
  	  	else{
  	  		mysearchform.submit();
  	  	}
  		}
  </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
<div class="container">
	  <h2 class="text-center">방 명 록 리 스 트</h2>
	  <br/>
	  <select name="pageSize" id="pageSize" onchange="pageCheck()" style="width:10%; margin-bottom: 5px;">
	    <option value="5"  ${pageSize==5  ? 'selected' : ''}>5건</option>
	    <option value="10" ${pageSize==10 ? 'selected' : ''}>10건</option>
	    <option value="15" ${pageSize==15 ? 'selected' : ''}>15건</option>
	    <option value="20" ${pageSize==20 ? 'selected' : ''}>20건</option>
	  </select>
	  <form name="mysearchform" method="post" action="${ctp}/guest/guestSearch">
	  	<select name="search" style="width:12%;" style="width:10%; margin-bottom: 5px;">
	      <option value="name">성 명</option>
	      <option value="email">이메일</option>
	    </select>
	  	<input type="text" name="searchString" id="searchString" style="width:10%; margin-bottom: 5px;"/>
	  	<input type="button" value="검색" onclick="fCheck()" />
	  	<input type="button" value="전체검색" onclick="location.href='${ctp}/guest/guestList';" />
	  </form>
	  <a href="${ctp}/guest/guestInput" class="btn btn-sm btn-secondary" style="float: right;">글쓰기</a>
		<div class="row">
			<div class="col-lg-12">
				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="ibox-content forum-container">
						<c:set var="curScrStartNo" value="${pageVo.curScrStartNo}"/>
		  				<c:forEach var="vo" items="${vos}" varStatus="st">		
								<div class="forum-item">
									<div class="row">
										<div class="col-md-1 forum-info">
											<div>
												<small>방문번호</small>
			                	<span class="views-number">${curScrStartNo}</span>
											</div>
										</div>
										<div class="col-md-5">
											<div class="forum-icon">
												<i class="fa fa-bookmark"></i>
											</div>
											<font size="4pt">${vo.name}</font>
											<div class="forum-sub-title">${fn:replace(vo.content, newLine, '<br/>')}</div>
										</div>
										<div class="col-md-3 forum-info">
											<div>
												<small>방문일자</small>
											</div>
			                  <span class="views-number">${fn:substring(vo.visitDate,0,19)}</span>
											<div>
												<small>이메일</small>
											</div>
			                  <span class="views-number">
				                  <c:if test="${empty vo.email || fn:length(vo.email)<=4 || fn:indexOf(vo.email,'@')==-1 || fn:indexOf(vo.email,'.')==-1}">- 없음 -</c:if>
					   						 	<c:if test="${!empty vo.email && fn:length(vo.email)>4 && fn:indexOf(vo.email,'@')!=-1 && fn:indexOf(vo.email,'.')!=-1}">${vo.email}</c:if>
			                  </span>
										</div>
										<div class="col-md-3 forum-info">
											<div>
												<small>방문IP</small>
											</div>
			                  <span class="views-number">${vo.hostIp}</span>
			                <div>
				                <c:if test="${sLevel == 0}">
													<a href="${ctp}/guest/guestUpdate?idx=${vo.idx}" class="btn btn-sm btn-warning">방명록 수정</a>
													<a href="javascript:delCheck(${vo.idx})" class="btn btn-sm btn-danger">삭제</a>
												</c:if>
											</div>
										</div>
									</div>
								</div>
						  <c:set var="curScrStartNo" value="${curScrStartNo - 1}"/>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	
	<!-- 첫페이지 / 이전블록 / 1(4) 2(5) 3(6) / 다음블록 / 마지막페이지 -->
  <div class="text-center">
    <ul class="pagination justify-content-center">
	    <c:if test="${pageVo.pag > 1}">
	      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/guest/guestList?pag=1&pageSize=${pageVo.pageSize}">첫페이지</a></li>
	    </c:if>
	    <c:if test="${pageVo.curBlock > 0}">
	      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/guest/guestList?pag=${(pageVo.curBlock-1)*pageVo.blockSize + 1}&pageSize=${pageVo.pageSize}">이전블록</a></li>
	    </c:if>
	    <c:forEach var="i" begin="${(pageVo.curBlock)*pageVo.blockSize + 1}" end="${(pageVo.curBlock)*pageVo.blockSize + pageVo.blockSize}" varStatus="st">
	      <c:if test="${i <= pageVo.totPage && i == pag}">
	    		<li class="page-item active"><a class="page-link bg-secondary border-secondary" href="${ctp}/guest/guestList?pag=${i}&pageSize=${pageVo.pageSize}">${i}</a></li>
	    	</c:if>
	      <c:if test="${i <= pageVo.totPage && i != pag}">
	    		<li class="page-item"><a class="page-link text-secondary" href="${ctp}/guest/guestList?pag=${i}&pageSize=${pageVo.pageSize}">${i}</a></li>
	    	</c:if>
	    </c:forEach>
	    <c:if test="${pageVo.curBlock < pageVo.lastBlock}">
	      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/guest/guestList?pag=${(pageVo.curBlock+1)*pageVo.blockSize + 1}&pageSize=${pageVo.pageSize}">다음블록</a></li>
	    </c:if>
	    <c:if test="${pag < pageVo.totPage}">
	      <li class="page-item"><a class="page-link text-secondary" href="${ctp}/guest/guestList?pag=${pageVo.totPage}&pageSize=${pageVo.pageSize}">마지막페이지</a></li>
	    </c:if>
    </ul>
  </div>
</div>

<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script type="text/javascript">
</script>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>