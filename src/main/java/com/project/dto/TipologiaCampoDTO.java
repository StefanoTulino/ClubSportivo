package com.project.dto;

import java.sql.Blob;

public class TipologiaCampoDTO {

	private int id;
	private String descrizione;
	private float incremento;
	private Blob img;
	
	
	public TipologiaCampoDTO() {
		
	}
	
	public TipologiaCampoDTO(int i,String d,float incr,Blob img) {
		this.id=i;
		this.descrizione=d;
		this.incremento=incr;
		this.img=img;
	}
	
	
	public TipologiaCampoDTO(int i,String d) {
		this.id=i;
		this.descrizione=d;
	}
	
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public float getIncremento() {
		return incremento;
	}
	public void setIncremento(float incremento) {
		this.incremento = incremento;
	}
	public Blob getImg() {
		return img;
	}
	public void setImg(Blob img) {
		this.img = img;
	}
	
	
	
	
	
	
	
	
	
}
