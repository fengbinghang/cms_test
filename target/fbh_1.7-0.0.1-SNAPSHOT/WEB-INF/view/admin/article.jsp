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
<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		var status="${a.status}";
		$("[name=status] option").each(function(){
			if(this.value==status){
				this.selected=true;
			}
		})
	})
	function check(){
		var str=$("#myForm").serialize();
		var url="article/selects.do?"+str;
		$("#content-wrapper").load(url);
	}
	function fenye(pageNum){
		var url="article/selects.do?pageNum="+pageNum+"&"+$("#myForm").serialize();
		$("#content-wrapper").load(url);
	}
	function look(id){
		$.post("article/selectOne.do",{id:id},function(obj){
			$("#exampleModalLabel").html(obj.title);
			$(".modal-body").html(obj.content);
			$("[name=btn]").val(obj.id);
		},"json")
	}
	function changeStatus(id,status){
		$.post("article/updateStatus.do",{"id":id,"status":status},function(flag){
			if(flag){
				alert("审核成功");
				var pageNum="${page.pageNum}";
				var url="article/selects.do?pageNum="+pageNum;
				$("#content-wrapper").load(url);
			}else{
				alert("审核失败");
			}
		},"json")
	}
</script>
</head>
<body>
	<div style="padding: 0 10px 0 10px;">
		<div style="margin-bottom: 10px;">
			<form id="myForm">
				文章标题：<input type="text" name="title" value="${a.title}">
				状态：<select name="status">
						<option value="">请选择状态</option>
						<option value="0">待审</option>
						<option value="1">审核通过</option>
						<option value="-1">审核未通过</option>
					</select>
				<button type="button" class="btn btn-danger" onclick="check()">搜索</button>
			</form>
		</div>
		<table class="table table-bordered">
			<tr>
				<td>编号</td>
				<td>标题</td>
				<td>标题图片</td>
				<td>作者</td>
				<td>点击量</td>
				<td>是否热门</td>
				<td>是否审核通过</td>
				<td>创建时间</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${page.list}" var="a" varStatus="i">
				<tr>
					<td>${a.id}</td>
					<td>${a.title}</td>
					<td>${a.picture}</td>
					<td>${a.u.username}</td>
					<td>${a.hits}</td>
					<td>${a.hot}</td>
					<td>
						${a.status==0?'待审':a.status==1?'审核通过':'审核未通过'}
						<%-- <c:if test="${a.status==0}">
							<select name="status" onchange="changeStatus(${a.id },this)">
								<option value="0">---请审核---</option>
								<option value="1">审核通过</option>
								<option value="-1">审核未通过</option>
							</select>
						</c:if>
						<c:if test="${a.status!=0}">
							${a.status==1?'审核通过':'审核未通过'}
						</c:if> --%>
					</td>
					<td><fmt:formatDate value="${a.created}" pattern="yyyy-MM-dd"/></td>
					<td>
						<button type="button" onclick="look(${a.id})" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
							详情
						</button>
					</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 引入分页页面 -->
		<jsp:include page="/WEB-INF/view/public/page.jsp"/>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel"></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	...
	      </div>
	      <div class="modal-footer">
	        <button type="button" name="btn" class="btn btn-secondary" data-dismiss="modal" onclick="changeStatus(this.value,1)">通过</button>
	        <button type="button" name="btn" class="btn btn-primary" data-dismiss="modal" onclick="changeStatus(this.value,-1)">不通过</button>
	      </div>
	    </div>
	  </div>
	</div>
</body>
</html>