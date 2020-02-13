<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
	<link rel="stylesheet" href="resource/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="resource/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="resource/kindeditor/plugins/code/prettify.js"></script>
	<script charset="utf-8" src="resource/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="resource/kindeditor/lang/zh-CN.js"></script>
	<script src="resource/js/jquery-3.2.1.js"></script>
	<script>
		KindEditor.ready(function(K) {
			window.editor1 = K.create('textarea[name="content1"]', {
				cssPath : 'resource/kindeditor/plugins/code/prettify.css',
				uploadJson : 'resource/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : 'resource/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
		$(function(){
			$.post("article/selectsChannel.do",function(arr){
				for(var i in arr){					
					$("[name='ch.id']").append("<option value='"+arr[i].id+"'>"+arr[i].name+"</option>");
				}
			},"json")
			$("[name='ch.id']").change(function(){
				$("[name='ca.id'] option:gt(0)").remove();
				var channel_id=this.value;
				$.post("article/selectsCategory.do",{"id":channel_id},function(arr){
					for(var i in arr){
						$("[name='ca.id']").append("<option value='"+arr[i].id+"'>"+arr[i].name+"</option>");
					}
				},"json")
			})
		})
		function add(){
			//序列化表单数据 带文件
			var formData=new FormData($("#f1")[0]);
			//封装富文本中的html内容
			formData.append("content",editor1.html());
			//ajax提交
			$.ajax({
				//告诉jquery不要去处理发送的数据
				processData:false,
				//告诉jquery不要去设置Content-Type请求头
				contentType:false,
				url:"article/addArticle.do",
				async:false,
				data:formData,
				type:"post",
				success:function(flag){
					if(flag){
						alert("发布成功");
					}else{
						alert("发布失败");
					}
				}
			})
		}
	</script>
</head>
<body>
	<%=htmlData%>
	<form name="example" id="f1">
		文章标题:
		<input type="text" name="title" class="form-control"><br/>
		文章内容:
		<textarea name="content1" cols="100" rows="8" style="width:920px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br/>
		<div class="form-inline">
			栏目:<select name="ch.id" class="form-ccontrol">
					<option value="">---请选择栏目---</option>
				</select>&nbsp;&nbsp;
			分类:<select name="ca.id" class="forn-control">
					<option value="">---请选择分类---</option>
				</select>
		</div>
		<br/>
		标题图片:
		<input type="file" name="myFile">
		<br>
		<button class="btn btn-primary" onclick="add()">发布</button>
	</form>
</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>