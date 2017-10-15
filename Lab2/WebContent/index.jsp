<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC 
"-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
    <title>Welcome</title>
    <!-- BOOTSTRAP CORE STYLE CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FONTAWESOME STYLE CSS -->
     <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <!-- CUSTOM STYLE CSS -->
    <link href="assets/css/style.css" rel="stylesheet" />
</head>
<body>

        <div class="navbar navbar-inverse navbar-fixed-top " >
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#" ><strong style=""></strong> Search Book<small >by author</small></a>
            </div>
            <div class="navbar-collapse collapse move-me">
                <ul class="nav navbar-nav navbar-right set-links">
                    <li><a href=index.jsp class="active-menu-item">HOME</a></li>
                     <li><a href=add_author.jsp>ADD AN AUTHOR</a></li>
                     <li><a href=about.jsp>ABOUT</a></li>
                     <li><a href=help.jsp>HELP</a></li>
                </ul>
            </div>

        </div>
    </div>
    
    <!--MENU SECTION END-->
    <section id="home-sec">
        <div class="overlay text-center">
            <h1 >LEARN A BOOK</h1>
            <hr class="hr-set"/>

            <p>Provides Lots Of Author</p>
        </div>
    </section>
       
    
    <section id="search-domain" >
        <div class="container">
            <div class="row">
			<s:form action="Search.action" theme="simple">
				<div class="col-md-8">
				<s:textfield name="name" placeholder="Author's Name" class="form-control input-cls"/>
				</div>
				<div class="col-md-4">
				<s:submit value="Search" class="btn btn-info btn-lg btn-set" align="center"/>
				</div>
			</s:form>
            </div>
        </div>
    </section>
	

	
</body>
</html>