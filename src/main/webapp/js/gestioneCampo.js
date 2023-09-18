//Funziona ma mi fa scrivere dentro e se non corrisponde a num,mette spazio vuoto
function onlyNumber(input){
	var num=/[^0-9]/gi;
	input.value=input.value.replace(num,"");
}

//SOLO NUMERI INTERI e decimali, ma senza virgola
		function isNumberKey(evt){
		    var charCode = (evt.which) ? evt.which : evt.keyCode
		    		if (charCode > 31 && (charCode != 46 &&(charCode < 48 || charCode > 57)))
		        return false;
		    return true;
		}


function checkTipologia(id){
	var a=document.getElementById("menuTipologia"+id).value;
	document.getElementById("tipo"+id).value= a;
	//checkButtonTipologia(id);
}


function checkPrezzo(id){
	var a=document.getElementById("costoCampo"+id).value;
	document.getElementById("prezzo"+id).value= a;
	//checkButtonTipologia(id);
}



/*function checkButtonTipologia(id){
	let option1= document.getElementById("menuTipologia"+id).value;
	//let option2= document.getElementById("costoCampo"+id).value;
	let b= document.getElementById("linkBtnUpdate"+id);
	//let c= document.getElementById("linkBtnUpdate"+id);
	
	if(option1==0 ){
		b.style.pointerEvents='none';
	} else {
		b.style.pointerEvents='auto';
	}
		
}*/