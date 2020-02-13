<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/index3.css">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript">
	$(function(){
		var registerMsg="${registerMsg}";
		if(registerMsg.indexOf("注册成功,请登录")!=-1){
			alert(registerMsg);
			location="user/toLogin.do";
		}else if(registerMsg.indexOf("注册失败")!=-1){			
			alert(registerMsg);
		}
		$("[name='gender.displayName'][value='男']").prop("checked",true);
		$.validator.setDefaults({
			elementPlacement:function(error,ele){
				ele.parent().append(error);
			}
		})
		$("#myForm").validate({
			rules:{
				username:{required:true,rangelength:[2,4],remote:{
					url:"user/isUnique.do",
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
	<form:form action="user/register.do" method="post" modelAttribute="u" id="myForm">
		<table>
			<tr>
				<th colspan="2">欢迎注册</th>
			</tr>
			<tr>
				<td>用户名</td>
				<td>
					<input type="text" name="username"/>
					<form:errors path="username" cssStyle="color:red"/>
				</td>
			</tr>
			<tr>
				<td>密码</td>
				<td>
					<input type="password" name="password" id="pwd"/>
					<form:errors path="password" cssStyle="color:red"/>
				</td>
			</tr>
			<tr>
				<td>重复密码</td>
				<td>
					<input type="password" name="pwd2">
				</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					<c:forEach items="<%=com.fbh.enums.Gender.values() %>" var="g">
						<input type="radio" name="gender.displayName" value="${g.displayName}">${g.displayName}
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<form:button>注册</form:button>
					<input type="reset" value="重置">
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>