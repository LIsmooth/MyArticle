<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!--slider-->
<link href="css/camera.css" rel="stylesheet" type="text/css" media="all" />
<script type='text/javascript' src='js/jquery.min.js'></script>
<script type='text/javascript' src='js/jquery.mobile.customized.min.js'></script>
<script type='text/javascript' src='js/jquery.easing.1.3.js'></script>
<script type='text/javascript' src='js/camera.min.js'></script>
<script>
	jQuery(function() {

		jQuery('#camera_wrap_1').camera({
			thumbnails : true
		});

		jQuery('#camera_wrap_2').camera({
			height : '400px',
			loader : 'bar',
			pagination : false,
			thumbnails : true
		});
		jQuery("#homepage").addClass("active");
	});
</script>
</head>
<body>
	<!--start header-->
<%@include file="header.jsp" %>
	
	<!-- start slider -->
	<div class="slider_bg">
		<div class="wrap">
			<div class="wrapper">
				<div class="slider">
					<!-- #camera_wrap_1 -->
					<div class="fluid_container">

						<div class="camera_wrap camera_azure_skin" id="camera_wrap_1">
							<div data-thumb="images/slider/slider1.jpg"
								data-src="images/slider/slider1.jpg"></div>
							<div data-thumb="images/slider/slider2.jpg"
								data-src="images/slider/slider2.jpg"></div>
							<div data-thumb="images/slider/slider3.jpg"
								data-src="images/slider/slider3.jpg"></div>
						</div>
						<!-- #camera_wrap_1 -->
						<form action="ArticleServlet" method="get">
						<input type="hidden" name="func" value="getall"/>
						<div style="position: absolute; left: 27%; text-align: center; top: 30%;width:46%;">
							<p class="homefont">SEARCH:</p>
							<p>
								<input type="text" name="queryStr" id="queryStr" class="homebox"/>
							</p>
							<p>
								<input type="submit" id="btnyes" value="Search" />
							</p>
						</div>
						</form>
					</div>
					<!-- end #camera_wrap_1 -->
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- start content -->
	<!-- start footer -->
	<%@include file="footer.jsp" %>
</body>

</html>

