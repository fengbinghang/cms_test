<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resource/css/bootstrap.css">
<script type="text/javascript" src="resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function fenye(pageNum) {
		$("#center").load("article/selectsMy.do?pageNum=" + pageNum);
	}
</script>
</head>
<body>
	<form></form>
	<div style="margin-top: 10px;">
		<ul class="list-unstyled">
			<c:forEach items="${page.list}" var="a">
				<li class="media"><img src="/pic/${a.picture}" alt="" class="mr-3" width="192px;" height="108px;">
					<div class="media-body text-center" style="padding-top: 20px;">
						<h5 class="mt-0 mb-1"><a href="javascript:void(0)" onclick="look(${a.id})" data-toggle="modal" data-target="#exampleModal">${a.title}</a></h5>
						${a.u.username}&nbsp;&nbsp;&nbsp;
						<fmt:formatDate value="${a.created}" pattern="yyyy-MM-dd HH:mm:ss" />
					</div></li>
				<hr>
			</c:forEach>
		</ul>
	</div>
	<div style="margin-left: 350px; margin-top: 20px;">
		<jsp:include page="/WEB-INF/view/public/page.jsp" />
	</div>
</body>
</html>