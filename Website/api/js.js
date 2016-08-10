var pathname = window.location.pathname; // Returns path only
var url      = window.location.href;     // Returns full URL

function Redirect() {
    window.location="http://www.tutorialspoint.com";
 }
            
    document.write("You will be redirected to main page in 10 sec. " + url + "......." + pathname);
 	setTimeout('Redirect()', 10000);
