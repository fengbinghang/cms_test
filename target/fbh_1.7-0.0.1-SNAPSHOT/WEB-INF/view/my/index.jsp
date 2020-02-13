<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" href="resource/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("a").click(function(){
			var url=$(this).attr("data");
			$("#center").load(url);
			//把action样式去除
			$("a").attr("class","list-group-item");
			$(this).attr("class","list-group-item active");
		})
	})
</script>
</head>
<body>
	<div class="container">
		<!-- 头 -->		
		<div class="row">
			<div class="col-md-12" style="height: 130px;background-color: #3579c6;line-height: 130px;">
				<img src="resource/img/36.jpg" style="width: 192px;height:108px;margin-top: -45px;">
				<font style="font-size:60px;color:white;">CMS个人中心</font>
			</div>
		</div>
		<!-- 下面 -->
		<div class="row" style="margin-top:10px;">
			<!-- left.jsp -->
			<div class="col-md-2" style="height: 200px;">
				<nav class="nav flex-column">
					<a class="list-group-item active"  href="javascript:void(0)" data="article/selectsMy.do">查看文章</a>
					<a class="list-group-item"  href="javascript:void(0)" data="article/toAdd.do">发布文章</a>
					<a class="list-group-item"  href="javascript:void(0)" data="">资料管理</a>
					<a class="list-group-item"  href="javascript:void(0)" data="">其它管理</a>					
				</nav>
			</div>
			<!-- 中间区域显示内容 -->
			<div class="col-md-10" style="height: 600px;" id="center">
				<!-- 引入富文本编辑器 -->
				<div style="display: none;">
					<jsp:include page="/resource/kindeditor/jsp/demo.jsp"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>