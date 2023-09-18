/*function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}*/


function openInfo(){
		document.getElementById("id03").style.display='block';
		/*document.getElementById('id03').style.display='block';*/
	}
	
function closeInfo(){
	document.getElementById("id03").style.display='none';
	/*document.getElementById('id03').style.display='none'*/
}


function openInfoAlert(){
		document.getElementById("id04").style.display='block';
		/*document.getElementById('id03').style.display='block';*/
	}
	
function closeInfoAlert(){
	document.getElementById("id04").style.display='none';
	/*document.getElementById('id03').style.display='none'*/
}
