package com.project.dao;

import java.sql.SQLException;
import java.util.List;

import com.project.dto.MatchDTO;
import com.project.utility.ClassificaMatch;
import com.project.utility.Match;

public interface MatchDAO {

	List<MatchDTO> getMatchOfNameTorneo(int idTorneo) throws SQLException;
	List<MatchDTO> matchDaDisputare() throws SQLException;
	
	
	String insertMatchCalendario(String data,String risultato,int torneo_fk,String sfidante1_fk,String sfidante2_fk) throws SQLException;
	
	//NUOVA FUNZIONALITà(PER TUTTI GLI UTENTI)
	List<MatchDTO> getMatchUserForTorneo(int idTorneo,String utente) throws SQLException;
	List<Match> getMatchUser(String utente) throws SQLException;
	
	//Classifica Campionato
	List<Match> getMatchUserCampionato(int idTorneo) throws SQLException;
	//Classifica Torneo Eliminazione Diretta
	List<Match> getMatchUserTorneo(int idTorneo) throws SQLException;
}
