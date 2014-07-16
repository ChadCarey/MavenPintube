
function search() 
{
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
    } );
}

function getUserVideos()
{
    $.get('PinTubeUserServlet',
    {'req' : 'getUserVideos'},
    function(data/*resulting data*/)
    {
        document.getElementById("main").innerHTML = data;
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