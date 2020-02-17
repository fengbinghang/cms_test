<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${page.pages!=0}">
		<nav aria-label="Page navigation example">
			<ul class="pagination">
				<li class="page-item">
					<a class="page-link" href="javascript:fenye(${page.prePage==0?1:page.prePage})" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				<c:forEach begin="${page.pageNum/10*10}" end="${page.pages}" step="1" var="pa" varStatus="count">
					<c:if test="${count.count<=9}">
						<c:if test="${page.pageNum==pa}">						
							<li class="page-item" style="background-color: gray;"><a class="page-link" href="javascript:fenye(${pa})">${pa}</a></li>					
						</c:if>
						<c:if test="${page.pageNum!=pa}">						
							<li class="page-item"><a class="page-link" href="javascript:fenye(${pa})">${pa}</a></li>					
						</c:if>
					</c:if>
				</c:forEach>
				<li class="page-item">
					<a class="page-link" href="javascript:fenye(${page.nextPage==0?page.pages:page.nextPage})" aria-label="Next">
				   		<span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</ul>
		</nav>
	</c:if>
	<c:if test="${page.pages==0}">
		<h3>很抱歉，没有相关内容的展示</h3>
	</c:if>
</body>
</html>