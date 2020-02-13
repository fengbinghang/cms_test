<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,inital-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="../resource/css/bootstrap.css">
<script type="text/javascript" src="../resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="../resource/js/jquery.validate.js"></script>
<script type="text/javascript" src="../resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		var registerMsg="${registerMsg}";
		if(registerMsg.indexOf("注册成功,请登录")!=-1){
			alert(registerMsg);
			location="toLogin.do";
		}else if(registerMsg.indexOf("注册失败")!=-1){			
			$("#alert").html(registerMsg);
		}
		$("[name='ordinal'][value='0']").prop("checked",true);
		$.validator.setDefaults({
			elementPlacement:function(error,ele){
				$("#alert").html(error);
			}
		})
		$("#myForm").validate({
			rules:{
				username:{required:true,rangelength:[2,4],remote:{
					url:"../user/isUnique.do",
					type:"post",
					dataType:"json",
					data:{"username":function(){return $("[name=username]").val();}}
				}},
				password:{required:true,minlength:6},
				pwd2:{required:true,equalTo:"#pwd"},
			},
			messages:{				
				username:{required:"<font color='red'>用户名不能为空</font>",rangelength:"<font color='red'>长度在2-4之间</font>",remote:"<font color='red'>用户名已存在</font>"},
				password:{required:"<font color='red'>密码不能为空</font>",minlength:"<font color='red'>密码>=6位</font>"},
				pwd2:{required:"<font color='red'>重复密码不能为空</font>",equalTo:"<font color='red'>与密码输入不一致</font>"},
			},
			submitHandler:function(form){
				form.submit();
			}
		})
	})
</script>
</head>
<body>
	<div class="container-fulid" style="margin-top: 6px;">
		<div class="row offset-4" style="margin-top: 180px;">
			<div class="col-5">
				<h1>欢迎注册</h1>
				<form:form action="register.do" method="post" modelAttribute="u" id="myForm">
					<div class="alert alert-success" id="alert" role="alert" style="display: none">
						<form:errors path="username" cssStyle="color:red"/>
						<form:errors path="password" cssStyle="color:red"/>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="username"
							name="username" placeholder="请输入用户名">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="请输入密码"
							id="pwd" name="password">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="请输入重复密码"
							name="pwd2">
					</div>
					<div class="form-group">
						<c:forEach items="<%=com.fbh.enums.Gender.values() %>" var="g">
							<input type="radio" name="ordinal" value="${g.ordinal}">${g.displayName}
						</c:forEach>
					</div>
					<div class="row">
						<div class="col-4">
							<input type="submit" class="btn btn-primary" value="注册">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" class="btn btn-primary" value="重置">
						</div>
						<div class="col-8">
							<small id="emailHelp" class="form-text text-muted">已有帐号，去<a
								href="../user/toLogin.do">登录</a></small>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>