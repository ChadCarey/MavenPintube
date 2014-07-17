<%-- 
    Document   : home
    Created on : Jun 16, 2014, 4:16:41 PM
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
        <script type="text/JavaScript" src="PinTubeFunctions.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

    </head>
    <body>
    <nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Welcome</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      <div class="btn-group">
  <button type="button" class="btn btn-warning dropdown-toggle navbar-btn" data-toggle="dropdown">
    ${user}<span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu">
    <li><a href="#">Sign Out</a></li>
  </ul>
	</div>
      </ul>

      <div class="navbar-form navbar-left" role="search" id="searchbox">
        <div class="form-group">
          <input type="text" class="form-control" name="searchQ" placeholder="Search Youtube" onkeyup="search()"/>
        </div>
        <input type="button" class="btn btn-default" onclick='search()' value="Search"/>
      </div>
      
      	<input type="button" class="btn btn-default navbar-btn" onclick='getUserVideos()' value="My Videos"/>
      
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
        <header>
       <!--<h4>Welcome ${user}</h4>
        <form action="PinTubeUserServlet" method="GET">
            <input type="button" onclick='getUserVideos()' value="My Videos"/>
            <input type="submit" value="Log Out"/>
        </form>
        
            <form id="searchbox" method="GET">
                <input type="text" name="searchQ" placeholder="Search YouTube" onkeyup="search()"/> 
                <input type="button" onclick='search()' value="Search"/>
            </form>-->
        </header>
        <div id="main">
            
        </div>
    </body>
</html>
