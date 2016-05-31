$("document").ready(function(){
	var scrolltop=0;
	$(".more-btn").click(function(){

		var ev=ev||window.event;
		var target=ev.target||ev.srcElement;
		if(target.nodeName.toLowerCase() == "a"){
	    var id=target.getAttribute("id");
	    $(".services-left-grids").css({"display":"none"});
	    /*$(".services-left-grids").fadeIn();*/
	    $("#look").css({"display":"block"});
	    /*$("#look").fadeOut();*/
	    var msg=$("#"+id).next().html();//内容
	    var time=$("#"+id).prev().html();//时间
		var sub=$("#"+id).prev().prev().html();//主题
		var from=$("#"+id).prev().prev().prev().text();//发件人
		$("#from").text(from);
		$("#sub").text(sub);
		$("#time").text(time);
		$("#msg").html(msg);
		scrolltop=$(document).scrollTop();//保存当前滚动条高度
		$(document).scrollTop(0);
	    }

	    $("#back").click(function(){
			/*$("#look").fadeIn();*/
	    	$(document).scrollTop(scrolltop);//返回到刚刚的滚动条高度
		    $(".services-left-grids").css({"display":"block"});//打开邮件列表
		   /* $(".services-left-grids").fadeOut();*/
		    $("#look").css({"display":"none"});//关闭某一封邮件
	    });
	});
});