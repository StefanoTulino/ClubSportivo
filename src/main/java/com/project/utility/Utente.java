package com.project.utility;

import java.sql.Blob;

import com.project.dto.TipologiaUtenteDTO;

public class Utente {
	
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private TipologiaUtenteDTO tipologiaUtente;
	private Blob logo;
	
	
	public Utente(String e,String p,String n,String c,TipologiaUtenteDTO t,Blob logo) {
		this.email=e;
		this.password=p;
		this.nome=n;
		this.cognome=c;
		this.tipologiaUtente=t;
		this.logo=logo;
	}
	
	
	
	public Utente(String e,String p,String n,String c,TipologiaUtenteDTO t) {
		this.email=e;
		this.password=p;
		this.nome=n;
		this.cognome=c;
		this.tipologiaUtente=t;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	public TipologiaUtenteDTO getTipologiaUtente() {
		return tipologiaUtente;
	}


	public void setTipologiaUtente(TipologiaUtenteDTO tipologiaUtente) {
		this.tipologiaUtente = tipologiaUtente;
	}

	public Blob getLogo() {
		return logo;
	}

	public void setLogo(Blob logo) {
		this.logo = logo;
	}
	
	

}
