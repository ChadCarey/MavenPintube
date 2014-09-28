<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pintube Google Signin!</title>

	<script type="text/javascript">
		function signinCallback(authResult) {
	  		if (authResult['status']['signed_in']) {
		    	// TODO - Update the app to reflect a signed in user
		    	//      - So basically, check if they already have signed in to Pintube with
		    	//      - their Google account before (e.g. query the database) and if
		    	//      - they have logged in before, load their videos, etc..
		    	//      -
		    	//      - If it's their first time signing in with google, add them
		    	//      - to the database
		    	
		    	// Hide the sign-in button now that the user is authorized, for example:
		    	document.getElementById('signinButton').setAttribute('style', 'display: none');
		    	alert("you are signed in!");
		  	} else {
		    	// Update the app to reflect a signed out user
		    	// Possible error values:
		    	//   "user_signed_out" - User is signed-out
		    	//   "access_denied" - User denied access to your app
		    	//   "immediate_failed" - Could not automatically log in the user
		    	console.log('Sign-in state: ' + authResult['error']);
		  	}
		}
	</script>
</head>
<body>

	<span id="signinButton">
  		<span 
  			class="g-signin" 
  			data-callback="signinCallback" 
  			data-clientid="747060127972-096stqu33ip7pgbkj5i0maq4t9tie14f.apps.googleusercontent.com" 
  			data-cookiepolicy="single_host_origin" 
  			data-requestvisibleactions="http://schema.org/AddAction" 
  			data-scope="https://www.googleapis.com/auth/plus.login">
  		</span>
	</span>

   <!-- Place this asynchronous JavaScript just before your </body> tag -->
    <script type="text/javascript">
      (function() {
       var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
       po.src = 'https://apis.google.com/js/client:plusone.js';
       var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
     })();
    </script>
    
</body>
</html>