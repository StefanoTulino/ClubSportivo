package com.project.dto;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PrenotazioneDTO {
	
	private int id;
	private Date data_inizio;
	private Date data_fine;
	private String utente;
	private int idCampo;
	private int idTorneo;
	
	public PrenotazioneDTO() {
		
	}
	
	public PrenotazioneDTO(int i,Date di,Date df,String u,int ic,int it) {
		this.id=i;
		this.data_inizio=di;
		this.data_fine=df;
		this.utente=u;
		this.idCampo=ic;
		this.idTorneo=it;
	}

	public PrenotazioneDTO(int i, Date di, Date df, String u, int ic) {
		this.id=i;
		this.data_inizio=di;
		this.data_fine=df;
		this.utente=u;
		this.idCampo=ic;
	}
	
	
	public PrenotazioneDTO( Date di, Date df, int ic) {
		this.data_inizio=di;
		this.data_fine=df;
		this.idCampo=ic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData_inizio() {
		return data_inizio;
	}

	public void setData_inizio(Date data_inizio) {
		this.data_inizio = data_inizio;
	}

	public Date getData_fine() {
		return data_fine;
	}

	public void setData_fine(Date data_fine) {
		this.data_fine = data_fine;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public int getIdCampo() {
		return idCampo;
	}

	public void setIdCampo(int idCampo) {
		this.idCampo = idCampo;
	}

	public int getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(int idTorneo) {
		this.idTorneo = idTorneo;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime ldate = LocalDateTime.ofInstant(Instant.ofEpochMilli(data_inizio.getTime()), ZoneId.systemDefault());
	    String dataInizio =  ldate.format(formatter);
	    LocalDateTime ldate2 = LocalDateTime.ofInstant(Instant.ofEpochMilli(data_fine.getTime()), ZoneId.systemDefault());
	    String dataFine =  ldate2.format(formatter);
		
		
		
		return " con data di inizio:" + dataInizio+ " e data di fine:" + dataFine 
				+ ".\n";
	}
	
	

}
