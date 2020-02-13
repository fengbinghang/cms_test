<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resource/css/bootstrap.css">
<script type="text/javascript" src="../resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="../resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function add(){
		var str=$("#myForm").serialize();
		$.post("insertLink.do",str,function(flag){
			if(flag){
				alert("添加成功");
				location="../admin.do";
			}else{
				alert("添加失败");
			}
		},"json")
	}
</script>
</head>
<body>
	<div style="margin-bottom: 10px;">
		<form id="myForm">
			<input class="form-control" type="text" placeholder="友情链接文本" name="text">
			<input class="form-control" type="text" placeholder="友情链接地址" name="url">
			<input  class="btn btn-danger" type="button" value="添加" onclick="add()">
		</form>
	</div>
</body>
</html>