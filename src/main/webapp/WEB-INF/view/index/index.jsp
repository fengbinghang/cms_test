<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resource/css/bootstrap.css" rel="stylesheet">
<link href="resource/css/cms.css" rel="stylesheet">
<link href="resource/css/index.css" rel="stylesheet">
<script type="text/javascript" src="resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function fenye(pageNum) {
		var key = $("[name=key]").val();
		if (key == '' || key == undefined || key == null) {
			var hot="${art.hot}";
			if(hot==0){			
				var ch="${art.ch.id}";
				var ca="${art.ca!=null?art.ca.id:''}";
				if(ca==''||ca==undefined||ca==null){
					location = "index.do?pageNum=" + pageNum+"&ch.id="+ch;									
				}else{
					location = "index.do?pageNum=" + pageNum+"&ch.id="+ch+"&ca.id="+ca;					
				}
			}else{
				location = "index.do?pageNum=" + pageNum+"&hot="+hot;				
			}
		} else {
			location = "search.do?pageNum=" + pageNum + "&key=" + key;
		}
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<!-- 上 -->
		<div class="row">
			<div class="col-md-12"
				style="height: 50px; background-color: #000000;">
				<div style="float: right; padding-right: 260px;">
					<jsp:include page="/WEB-INF/view/index/top.jsp" />
				</div>
			</div>
		</div>
		<!-- 中 -->
		<div class="row" style="margin-top: 10px;">
			<!-- 左（所有的栏目） -->
			<div class="col-md-2">
				<ul class="list-group text-center"
					style="width: 150px; float: right; margin-right: 50px;">
					<li class="list-group-item ${art.hot==1?'active':''}"><a
						href="index.do" class="channel">热门</a></li>
					<c:forEach items="${channels}" var="ch">
						<li class="list-group-item ${art.ch.id==ch.id?'active':''}"><a
							href="index.do?ch.id=${ch.id}" class="channel">${ch.name}</a></li>
					</c:forEach>
				</ul>
			</div>
			<!-- 中 -->
			<div class="col-md-7 split min_h_500" id="center">
				<!-- 搜索框 -->
				<form action="search.do" method="post">
					<div class="input-group mb-3">
						<input type="text" name="key" value="${key}" class="form-control"
							placeholder="请输入要搜索的内容" aria-label="Recipient's username"
							aria-describedby="button-addon2">
						<div class="input-group-append">
							<button class="btn btn-outline-secondary" id="button-addon2">搜索</button>
						</div>
					</div>
				</form>
				<c:if test="${art.hot==1}">
					<div id="carouselExampleIndicators" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carouselExampleIndicators" data-slide-to="0"
								class="active"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
						</ol>
						<div class="carousel-inner">
							<c:forEach items="${slides}" var="s" varStatus="i">
								<div class="carousel-item ${i.index==0?'active':''}">
									<img src="/pic/${s.picture}" class="d-block w-100" alt="..."
										style="height: 200px;">
									<div class="carousel-caption d-none d-md-block">
										<h5>${s.title}</h5>
									</div>
								</div>
							</c:forEach>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleIndicators"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next"
							href="#carouselExampleIndicators" role="button" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
					<br />
				</c:if>
				<c:if test="${!empty art.ch && art.ch.id!=0}">
					<div class="subchannel">
						<ul class="sub-list" style="width: 660px;">
							<li class="sub-item ${art.ca==null?'sub-selected':''}"><a
								href="index.do?ch.id=${art.ch.id}">全部</a></li>
							<c:forEach items="${categorys}" var="ca">
								<li class="sub-item ${art.ca.id==ca.id?'sub-selected':''}">
									<a href="index.do?ch.id=${art.ch.id}&ca.id=${ca.id}">${ca.name}</a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<ul class="list-unstyled" style="margin-top: 20px;">
					<c:forEach items="${page.list}" var="a">
						<li class="media"><img src="/pic/${a.picture}" alt=""
							class="mr-3" width="192px;" height="108px;">
							<div class="media-body text-center" style="padding-top: 20px;">
								<h5 class="mt-0 mb-1">
									<a href="getOne.do?id=${a.id}">${a.title}</a>
								</h5>
								<c:if test="${empty key}">
									${a.u.username}&nbsp;&nbsp;&nbsp;
								</c:if>
								<c:if test="${!empty key}">
									${a.username}&nbsp;&nbsp;&nbsp;
								</c:if>
								<fmt:formatDate value="${a.created}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</div></li>
						<hr>
					</c:forEach>
				</ul>
				<jsp:include page="/WEB-INF/view/public/page.jsp"></jsp:include>
			</div>
			<!-- 右 -->
			<div class="col-md-3 split min_h_500">
				<div class="card" style="width: 250px;">
					<div class="card-header">最新文章</div>
					<ul class="list-group list-group-flush">
						<c:forEach items="${newPage.list}" var="a">
							<li class="list-group-item"><a href="getOne.do?id=${a.id}">${a.title}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="card" style="width: 250px; margin-top: 50px;">
					<div class="card-header">最新图片</div>
					<ul class="list-group list-group-flush">
						<c:forEach items="${images}" var="c">
							<li class="list-group-item"><img src="/pic/${c.name}"
								style="width: 100px; height: 50px;"
								onclick="location='getOne.do?id=${c.id}'"></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<!-- 下 -->
		<div class="row" style="margin-top: 30px;">
			<div class="col-md-12">
				<p class="text-center">
					友情链接：
					<c:forEach items="${ls.list}" var="l">
						<a href="${l.url}" target="_blank">${l.text}</a>&nbsp;&nbsp;&nbsp;
					</c:forEach>
				</p>
			</div>
		</div>
	</div>
</body>
</html>