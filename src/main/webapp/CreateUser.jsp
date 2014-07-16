<%-- 
    Document   : CreateUser
    Created on : Jun 18, 2014, 2:27:05 PM
    Author     : chad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pintube</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
    </head>
    <body>
    <div style="width:500px; margin:auto; padding:50px 0px">
    <h3>Welcome to Pintube!</h3>
    </div>
        <div class="panel panel-warning" style="width:500px; margin:auto">
    <div class="panel-heading">
    <h3 class="panel-title">Register</h3>
  	</div>
        <p>${incorrect}</p>
        <form action="CreateUserServlet" method="POST">
        <div class="input-group" style="width:330px; padding: 20px">
  			<span class="input-group-addon">Username</span>
  			<input type="text" class="form-control" name="username"/>
		</div>
		<div class="input-group" style="width:330px; padding: 0px 20px">
  			<span class="input-group-addon">Password</span>
  			<input type="password" class="form-control" name="pass"/>
		</div>
		<div class="input-group" style="width:330px; padding: 5px 20px 0px">
  			<span class="input-group-addon">Confirm</span>
  			<input type="password" class="form-control" name="pass2"/>
		</div>
     <!--       Username: <input name="username" type="text"/>
            <br/>
            Password: <input name="password" type="password"/>
            <br/> -->
            <div style="padding: 10px 20px">
            	<input type="submit" value="Sign Up" class="btn btn-default navbar-btn"/>
            </div>
     </form>
     <!--    </form>
       <div style="float:right">
        	No Account? <a href="CreateUser.jsp">Sign Up</a>
        </div>
     </div>
        <h4>${invalid}</h4>
        <form action="CreateUserServlet" method="POST">
            Username: <input type="text" name="user"/> <br/>
            Password: <input type="password" name="pass"/> <br/>
            Re-enter your password: <input type="password" name="pass2"/> <br/>
            <input type="submit" value="sign up"/>
        </form>-->
        </div>
    </body>
</html>
