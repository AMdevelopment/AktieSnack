
$("#send_button").
hover(function() {
  $(this).not(".clickedTemp").css("opacity", "1.0");
}, function() {
  $(this).not(".clickedTemp").css("opacity", "0.90");
}).
on("click", function(){

  var email = $("#email_adress").val();
  if(isValidEmailAddress(email)){

    $.ajax({
      type: "POST",
      headers: {
          'Accept': 'text/plain',
          'Content-Type': 'application/json'
      },
      url: "http://stolktalktommy.nuui2tmhaj.eu-central-1.elasticbeanstalk.com/inquiry",
      data: JSON.stringify({'email':email}),
      success: function(response){
        console.log(JSON.stringify(response));

        if(response == "OK"){
          $("#send_button").html("Tack för visat intresse!");
          $("#email_adress").hide();
        }
        else{
          $("#form_container").css("margin-top","2vh");
          $("#h3").text("Ett fel uppstod, försök igen.");
          setTimeout(function () {
            reloader();
          }, 1000);
          }
        }
    });
  }
  else{
    $("#form_container").css("margin-top","2vh");
    $("#h3").text("Ett fel uppstod, försök igen.");
    setTimeout(function () {
      reloader();
    }, 1000);
  }

});

function isValidEmailAddress(emailAddress) {
    var pattern = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
    return pattern.test(emailAddress);
};

function reloader(){
  location.reload();
};
