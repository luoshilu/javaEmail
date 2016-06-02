<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ page import="receive.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String username = (String) request.getSession().getAttribute("username");
String password =(String) request.getSession().getAttribute("password");
receiveEmaillist rel = new receiveEmaillist();
List<EmailObject>alllist = new ArrayList<EmailObject>();
try {
	alllist = rel.Emaillist(username, password);
	//alllist.get(0).getFrom() = request.se
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
%>
<!DOCTYPE html>
<html>
<head>
<title>收件箱</title>
<meta charset="UTF-8">
<link href="css/bootstrap.css" rel='stylesheet' type='text/css'/>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<script src="js/jquery.min.js"></script>
<link href='http://fonts.googleapis.com/css?family=Amaranth:400,700,700italic,400italic' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Signika+Negative:300,400,600,700|Amaranth:400,700,700italic,400italic' rel='stylesheet' type='text/css'>
</head>
<body>
<!---->
	 <div class="container">
	     <div class="top-header">
			 <div class="logo">
				 	<a href="index.html"><img src="images/logo.png" alt="" /></a>
			 </div>
			 <div class="top-menu">
				<span class="menu"> </span>
				<ul>
					  <li><span class="pic1"></span><a href="index.html">首页</a></li>
					 <li class="active"><span class="pic2"></span><a href="inbox.jsp">收件箱</a></li>
					 <li><span class="pic3"></span><a href="outbox.html">发件箱</a></li>
					 <li><span class="pic4"></span><a href="write.html">写信</a></li>
						<div class="clearfix"></div>
				 </ul>
				 <!--script-nav-->
				 <script>
				 $("span.menu").click(function(){
				 $(".top-menu ul").slideToggle("slow" , function(){
				 });
				 });
				 </script>
				 <!--script-nav-->
			 </div>
		  </div>
	 </div>
<!-- Services -->
					<div class="services">					
						<div class="container">
						<h1>收件箱(<%=alllist.size()%>)</h1>
							<div class="services-grids">
								<div class="col-md-9 services-left">
									
								<div id="look">
									<table style="float:right">
										<tr>
											<td><button  id="back" type="button" class="close" aria-label="Close"><span aria-hidden="true">关闭&times;</span></button></td>
											<!-- <td><input calss="btn btn-default" id="back" type="submit" value="返回"> </td> -->
											<!-- <td><input calss="btn btn-success" id="rec" type="submit" value="回复"></td> -->
										</tr>
									</table>
									<p class="text-muted">发件人:</p>
									<p class="text-info" id="from"></p>
									<p class="text-muted">主题:</p>
									<p class="text-info" id="sub"></p>
									<p class="text-muted">时间:</p>
									<p class="text-info"id="time"></p>
									<p class="text-muted">内容:</p>
									<p class="text-info" id="msg"></p>
								</div>
								<div id="select">
									<table>
										<tr>
											<td><input class="btn btn-default btn-sm" type="submit" value="全选"></td>
											<td><input class="btn btn-default btn-sm" disabled="disabled" type="submit" value="转发"></td>
											<td><input class="btn btn-default btn-sm" disabled="disabled" type="submit" value="回复"></td>
											<td><input class="btn btn-default btn-sm" disabled="disabled" type="submit" value="删除"></td>
										</tr>
									</table>
								</div>
								
								<script>


								/*var s=0;
								$(".checkbox").click(function(ev){
									for(var i=0;i<ev.length;i++){

									}
									$ tgt=ev.target();
									if(s==0){
									$("#select").fadeOut();
								}
								s++;
									alert(ev.target().value);
								});
								$(".checkbox").click({
									if(s==1){
									$("#select").fadeIn();
									}
									s--;
								});*/
								</script>							
									<div class="services-left-grids">

										<%for(int floor=alllist.size();floor>0;floor--){
										%>
										<div class="col-md-9 services-left-grid">
											<p><input calss="checkbox" type="checkbox">
											<%=(alllist.get(floor-1).getFrom().replace('<','|')).replace('>',' ')%>
											</p>
											<h3><%=alllist.get(floor-1).getSubject()%></h3>
											<p><%=alllist.get(floor-1).getTime()%></p>
											
											<a id="<%=alllist.get(floor-1).getNumber()%>" class="more-btn">详情</a>
											<mes id="mes"><%=alllist.get(floor-1).getText()%></mes>
										</div>
										<%}%>
										
										<div class="clearfix"> </div>
										<!-- <ul class="pagenation">
											<li><a href="#">1</a></li>
											<li><a href="#">2</a></li>
											<li><a href="#">3</a></li>
										</ul> -->
									</div>
								</div>
								<div id="sjz" class="col-md-3 services-right">
									<h3>时间轴</h3>
									<ul id="tms">
										<li><a href="#"><span id="t1"> </span>今天</a></li>
										<li><a href="#"><span id="t2"> </span>昨天</a></li>
										<li><a href="#"><span id="t3"> </span>星期三</a></li>
										<li><a href="#"><span id="t4"> </span>星期二</a></li>
										<li><a href="#"><span id="t5"> </span>星期一</a></li>
										<li><a href="#"><span id="t6"> </span>上一周</a></li>
										<li><a href="#"><span id="t7"> </span>更早</a></li>
									</ul>
								</div>
								
								<div class="clearfix"> </div>
							</div>
						</div>
					</div>
<!-- Services -->
<div class="featured">
	 <div class="container">
		 <h3><span>**** </span>************</h3>
		 <div class="line"></div>
			<div class="clearfix"></div>
	 </div>
</div>
<!---->
<div class="brands">
	 <div class="container">
	 </div>
</div>
<!---->
<div class="fotter">
	 <div class="container">
		 <p class="copyright">2016-mail</p>
		 <div class="social-icons">
		 <a href="#"><span class="facebook"> </span></a>
		 <a href="#"><span class="twitter"> </span></a>
		 </div>
		 <div class="clearfix"></div>
	 </div>
</div>
<script src="js/inbox.js"></script>
<!---->
</body>
</html>