package com.project.dto;

public class CampoDTO {
	
	private int id;
	private float prezzo;
	private int prenotabile;
	private int tipologia;
	
	public CampoDTO() {
		
	}
	
	
	public CampoDTO(int i,float p,int prenot,int t) {
		this.id=i;
		this.prezzo=p;
		this.prenotabile=prenot;
		this.tipologia=t;
	}
	
	
	public CampoDTO(int i,float p,int t) {
		this.id=i;
		this.prezzo=p;
		this.tipologia=t;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public int getTipologia() {
		return tipologia;
	}

	public void setTipologia(int tipologia) {
		this.tipologia = tipologia;
	}
	
	
	

	public int getPrenotabile() {
		return prenotabile;
	}


	public void setPrenotabile(int prenotabile) {
		this.prenotabile = prenotabile;
	}


	@Override
	public String toString() {
		return "CampoDTO [id=" + id + ", prezzo=" + prezzo + ", prenotabile=" + prenotabile + ", tipologia=" + tipologia
				+ "]";
	}

}
