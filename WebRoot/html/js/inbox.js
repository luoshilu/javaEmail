$("document").ready(function(){
	//alert($("#sjz").offset().top);
		//控制日期箭头
		$("#t1").css({"background":"url(images/arrow.png) no-repeat 0px 0px"});
		var t=1;
		var myscroll=0;
		$(window).scroll(function(){
			var height=$(document).scrollTop();
			var num=Math.abs(height-myscroll);
			if(height>170){
				$("#sjz").css({"position":"fixed","top":"34px"});
			}else if(height<170){
				$("#sjz").css({"position":"absolute","top":"204px"});
			}
			if(num>300){
				if((height-myscroll)>0&&t<7){
					$("#t"+t).css({"background":"url() no-repeat 0px 0px"});//清除背景图片图片
					t++;
					/*$("#t"+t).show();*/
					$("#t"+t).css({"background":"url(images/arrow.png) no-repeat 0px 0px"});//滚动条下滑，给下面的元素添加背景图片
					myscroll=$(document).scrollTop();
					
				}else if((height-myscroll)<0&&t>1){
					$("#t"+t).css({"background":"url() no-repeat 0px 0px"});//清除背景图片图片
					t--;
					$("#t"+t).css({"background":"url(images/arrow.png) no-repeat 0px 0px"});
					myscroll=$(document).scrollTop();
				}
			}
		});



	var scrolltop=0;
	$(".more-btn").click(function(){

		var ev=ev||window.event;
		var target=ev.target||ev.srcElement;
		if(target.nodeName.toLowerCase() == "a"){
	    var id=target.getAttribute("id");
	    $(".services-left-grids").css({"display":"none"});//隐藏
	    /*$(".services-left-grids").fadeIn();*/
	    $("#look").css({"display":"block"});
	    $("#select").css({"display":"none"});//隐藏
	    $("#sjz").css({"display":"none"});//隐藏
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
		t2=t;                             //保存当前时间轴
		$(document).scrollTop(0);
	    }

	    $("#back").click(function(){
			/*$("#look").fadeIn();*/
	    	$(document).scrollTop(scrolltop);//返回到刚刚的滚动条高度
		    $(".services-left-grids").css({"display":"block"});//打开邮件列表
		   /* $(".services-left-grids").fadeOut();*/
		    $("#look").css({"display":"none"});//关闭某一封邮件
		    $("#select").css({"display":"block"});
		    $("#sjz").css({"display":"block"});
	    });
	});
});