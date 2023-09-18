function viewMatchLista(){
	var t = document.getElementById("id01-lista");
	t.style.display="block";								
	}
	

function valueTorneo(id){
	var b= document.getElementById("valoreTorneo");
	b.value=id;
}


	
function valueSquadra(id){
	var a= document.getElementById("tastoSquadra"+id).value;
	var b= document.getElementById("nomeSquadra");
	b.value=a;
}


function changeTitle(){
	var a= document.getElementById("titlePage1").value;
	alert(a);
	alert(a.value);
}