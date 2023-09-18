package com.project.dto;

public class TipologiaUtenteDTO {
	
	private int id;
	private String nome;
	private String descrizione;
	
	public TipologiaUtenteDTO(int i,String n,String d) {
		this.id=i;
		this.nome=n;
		this.descrizione=d;
	}

	public TipologiaUtenteDTO() {
		
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	

}
