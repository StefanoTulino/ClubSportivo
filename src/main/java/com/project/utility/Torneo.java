//NON è un Dto in quanto perde l'ora con java.sql.Date
package com.project.utility;

import java.sql.Date;
import java.sql.Timestamp;

public class Torneo {
	
	private int id;
	private String nome;
	private Timestamp dataInizio;
	private Timestamp dataFine;
	private int numSquadre;
	private int postiDisponibili;
	private int idCampo;
	
	public Torneo() {
		
	}
	
	
	public Torneo(int i,String n,Timestamp dI,Timestamp dF,int num,int disp,int idC) {
		this.id=i;
		this.nome=n;
		this.dataInizio=dI;
		this.dataFine=dF;
		this.numSquadre=num;
		this.postiDisponibili=disp;
		this.idCampo=idC;
	}
	
	
	
	
	
	public Torneo(int i,String n,Timestamp dI,Timestamp dF,int num,int idC) {
		this.id=i;
		this.nome=n;
		this.dataInizio=dI;
		this.dataFine=dF;
		this.numSquadre=num;
		this.idCampo=idC;
	}
	
	
	public Torneo(int i,String n,Timestamp dI,Timestamp dF,int num) {
		this.id=i;
		this.nome=n;
		this.dataInizio=dI;
		this.dataFine=dF;
		this.numSquadre=num;
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


	public int getNumSquadre() {
		return numSquadre;
	}

	public void setNumSquadre(int numSquadre) {
		this.numSquadre = numSquadre;
	}

	public int getIdCampo() {
		return idCampo;
	}

	public void setIdCampo(int idCampo) {
		this.idCampo = idCampo;
	}


	public int getPostiDisponibili() {
		return postiDisponibili;
	}


	public void setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}


	
	@Override
	public String toString() {
		return "Torneo [id=" + id + ", nome=" + nome + ", dataInizio=" + dataInizio + ", dataFine=" + dataFine
				+ ", numSquadre=" + numSquadre + ", postiDisponibili=" + postiDisponibili + ", idCampo=" + idCampo
				+ "]";
	}

		

}
