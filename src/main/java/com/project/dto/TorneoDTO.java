package com.project.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class TorneoDTO {

	private int id;
	private String nome;
	private Timestamp dataInizio;
	private Timestamp dataFine;
	private int numeroSquadre;
	//CAMPIONATO,ELIMINAZIONE DIRETTA,ECC.
	private int tipologiaTorneo;
	
	
	public TorneoDTO() {
		
	}
	
	
	public TorneoDTO(int i,String n,Timestamp dI,Timestamp dF,int num,int tip) {
		this.id=i;
		this.nome=n;
		this.dataInizio=dI;
		this.dataFine=dF;
		this.numeroSquadre=num;
		this.tipologiaTorneo=tip;
	}
	
	
	public TorneoDTO(int i,String n,Timestamp dI,Timestamp dF,int num) {
		this.id=i;
		this.nome=n;
		this.dataInizio=dI;
		this.dataFine=dF;
		this.numeroSquadre=num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Timestamp getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Timestamp dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Timestamp getDataFine() {
		return dataFine;
	}

	public void setDataFine(Timestamp dataFine) {
		this.dataFine = dataFine;
	}

	public int getNumeroSquadre() {
		return numeroSquadre;
	}

	public void setNumeroSquadre(int numeroSquadre) {
		this.numeroSquadre = numeroSquadre;
	}


	public int getTipologiaTorneo() {
		return tipologiaTorneo;
	}


	public void setTipologiaTorneo(int tipologiaTorneo) {
		this.tipologiaTorneo = tipologiaTorneo;
	}


	@Override
	public String toString() {
		return "TorneoDTO [id=" + id + ", nome=" + nome + ", dataInizio=" + dataInizio + ", dataFine=" + dataFine
				+ ", numeroSquadre=" + numeroSquadre + ", tipologiaTorneo=" + tipologiaTorneo + "]";
	}

	
	
	
	
//	@Override
//	public String toString() {
//		return "TorneoDTO2 [id=" + id + ", nome=" + nome + ", dataInizio=" + dataInizio + ", dataFine=" + dataFine
//				+ ", numSquadre=" + numeroSquadre + "]";
//	}
	
}
