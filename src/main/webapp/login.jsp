<%-- 
    Document   : index
    Created on : Jun 16, 2014, 8:33:42 AM
    Author     : chad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Montage</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
    </head>
    <body>
    <div style="width:500px; margin:auto; padding:50px 0px">
    <h3>Welcome to Montage!</h3>
    </div>
    <div class="panel panel-warning" style="width:500px; margin:auto">
    <div class="panel-heading">
    <h3 class="panel-title">Please Sign In</h3>
  	</div>
        <p>${incorrect}</p>
        <form action="MontageUserServlet" method="POST">
        <div class="input-group" style="width:330px; padding: 20px">
  			<span class="input-group-addon">Username</span>
  			<input type="text" class="form-control" name="username" autofocus/>
		</div>
		<div class="input-group" style="width:330px; padding: 0px 20px">
  			<span class="input-group-addon">Password</span>
  			<input type="password" class="form-control" name="password"/>
		</div>
     <!--       Username: <input name="username" type="text"/>
            <br/>
            Password: <input name="password" type="password"/>
            <br/> -->
            <div style="padding: 10px 20px">
            	<input type="submit" value="Sign In" class="btn btn-default navbar-btn"/>
            </div>
        </form>
        <div style="float:right">
        	No Account? <a href="CreateUser.jsp">Sign Up</a>
        </div>
     </div>
    </body>
</html>
