$("document").ready(function(){
	var mail="";
	var password="";
	$("#mail").blur(function(){
		mail=this.value;
	});
	$("#password").blur(function(){
		password=this.value;
	});

	$("#submit").click(function(){
		if(mail==""||password==""){
			alert("请输入用户名和密码");
		}else{
			$("#ts").text("登录中,请稍后...");
			if (window.XMLHttpRequest)
			  {
			  xmlhttp=new XMLHttpRequest();
			  }
			else
			  {
			  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  }
			  
				xmlhttp.onreadystatechange=function()
				{
				  if (xmlhttp.readyState==4 && xmlhttp.status==200)
				    {
				    	if(xmlhttp.responseText=="1")
				    	{
				    	 window.location.href='index.html';
				    	}else{
				    		$("#ts").html("登录失败.");
				    	}
				}
			}
		xmlhttp.open("GET","servlet/indexServlet?username="+mail+"&password="+password,false);
	    xmlhttp.send();
		}
		});
});