<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Contact</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="keywords" content="" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/asset/app.css" rel="stylesheet" type="text/css" />
<link href="css/asset/amazeui.css" rel="stylesheet" type="text/css" />
<link href="css/asset/app.css" rel="stylesheet" type="text/css" />
<link href="css/asset/amazeui.flat.css" rel="stylesheet" type="text/css" />
<link href="css/asset/amazeui.flat.min.css" rel="stylesheet"
	type="text/css" />

<script src="js/jquery-3.0.0.min.js" type="text/javascript"></script>
<!-- /font files  -->
<script type="text/javascript">
        function hideit() {
            $("#welcome").css({"display": "none"});
            $("#write").css({"display": "block"});
        }
        function exitit() {
            $("#welcome").css({"display": "block"});
            $("#write").css({"display": "none"});
        }
    </script>
</head>
<body>
<%@include file="header.jsp" %>
	<!-- start content -->
	<div>
		<div class="wrap">
			<div class="wrapper">
				<!-- start main -->
				<table class="am-table am-table-striped am-table-hover table-main">
					<thead>
						<tr >
							<th class="table-id">No</th>
							<th class="table-title">Title</th>
							<th class="table-type">Class</th>
							<th class="table-date am-hide-sm-only">Time</th>
							<th class="table-set">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var='arti' items="${articles}">
						<c:if test="${arti.isDeleted==0}">
							<tr>
								<td>${arti.articleno}</td>
								<td><a
									href="ArticleServlet?func=checkArticle&ano=${arti.articleno}">${arti.title}</a></td>
								<td>${arti.type.typename}</td>
								<td class="am-hide-sm-only">
								<fmt:formatDate value='${arti.lastchange}' pattern='yyyy年MM月dd日 HH:mm:ss' /></td>
								<td style="width: 220px;">
									<div class="am-btn-toolbar">
										<div class="am-btn-group am-btn-group-xs">
											<button type="button"
												class="am-btn am-btn-default am-btn-xs am-text-secondary"
												value="${arti.articleno}" onclick="viewArticle(this)">
												<span class="am-icon-pencil-square-o"></span> 编辑
											</button>
											<button type="button"
												class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only deletebtn"
												value="${arti.articleno}">
												<span class="am-icon-trash-o"></span> 删除
											</button>
										</div>
									</div>
								</td>
							</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
<%@include file="footer.jsp" %>
</body>
<script>
	$(function() {
		$(".deletebtn").each(function() {
			$(this).click(function() {
				var ano = this.value;

				if (confirm("删除后不可恢复，确认删除？")) {
					$.ajax({
						url : 'UserServlet?func=delarti',
						data : "ano=" + ano,
						type : 'post',
						success : function(msg) {
							/* var s = $("#totalsize").text();
							$("#totalsize").text(s - 1); */
							window.location.reload();
						},
						error : function(r, s, e) {
							alert("出错了==" + r.status + "---" + s + "---" + e);
						}
					});
				}

			});
		});
		viewArticle=function(tag){
			var ano=tag.value;
			window.location="ArticleServlet?func=modify&ano="+ano;
		}
	})
</script>
</html>
