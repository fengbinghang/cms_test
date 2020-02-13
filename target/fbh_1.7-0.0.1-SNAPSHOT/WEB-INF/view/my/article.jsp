<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="margin-top:10px;">
		<ul class="list-unstyled">
			<c:forEach items="${page.list}" var="a">
				<li class="media">
					<img src="/pic/${a.picture}" alt="" class="mr-3" width="192px;" height="108px;">
					<div class="media-body">
						<h5 class="mt-0 mb-1">${a.title}</h5>
						<fmt:formatDate value="${a.created}" pattern="yyyy-MM-dd"/>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>