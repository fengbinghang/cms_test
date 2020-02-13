<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		var loginMsg="${loginMsg}";
		if(loginMsg!=""){
			if(loginMsg.indexOf("用户登录成功")!=-1){
				location="../index.do";
			}else if(loginMsg.indexOf("管理员登录成功")!=-1){
				location="../admin.do";
			}
			$("#alert").html(loginMsg);
			$("#alert").show();
		}
		$.validator.setDefaults({
			elementPlacement:function(error,ele){
				ele.parent().append(error);
			}
		})
		$("#myForm").validate({
			rules:{
				username:{required:true},
				password:{required:true},
			},
			messages:{				
				username:{required:"<font color='red'>用户名不能为空</font>"},
				password:{required:"<font color='red'>密码不能为空</font>"},
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
				<h1>欢迎回来</h1>
				<h2 style="color:red;">${error}</h2>
				<form:form action="login.do" method="post" modelAttribute="u" id="myForm">
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
							id="password" name="password">
					</div>
					
					<div class="row">
						<div class="col-4">
							<input type="submit" class="btn btn-primary" value="登录">
						</div>
						<div class="col-8">
							<small id="emailHelp" class="form-text text-muted">没有帐号，去<a
								href="../user/toRegister.do">注册</a></small>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>