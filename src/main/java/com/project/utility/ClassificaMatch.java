package com.project.utility;

import java.util.List;

public class ClassificaMatch {
	private String torneo;
	private List<Squadra> squadra;
	
	
	
	
	public ClassificaMatch() {
		
	}




	public ClassificaMatch(String torneo, List<Squadra> squadra) {
		this.torneo = torneo;
		this.squadra = squadra;
	}

	
	public ClassificaMatch(List<Squadra> squadra) {
		this.squadra = squadra;
	}



	public String getTorneo() {
		return torneo;
	}




	public void setTorneo(String torneo) {
		this.torneo = torneo;
	}




	public List<Squadra> getSquadra() {
		return squadra;
	}




	public void setSquadra(List<Squadra> squadra) {
		this.squadra = squadra;
	}




	@Override
	public String toString() {
		return "Classifica: [Squadra=" + squadra + "\n]\n";
	}


	
	
}
