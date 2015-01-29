<%-- 
    Document   : home
    Created on : Jun 16, 2014, 4:16:41 PM
    Author     : chad
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Montage</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="bootstrap-select.css">
        <!-- Optional theme -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
        <!-- Montage Styles -->
        <link rel="stylesheet" type="text/css" href="MontageStyleSheet.css">
        <script type="text/JavaScript" src="MontageFunctions.js"></script>
        <script type="text/javascript" src="bootstrap-select.js"></script>
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </head>
    <body onload='checkUser("${user}")'>
    <nav id="welcome" class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
        <a class="navbar-brand" href="#" onclick="getUserReels()">Welcome</a>
      <div class="btn-group navbar-left">
      <button type="button" class="btn btn-warning dropdown-toggle navbar-btn navbar-left" data-toggle="dropdown">
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
          <input type="text" class="form-control" name="searchQ" placeholder="Search Youtube" onkeyup=" if (event.keyCode === 13) {search();}"/>
          <span class="input-group-btn">
              <button type="button" class="btn btn-default"  onclick='search()'>Search</button>
          </span>
        </div>
      </div>
<!--     	<input type="button" class="btn btn-default navbar-btn" onclick="getUserReels()" value="My Reels"/>-->
        <!-- Split button -->
<div class="btn-group navbar-btn navbar-left">
  <button type="button" class="btn btn-default" onclick="getUserReels()">My Reels</button>
  <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
    <span class="caret"></span>
    <span class="sr-only">Toggle Dropdown</span>
  </button>
  <ul id="reelList" class="dropdown-menu" role="menu">
      <li><a href="#">ERROR - could not load Reels</a></li>
  </ul>
</div>
        <div class="navbar-left">
            <!--<h3><span class="label label-default navbar-left" id="catLabel"></span></h3>-->
    <p class="navbar-text noselect"><span class="label label-default" id="catLabel"></span></p>
            </div>
    </div><!-- /.navbar-collapse -->
    
  </div><!-- /.container-fluid -->
</nav>
        <header>
       <!--old location of code. Could be used for something else-->
        </header>
        <!-- Modal -->
<div class="modal fade" id="searching" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="overflow:scroll">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header" style="text-align:center">
        <!--<button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>  
        </button>-->
        <h4 class="modal-title" id="myModalLabel">Searching Youtube</h4>
      </div>
      <div class="modal-body" style="max-height: 450px; overflow-y: auto">
          <div id="sBar"></div>
      </div>
      <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-warning" onclick="saveTags()">Save Tags</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade bs-example-modal-lg" id="vid" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div id="vidBody" class="modal-content">
      ...
    </div>
  </div>
</div>
<div class="modal fade bs-example-modal-sm" id="addReel" style="padding-top:10%"tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
    <!--<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h5 class="modal-title" id="myModalLabel">New Tag</h5>
    </div>-->
      <div class="modal-body">
          <div class="input-group">
          <input class="form-control" id="newTagName" placeholder="Tag Name" onkeyup=" if (event.keyCode === 13) {addTag();}" type="text">
          <span class="input-group-btn">
              <button type="button" class="btn btn-default" onclick="addTag()">Save Tag</button>
          </span>
        </div>
        <!--<input type="text" id="newTag" autofocus/>-->
      </div>
        <!--<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" onclick="addTag()">Save Tag</button>
        </div>-->
    </div>
  </div>
</div>
<div id="main"></div>
    </body>
</html>
