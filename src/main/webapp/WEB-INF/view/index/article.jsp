<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resource/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function fenye(pageNum){
		var id="${art.u.id}";
		location="getOne.do?id="+id+"&pageNum="+pageNum;
	}
	function fb(uid,aid){
		var content=$("[name=content]").val();
		$.post("insertComment.do",{"content":content,"u.id":uid,"a.id":aid},function(flag){
			if(flag){
				alert("发布成功");
				location="getOne.do?id="+aid;
			}else{
				alert("发布失败");
			}
		},"json")
	}
</script>
</head>
<body style="background-image: url('resource/img/13.jpg');">
	<center>
		<div class="container" style="background-color: white;width:800px;padding-top: 10px;margin-top: 20px;">
			<h1 style="margin: 10px 0px;"><span class="border border-warning" style="padding: 5px 10px;">${art.title}</span></h1>
			<hr style="background-color: gray;"/>
			${art.u.username}<br>
			${art.content}<br/><br/>
			<img alt="..." src="/pic/${art.picture}" style="width:768px;height:432px;">
			<br/>
			<p style="text-align: right;padding-right: 50px;"><fmt:formatDate value="${art.created}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
			<hr style="background-color: gray;"/>
			<button class="btn btn-outline-dark" onclick="location='index.do'">返回首页</button>
			<br/>
			<br/>
			<c:if test="${username==null}">
				请登录后再发布评论
			</c:if>
			<c:if test="${username!=null}">
				<textarea placeholder="评论内容" name="content"></textarea>
				<button class="btn btn-primary" onclick="fb(${art.u.id},${art.id})">发布</button>
			</c:if>
			<ul class="list-group">
			  <c:forEach items="${page.list}" var="com">
				<li class="list-group-item">
					${com.u.username}&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${com.created}" pattern="yyyy-MM-dd HH:mm:ss"/><br/>
					${com.content}
				</li>
			</c:forEach>
			</ul>
			<jsp:include page="/WEB-INF/view/public/page.jsp"/>
		</div>
	</center>
</body>
</html>