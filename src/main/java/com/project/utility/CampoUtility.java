package com.project.utility;

import java.sql.Blob;

public class CampoUtility {
	
	private int id;
	private String dettaglio;
	private float costo;
	private float prezzoScontato;
	private int tipologia;
	private Blob  img;
	private boolean disponibilità;
	
	public CampoUtility() {
		
	}
	
	

	public CampoUtility(int n,String d,float c,float p,int tipCampo,Blob img,boolean disp) {
		this.id=n;
		this.dettaglio=d;
		this.costo=c;
		this.prezzoScontato=p;
		this.tipologia=tipCampo;
		this.img=img;
		this.disponibilità=disp;
	}
	
	
	public CampoUtility(int n,String d,float c,int tipCampo,Blob img,boolean disp) {
		this.id=n;
		this.dettaglio=d;
		this.costo=c;
		this.tipologia=tipCampo;
		this.img=img;
		this.disponibilità=disp;
	}
	

	
	public CampoUtility(int n,String d,float c,int tipCampo,boolean disp) {
		this.id=n;
		this.dettaglio=d;
		this.costo=c;
		this.tipologia=tipCampo;
		this.disponibilità=disp;
	}
	
	
	
	public CampoUtility(int n,String d,float c,int tipCampo,Blob img) {
		this.id=n;
		this.dettaglio=d;
		this.costo=c;
		this.tipologia=tipCampo;
		this.img=img;
	}
	
	
	public CampoUtility(int n,String d,float c,Blob img) {
		this.id=n;
		this.dettaglio=d;
		this.costo=c;
		this.img=img;
	}
	
	
	public CampoUtility(int n,String d,float c) {
		this.id=n;
		this.dettaglio=d;
		this.costo=c;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getDettaglio() {
		return dettaglio;
	}



	public void setDettaglio(String dettaglio) {
		this.dettaglio = dettaglio;
	}



	public float getCosto() {
		return costo;
	}



	public void setCosto(float costo) {
		this.costo = costo;
	}



	public int getTipologia() {
		return tipologia;
	}



	public void setTipologia(int tipologia) {
		this.tipologia = tipologia;
	}



	public Blob getImg() {
		return img;
	}



	public void setImg(Blob img) {
		this.img = img;
	}



	public boolean isDisponibilità() {
		return disponibilità;
	}



	public void setDisponibilità(boolean disponibilità) {
		this.disponibilità = disponibilità;
	}


	public float getPrezzoScontato() {
		return prezzoScontato;
	}



	public void setPrezzoScontato(float prezzoScontato) {
		this.prezzoScontato = prezzoScontato;
	}



	@Override
	public String toString() {
		return "CampoUtility [id=" + id + ", dettaglio=" + dettaglio + ", costo=" + costo + ", prezzoScontato="
				+ prezzoScontato + ", tipologia=" + tipologia + ", img=" + img + ", disponibilità=" + disponibilità
				+ "]";
	}

}
