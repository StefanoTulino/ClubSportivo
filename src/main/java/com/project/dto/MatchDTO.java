package com.project.dto;

import java.sql.Date;

public class MatchDTO {

	private int id;
	private Date data;
	private String risultato;
	private String risultatoSfidante1;
	private String risultatoSfidante2;
	private int idTorneo;
	private String sfidante1;
	private String sfidante2;
	
	
	public MatchDTO() {
		
	}


	public MatchDTO(int id, Date data, String risultato,String risultatoSfidante1,String risultatoSfidante2,
			int idTorneo, String sfidante1, String sfidante2) {
		super();
		this.id = id;
		this.data = data;
		this.risultato = risultato;
		this.risultatoSfidante1=risultatoSfidante1;
		this.risultatoSfidante2=risultatoSfidante2;
		this.idTorneo = idTorneo;
		this.sfidante1 = sfidante1;
		this.sfidante2 = sfidante2;
	}
	
	
	public MatchDTO(int id, Date data, String risultato, int idTorneo, String sfidante1, String sfidante2) {
		super();
		this.id = id;
		this.data = data;
		this.risultato = risultato;
		this.idTorneo = idTorneo;
		this.sfidante1 = sfidante1;
		this.sfidante2 = sfidante2;
	}

	
	public MatchDTO(Date data, String risultato, int idTorneo, String sfidante1, String sfidante2) {
		super();
		this.id = id;
		this.data = data;
		this.risultato = risultato;
		this.idTorneo = idTorneo;
		this.sfidante1 = sfidante1;
		this.sfidante2 = sfidante2;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public String getRisultato() {
		return risultato;
	}


	public void setRisultato(String risultato) {
		this.risultato = risultato;
	}


	public int getIdTorneo() {
		return idTorneo;
	}


	public void setIdTorneo(int idTorneo) {
		this.idTorneo = idTorneo;
	}


	public String getSfidante1() {
		return sfidante1;
	}


	public void setSfidante1(String sfidante1) {
		this.sfidante1 = sfidante1;
	}


	public String getSfidante2() {
		return sfidante2;
	}


	public void setSfidante2(String sfidante2) {
		this.sfidante2 = sfidante2;
	}

	public String getRisultatoSfidante1() {
		return risultatoSfidante1;
	}


	public void setRisultatoSfidante1(String risultatoSfidante1) {
		this.risultatoSfidante1 = risultatoSfidante1;
	}


	public String getRisultatoSfidante2() {
		return risultatoSfidante2;
	}


	public void setRisultatoSfidante2(String risultatoSfidante2) {
		this.risultatoSfidante2 = risultatoSfidante2;
	}


	@Override
	public String toString() {
		if(risultato==null) {
			risultato="DA DISPUTARE";
			risultatoSfidante1="-";
			risultatoSfidante2="-";
		}
		return "Id=" + id + ", Data=" + data + ", Risultato=" + risultato + ", Risultato Sfidante1="
				+ risultatoSfidante1 + ", Risultato Sfidante2=" + risultatoSfidante2 + ", idTorneo=" + idTorneo
				+ ", Sfidante1=" + sfidante1 + ", Sfidante2=" + sfidante2;
	}
	
	

}
