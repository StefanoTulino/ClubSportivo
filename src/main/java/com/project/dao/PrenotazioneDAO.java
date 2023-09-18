package com.project.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.project.dto.PrenotazioneDTO;

public interface PrenotazioneDAO {

	public String addPrenotazione(String idUtente,int idCampo, String d,String d2) throws ParseException;
	public List<PrenotazioneDTO> listaPrenotazioniForUtente(String email) throws SQLException;
	
	//modificaPrenotazione
	public PrenotazioneDTO searchPrenotazione(String idUtente,int idCampo,String dataInizio) throws SQLException;
	public PrenotazioneDTO searchPrenotazione1(int idPrenotazione) throws SQLException;

	public String changePrenotazione(int idPrentazione,int idCampo,String dataInizio,
			String dataFine) throws SQLException;
	public String deletePrenotazione(int idPrenotazione) throws SQLException;
	public List<PrenotazioneDTO> prenotazioniError(String emailUtente) throws SQLException;
	
	
	//Prenotazione per tornei
	public String addPrenotazioneTorneo(String idUtente,int idCampo,int idTorneo,String d,String d2) throws ParseException;
}
