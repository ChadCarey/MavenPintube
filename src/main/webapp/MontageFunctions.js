var sBar = '<div id= "sBar" class="progress">' +
  '<div class="progress-bar progress-bar-warning progress-bar-striped active"' +  
  'role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">' +
  '<span class="sr-only">100% Complete (warning)</span>' +
  '</div>' +
  '</div>';
var aBtn = '<div><select class="selectpicker" multiple>' +
      '<optgroup label="Reels">' +
          '<option value="1" >funny</option>' +
          '<option value="2" >scary</option>' +
          '<option value="3" >action</option>' +
      '</optgroup>' +
          '<option value="-1" > + New Reel</option>' +
  '</select></div>';
function search() 
{	
    $('#myModalLabel').text("Searching Youtube");
    $('#sBar').replaceWith(sBar);
    $('#searching').modal('show');
    var search = $('[name=searchQ]').val();
    if (search === "") {
        document.getElementById("main").innerHTML = "";
        getUserReels();
        return;
    }
    $.get('SearchYouTube',
    {'search' : search},
    function(data/*resulting data*/,status,xhr/*xmlobject*/)
    {
        //document.getElementById("main").innerHTML = data;
        $(document).ready( function() {
        	$('#sBar').replaceWith('<div id="sBar" style="text-align:center; margin:auto">'+data+'</div>');
                $('.selectpicker').selectpicker('refresh');
                
        });
    } );
}

function getUserVideos(category)
{
    $('#myModalLabel').text("Loading Videos");
    $('#sBar').replaceWith(sBar);
    $('#searching').modal('show');
    $.get('MontageUserServlet',
    {'req' : 'getUserVideos',
     'reel' : category},
    function(data/*resulting data*/)
    {
        document.getElementById("main").innerHTML = data;
        $(document).ready( function() {
        	$('#searching').modal('hide');
        });
    } );
}

function getUserReels()
{
    $('#myModalLabel').text("Loading Reels");
    $('#sBar').replaceWith(sBar);
    $('#searching').modal('show');
    $.get('MontageUserServlet',
    {'req' : 'getUserReels'},
    function(data/*resulting data*/)
    {
        document.getElementById('main').innerHTML = data;
        $(document).ready( function() {
          //  alert("run");
                // change list menu
                var reels =  parseReel(); 
                $('#reelList').empty();
         
                // loop through data for Reels
                for (i = 0; i < reels.length; i++)
                {
                    $('#reelList').append( 
                        '<li><a href="#" onclick="getUserVideos(' + reels[i].id + ')">' + 
                        reels[i].name + '</a></li>');
                
                }

                // add New Reel option
               /* $('#reelList').append(
                        '<li class="divider"></li>' +
                        '<li><a href="#">+ Add Reel</a></li>');*/
                // take down loading after everything is ready to go
                $('#searching').modal('hide');
        });
    } );
}

function addVideo(videoTitle, videoID)
{
    // use jquery serialize method?
    var str = 'AddVideoServlet?videoID=' + videoID;
    str += '&videoTitle=' + videoTitle;
    str += '&' + $('#newVideo').serialize();
    $.post(str,
    function(data/*resulting data*/)
    {
        document.getElementById("main").innerHTML = "<h2>Tagged!</h2>";
        $('#searching').modal('hide');
    } );
}

function parseReel(/*should be result of getUserReels data*/)
{
   var reels = $(".reel");
   var arr = [];
   for (i = 0; i < reels.length; i++)
   {
       var tags = reels[i].getElementsByTagName("A")[0];
       var att = tags.getAttribute("onclick").split("");
       var num = "";
       for(j = 0; j < att.length; j++)
       {
           // build id num
           if (!isNaN(att[j]))
               num = num + att[j];
       }
       var text = tags.firstChild.innerHTML;
       arr.push({id:num, name: text});
   }
   return arr;
}