package com.project.utility;

import java.sql.Blob;

public class Squadra {

	private String email;
	private String nome;
	private Blob logo;
	private int partiteGiocate;
	private int vittoria;
	private int pareggio;
	private int sconfitta;
	private int puntiTotali;
	
	
	public Squadra() {
		
	}
	
	
	public Squadra(String email) {
		this.email=email;
	}
	

	public Squadra(String em,String nome,Blob logo, int partiteGiocate, int vittoria, int pareggio, int sconfitta, int puntiTotali) {
		this.email=em;
		this.nome = nome;
		this.logo=logo;
		this.partiteGiocate = partiteGiocate;
		this.vittoria = vittoria;
		this.pareggio = pareggio;
		this.sconfitta = sconfitta;
		this.puntiTotali = puntiTotali;
	}
	
	
	public Squadra(String email, int partiteGiocate, int vittoria, int pareggio, int sconfitta, int puntiTotali) {
		this.email = email;
		this.partiteGiocate = partiteGiocate;
		this.vittoria = vittoria;
		this.pareggio = pareggio;
		this.sconfitta = sconfitta;
		this.puntiTotali = puntiTotali;
	}


	
	


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getPartiteGiocate() {
		return partiteGiocate;
	}


	public void setPartiteGiocate(int partiteGiocate) {
		this.partiteGiocate = partiteGiocate;
	}


	public int getVittoria() {
		return vittoria;
	}


	public void setVittoria(int vittoria) {
		this.vittoria = vittoria;
	}


	public int getPareggio() {
		return pareggio;
	}


	public void setPareggio(int pareggio) {
		this.pareggio = pareggio;
	}


	public int getSconfitta() {
		return sconfitta;
	}


	public void setSconfitta(int sconfitta) {
		this.sconfitta = sconfitta;
	}


	public int getPuntiTotali() {
		return puntiTotali;
	}


	public void setPuntiTotali(int puntiTotali) {
		this.puntiTotali = puntiTotali;
	}
	
	
	public Blob getLogo() {
		return logo;
	}

	public void setLogo(Blob logo) {
		this.logo = logo;
	}

	
	public void reset() {
		this.nome = "";
		this.partiteGiocate = 0;
		this.vittoria = 0;
		this.pareggio = 0;
		this.sconfitta = 0;
		this.puntiTotali = 0;
	}
	
	
	

	@Override
	public String toString() {
		return "Email=" + email + ", Nome=" + nome + ", Partite Giocate=" + partiteGiocate + ", Vittoria=" + vittoria + ", Pareggio="
				+ pareggio + ", Sconfitta=" + sconfitta + ", Punti Totali=" + puntiTotali;
	}



//	@Override
//	public boolean equals(Object obj) {
//		if (obj == null) {
//            return false;
//        }
//		
//		if (!(obj instanceof Squadra)) {
//	        return false;
//		}
//		
//	    Squadra s = (Squadra) obj;
//	    if(!(s.getNome().equals(this.nome))) {
//	    	return false;
//	    }
//	    
//	 return true;
//	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	//OK
//	@Override
//	public boolean equals(Object obj) {
////		if(obj == this) {
////			return true;
////		}
//		
////		if(!(obj instanceof String)) {
////			return false;
////		}
//		
//		if (obj instanceof String) {
//			Squadra s = (Squadra) obj;
//            String nome =  s.getNome();
//            return this.nome == s.nome;
//        }
//		
//		// typecast o to Complex so that we can compare data members
//        Squadra s = (Squadra) obj;
////        String nome= s.getNome();
//
//         
//        // Compare the data members and return accordingly
////        return String.equals(nome, s.nome) == 0
////                && Double.compare(im, c.im) == 0;
//	return false;
//	}
	
	
	
	
	
	
	
	

}
