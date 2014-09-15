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
        <script type="text/javascript">
            function checkUser() 
            {
                if (!"${user}") 
                {
                    window.location.replace("index.jsp");
                }
            }
        </script>
    </head>
    <body onload="checkUser()">
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
      <div class="btn-group" style="float:left">
      <button type="button" class="btn btn-warning dropdown-toggle navbar-btn" data-toggle="dropdown">
    ${user}<span class="caret"></span>
  </button>
  <ul class="dropdown-menu" role="menu">
    <li><a href="Logout">Sign Out</a></li>
  </ul>
  </div>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <div class="navbar-form navbar-left" role="search" id="searchbox">
        <div class="input-group">
          <input type="text" class="form-control" name="searchQ" placeholder="Search Youtube" onkeyup=" if (event.keyCode == 13) {search()}"/>
          <span class="input-group-btn">
              <button type="button" class="btn btn-default"  onclick='search()'>Search</button>
          </span>
        </div>
        
      </div>
      	<input type="button" class="btn btn-default navbar-btn" onclick='getUserVideos()' value="My Videos"/>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
        <header>
       <!--old location of code. Could be used for something else-->
        </header>
        <!-- Modal -->
<div class="modal fade" id="searching" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header" style="text-align:center">
        <h4 class="modal-title" id="myModalLabel">Searching Youtube</h4>
      </div>
      <div class="modal-body">
       <div class="progress">
  <div class="progress-bar progress-bar-warning progress-bar-striped active"  role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
    <span class="sr-only">100% Complete (warning)</span>
  </div>
</div>
      </div>
    </div>
  </div>
</div>
        <div id="main" style="padding:0px 20px">
            
        </div>
    </body>
</html>
