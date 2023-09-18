package com.project.utility;

import java.util.HashMap;
import java.util.List;



public class TorneoEliminazione {
	
//	private List<Squadra> listaSquadra;
	private List<HashMap<String,Integer>> listaSquadra;
	private int numColonna;
	private List<String> squadreVincenti;

	public TorneoEliminazione() {
		super();
	}

	

	public TorneoEliminazione(List<HashMap<String,Integer>> listaSquadra, int numColonna, List<String> squadreVincenti) {
		super();
		this.listaSquadra = listaSquadra;
		this.numColonna = numColonna;
		this.squadreVincenti = squadreVincenti;
	}



	public List<HashMap<String,Integer>> getListaSquadra() {
		return listaSquadra;
	}

	public void setListaSquadra(List<HashMap<String,Integer>> listaSquadra) {
		this.listaSquadra = listaSquadra;
	}
	
	public int getNumColonna() {
		return numColonna;
	}

	public void setNumColonna(int numColonna) {
		this.numColonna = numColonna;
	}



	public List<String> getSquadreVincenti() {
		return squadreVincenti;
	}



	public void setSquadreVincenti(List<String> squadreVincenti) {
		this.squadreVincenti = squadreVincenti;
	}



	@Override
	public String toString() {
		return "TorneoEliminazione [listaSquadra=" + listaSquadra + ", numColonna=" + numColonna + "] ";
//				+ "squadreVincenti="+ squadreVincenti + "]";
	}	

}