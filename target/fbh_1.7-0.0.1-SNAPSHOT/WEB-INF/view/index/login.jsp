<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resource/css/index3.css">
<script type="text/javascript" src="/resource/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
<script type="text/javascript">
	$(function(){
		var loginMsg="${loginMsg}";
		if(loginMsg!=""){
			if(loginMsg.indexOf("登录成功")!=-1){
				alert(loginMsg);
				location="list.do";
			}
			alert(loginMsg);
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
	<form:form action="user/login.do" method="post" modelAttribute="u" id="myForm">
		<table>
			<tr>
				<th colspan="2">欢迎回来</th>
			</tr>
			<tr>
				<td>用户名</td>
				<td>
					<form:input path="username"/>
					<form:errors path="username" cssStyle="color:red"/>
				</td>
			</tr>
			<tr>
				<td>密码</td>
				<td>
					<form:input path="password"/>
					<form:errors path="password" cssStyle="color:red"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<form:button>登录</form:button>
					<input type="button" onclick="location='toRegister.do'" value="注册">
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>