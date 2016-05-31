/*$("document").ready(function(){
	alert("ok");
	$.ajax(function(){
		url:"servlet/getSession",
		dateType:"text",
		type:"GET",
		success:function(date){
			$("#mail").text(date);
		}
	});
});*/
$("document").ready(function(){
		if (window.XMLHttpRequest)
		  {// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		  }
		else
		  {// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
			xmlhttp.onreadystatechange=function()
			{
			  if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    {
			    	$("#mail").text(xmlhttp.responseText);
			    }
		}
	xmlhttp.open("GET","servlet/getSession",false);
    xmlhttp.send();
});