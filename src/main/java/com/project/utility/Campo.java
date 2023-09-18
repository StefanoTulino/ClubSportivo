package com.project.utility;

import java.sql.Blob;

import com.project.dto.TipologiaCampoDTO;

public class Campo {
	
	private int id;
	private String dettaglio;
	private float costo;
	private int prenotabile;
	private TipologiaCampoDTO tipologiaCampo;
	private Blob  img;
	
	
	public Campo() {
		
	}
	
	
	
	public Campo(int n,String d,float c,int prenot,Blob img,TipologiaCampoDTO t) {
		this.id=n;
		this.dettaglio=d;
		this.costo=c;
		this.prenotabile=prenot;
		this.img=img;
		this.tipologiaCampo=t;
	}
	
	
	
	public Campo(int n,String d,float c,Blob img,TipologiaCampoDTO t) {
		this.id=n;
		this.dettaglio=d;
		this.costo=c;
		this.img=img;
		this.tipologiaCampo=t;
	}
	
	
	public Campo(int n,String d,float c,TipologiaCampoDTO t) {
		this.id=n;
		this.dettaglio=d;
		this.costo=c;
		this.tipologiaCampo=t;
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

	public TipologiaCampoDTO getTipologiaCampo() {
		return tipologiaCampo;
	}

	public void setTipologiaCampo(TipologiaCampoDTO tipologiaCampo) {
		this.tipologiaCampo = tipologiaCampo;
	}

	public Blob getImg() {
		return img;
	}

	public void setImg(Blob img) {
		this.img = img;
	}


	public int getPrenotabile() {
		return prenotabile;
	}



	public void setPrenotabile(int prenotabile) {
		this.prenotabile = prenotabile;
	}



	@Override
	public String toString() {
		return "CampoUtility [id=" + id + ", dettaglio=" + dettaglio + ", costo=" + costo + ", tipologia=" + tipologiaCampo
				+ ", img=" + img;
	}

}
