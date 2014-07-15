
function search() 
{
    var search = $('[name=searchQ]').val();
    if (search == "") {
        document.getElementById("main").innerHTML = "";
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


function addVideo(video)
{
    $.get('PinTubeUserServlet',
    {'video' : video},
    function(data/*resulting data*/)
    {
        document.getElementById("main").innerHTML = data;
    } );
}