function search() 
{	
	$('#myModalLabel').text("Searching Youtube");
	$('#searching').modal('show');
    var search = $('[name=searchQ]').val();
    if (search == "") {
        document.getElementById("main").innerHTML = "";
        getUserVideos();
        return;
    }
    $.get('SearchYouTube',
    {'search' : search},
    function(data/*resulting data*/,status,xhr/*xmlobject*/)
    {
        document.getElementById("main").innerHTML = data;
        
        $(document).ready( function() {
        	$('#searching').modal('hide');
        });
    } );
}

function getUserVideos()
{
	$('#myModalLabel').text("Loading Videos");
	$('#searching').modal('show');
    $.get('PinTubeUserServlet',
    {'req' : 'getUserVideos'},
    function(data/*resulting data*/)
    {
        document.getElementById("main").innerHTML = data;
        $(document).ready( function() {
        	$('#searching').modal('hide');
        });
    } );
}


function addVideo(videoTitle, videoID)
{
    var str = 'AddVideoServlet?videoID=' + videoID;
    str += '&videoTitle=' + videoTitle;
    $.post(str,
    function(data/*resulting data*/)
    {
        document.getElementById("main").innerHTML = "<h2>Pinned!</h2>";
    } );
}