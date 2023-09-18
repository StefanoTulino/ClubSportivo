package com.project.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import com.project.dto.CampoDTO;
import com.project.dto.MatchDTO;
import com.project.dto.PrenotazioneDTO;
import com.project.dto.TipologiaCampoDTO;
import com.project.dto.TipologiaTorneoDTO;
import com.project.dto.TipologiaUtenteDTO;
import com.project.dto.TorneoDTO;
import com.project.dto.UtenteDTO;
import com.project.exception.LoginException;
import com.project.exception.RegistrazioneException;
import com.project.utility.Campo;
import com.project.utility.CampoUtility;
import com.project.utility.ClassificaMatch;
import com.project.utility.Match;
import com.project.utility.Squadra;
import com.project.utility.Torneo;
import com.project.utility.TorneoEliminazione;
import com.project.utility.Utente;
import com.project.utility.UtenteSession;

public interface ProgettoService {

	public UtenteSession login(String e,String p) throws LoginException, SQLException;
	public UtenteSession registrazione(String email,String password,String nome,
			String cognome,boolean tipologia) throws RegistrazioneException, SQLException;
	
	//admin
	public Utente dettaglioUtente(String email) ;
	public UtenteDTO modificaPasswordUtente(String nuovaPassword,String email);
	List<Utente> gestioneUtenti2() ;
	List<TipologiaUtenteDTO> listaTipologia() ;
	public UtenteDTO modificaTipologiaUtente(int tipologiaUtente,String email);
	
	
	//CAMPO
	public List<CampoUtility> getAllInfocampi(String idUtente,String d);
	public byte[] getImageCampo(int id);
	public List<TipologiaCampoDTO> listaTipologiaCampo();
	public List<Campo> campiAll();
	public CampoDTO modificaPrezzoCampo(int id,float prezzo);
	public CampoDTO modificaTipologiaCampo(int id,int tipologia);
	public String addCampo(float prezzo, int tipologia);
	public String addTipologiaCampo(String descrizione, float incremento, InputStream i,int size);
	public String setStatusCampo(int id,int prenotabile);
	public CampoDTO modificheAdminCampo(int id,float prezzo,int tipologia) ;
	
	
	//PRENOTAZIONE
	public String addPrenotazione(String idUtente,int idCampo,String d);
	public String addPrenotazioneVip(String idUtente,int idCampo, String dI,String dF);
	public List<PrenotazioneDTO> prenotazioniAttiveForUtente(String email);
	
	public List<PrenotazioneDTO> prenotazioniError(String email);
	public String modificaPrenotazione(int idPrenotazione, int idCampo, String dataInizio,String dataFine);
	public String eliminaPrenotazione(int idPrenotazione);
	public List<CampoUtility> campiDisponibili(String data,String dataFine);
	
	//addPrenotazione with tipologia
	public List<CampoUtility> getAllInfocampiComplete(String idUtente,String d,String tipologia);
	
	
	//TORNEO Admin
	public List<Torneo> getAllTorneiAttivi();
	public List<TorneoDTO> listaTornei();
	public List<CampoUtility> getAllInfocampiForTorneo(String dI,String dF);
	public TorneoDTO modificheAdminTorneo(int idTorneo,String dI,String dF);
	public String addTorneo(String nome,String dataInizio,String dataFine,int numSquadra,int idCampo, int tipologiaTorneo);
	
	
	
	//TORNEO: tutto il resto
	public List<Torneo> TorneoVipDisponibili(String emailUtente);
	public List<CampoUtility> getAllInfocampiVipComplete(String dataInizio,String dataFine,String tipologia);
	public List<CampoUtility>  getAllInfocampiVip(String dataInizio,String dataFine);
	public String addPrenotazioneTorneo(String idUtente,int idCampo,int idTorneo);
	public TorneoDTO cercaTorneo(int id);
	public List<Torneo> TorneoAssDisponibili(String emailUtente);
	public List<Torneo> TorneoAssTipologiaDisponibili(String email, String tipologia);
	public List<CampoUtility> getAllInfocampiAssAdminComplete(String dataInizio, String dataFine, String tipologia);
	List<Squadra> listaUtentiForTorneo(int idTorneo);
	int idTorneoByName(String nomeTorneo);
	
	//PER TUTTI COLORO CHE HANNO A CHE FARE CON TORNEO E LE LORO DATE
	String dataFormattata(String str);
	

	
	//MATCH
	int estraiIdTorneo(String selectTorneo);
	List<MatchDTO> getMatchFromIdTorneo(int idTorneo);
	List<MatchDTO> listaMatchAttivi();
	void insertMatchCalendarioTorneo(String data,String risultato,int torneo_fk,String sfidante1_fk,String sfidante2_fk);
	List<String> listaUtenti();
	
	List<MatchDTO> listaMatchUserPerTorneo(int idTorneo,String utente);
	List<Match> listaMatchUser(String nomeSquadra);
	ClassificaMatch classificaTorneoCampionato(int idTorneo);
//	ClassificaMatch classificaTorneoEliminazione(int idTorneo); --> sviluppato in UTILITY.java
//	ClassificaMatch classificaTorneo(int idTorneo);
		//Classifica Torneo ad Eliminazione Diretta
	List<TorneoEliminazione> tabelloneTorneo(int idTorneo,List<MatchDTO> lista);
	
	
	//LOGO PER SQUADRE DEL TORNEO
	public byte[] getImageUser(String email) throws IOException;
	public List<TipologiaTorneoDTO> listaTipologiaTorneo();
}

