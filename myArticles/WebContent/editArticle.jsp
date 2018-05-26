<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Contact</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <meta name="keywords" content=""/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet"/>
    <script src="js/jquery-3.0.0.min.js" type="text/javascript"></script>

</head>
<body>
<%@include file="header.jsp" %>
<!-- start content -->
<div class="content_bglog">
    <div class="wrap">
        <div class="wrapper">
            <!-- start main -->
        	<form action="ArticleServlet" method="post" enctype="multipart/form-data">
        	<input type="hidden" id="isModify" name="isModify" value="${arti.articleno}0"/>
            <div id="write">
            <p class="homefont">Title:<input type="text" id="tbTitle" name="title" value="${arti.title}"></p>

            <p class="homefont">
                Content:
            <span style="width: inherit; text-align: center">
            <textarea id="tbContent" name="content" style="height:339px;width:87%">${arti.a_content}</textarea>
            </span>
            </p>

            <p class="homefont">
                Class:
                <select id="typelist" name="typeno">
                	<option>无分类信息，请刷新页面</option>
                </select>
                <span style="margin-left: 50px"></span>
            </p>

            <p class="homefont">
                Picture:
                <input type="file" name="figure">
            </p>

            <div class="button-row">
                <input type="submit" id="btnUp" class="sign-in" value="Upload"/>
                <input type="button" id="btnCancel" class="reset" value="Cancel" onclick="javascript:history.go(-1);"/>
                <div class="clear"></div>
            </div>
            </div>
            </form>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
</body>
    <script>
    $(function(){   	
        
        $("#loginpage").addClass("active");
        $.ajax({
    		url : 'ArticleServlet?func=getTypes',
    		type : 'post',
    		dataType : "json",
    		success : function(msg) {
    			$("#typelist").html("<option value=\"-1\">--请选择--</option>");
    			for (var i = 0; i < msg.length; i++) {
    				var strhtml = "<option id='op"+msg[i].value+"' value='" + msg[i].value + "'>"+ msg[i].name + "</option>";
    				$("#typelist").append(strhtml);
    			}	
    			setType();//修改的话设置分类
    		},
    		error : function(r, s, e) {
    			alert("出错了==" + r.status + "---" + s + "---" + e);
    		}
        });
        setType=function(){
        	if($("#isModify").val()!="0"){
        		$("#typelist").val('${arti.type.typeno}');
        	}
        };
        
    }) 
    </script>
</html>
    