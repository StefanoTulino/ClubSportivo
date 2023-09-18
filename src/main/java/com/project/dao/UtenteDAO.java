package com.project.dao;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import com.project.dto.TipologiaUtenteDTO;
import com.project.dto.UtenteDTO;
import com.project.exception.LoginException;
import com.project.exception.RegistrazioneException;
import com.project.utility.Utente;

public interface UtenteDAO {

	public UtenteDTO loginUtente(String email,String password) throws LoginException, SQLException;
	public UtenteDTO registrazioneUtente(UtenteDTO utente,boolean tipologia) throws RegistrazioneException, SQLException;
	public UtenteDTO searchUtente(String email) throws SQLException;
	
	// mi serve una lista con tutti i nomi degli utenti
	public List<String> getAllUser() throws SQLException;
	
	
	//admin
	public Utente dettaglioUtente(String email) throws SQLException;
	public void modificaPasswordUtente(String nuovaPassword,String email) throws SQLException;
	List<Utente> listaUtenti() throws SQLException;
	List<TipologiaUtenteDTO> listaTipologia() throws SQLException;
	public void modificaTipologiaUtente(int nomeTipologia,String email) throws SQLException;
	
	//View Logo for Ass
	public Blob getImagesForUser(String email) throws SQLException;
	
}
