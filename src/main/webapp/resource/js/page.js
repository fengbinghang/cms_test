//分页
	$(function(){
		$(".page-link").click(function(){
			var url=$(this).attr("data");
			alert(url);
			$("#content-wrapper").load(url);
		})
	})