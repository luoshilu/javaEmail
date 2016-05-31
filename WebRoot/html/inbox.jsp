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
									<table>
										<tr>
											<td><input id="back" type="submit" value="返回"> </td>
											<td><input type="submit" value="回复"></td>
										</tr>
									</table>
									<p>发件人:</p>
									<p id="from"></p>
									<p>主题:</p>
									<p id="sub"></p>
									<p>时间:</p>
									<p id="time"></p>
									<p>内容:</p>
									<p id="msg"></p>
								</div>							
									<div class="services-left-grids">

										<%for(int floor=alllist.size();floor>0;floor--){
										%>
										<div class="col-md-6 services-left-grid">
											<h3><%=(alllist.get(floor-1).getFrom().replace('<','|')).replace('>',' ')%></h3>
											<p><%=alllist.get(floor-1).getSubject()%></p>
											<p><%=alllist.get(floor-1).getTime()%></p>
											
											<a id="<%=alllist.get(floor-1).getNumber()%>" class="more-btn">详情</a>
											<mes id="mes"><%=alllist.get(floor-1).getText()%></mes>
										</div>
										<%}%>
										
										<div class="clearfix"> </div>
										<ul class="pagenation">
											<li><a href="#">1</a></li>
											<li><a href="#">2</a></li>
											<li><a href="#">3</a></li>
										</ul>
									</div>
								</div>
								<div class="col-md-3 services-right">
									<h3>时间轴</h3>
									<ul>
										<li><a href="#"><span> </span>今天</a></li>
										<li><a href="#"><span> </span>昨天</a></li>
										<li><a href="#"><span> </span>星期X</a></li>
										<li><a href="#"><span> </span>上一周</a></li>
										<li><a href="#"><span> </span>更早</a></li>
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