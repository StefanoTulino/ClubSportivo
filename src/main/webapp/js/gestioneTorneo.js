//ST new (non viene mai usata purtroppo)
function hideTitleTorneiAttivi(){
	var t= document.getElementById("titleTorneiAttivi").style.display='none';
}


function viewAlltornei(){
	var t = document.getElementById("div02");
	var home= document.getElementById("home").style.display='none';
	var torneiModificabili= document.getElementById("div03").style.display='none';
	t.style.display = "block";  // <-- Set it to block
	}


function torneiModificabili(){
	var t = document.getElementById("div03");
	var home= document.getElementById("home").style.display='none';
	var torneiAll= document.getElementById("div02").style.display='none';
	t.style.display = "block";  // <-- Set it to block
	}


function checkDataInizio(id) {
	var a = document.getElementById("editDataInizio" + id).value;
	
	//QUI NON METTIAMO .value, altrimenti esplode tutto visto che già abbiamo un value di default settato
	//tale risultato infatti è già un valore
	var b = document.getElementById("dataInizio1" + id);
	b.value=a;
	checkModifica(id);
	}


function checkDataFine(id) {
	//Prende il valore dalla tabella e lo mette in una variabile a
	var a = document.getElementById("editDataFine" + id).value;
	//salvato il valore nella precedente variabile,lo associamo all'input type di tipo hidden che usiamo nel form
	var b= document.getElementById("dataFine1" + id);
	b.value=a;
	checkModifica(id);
	}




function checkModifica(id){
	/*var dI= document.getElementById("dataInizio1" + id);*/
	//A e DI sono sempre uguali dopo aver chiamato le funzioni di prima
	var btnImage= document.getElementById("btnImage"+ id);
	btnImage.removeAttribute("disabled");
	}



















/*function checkBtnCalendario(id){
	let option= document.getElementById("postiDisponibili"+id).value;
	let b= document.getElementById("linkBtnCalendario"+id);
	
	//Rispetto a gestioneUtente, qui se la quantità è 0, allora vuol dire che si è raggiunto il nr. necessario
	//di squadre e che quindi è possibile creare un calendario
	if(option==0){
		b.style.pointerEvents='auto';
		} else {
			b.style.pointerEvents='none';
		}

	}*/

