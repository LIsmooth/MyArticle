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
            <div class="info" id="welcome">
                <h2>Welcome,${user.nickname}</h2>

                <div style="text-align: center">
                    <a href="editArticle.jsp"><input type="button" value="Write It" id="btnUpload" onclick="hideit()"/></a>
                    <a href="UserServlet?func=favorite"><input type="button" value="My Favorite" id="btnFavor" style="border-top-color:#f67b09;background-image: linear-gradient(to bottom,#ff8807,#e47907)"/> </a>                 
                    <a href="UserServlet?func=myartis"><input type="button" value="My Articals" id="btnMine" style="border-top-color:#57ad2f;background-image: linear-gradient(to bottom,#57ad2f,#438525)"/></a>
                    <a href="UserServlet?func=guess"><input type="button" value="Guess My Taste" id="btnGuess" style="border-top-color:#00cad8;background-image: linear-gradient(to bottom,#00dfee,#00a9b5)"/></a>
                    <a href="UserServlet?func=history"> <input type="button" value="History" id="btnHistory" style="border-top-color:#888788;background-image: linear-gradient(to bottom,#888788,#565556)"/></a>
                    <a href="UserServlet?func=logoff"><input type="button" id="btnLogoff" class="btnLogoff" value="Log Off" style="border-top-color:#c0392b;background-image: linear-gradient(to bottom,#dc3131,#c0392b)"/></a>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>
</body>
    <script>
    $(function(){   	
        hideit=function() {
            $("#welcome").css({"display": "none"});
            $("#write").css({"display": "block"});
        }
    	 exitit=function() {
            $("#welcome").css({"display": "block"});
            $("#write").css({"display": "none"});
        }
        $("#loginpage").addClass("active");
        
    }) 
    </script>
</html>
    