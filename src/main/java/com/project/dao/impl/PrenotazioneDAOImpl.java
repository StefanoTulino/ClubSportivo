package com.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.project.dao.PrenotazioneDAO;
import com.project.dto.PrenotazioneDTO;
import com.project.utility.BaseDAO;

public class PrenotazioneDAOImpl extends BaseDAO implements PrenotazioneDAO {

	private Connection connection = null;
	private Statement statement = null;
	
	
	public PrenotazioneDAOImpl() {
		super();
	}
	
	

	@Override
	public String addPrenotazione(String idUtente,int idCampo, String d,String d2) throws ParseException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		
		
		try { 
		
			String sql="INSERT INTO PRENOTAZIONE_CL(data_inizio,data_fine,utente_fk,campo_fk) VALUES ( "
					+ "to_date(?,'yyyy-mm-dd hh24:mi:ss'),to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?) ";
			
			PreparedStatement prep= connection.prepareStatement(sql);			
			prep.setString(1, d);
			prep.setString(2, d2);
			prep.setString(3, idUtente);
			prep.setInt(4, idCampo);
			int result = prep.executeUpdate();

			if(result==1) {
				return "OK";
			}
				
		} catch(SQLException e) {
			System.out.println("Exception: "+ e.getMessage());
			
		}
		return null;
		
	}
	
	
	@Override
	public List<PrenotazioneDTO> listaPrenotazioniForUtente(String email) throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		List<PrenotazioneDTO> lista= new ArrayList<PrenotazioneDTO>();
		
		try {
		String sql= 
				//ST new
//				" SELECT p.id_pk as Id_Prenotazione,p.data_inizio,p.data_fine,p.utente_fk as Utente,p.campo_fk as Campo,u.nome,u.cognome,u.tipologia_fk as Utente_Tipologia FROM prenotazione_cl p "
				
				" SELECT p.id_pk as Id_Prenotazione,p.data_inizio,p.data_fine,p.utente_fk as Utente,p.campo_fk as Campo FROM prenotazione_cl p "
				+ "JOIN utente_cl u ON p.utente_fk=u.email_pk "
				+ "WHERE u.email_pk=? AND p.data_fine>=SYSDATE ";
		        
		PreparedStatement preparedStatement= connection.prepareStatement(sql);
		preparedStatement.setString(1,email);
		
		ResultSet rs= preparedStatement.executeQuery();
		
		while(rs.next()) {
			lista.add(new PrenotazioneDTO(rs.getInt("Id_Prenotazione"),rs.getDate("data_inizio"),
					rs.getDate("data_fine"),rs.getString("Utente"),rs.getInt("Campo")
					));
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: " + e.getMessage());
		}
		
	return lista;
	}

//-----------------------------------------------------------------------------------
	
	@Override
	public PrenotazioneDTO searchPrenotazione1(int idPrenotazione) throws SQLException {
		super.connect();
		PrenotazioneDTO prenotazione= null;
		connection=super.getConnection();
		statement= super.getStatement();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from campo_cl where id_pk=?" );
			preparedStatement.setInt(1, idPrenotazione);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				prenotazione = new PrenotazioneDTO(rs.getInt("id_pk"), rs.getDate("data_inizio"), rs.getDate("data_fine"),
						rs.getString("utente_fk"),rs.getInt("campo_fk"),rs.getInt("torneo_fk") );		
			return prenotazione;
			} 

		}  catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		 	finally {
		 		super.closeConnect();
		 	}
	return null;
	}
	
	
	
	
	@Override
	public PrenotazioneDTO searchPrenotazione(String idUtente, int idCampo, String dataInizio) throws SQLException {
		super.connect();
		PrenotazioneDTO prenotazione= null;
		connection=super.getConnection();
		statement= super.getStatement();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from campo_cl where utente_fk=? AND "
							+ "campo_fk=? AND data_inizio=to_date('?','yyyy-mm-dd hh24:mi:ss')" );
			preparedStatement.setString(1, idUtente);
			preparedStatement.setInt(2, idCampo);
			preparedStatement.setString(3, dataInizio);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				prenotazione = new PrenotazioneDTO(rs.getInt("id_pk"), rs.getDate("data_inizio"), rs.getDate("data_fine"),
						rs.getString("utente_fk"),rs.getInt("campo_fk"),rs.getInt("torneo_fk") );		
			return prenotazione;
			} 

		}  catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		 	finally {
		 		super.closeConnect();
		 	}
	return null;
	}

	@Override
	public List<PrenotazioneDTO> prenotazioniError(String emailUtente) throws SQLException{
		super.connect();
		List<PrenotazioneDTO> prenotazione= new ArrayList<PrenotazioneDTO>();
		connection=super.getConnection();
		statement= super.getStatement();
		try {
			
			String sql= "select p.id_pk,p.data_inizio,p.data_fine,p.utente_fk,p.torneo_fk,p.campo_fk,c.prenotabile "
					+ "from prenotazione_cl p "
					+ "JOIN campo_cl c ON p.campo_fk=c.id_pk "
					+ "WHERE p.utente_fk=? AND p.data_inizio>SYSDATE AND c.prenotabile=0";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, emailUtente);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				prenotazione.add( new PrenotazioneDTO( rs.getInt("id_pk"), rs.getDate("data_inizio"), 
						rs.getDate("data_fine"),rs.getString("utente_fk"),
							rs.getInt("campo_fk"),rs.getInt("torneo_fk") ));		
			
			} 

		}  catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		 	finally {
		 		super.closeConnect();
		 	}
	return prenotazione;
		
		
		
	}
	

	@Override
	public String changePrenotazione(int idPrenotazione, int idCampo, String dataInizio,String dataFine) throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
	
		try { 		
//			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//			LocalDateTime ldate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dataFine.getTime()), ZoneId.systemDefault());
//		    String strDate =  ldate.format(formatter2);
//			System.out.println(strDate);
			
			
			String sql="UPDATE PRENOTAZIONE_CL SET data_inizio=to_date(?,'yyyy-mm-dd hh24:mi:ss'), "
					+ "data_fine=to_date(?,'yyyy-mm-dd hh24:mi:ss'), "
					+ "campo_fk=? WHERE id_pk=? ";
			
			PreparedStatement prep= connection.prepareStatement(sql);	
			prep.setString(1, dataInizio);
			prep.setString(2, dataFine);
			prep.setInt(3, idCampo);
			prep.setInt(4, idPrenotazione);
			int result=prep.executeUpdate();
			
			if(result==1) {
				System.out.println("Record Prenotazione Modifcato");
				return "OK";
				} 
				else {
					System.out.println("Record Prenotazione NON modificato");
				} 	

		} catch(SQLException e) {
			System.out.println("Exception: "+ e.getMessage());
		} finally {
	 		super.closeConnect();
	 	}
	return null;
	}



	@Override
	public String deletePrenotazione(int idPrenotazione) throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
	
		try {
			String sql="DELETE FROM PRENOTAZIONE_CL WHERE id_pk=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idPrenotazione);
			int result = preparedStatement.executeUpdate();
			if(result==1) {
				System.out.println("Record Prenotazione Cancellato");
				return "OK";
				} 
				else {
					System.out.println("Record Prenotazione NON cancellato");
				} 
			} catch(SQLException e) {
				System.out.println("Exception: "+ e.getMessage());
			} finally {
		 		super.closeConnect();
		 	}
	return null;
	}


	
	//---------- TORNEO PRENOTAZIONE -----------------------------------------

	@Override
	public String addPrenotazioneTorneo(String idUtente, int idCampo, int idTorneo, String d, String d2)
			throws ParseException {
		
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		
		
		try { 
			String sql="INSERT INTO PRENOTAZIONE_CL(data_inizio,data_fine,utente_fk,campo_fk,torneo_fk) VALUES ( "
					+ "to_date(?,'yyyy-mm-dd hh24:mi:ss'),to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?) ";
			
			PreparedStatement prep= connection.prepareStatement(sql);			
			prep.setString(1, d);
			prep.setString(2, d2);
			prep.setString(3, idUtente);
			prep.setInt(4, idCampo);
			prep.setInt(5, idTorneo);
			int result = prep.executeUpdate();

			if(result==1) {
				return "OK";
			}
				
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: "+ e.getMessage());
			
		} 
		return null;
	}
	
}
