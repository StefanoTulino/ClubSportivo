function myFunctionGestione() {
			  document.getElementById("GestionemyDropdown1").classList.toggle("show");
			}
		
		function myFunctionPrenotazione() {
			  document.getElementById("GestionemyDropdown2").classList.toggle("show");
			}

			// Close the dropdown if the user clicks outside of it
			window.onclick = function(event) {
			  if (!event.target.matches('.dropGestionebtn')) {
			    var dropdowns = document.getElementsByClassName("dropdown-content");
			    var i;
			    for (i = 0; i < dropdowns.length; i++) {
			      var openDropdown = dropdowns[i];
			      if (openDropdown.classList.contains('show')) {
			        openDropdown.classList.remove('show');
			      }
			    }
			  }
			}