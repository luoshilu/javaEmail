$("document").ready(function(){
	var set="";
	var sub="";
	var con="";
	$("#rec").blur(function(){
		set=this.value;
	});
	$("#sub").blur(function(){
		sub=this.value;
	});
	$("#con").blur(function(){
		con=this.value;
	});
	$("#send").click(function(){
		alert(set+sub+con);
		$.ajax({
			type:"POST",
			url:"servlet/sendServlet?",
			data:"setname="+set+"&subject="+sub+"&message="+con,
			dateType:"text",
			success: function(date){
				if(date=="true")
				{
					$("#tis").text("发送成功！");
				}else{
					$("#tis").text("发送失败！");
				}
			}
		});
	});
});