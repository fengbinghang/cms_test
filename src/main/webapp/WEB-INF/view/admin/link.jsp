<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	function fenye(pageNum){
		var url="link/getLinks.do?pageNum="+pageNum;
		$("#content-wrapper").load(url);
	}
</script>
</head>
<body>
	<div style="padding: 0 10px 0 10px;">
		<div>
			<button class="btn btn-danger" onclick="location='link/toAdd.do'">添加友情链接</button>
		</div>
		<table class="table table-bordered">
			<tr>
				<td>序号</td>
				<td>友情链接文本</td>
				<td>友情连接地址</td>
				<td>添加时间</td>
			</tr>
			<c:forEach items="${page.list}" var="l" varStatus="i">
				<tr>
					<td>${i.index+page.startRow}</td>
					<td>${l.text}</td>
					<td>${l.url}</td>
					<td><fmt:formatDate value="${l.created}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>
		</table>
		<!-- 引入分页页面 -->
		<jsp:include page="/WEB-INF/view/public/page.jsp"/>
	</div>
</body>
</html>