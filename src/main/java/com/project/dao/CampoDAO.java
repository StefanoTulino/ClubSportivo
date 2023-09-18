package com.project.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import com.project.dto.CampoDTO;
import com.project.dto.TipologiaCampoDTO;
import com.project.exception.CustomException;
import com.project.utility.Campo;
import com.project.utility.CampoUtility;

public interface CampoDAO {
	
	public List<CampoUtility> getAllInfoCampi() throws CustomException, SQLException;
	public CampoDTO searchCampo(int id) throws SQLException;
	public List<TipologiaCampoDTO> listaTipologia() throws SQLException;
	
//	public List<CampoUtility> listaCampiPrenotati() throws SQLException;
	List<CampoUtility> listaCampiDisponibili(String dI,String dF) throws SQLException, CustomException;
	public Blob getImagesForCampo(int id) throws SQLException;
	
	
	//admin
	public List<Campo> getAllCampi() throws CustomException;
	
	public void changePrezzoCampo(int id,float prezzo) throws SQLException;
	public void changeTipologiaCampo(int id,int tipologia) throws SQLException;
	public void changeStatusCampo(int id,int prenotabile) throws SQLException;
	public void changeMultiples(int id,float prezzo,int tipologia) throws SQLException;
	
	public String addCampo(float prezzo,int tipologia) throws SQLException;
	public String addTipologiaCampo(String descrizione,float incremento,InputStream i,int size) throws SQLException, IOException;
	
	//addPrenotazione with tipologia
	public List<CampoUtility> getAllInfoCampiComplete(String tipologia) throws CustomException;
	public List<CampoUtility> listaCampiDisponibiliUtentiVip(String dI, String dF) throws CustomException, SQLException;
	public List<CampoUtility> listaCampiDisponibiliCompletoUtentiSpeciali(String dI, String dF,String tipologia) throws CustomException, SQLException;
	
	
	//CONVERSIONE ID CON NOME TIPOLOGIA FOR VIP
	public List<CampoUtility> searchListCampiDaScontare() throws SQLException, CustomException;
}
