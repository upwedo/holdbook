<%@ page language="java" contentType="text/html; charset=gbk"    pageEncoding="gbk"%>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/css/reset.css"
	type="text/css" media="screen">

<!-- Main Stylesheet -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/css/style.css"
	type="text/css" media="screen">

<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/css/invalid.css"
	type="text/css" media="screen">

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
<script type="text/javascript" src="${pageContext.request.contextPath}/content/js/jquery-1.js"></script>

<!-- jQuery Configuration -->
<script type="text/javascript" src="${pageContext.request.contextPath}/content/js/simpla.js"></script>

<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="${pageContext.request.contextPath}/content/js/facebox.js"></script>

<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="${pageContext.request.contextPath}/content/js/jquery_002.js"></script>

<!-- jQuery Datepicker Plugin -->
<script type="text/javascript" src="${pageContext.request.contextPath}/content/js/jquery.js"></script>
<!--[if IE]><script type="text/javascript" src="resources/scripts/jquery.bgiframe.js"></script><![endif]-->


<!-- Internet Explorer .png-fix -->

<!--[if IE 6]>
			<script type="text/javascript" src="resources/scripts/DD_belatedPNG_0.0.7a.js"></script>
			<script type="text/javascript">
				DD_belatedPNG.fix('.png_bg, img, li');
			</script>
		<![endif]-->

</head>

<body>
	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->

		<div id="sidebar">
			<div id="sidebar-wrapper">
				<!-- Sidebar with logo and menu -->

				<h1 id="sidebar-title" align="center">
					<a href="#">${session.user}</a>
				</h1>
				<ul id="main-nav">
					<!-- Accordion Menu -->
					<li><a style="padding-right: 15px;" href="#"
						class="nav-top-item"> 代码管理 </a>
						<ul style="display: none;">
							<li><a href="${pageContext.request.contextPath}/entity.do">Entity</a></li>
							<li><a href="${pageContext.request.contextPath}/dao.do">Dao</a></li>
							<li><a href="${pageContext.request.contextPath}/daoImpl.do">DaoImpl</a></li>
							<li><a href="${pageContext.request.contextPath}/service.do">Service</a></li>
							<li><a href="${pageContext.request.contextPath}/serviceImpl.do">ServiceImpl</a></li>
						</ul></li>

					<li><a style="padding-right: 15px;" href="#"
						class="nav-top-item"> 文件管理 </a>
						<ul style="display: none;">
							<li><a href="#">Upload Images</a></li>
							<li><a href="#">Manage Galleries</a></li>
							<li><a href="#">Manage Albums</a></li>
							<li><a href="#">Gallery Settings</a></li>
						</ul></li>

					<li><a style="padding-right: 15px;" href="#"
						class="nav-top-item">文档说明</a>
						<ul style="display: none;">
							<li><a href="#">Calendar Overview</a></li>
							<li><a href="#">Add a new Event</a></li>
							<li><a href="#">Calendar Settings</a></li>
						</ul></li>

					<li><a href="#" class="nav-top-item"> 项目部署 </a>
						<ul style="display: none;">
							<li><a href="#">General</a></li>
							<li><a href="#">Design</a></li>
							<li><a href="#">Your Profile</a></li>
							<li><a href="#">Users and Permissions</a></li>
						</ul></li>

				</ul>
				<!-- End #main-nav -->
			</div>
		</div>
		<!-- End #sidebar -->
