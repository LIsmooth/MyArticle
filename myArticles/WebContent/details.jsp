<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
 <title>Details</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <script src="js/jquery-3.0.0.min.js" type="text/javascript"></script>
    <script>
$(function(){
	$("#artipage").addClass("active");
	addFavor=function(ano){
		$.ajax({
			url:"UserServlet?func=addFavor&ano="+ano,
			success:function(msg){
				//alert(msg);
				if(msg=="succ")
					alert("Success!");
				else if(msg=="already")
					alert("This article has already been your favorite!");
				else
					window.location="login.jsp";
			},
			error : function() {  
		          alert("fail!");  
		     }  
		});
	}
});
</script>
</head>
<body>
<%@include file="header.jsp" %>
<!-- start content -->
<div class="content_bg">
<div class="wrap">
<div class="wrapper">
	<!-- start main -->
	<c:choose>
	<c:when test="${(empty arti)||(arti.isDeleted==1)}">
	<div class="main">
		链接不存在！可能已被删除！
		<div class="clear"></div>
	</div>
	</c:when>
	<c:otherwise>
		<div class="main">
		<div class="details">
			<h2 class="detail_h2">${arti.title}</h2>
		<div class="det_pic img_details" style="text-align:center;">
			<img src="images/upload/${arti.figure}" alt="figure" />
		</div>
		<div class="det-para">
			<p class="top para_details">
				${arti.a_content}
			</p>
		</div>
		<div>
			<div class="read_more">
				<button class="btn" onclick="addFavor('${arti.articleno}')">Favorite</button>
			</div>
		</div>
		</div>
		<div class="clear"></div>
		</div>
	</c:otherwise>
	</c:choose>
</div>
</div>
</div>
<%@include file="footer.jsp" %>
</body>
</html>
    