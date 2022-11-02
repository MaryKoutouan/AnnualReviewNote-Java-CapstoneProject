function includeHTML() {
  var z, i, elmnt, file, xhttp;
  /*loop through a collection of all HTML elements:*/
  z = document.getElementsByTagName("*");
  for (i = 0; i < z.length; i++) {
    elmnt = z[i];
    /*search for elements with a certain atrribute:*/
    file = elmnt.getAttribute("w3-include-html");
    if (file) {
      /*make an HTTP request using the attribute value as the file name:*/
      xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
          if (this.status == 200) {elmnt.innerHTML = this.responseText;}
          if (this.status == 404) {elmnt.innerHTML = "Page not found.";}
          /*remove the attribute, and call this function once more:*/
          elmnt.removeAttribute("w3-include-html");
          includeHTML();
        }
      }
      xhttp.open("GET", file, true);
      xhttp.send();
      /*exit the function:*/
      return;
    }
  }
};

const userConnected = document.cookie.split("=")[1];

if (userConnected) {
    if (document.getElementById('LoginButton').style.display === "block") {
        document.getElementById('LoginButton').style.display = "none";
        document.getElementById('LogoutButton').style.display = "block";
    } else {
        document.getElementById('LoginButton').style.display = "block"
        document.getElementById('LogoutButton').style.display = "none";
    }
}

async function checkUserCompany(userId) {
 await fetch('http://localhost:8080/api/professionalinformation/users/' + userId,{
                  method: "GET",
                  headers: {'Content-Type':'application/json'}
              }).then(response => response.json()).then(data => {
              if (data.status === 400 || data.length === 0) {
                document.getElementById('form-createCompany').style.display = "block";
              } else {
               document.getElementById('form-createnote').style.display = "block";
              }
              console.log(data)
              }).catch(err => console.error(err))
}


