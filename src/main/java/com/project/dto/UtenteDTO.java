package com.project.dto;

import java.io.Serializable;
import java.sql.Blob;

public class UtenteDTO implements Serializable {

	private static final long serialVersionUID = -6503270421032516499L;
	
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private int tipologiaUtente;
	private Blob logo;
	
	
	
	public UtenteDTO() {
		
	}
	
	
	public UtenteDTO(String e,String p,String n,String c,int tip,Blob logo) {
		this.email=e;
		this.password=p;
		this.nome=n;
		this.cognome=c;
		this.tipologiaUtente=tip;
		this.logo=logo;
	}
	
	
	
	public UtenteDTO(String e,String p,String n,String c,int tip) {
		this.email=e;
		this.password=p;
		this.nome=n;
		this.cognome=c;
		this.tipologiaUtente=tip;
	}


	public UtenteDTO(UtenteDTO u) {
		this.email=u.getEmail();
		this.password=u.getPassword();
		this.nome=u.getNome();
		this.cognome=u.getCognome();
		this.tipologiaUtente= u.getTipologiaUtente()!=0 ? u.getTipologiaUtente() : 1; 
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


	public int getTipologiaUtente() {
		return tipologiaUtente;
	}


	public void setTipologiaUtente(int tipologiaUtente) {
		this.tipologiaUtente = tipologiaUtente;
	}


	public Blob getLogo() {
		return logo;
	}

	public void setLogo(Blob logo) {
		this.logo = logo;
	}


	@Override
	public String toString() {
		return "UtenteDTO [email=" + email + ", password=" + password + ", nome=" + nome + ", cognome=" + cognome
				+ ", tipologiaUtente=" + tipologiaUtente + ", logo=" + logo + "]";
	}
	
	
	
	
	

}
