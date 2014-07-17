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
        <title>JSP Page</title>
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script type="text/JavaScript" src="PinTubeFunctions.js"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

        <!-- Latest compiled and minified JavaScript -->
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        
    </head>
    <body>
        <header>
        <h4>Welcome ${user}</h4>
        <form action="PinTubeUserServlet" method="GET">
            <input type="button" onclick='getUserVideos()' value="My Videos"/>
            <input type="submit" value="Log Out"/>
        </form>
        
            <!--<form id="searchbox" method="GET">-->
                <input type="text" name="searchQ" placeholder="Search YouTube" onkeyup="search()"/> 
                <input type="button" onclick='search()' value="Search"/>
            <!--</form>-->
        </header>
        <div id="main">
            
        </div>
    </body>
</html>
