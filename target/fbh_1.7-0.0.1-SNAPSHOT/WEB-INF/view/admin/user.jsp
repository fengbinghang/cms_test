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
	function check(){
		var name=$("[name=username]").val();
		var url="user/getUserList.do?username="+name;
		$("#content-wrapper").load(url);
	}
	function fenye(pageNum){
		var url="user/getUserList.do?pageNum="+pageNum+"&"+$("#myForm").serialize();
		$("#content-wrapper").load(url);
	}
	function locked(id,num){
		$.post("user/updateLocked.do",{"id":id,"locked":num==0?1:0},function(flag){
			var pageNum="${page.pageNum}";
			var url="user/getUserList.do?pageNum="+pageNum;
			$("#content-wrapper").load(url);
		},"json")
	}
</script>
</head>
<body>
	<div style="padding: 0 10px 0 10px;">
		<div style="margin-bottom: 10px;">
			<form id="myForm">
				用户姓名：<input type="text" name="username" value="${username}">
				<button type="button" class="btn btn-danger" onclick="check()">搜索</button>
			</form>
		</div>
		<table class="table table-bordered">
			<tr>
				<td>序号</td>
				<td>用户名</td>
				<td>昵称</td>
				<td>性别</td>
				<td>生日</td>
				<td>注册日期</td>
				<td>修改日期</td>
				<td>状态</td>
			</tr>
			<c:forEach items="${page.list}" var="u" varStatus="i">
				<tr>
					<td>${i.index+page.startRow}</td>
					<td>${u.username}</td>
					<td>${u.nickname}</td>
					<td>${u.gender.displayName}</td>
					<td><fmt:formatDate value="${u.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${u.created}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${u.updated}</td>
					<td>
						<c:if test="${u.locked==0}">
							<button type="button" class="btn btn-danger" onclick="locked(${u.id},${u.locked})">改为禁用</button>
						</c:if>
						<c:if test="${u.locked==1}">
							<button type="button" class="btn btn-success" onclick="locked(${u.id},${u.locked})">改为正常</button>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 引入分页页面 -->
		<jsp:include page="/WEB-INF/view/public/page.jsp"/>
	</div>
</body>
</html>