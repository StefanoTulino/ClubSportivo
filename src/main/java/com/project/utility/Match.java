package com.project.utility;

import java.sql.Date;

public class Match {
	private String torneo;
	private String tipologiaTorneoNome;
	private Date data;
	private String risultato;
	private String risultatoSfidante1;
	private String risultatoSfidante2;
	private String sfidante1;
	private String sfidante2;
	private int campo;

	public Match(String torneo, Date data, String risultato, String risultato1, String risultato2, String sfidante1,
			String sfidante2, int campo) {
		this.torneo = torneo;
		this.data = data;
		this.risultato = risultato;
		this.risultatoSfidante1 = risultato1;
		this.risultatoSfidante2 = risultato2;
		this.sfidante1 = sfidante1;
		this.sfidante2 = sfidante2;
		this.campo = campo;
	}

	public Match(String torneo,String tipologiaTorneoN, Date data, String risultato, String sfidante1, String sfidante2, int campo) {
		this.torneo = torneo;
		this.tipologiaTorneoNome= tipologiaTorneoN;
		this.data = data;
		this.risultato = risultato;
		this.sfidante1 = sfidante1;
		this.sfidante2 = sfidante2;
		this.campo = campo;
	}
	
	
	public Match(String torneo, String risultato, String risultatoSfidante1, String risultatoSfidante2,
			String nomeSfidante1, String nomeSfidante2) {
		super();
		this.torneo = torneo;
		this.risultato = risultato;
		this.risultatoSfidante1 = risultatoSfidante1;
		this.risultatoSfidante2 = risultatoSfidante2;
		this.sfidante1 = nomeSfidante1;
		this.sfidante2 = nomeSfidante2;
		
	}

	
	
	public String getTorneo() {
		return torneo;
	}

	public void setTorneo(String torneo) {
		this.torneo = torneo;
	}

	public String getTipologiaTorneoNome() {
		return tipologiaTorneoNome;
	}

	public void setTipologiaTorneoNome(String tipologiaTorneoNome) {
		this.tipologiaTorneoNome = tipologiaTorneoNome;
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

	public int getCampo() {
		return campo;
	}

	public void setCampo(int campo) {
		this.campo = campo;
	}

	
//	@Override
//	public String toString() {
//		return "Match [torneo=" + torneo + ", data=" + data + ", risultato=" + risultato + ", risultato1=" + risultato1
//				+ ", risultato2=" + risultato2 + ", sfidante1=" + sfidante1 + ", sfidante2=" + sfidante2 + ", campo="
//				+ campo + "]";
//	}
	
	
	@Override
	public String toString() {
		return  torneo + ", tipologia Torneo:"+tipologiaTorneoNome+", in Data:" + data + ", con il seguente Risultato:" + risultato + ". Sfidante1=" + sfidante1 + ","
				+ " Sfidante2=" + sfidante2 + ", Campo="+ campo + "] ";
	}

	
}
