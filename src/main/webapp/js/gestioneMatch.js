/* 		NON FUNZIONA CAUSA BLOCCO LISTA AL DI FUORI DEL DIV */
		function viewMatch(){
			 var t = document.getElementById("id02");
			    t.style.display = "block";  // <-- Set it to block
		}
		
			/* OK FUNZIONA */
			function viewMatchFuturi(){
				 var t = document.getElementById("id03");
				 var divMatch=document.getElementById("id02").style.display="none";
				 var divMatchList=document.getElementById("id02-lista").style.display="none";
				    t.style.display = "block";  // <-- Set it to block
			}
			
			function viewMatchLista(){
				var s= document.getElementById("btnMatchTorneoForm");
				/*alert(s);*/
				/*if(document.getElementById("btnMatchTorneoForm").clicked == true){*/
					if(s!= undefined){
						var divMatchFuturi=document.getElementById("id03").style.display="none";
						var t = document.getElementById("id02-lista");
						t.style.display="block";
											
					}
					
				/*}*/
			}