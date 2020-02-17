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
		$("#center").load("save/getSaves.do?id=${id}&pageNum=" + pageNum);
	}
	function del(id){
		$.post("save/deleteSave.do",{"id":id},function(flag){
			if(flag){
				alert("删除成功");
				$("#center").load("save/getSaves.do?id=${id}");
			}else{
				alert("删除失败");
			}
		},"json");
	}
</script>
</head>
<body>
	<div style="margin-top: 10px;">
		<h1>我的收藏夹</h1>
		<hr/>
		<ul class="list-unstyled">
			<c:forEach items="${page.list}" var="s">
				<li style="border-bottom: solid;border-bottom-color: #c1c1c1;">
					<a href="${s.url}">${s.text}</a><br/>
					时间：<fmt:formatDate value="${s.created}" pattern="yyyy-MM-dd HH:mm:ss"/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:del(${s.id})">删除</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div style="margin-left: 350px; margin-top: 20px;">
		<jsp:include page="/WEB-INF/view/public/page.jsp" />
	</div>
</body>
</html>