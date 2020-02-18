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
	function addSave(text,id,uid){
		$.post("save/addSave.do",{"text":text,"url":id,"uid":uid},function(flag){
			if(flag){
				alert("收藏成功");
			}else{
				alert("收藏失败");
			}
		},"json")
	}
</script>
</head>
<body style="background-image: url('resource/img/13.jpg');">
	<div class="container" style="float:left;background-color: white;width:950px;padding-top: 10px;margin-top: 20px;margin-left: 500px;">
		<div>
			<h1 style="margin: 10px 0px;"><span class="border border-warning" style="padding: 5px 10px;">${art.title}</span></h1>
			<img alt="..." src="/pic/${art.picture}" style="width:192px;height:108px;">
			<hr style="background-color: gray;"/>
			发表人：${art.u.username}<br/><br/>
			<c:if test="${art.ct.ordinal==0}">
				${art.content}<br/><br/>
			</c:if>
			<c:if test="${art.ct.ordinal==1}">
				<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
				  <ol class="carousel-indicators">
				    <c:forEach items="${art.cs}" var="c" varStatus="count">
					    <li data-target="#carouselExampleCaptions" data-slide-to="${count.count-1}"  class="${count.count==1?'active':''}"></li>				    	
				    </c:forEach>
				  </ol>
				  <div class="carousel-inner"> 
					<c:forEach items="${art.cs}" var="c" varStatus="count">
						 <div class="carousel-item ${count.count==1?'active':''}">
					      <img src="/pic/${c.name}" class="d-block w-100" alt="...">
					      <div class="carousel-caption d-none d-md-block">
					        <p style="color:yellow;">${c.content}</p>
					      </div>
					    </div>
					</c:forEach>
				  </div>
				  <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="sr-only">Previous</span>
				  </a>
				  <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="sr-only">Next</span>
				  </a>
				</div>
			</c:if>
			<br/>
			<p style="text-align: right;padding-right: 50px;"><fmt:formatDate value="${art.created}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
			<hr style="background-color: gray;"/>
			<button class="btn btn-outline-dark" onclick="location='index.do'">返回首页</button>
			<c:if test="${username!=null}">
				<button class="btn btn-outline-dark" onclick="addSave('${art.title}',${art.id},${id})">收藏文章</button>
			</c:if>
			<br/>
			<br/>
			<c:if test="${username==null}">
				请登录后再发布评论
			</c:if>
			<c:if test="${username!=null}">
				<textarea placeholder="评论内容" name="content"></textarea>
				<button class="btn btn-primary" onclick="fb(${id},${art.id})">发布</button>
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
		<!-- 下 -->
		<div class="row" style="margin-top: 30px;">
			<div class="col-md-12">
				<p class="text-center">
					友情链接：
					<c:forEach items="${fs.list}" var="l">
						<a href="${l.url}" target="_blank">${l.text}</a>&nbsp;&nbsp;&nbsp;
					</c:forEach>
				</p>
			</div>
		</div>
	</div>
	<div class="card" style="width: 18rem;float:left;margin-top: 60px;margin-left: 60px;">
		 <div class="card-header">
		 	  热门推荐
			</div>
			<ul class="list-group list-group-flush">
			  <c:forEach items="${hots.list}" var="a">
			  	<li class="list-group-item"><a href="getOne.do?id=${a.id}">${a.title}</a></li>
			  </c:forEach>
		 </ul>
	</div>
</body>
</html>