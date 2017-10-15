<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <title>Show Details</title>
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
    
    <section class="headline-sec">
        <div class="overlay ">
            <h3 >SHOW DETAILS<i class="fa fa-angle-double-right "></i></h3>

        </div>
    </section>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-md-6" >
                    
                    <div class="alert alert-info" >
                        
                            <strong>INFORMATION</strong>
                            <br />


	<table>
		<tr>
			<td>
				ISBN
			</td>
			<td>
				<s:property value="detail_result[0][0]"/>
			</td>
		</tr>
		
		<tr>
			<td>
				Title
			</td>
			<td>
				<s:property value="detail_result[0][1]"/>
			</td>
		</tr>
		
		<tr>
			<td>
				AuthorID
			</td>
			<td>
				<s:property value="detail_result[0][2]"/>
			</td>
		</tr>
		
		<tr>
			<td>
				Publisher
			</td>
			<td>
				<s:property value="detail_result[0][3]"/>
			</td>
		</tr>
		
		<tr>
			<td>
				PublishDate
			</td>
			<td>
				<s:property value="detail_result[0][4]"/>
			</td>
		</tr>
		
		<tr>
			<td>
				Price
			</td>
			<td>
				<s:property value="detail_result[0][5]"/>
			</td>
		</tr>
		<tr>
			<td>
				
			</td>
			<td>
				<s:a href="deliver_details?isbn=%{detail_result[0][0]}&
										title=%{detail_result[0][1]}&
										authorID=%{detail_result[0][2]}&
										publisher=%{detail_result[0][3]}&
										publishDate=%{detail_result[0][4]}&
										price=%{detail_result[0][5]}"><input type="button" class="btn btn-primary" value="更新"/></s:a>
			</td>
		</tr>
	</table>
	
	<table>
		<tr>
			<td>
				AuthorID
			</td>
			<td>
				<s:property value="detail_result[1][0]"/>
			</td>
		</tr>
		
		<tr>
			<td>
				Name
			</td>
			<td>
				<s:property value="detail_result[1][1]"/>
			</td>
		</tr>
		<tr>
			<td>
				Age
			</td>
			<td>
				<s:property value="detail_result[1][2]"/>
			</td>
		</tr>
		
		<tr>
			<td>
				Country
			</td>
			<td>
				<s:property value="detail_result[1][3]"/>
			</td>
		</tr>
	</table>
	
                    </div>
                </div>
            </div>
        </div>
    </section>

</body>
</html>