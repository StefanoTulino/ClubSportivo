package com.project.utility;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import com.project.dto.PrenotazioneDTO;
import com.project.dto.UtenteDTO;

public class UtenteSession implements Serializable {
	private static final long serialVersionUID = 7495005104972328788L;
	
	private String email;
	private String nome;
	private String cognome;
	private int tipologia;
	private List<PrenotazioneDTO> listaPrenotazione=new ArrayList<>();
	private Blob logo;
	
	
	public UtenteSession(String e,String n,String c,int t,List<PrenotazioneDTO> l,Blob logo) {
		this.email=e;
		this.nome=n;
		this.cognome=c;
		this.tipologia=t;
		this.listaPrenotazione=l;
		this.logo= logo;
	}
	
	
	public UtenteSession(String e,String n,String c,int t) {
		this.email=e;
		this.nome=n;
		this.cognome=c;
		this.tipologia=t;
	}
	

	public UtenteSession(String e,String n,String c,int t,List<PrenotazioneDTO> l) {
		this.email=e;
		this.nome=n;
		this.cognome=c;
		this.tipologia=t;
		this.listaPrenotazione=l;
	}
	
	
	public UtenteSession(UtenteDTO u) {
		this.email=u.getEmail();
		this.nome=u.getNome();
		this.cognome=u.getCognome();
		this.tipologia=u.getTipologiaUtente();
	}

	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTipologia() {
		return tipologia;
	}

	public void setTipologia(int tipologia) {
		this.tipologia = tipologia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<PrenotazioneDTO> getListaPrenotazione() {
		return listaPrenotazione;
	}

	public void setListaPrenotazione(List<PrenotazioneDTO> listaPrenotazione) {
		this.listaPrenotazione = listaPrenotazione;
	}

	public Blob getLogo() {
		return logo;
	}

	public void setLogo(Blob logo) {
		this.logo = logo;
	}


	
	@Override
	public String toString() {
		return "UtenteSession [email=" + email + ", nome=" + nome + ", cognome=" + cognome + ", tipologia=" + tipologia
				+ ", listaPrenotazione=" + listaPrenotazione + ", logo=" + logo + "]";
	}
	
}
