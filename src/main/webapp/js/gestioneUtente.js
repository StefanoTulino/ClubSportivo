

function checkTipologia(email){
	var a=document.getElementById("menuTipologia"+email).value;
	document.getElementById("tipo"+email).value= a;
	checkButtonTipologia(email);
}

function checkButtonTipologia(email){
	let option= document.getElementById("menuTipologia"+email).value;
	let b= document.getElementById("linkBtnTipologia"+email);
	
	if(option==0){
		b.style.pointerEvents='none';
	} else {
		b.style.pointerEvents='auto';
	}
		
}


/*function checkButtonTipologia(email){
	let option= document.getElementById("menuTipologia"+email).value;
	let b= document.getElementById("btnTipologia"+email);
	if(option==0){
		b.setAttribute('disabled','disabled');
		}
		
	}*/