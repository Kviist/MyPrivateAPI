function sendALocation(location){
     $.ajax({
        method: "GET",
        url: "http://127.0.0.1:7313/weatherdatasets/" + location,
    }).done(function(response){
        $('.current-weather').text(response),
        getPlaylistName(response),
        getTracks(response);
     });   
}

function getPlaylistName(weather){
     $.ajax({
        method: "GET",
        url: "http://127.0.0.1:7313/playlistName/" + weather,
    }).done(function(response){
         $('.current-playlist').text(response);
     });
    
}
function getTracks(weather){
     $.ajax({
        method: "GET",
        url: "http://127.0.0.1:7313/tracks/" + weather,
    }).done(function(data){
         var list = JSON.parse(data);
         var frontEndList = $('.songs');
         
         for(var i = 0; i < list.length; i++){
             html = '<li id="song_' + i + '"><i>' + list[i]['name'] + '</i> - ' + list[i]['artists'] + '</li>';
    
             frontEndList.append(html);
         }
     });
    
}


$(document).ready(function(){
    
    setTimeout(function(){
        $('.main-nav').fadeIn(1000).removeClass('hidden'); 
    }, 500);
    
     setTimeout(function(){
        $('.inputfield').fadeIn(1000).removeClass('hidden');
    }, 1000);
    
      setTimeout(function(){
        $('.welcome-text').fadeIn(1000).removeClass('hidden');
    }, 1000);
    
    $('.inputfield').keyup(function(event){
    if(event.keyCode == 13){
        sendALocation($('#search').val());
        $('header').load('ListPage.html');
    }
});
    
});


