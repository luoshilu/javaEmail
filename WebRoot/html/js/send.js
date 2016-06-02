$("document").ready(function(){
	var set="";
	var sub="";
	var con="";
	var tt="";
	$("#rec").blur(function(){
		set=this.value;
	});
	$("#sub").blur(function(){
		sub=this.value;
	});
	$("#con").blur(function(){
		con=this.value;
	});
	/*$("#file").change(function(){
		alert(this.value);
		tt=this.value;
		alert("tt"+tt);
		alert(getPath(this));*/
	//tt=getPath(this);
		//附带不用修改浏览器安全配置的javascript代码，兼容ie， firefox全系列

	/*function getPath(obj)  
	{  
	  if(obj)  
	    {  
	 
	    if (window.navigator.userAgent.indexOf("MSIE")>=1)  
	      {  
	        obj.select();  
	 
	      return document.selection.createRange().text;  
	      }  
	 
	    else if(window.navigator.userAgent.indexOf("Firefox")>=1)  
	      {  
	      if(obj.files)  
	        {  
	 
	        return obj.files.item(0).getAsDataURL();  
	        }  
	      return obj.value;  
	      }  
	    return obj.value;  
	    }
	 }
}); */
//参数obj为input file对象
/*var tt = document.getElementById("send");  
tt.select();  
var realpath = document.selection.createRange().text;*/  

	$("#send").click(function(){
		
		/*var tt = document.getElementById("file");  
		tt.select();  
		var realpath = document.selection.createRange().text;*/
		
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