
$("#send_button").
hover(function() {
  $(this).not(".clickedTemp").css("opacity", "1");
}, function() {
  $(this).not(".clickedTemp").css("opacity", "0.8");
}).
on("click", function(){
  $("#email_adress").hide();
  $(this).addClass("clickedTemp");
  $(this).css("border-radius", "5px 5px 5px 5px");

  var email = $("#email_adress").val();
  if(isValidEmailAddress(email)){

    $.ajax({
      type: "POST",
      headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
      },
      dataType: "json",
      url: "http://stolktalktommy.nuui2tmhaj.eu-central-1.elasticbeanstalk.com/register",
      data: JSON.stringify({'email':email}),
      success: function(response){
        alert(JSON.stringify(response));

        if(response == "OK"){
          $("#send_button").html("Tack!");
        }
        else{
          alert("Ett fel uppstod")
          location.reload();
        }
      }
    });
  }
  else{
    alert("Epost-adressen var inte giltig, försök igen.")
    location.reload();
  }
});

function isValidEmailAddress(emailAddress) {
    var pattern = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
    return pattern.test(emailAddress);
};
