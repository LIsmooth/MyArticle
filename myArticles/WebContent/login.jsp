<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Contact</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="keywords" content="" />
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/style.css" rel="stylesheet" />
    <!-- font files  -->
    <script src="js/jquery-3.0.0.min.js" type="text/javascript"></script>
    <script>
$(function(){
	$("#loginpage").addClass("active");
});
</script>
</head>
<body>
<%@include file="header.jsp" %>
     <!-- start content -->
     <div class="content_bglog">
            <div class="wrap">
                <div class="wrapper">
                    <!-- start main -->
                            <div class="log">
                                <h1>Join In us now!</h1>
                                <form action="LoginServlet?func=login" method="post">
                                <div class="content1">
                                    <h2>Sign In Form</h2>
                                    <input type="text" name="userid" id="userid" value="USERNAME" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'USERNAME';}" />
                                    <input type="password" name="pwd" id="psw" value="123456" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'PASSWORD';}"/>
                                    <div class="button-row">
                                        <input type="submit" id="btnsignin" class="sign-in" value="Sign In" />
                                        <input type="reset" id="btnreset" class="reset" value="Reset" />
                                        <div class="clear">
                                        </div>
                                    </div>
                                </div>
                                </form>
                                
                                <form action="LoginServlet?func=register" method="post">
                                <div class="content2">
                                    <h2>Sign Up Form</h2>
                                    <input type="text" id="registerid" name="userid" value="USERID" onfocus="this.value = '';" onblur="onSubmit()"/>
                                    <input type="text" name="nickname" value="NICKNAME" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'NICKNAME';}" />
                                    <input type="password" name="psw" value="PASSWORD" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'PASSWORD';}" />
                                    <input type="submit" id="subbtn" class="register" value="Register" />
                                </div>
                                </form>
                                <div class="clear">
                                </div>
                            </div>
                </div>
            </div>
        </div>
<%@include file="footer.jsp" %>
</body>
<script>
onSubmit=function(){
	if (this.value == '') {this.value = 'USERID';}
	//alert($("#registerid").val());
	if($("#registerid").val()!='')
	$.ajax({
		url:"LoginServlet?func=isexist",
		data:"uid="+$("#registerid").val(),
		type : 'post',
		success:function(msg){
			if(msg=="ok"){
				alert("用户名已被占用，请更改！");
				$("#subbtn").attr("disabled",true);
			}else{
				alert("恭喜！该用户名可以使用！")
				$("#subbtn").attr("disabled",false);
			}
		}
	});
	
}
</script>
</html>
