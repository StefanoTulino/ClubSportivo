package com.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.dao.MatchDAO;
import com.project.dto.MatchDTO;
import com.project.utility.BaseDAO;
import com.project.utility.ClassificaMatch;
import com.project.utility.Match;

public class MatchDAOImpl extends BaseDAO implements MatchDAO {

	private Connection connection = null;
	private Statement statement = null;
	
	
	@Override
	public List<MatchDTO> getMatchOfNameTorneo(int idTorneo) throws SQLException {
		super.connect();
		connection= super.getConnection();
		statement= super.getStatement();
		List<MatchDTO> lista= new ArrayList<MatchDTO>();
		
		try {
			
			String sql= "SELECT * FROM match_cl m "
						+"WHERE m.torneo_fk=? "
						+ "ORDER BY m.data";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setInt(1, idTorneo);
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()) {
				lista.add(new MatchDTO(rs.getInt("id_pk"),rs.getDate("data"),rs.getString("risultato"),
						rs.getString("risultato_sfidante1"),rs.getString("risultato_sfidante2"),
						rs.getInt("torneo_fk"),rs.getString("sfidante1_fk"),rs.getString("sfidante2_fk")));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: "+ e.getMessage());	
		} finally {
			super.closeConnect();
		}
	return lista;
	}

	

	@Override
	public List<MatchDTO> matchDaDisputare() throws SQLException {
		super.connect();
		connection= super.getConnection();
		statement= super.getStatement();
		List<MatchDTO> lista= new ArrayList<MatchDTO>();
		
		try {
			
			String sql= "SELECT * FROM match_cl m "
						+"WHERE m.data > SYSDATE ";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()) {
				lista.add(new MatchDTO(rs.getInt("id_pk"),rs.getDate("data"),rs.getString("risultato"),
						rs.getInt("torneo_fk"),rs.getString("sfidante1_fk"),rs.getString("sfidante2_fk")));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: "+ e.getMessage());
			
		} finally {
			super.closeConnect();
		}
		
	return lista;
	}



	@Override
	public String insertMatchCalendario(String data,String risultato,int torneo_fk,String sfidante1_fk,
				String sfidante2_fk) throws SQLException {
		
		super.connect();
		connection= super.getConnection();
		statement= super.getStatement();
		
		try {
			
			String sql="INSERT INTO match_cl(data,risultato,torneo_fk,sfidante1_fk,sfidante2_fk) "
						+ "VALUES(to_date(?,'yyyy-mm-dd hh24:mi'), ?, ?, ?, ? ) " ;
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
//			for(MatchDTO m: l) {
				preparedStatement.setString(1,data);
				preparedStatement.setString(2, risultato);
				preparedStatement.setInt(3, torneo_fk);
				preparedStatement.setString(4, sfidante1_fk);
				preparedStatement.setString(5, sfidante2_fk);
//			}
				int result=preparedStatement.executeUpdate();
				if(result!=0) {
					return "Inserimento Match Completato";
				}
		
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Exception: "+ e.getMessage());
			} finally {
				super.closeConnect();
			}
	return null;
	}



	@Override
	public List<MatchDTO> getMatchUserForTorneo(int idTorneo,String utente) throws SQLException {
		super.connect();
		Connection connection= super.getConnection();
		List<MatchDTO> lista= new ArrayList<MatchDTO>();
		
		try {
			
			String sql=" SELECT * FROM MATCH_CL M "
						+ "WHERE m.torneo_fk=? AND m.sfidante1_fk=? "
						+ "UNION "
						+" SELECT * FROM MATCH_CL M "
						+ "WHERE m.torneo_fk=? AND m.sfidante2_fk=? " ;
			
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setInt(1, idTorneo);
			preparedStatement.setString(2, utente);
			preparedStatement.setInt(3, idTorneo);
			preparedStatement.setString(4, utente);
			
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				lista.add(new MatchDTO(rs.getDate("data"), rs.getString("risultato"), rs.getInt("torneo_fk"),
						rs.getString("sfidante1_fk"), rs.getString("sfidante2_fk")) );
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: "+e.getMessage());
		} finally {
			super.closeConnect();
		}
	return lista;
	}



	@Override
	public List<Match> getMatchUser(String utente) throws SQLException {
		
		super.connect();
		Connection connection= super.getConnection();
		List<Match> lista= new ArrayList<Match>();
		
		try {
			
			String sql= "SELECT DISTINCT t.nome,tt.nome as Tip_Torneo, t.tipologia_torneo_fk,m.data,m.risultato,m.sfidante1_fk,m.sfidante2_fk,a.campo_fk "
					+ "FROM match_cl m "
					+ "JOIN torneo_cl t ON m.torneo_fk=t.id_pk "
					+ "JOIN tipologia_torneo_ct tt ON t.tipologia_torneo_fk=tt.id_pk "
					+ "JOIN prenotazione_cl p ON t.id_pk=p.torneo_fk "
					+ " JOIN accesso_cl a ON t.id_pk=a.torneo_fk "
					+ "JOIN campo_cl c ON a.campo_fk= c.id_pk "
					+ "WHERE m.sfidante1_fk=? OR m.sfidante2_fk=? ";
		
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, utente);
			preparedStatement.setString(2, utente);	
			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				lista.add(new Match(rs.getString("nome"),rs.getString("Tip_Torneo"),rs.getDate("data"), 
						rs.getString("risultato"),rs.getString("sfidante1_fk"), rs.getString("sfidante2_fk")
						,rs.getInt("campo_fk")));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: "+e.getMessage());
		} finally {
			super.closeConnect();
		}
	return lista;
	}
	
	
	
	//CONTINUARE A FARE IL SERVICE E POI TESTARLO
	@Override
	public List<Match> getMatchUserCampionato(int idTorneo) throws SQLException {
		
		super.connect();
		Connection connection= super.getConnection();
		List<Match> lista= new ArrayList<Match>();
		
		try {
			
			String sql= "SELECT DISTINCT t.nome,m.risultato,m.risultato_sfidante1,m.risultato_sfidante2,m.sfidante1_fk,m.sfidante2_fk "
					+ "FROM match_cl m "
					+ "JOIN torneo_cl t ON m.torneo_fk=t.id_pk "
					+ "JOIN tipologia_torneo_ct tt ON t.tipologia_torneo_fk=tt.id_pk "
					+ "JOIN prenotazione_cl p ON t.id_pk=p.torneo_fk "
					+ " JOIN accesso_cl a ON t.id_pk=a.torneo_fk "
					+ "JOIN campo_cl c ON a.campo_fk= c.id_pk "
					+ "WHERE t.tipologia_torneo_fk=1 AND t.id_pk=?";
		
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setInt(1, idTorneo);

			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				lista.add(new Match(rs.getString("nome"),rs.getString("risultato"),
						rs.getString("risultato_sfidante1"),rs.getString("risultato_sfidante2"),
						rs.getString("sfidante1_fk"), rs.getString("sfidante2_fk") ));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: "+e.getMessage());
		} finally {
			super.closeConnect();
		}
	return lista;
	}



	@Override
	public List<Match> getMatchUserTorneo(int idTorneo) throws SQLException {
		super.connect();
		Connection connection= super.getConnection();
		List<Match> lista= new ArrayList<Match>();
		
		try {
			
			String sql= "SELECT DISTINCT t.nome,m.risultato,m.risultato_sfidante1,m.risultato_sfidante2,m.sfidante1_fk,m.sfidante2_fk "
					+ "FROM match_cl m "
					+ "JOIN torneo_cl t ON m.torneo_fk=t.id_pk "
					+ "JOIN tipologia_torneo_ct tt ON t.tipologia_torneo_fk=tt.id_pk "
					+ "JOIN prenotazione_cl p ON t.id_pk=p.torneo_fk "
					+ " JOIN accesso_cl a ON t.id_pk=a.torneo_fk "
					+ "JOIN campo_cl c ON a.campo_fk= c.id_pk "
					+ "WHERE t.tipologia_torneo_fk=2 AND t.id_pk=?";
		
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setInt(1, idTorneo);

			ResultSet rs=preparedStatement.executeQuery();
			while(rs.next()) {
				lista.add(new Match(rs.getString("nome"),rs.getString("risultato"),
						rs.getString("risultato_sfidante1"),rs.getString("risultato_sfidante2"),
						rs.getString("sfidante1_fk"), rs.getString("sfidante2_fk") ));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: "+e.getMessage());
		} finally {
			super.closeConnect();
		}
	return lista;
	}

	
}
