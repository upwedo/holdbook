<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>证券后台管理系统-登录</title>

<!--                    CSS                       -->

<!-- Reset Stylesheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/css/reset.css" type="text/css" media="screen">

<!-- Main Stylesheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/css/style.css" type="text/css"	media="screen">

<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet"	href="${pageContext.request.contextPath}/content/css/invalid.css" type="text/css"	media="screen">

<!-- Colour Schemes
	  
		Default colour scheme is green. Uncomment prefered stylesheet to use it.
		
		<link rel="stylesheet" href="resources/css/blue.css" type="text/css" media="screen" />
		
		<link rel="stylesheet" href="resources/css/red.css" type="text/css" media="screen" />  
	 
		-->
<!-- Internet Explorer Fixes Stylesheet -->
<!--[if lte IE 7]>
			<link rel="stylesheet" href="resources/css/ie.css" type="text/css" media="screen" />
		<![endif]-->
<!--                       Javascripts                       -->

<!-- jQuery -->
<script type="text/javascript"	src="${pageContext.request.contextPath}/content/js/jquery-1.js"></script>

<!-- jQuery Configuration -->
<script type="text/javascript"	src="${pageContext.request.contextPath}/content/js/simpla.js"></script>

<!-- Facebox jQuery Plugin -->
<script type="text/javascript"	src="${pageContext.request.contextPath}/content/js/facebox.js"></script>

<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" 	src="${pageContext.request.contextPath}/content/js/jquery.js"></script>

<!-- Internet Explorer .png-fix -->

<!--[if IE 6]>
			<script type="text/javascript" src="resources/scripts/DD_belatedPNG_0.0.7a.js"></script>
			<script type="text/javascript">
				DD_belatedPNG.fix('.png_bg, img, li');
			</script>
		<![endif]-->

</head>
<body id="login">
	<div id="login-wrapper" class="png_bg">
		<div id="login-top">
			<h1>证券后台管理系统</h1>
			<!-- Logo (221px width) -->
		</div>
		<!-- End #logn-top -->
		<div id="login-content">
			<s:form action="processLogin" method="post">
				<p>
					<label>用户名</label> <input class="text-input" type="text" name="user.username">
				</p>
				<div class="clear"></div>
				<p>
					<label>密 码</label> <input class="text-input" type="password" name="user.pwd">
				</p>
				<div class="clear"></div>
				<p id="remember-password">
					<input type="checkbox" name="rmb">Remember me
				</p>
				<div class="clear"></div>
				<p>
					<input class="button" value="登录" type="submit" name="login">
				</p>
			</s:form>
		</div>
		<!-- End #login-content -->
		<div style="padding-top: 153px;font-size:16px;font-family:宋体;font-weight:bold">
           <p align="center">Copyright&copy;版权归XXXX公司,2015</p>
        </div>
	</div>
</body>
</html>