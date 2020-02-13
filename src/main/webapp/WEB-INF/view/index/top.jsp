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
	<nav class="nav">
		<c:if test="${role==1}">
			<a class="nav-link" href="admin.do">后台管理</a>
		</c:if>
		<c:if test="${username==null}">
			<a class="nav-link" href="user/toLogin.do">登录</a>
			<a class="nav-link" href="user/toRegister.do">注册</a>
		</c:if>
		<c:if test="${username!=null}">
			<a class="nav-link" href="my.do">个人中心</a>
			<a class="nav-link" href="user/out.do">注销</a>
		</c:if>
	</nav>
</body>
</html>