var sBar = '<div id= "sBar" class="progress">' +
  '<div class="progress-bar progress-bar-warning progress-bar-striped active"' +  
  'role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">' +
  '<span class="sr-only">100% Complete (warning)</span>' +
  '</div>' +
  '</div>';
function search() 
{	
    $('#myModalLabel').text("Searching Youtube");
    $('.modal-footer').hide();
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
                $('.modal-footer').show();
        	$('#sBar').replaceWith('<div id="sBar" style="text-align:center; margin:auto">'+data+'</div>');
                var btn = reelBtn(null);
                refreshReelBtns(btn);
        });
    } );
}
function addTagLabel()
{
    $('div > .selectpicker').parent().removeClass("open");
    $('#addReel').modal('show');
    $('#addReel').on('shown.bs.modal', function () {
    $('#newTagName').focus();
});
    $('#newTagName').focus();
    $("#newTagName").val("");
}

function addTag()
{
    $("#addReel").modal('hide');
    var text = $("#newTagName").val();
    if (text !== "")
    {
       var newOption = "<option value='" + text+ "'>" + text + "</option>";
       var btn = reelBtn(newOption);
       var tag = "<li style='display:none'><a name='" + text + "'onclick='0'>" + text + "</a></li>";
       $("#reelList").append(tag);
       refreshReelBtns(btn);
    }
}

function getUserVideos(category)
{
    $('#myModalLabel').text("Loading Videos");
    $('#sBar').replaceWith(sBar);
    $('.modal-footer').hide();
    $('#searching').modal('show');
    var name = $("a[name="+ category +"]").text();
    $.get('MontageUserServlet',
    {'req' : 'getUserVideos',
     'reel' : category},
    function(data/*resulting data*/)
    {
        document.getElementById("main").innerHTML = data;
        $(document).ready( function() {
        	$('#searching').modal('hide');
                $('#catLabel').text(name);
        });
    } );
}

function getUserReels()
{
    $('#myModalLabel').text("Loading Reels");
    $('#sBar').replaceWith(sBar);
    $('.modal-footer').hide();
    $('#searching').modal('show');
    $('#catLabel').text("");
    $.get('MontageUserServlet',
    {'req' : 'getUserReels'},
    function(data/*resulting data*/)
    {
        document.getElementById('main').innerHTML = data;
        $(document).ready( function() {
                // change list menu
                var reels =  parseReel(); 
                $('#reelList').empty();
         
                // loop through data for Reels
                for (var i = 0; i < reels.length; i++)
                {
                    $('#reelList').append(
                        '<li><a href="#" name="' + reels[i].id +
                        '"onclick="getUserVideos(' + reels[i].id + ')">' + 
                        reels[i].name + '</a></li>');
                
                }
                if (reels.length === 0)
                {
                    $('#reelList').append( 
                        '<li><a href="#" name="" onclick="">' + 
                        'No Reels Available' + '</a></li>');
                }
                // take down loading after everything is ready to go
                $('#searching').modal('hide');
        });
    } );
}
function saveTags()
{
    $('.addVid').each(function()
    {
        var vidTitle = $(this).attr("name");
        var vidID = $(this).attr("value");
        addVideo (vidTitle, vidID);
    });
    //document.getElementById("main").innerHTML = "<h2>Tagged!</h2>";
    
    // wait a second before loading reels
    setTimeout(function () {$('#searching').modal('hide'); }, 4000);
    getUserReels();
}
function addVideo(videoTitle, videoID)
{
    var str = 'AddVideoServlet?videoID=' + videoID;
    str += '&videoTitle=' + videoTitle + '&';
    var id = '#' + videoID;
    var reelTags = $(id).serialize();
    if (reelTags !== "")
    {
        str += reelTags;
        // use sync so it will wait
        $.ajax({
            type: 'POST',
            url:str,
            async:false         
         });
    }
}

function playVideo(link, title)
{
    var window = "<div id='vidBody' style='text-align:center; margin:auto'>" +
                 "<div class='embed-responsive embed-responsive-16by9'>" +     
                 "<iframe class='embed-responsive-item' " +
                 "src='//www.youtube.com/embed/" +
                  link +
                 "?autoplay=1' allowfullscreen></iframe>" + 
                 "</div>" +
                 "</div>";
         $('#myLargeModalLabel').text(title);
         $('#vidBody').replaceWith(window);
         $('#vid').modal('show');
         $('#vid').on('hidden.bs.modal', function () {
           // restore default content on close
           //alert("modal hidden");
           $('#vidBody').replaceWith('<div id="vidBody" class="modal-content">...</div>');
           });
}
function getTempReels()
{
   var reels = $("#reelList > li");
   var arr = [];
   for (i = 0; i < reels.length; i++)
   {
       var tags = reels[i].getElementsByTagName("A")[0];
       var att = tags.getAttribute("onclick").split("");
       var num = "";
       var text = $(tags).text();
       num = $(tags).attr("name");
       if (num !== "")
       {
           arr.push({id:num, name: text});
       }
   }
   return arr;
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
function reelBtn(newOption)
{
    // build initial Reel button
    var reelBtn = "<select name='reels' class='selectpicker' data-width='auto' " + 
            "multiple data-selected-text-format='values'><optgroup label='Reels'>\n";
    var reels = getTempReels();
 
    for (var i = 0; i < reels.length; i++)
    {
        reelBtn = reelBtn + "<option value='" + reels[i].id + "' >" + 
                  reels[i].name + "</option>\n";
    }
    if (newOption !== null)
    {
        reelBtn = reelBtn + newOption;
    }
    reelBtn = reelBtn + "</optgroup>";
    reelBtn = reelBtn + "<optgroup label=''>";
    reelBtn = reelBtn + "<option> + New Reel</option>";
    reelBtn = reelBtn + "</optgroup>";
    reelBtn = reelBtn + "</select>\n";
    return reelBtn;
}

function refreshReelBtns(btn)
{
    // build button
    //alert(btn);
    // apply button to all forms
    //$('[data-toggle="popover"]').popover();
    $("form > .oldBtn").remove();
    $("form > select:first-of-type").replaceWith(btn);
    $('.selectpicker').selectpicker('refresh');
    $("form > div:first-of-type").addClass("oldBtn");
    var newReel = '<li class="dropdown-header" data-original-index="null"><span class="text"></span></li>';
    // link with popover, for now just a text box will work
    newReel = newReel + '<li><a class=\"opt newReelBtn\" onclick="addTagLabel()">' +
                  '<span class="text"> + New Reel</span></a></li>';
    $('.inner > li:last-child').replaceWith(newReel);
    //$('[data-toggle="popover"]').popover();
}
function checkUser(loggedin) 
{
   if (!loggedin) 
   {
       window.location.replace("login.jsp");
   }
   else
   {
       // load user video categories
       $("#welcome").fadeIn();
       getUserReels();
   }
}