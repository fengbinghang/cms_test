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
		function query(){
		alert(editor1.html())
			//alert( $("[name='content1']").attr("src"))
		} 
	</script>
</head>
<body>
	<%=htmlData%>
	<form name="example" method="post" action="demo.jsp">
		文章标题:
		<input type="text" name="title" class="form-control"><br/>
		<textarea name="content1" cols="100" rows="8" style="width:920px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br/>
		<div class="form-inline">
			栏目:<select name="channel_id" class="form-ccontrol">
					<option value="">---请选择栏目---</option>
				</select>&nbsp;&nbsp;
			分类:<select name="category_id" class="forn-control">
					<option value="">---请选择分类---</option>
				</select>
		</div>
		<br/>
		<input type="button" name="button" value="发布" onclick="query()" class="btn btn-primary"/>
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