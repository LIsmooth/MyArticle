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
    <script src="js/jquery-2.1.4.js"></script>
    <!-- /font files  -->
    <script type="text/javascript">
    jQuery(function(){
    	switchPage=function(url,tag){
    		
    		$("#funcPage li").removeClass("active");
    		$(tag).parent().addClass("active");
        	jQuery("#contentdiv").load(url);
			
    	};
        
        })
    </script>
</head>
<body>
        <!--start header-->
        <div class="h_bg">
            <div class="wrap">
                <div class="wrapper">
                    <div class="header">
                        <div class="logo">
                            <a href="index.html">
                                <img src="images/logo.png">
                            </a>
                        </div>
                        <div class="cssmenu">
                            <ul id="funcPage">
                                <li class="active"><a onclick="switchPage('home.jsp')"><span>Home</span></a></li>
                                <li><a href="about.html"><span>About</span></a></li>
                                <li class="has-sub"><a href="artical.html"><span>Article</span></a></li>
                                <li class="last"><a onclick="switchPage('login.jsp',this)"><span>Account</span></a></li>
                                <div class="clear">
                                </div>
                            </ul>
                        </div>
                        <div class="clear">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- start content -->
        <div id="contentdiv" class="content_bglog">

        </div>
        <!-- start footer -->
        <div class="footer_bg">
            <div class="wrap">
                <div class="wrapper">
                    <div class="footer">
                        <div class="copy">
                            <p class="w3-link">
                                Copyright &copy; 2016.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
                            </p>
                        </div>
                        <div class="f_nav">
                            <ul>
                                <li><a href="#">Skype</a></li>
                                <li><a href="#">Linked in</a></li>
                                <li><a href="#">Twitter</a></li>
                                <li><a href="#">Facebook</a></li>
                            </ul>
                        </div>
                        <div class="clear">
                        </div>
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
