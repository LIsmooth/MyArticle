<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <title>Articles</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

<link href="css/asset/amazeui.css" rel="stylesheet" type="text/css"/>
<link href="css/asset/amazeui.flat.css" rel="stylesheet" type="text/css"/>
<link href="css/asset/amazeui.min.css" rel="stylesheet" type="text/css"/>
<link href= "css/asset/app.css"rel="stylesheet" type="text/css"/>
<link href="css/asset/amazeui.flat.min.css" rel="stylesheet" type="text/css"/>

<script src="js/jquery-3.0.0.min.js" type="text/javascript"></script>
<script>
	$(function(){
		$("#artipage").addClass("active");
	})
</script>
</head>
<body>
<!--start header-->
<%@include file="header.jsp" %>
<!-- start content -->
<div class="content_bg">
<div class="wrap">
<div class="wrapper">
	<!-- start main -->
	<div class="main" style="padding-top:1%;">
	<div >
		<h2 style="margin-bottom:10px;">${pageTitle}</h2>
		
		<c:forEach var="arti" items="${articles}" varStatus="status">
		<div class="ser-grid-list img_style">
		<h5>Article ${status.index+1}</h5>
			<a href="ArticleServlet?func=checkArticle&ano=${arti.articleno}">
			<img src="images/upload/${arti.figure}" alt="figure" /></a>
			<p class="para">
			<span class="para-title">${arti.title}<br/></span>
			<span style="color:#29004c;">分类：${arti.type.typename}&nbsp;&nbsp;&nbsp;作者：${arti.user.nickname}<br/></span>
			${fn:substring(arti.a_content,0,160)}
			</p>
		</div>
		</c:forEach>	
		</div>
		
		 <div class="am-cf"></div>
		 <div>
		    <span style="display: inline-block;margin-top:20px;">共 <span id="totalsize">${pageBean.totalSize}</span> 条记录</span>
                            <div class="am-fr">
                                <ul class="am-pagination">
                                    <li class="${pageBean.currentPage=='1'?'am-disabled':''}">
                                    <a href="ArticleServlet?func=getall&currentpage=${pageBean.currentPage-1}&queryStr=${pageBean.queryString}">«</a></li>
                                        
										<c:set var="c" value="${pageBean.currentPage}"/>
 										<c:set var="t" value="${pageBean.totalPage}"/>
                                    	<c:forEach var="item" varStatus="status" begin="${c>3?((c+2)>t?(t-4):(c-2)):1}" end="${(c+2)>t?t:(c>3?(c+2):5)}">
                                    	<c:if test="${status.index==pageBean.currentPage}">
                                    		<li class="am-active"><a href="ArticleServlet?func=getall&currentpage=${status.index}&queryStr=${pageBean.queryString}">${status.index}</a></li>
                                    	</c:if>
                                    	<c:if test="${status.index!=pageBean.currentPage}">
                                    		<li><a href="ArticleServlet?func=getall&currentpage=${status.index}&queryStr=${pageBean.queryString}">${status.index}</a></li>
                                    	</c:if>
                                    	</c:forEach>                    

                                    
                                    <li class="${pageBean.currentPage==pageBean.totalPage?'am-disabled':''}">
                                    <a href="ArticleServlet?func=getall&currentpage=${pageBean.currentPage+1}&queryStr=${pageBean.queryString}">»</a></li>
                                </ul>
                            </div>
                        </div>
		 </div>
</div>
</div>
</div>
<!-- start footer -->
<%@include file="footer.jsp" %>
</body>
</html>

