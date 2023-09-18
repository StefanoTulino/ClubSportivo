package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.project.dto.TipologiaTorneoDTO;
import com.project.dto.TorneoDTO;
import com.project.exception.CustomException;
import com.project.utility.CampoUtility;
import com.project.utility.Squadra;
import com.project.utility.Torneo;

public interface TorneoDAO {

	//ALL
	String convertiTimestampInDate(String str) throws SQLException;
	List<Squadra> getUserTorneo(int idTorneo) throws SQLException;
	int getIdTorneoByName(String nameTorneo) throws SQLException;
	
	
	//admin
	public List<Torneo> getAllTorneiInCorso() throws SQLException;
	public List<TorneoDTO> getAllTornei() throws SQLException;
	public String insertValueTorneo(String nome,String dataInizio,String dataFine,int num, int tipologiaTorneo) throws SQLException;
	public String insertTorneo(int idTorneo,int idCampo) throws SQLException;
	public List<CampoUtility> getAllInfocampiForTorneo(String dI,String dF) throws SQLException, CustomException;
	public int getLastIdTorneo() throws SQLException;
	void updateTorneoDataInizio(int id,String dI) throws SQLException;
	void updateTorneoDataFine(int id,String df) throws SQLException;
	String updateTorneoDate(int id,String dI,String dF) throws SQLException;

	
	
	//Vip
	public List<Torneo> TorneoForVipDisponibili(String emailUtente) throws SQLException, CustomException;
	public List<TorneoDTO> TorneoUtentePrenotati(String emailUtente) throws SQLException, CustomException;
	public TorneoDTO searchTorneo(int id) throws SQLException;
	public List<Torneo> TorneoForVipDisponibiliComplete(String tipologia) throws SQLException, CustomException;
	
	
	//Ass
	public List<Torneo> TorneoForAssDisponibili(String emailUtente) throws SQLException, CustomException;
	List<Torneo> TorneoForAssTipologiaDisponibili(String emailUtente, String tipologia)
			throws SQLException, CustomException;
	List<TipologiaTorneoDTO> getTipologiaTorneo();
	
	
	
}
