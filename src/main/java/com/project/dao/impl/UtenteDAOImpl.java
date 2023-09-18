package com.project.dao.impl;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.dao.UtenteDAO;
import com.project.dto.TipologiaUtenteDTO;
import com.project.dto.UtenteDTO;
import com.project.exception.LoginException;
import com.project.exception.RegistrazioneException;
import com.project.utility.BaseDAO;
import com.project.utility.Utente;

public class UtenteDAOImpl extends BaseDAO implements UtenteDAO {
		
	private Connection connection = null;
	private Statement statement = null;

	
	
	public UtenteDAOImpl() {
		super();
	}
	
	
	@Override
	public UtenteDTO loginUtente(String email, String password) throws LoginException, SQLException {
		super.connect();
		UtenteDTO u = null;
		connection=super.getConnection();
		statement= super.getStatement();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from utente_cl where email_pk=? AND password=?");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				u = new UtenteDTO(rs.getString("email_pk"), rs.getString("password"), rs.getString("nome"),
						rs.getString("cognome"), rs.getInt("tipologia_fk"), rs.getBlob("logo"));
				return u;
			} else {
				throw new LoginException();
			}

		} catch (LoginException e) {
			System.out.println("LoginException: " + e.getMessage());
			throw new LoginException("Credenziali errate: login non valido");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			throw new LoginException("Errore di Sistema: riprovare più tardi");
		}
		 	finally {
		 		super.closeConnect();
		 	}
	}



	@Override
	//utente dto e boolean
	//se true== ass
	public UtenteDTO registrazioneUtente(UtenteDTO u, boolean tipologia)
			throws RegistrazioneException, SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		
		try {
			String sql="INSERT INTO UTENTE_CL(email_pk,password,"
					+ "nome,cognome";
			if(tipologia) {
				sql+=",tipologia_fk) VALUES(?,?,?,?,?)";
			} else {
				sql+=") VALUES(?,?,?,?)";
			}
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
				preparedStatement.setString(1, u.getEmail());
				preparedStatement.setString(2, u.getPassword());
				preparedStatement.setString(3, u.getNome());
				preparedStatement.setString(4, u.getCognome());
				if(tipologia) {
					//un'altra query che mi va a prendere il valore pk della tipologia
					preparedStatement.setInt(5,4);
					u.setTipologiaUtente(4);
				}
				int result = preparedStatement.executeUpdate();
				if(result==1) {
					UtenteDTO utenteResponse = new UtenteDTO(u);
					return utenteResponse;
				}
				
				else {
					throw new RegistrazioneException();
				}
				
		} catch(SQLException e) {
			System.out.println("RegistrazioneException: " + e.getMessage());
			throw new RegistrazioneException("Credenziali errate: registrazione non avvenuta");
		}	finally {
	 		super.closeConnect();
	 	}
	}


	
	@Override
	public List<Utente> listaUtenti() throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		TipologiaUtenteDTO t=new TipologiaUtenteDTO();
		List<Utente> listaUtenti=new ArrayList<>();
		
		try {
			ResultSet resultSet = statement.executeQuery("select u.tipologia_fk,t.nome_tip_utente,t.descrizione, "
					+ "u.email_pk,u.password,u.nome,u.cognome "
					+ "from utente_cl u JOIN tipologia_utente_ct t ON u.tipologia_fk=t.id_pk");
			ResultSetMetaData md= resultSet.getMetaData();
			System.out.println("Num.colonne: "+ md.getColumnCount());	
			while(resultSet.next()) {
				 t = new TipologiaUtenteDTO(resultSet.getInt("tipologia_fk"),
						resultSet.getString("nome_tip_utente"), resultSet.getString("descrizione"));
				 listaUtenti.add(new Utente(resultSet.getString("email_pk"),resultSet.getString("password"),
					resultSet.getString("nome"),resultSet.getString("cognome"),t));
			}
			
		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
		} finally {
			super.closeConnect();
		}
		return listaUtenti;
	}


	
	@Override
	public Utente dettaglioUtente(String email) throws SQLException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"select u.email_pk,u.password,u.nome,u.cognome,u.tipologia_fk,u.logo,t.nome_tip_utente,t.descrizione FROM utente_cl u JOIN tipologia_utente_ct t ON u.tipologia_fk=t.id_pk WHERE u.email_pk=?");
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			ResultSetMetaData md = resultSet.getMetaData();
			System.out.println("Num.colonne UTENTE: " + md.getColumnCount());
			if (resultSet.next()) {
				TipologiaUtenteDTO t = new TipologiaUtenteDTO(resultSet.getInt("tipologia_fk"),
						resultSet.getString("nome_tip_utente"), resultSet.getString("descrizione"));
				Utente utente = new Utente(resultSet.getString("email_pk"), resultSet.getString("password"),
						resultSet.getString("nome"), resultSet.getString("cognome"), t, resultSet.getBlob("logo"));
				return utente;
			}
		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
		} finally {
			super.closeConnect();
		}
		return null;
	}




	@Override
	public void modificaPasswordUtente(String nuovaPassword, String email) throws SQLException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("update UTENTE_CL SET password=? WHERE email_pk=?");
			preparedStatement.setString(1,nuovaPassword);
			preparedStatement.setString(2,email);
			int result=preparedStatement.executeUpdate();
			if(result==1) {
				System.out.println("Record Password Modifcato");
				} 
				else {
					System.out.println("Record NON modificato");
				}
			
			
		} catch(SQLException e) {
			System.out.println("Exception: "+ e.getMessage());
		} finally {
			super.closeConnect();
		}
	}


	@Override
	public UtenteDTO searchUtente(String email) throws SQLException {
		super.connect();
		UtenteDTO u = null;
		connection=super.getConnection();
		statement= super.getStatement();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select u.email_pk,u.password,u.nome,u.cognome,u.tipologia_fk "
										+ " from utente_cl where email_pk=?");
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				u = new UtenteDTO(rs.getString("email_pk"), rs.getString("password"), rs.getString("nome"),
						rs.getString("cognome"), rs.getInt("tipologia_fk"));
				return u;
			} 

		}  catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		 	finally {
		 		super.closeConnect();
		 	}
		return u;
	}

	

	@Override
	public List<TipologiaUtenteDTO> listaTipologia() throws SQLException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		List<TipologiaUtenteDTO> listaTipologia=new ArrayList<>();

		try {
			ResultSet resultSet = statement.executeQuery("select * from tipologia_utente_ct");
			ResultSetMetaData md = resultSet.getMetaData();
			System.out.println("Num.colonne: " + md.getColumnCount());
			while (resultSet.next()) {
				TipologiaUtenteDTO t = new TipologiaUtenteDTO(resultSet.getInt("id_pk"),
						resultSet.getString("nome_tip_utente"), resultSet.getString("descrizione"));
				listaTipologia.add(t);
			}
		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
		} finally {
			super.closeConnect();
		}
		return listaTipologia;
	}


	
	@Override
	public void modificaTipologiaUtente(int nomeTipologia, String email) throws SQLException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("update UTENTE_CL SET tipologia_fk=? WHERE email_pk=?");
			preparedStatement.setInt(1,nomeTipologia);
			preparedStatement.setString(2,email);
			int result=preparedStatement.executeUpdate();
			if(result==1) {
				System.out.println("Record Tipologia Modifcato");
				} 
				else {
					System.out.println("Record Tipologia NON modificato");
				}
					} catch(SQLException e) {
						System.out.println("Exception: "+ e.getMessage());
			} finally {
				super.closeConnect();
			}
		}


	@Override
	public List<String> getAllUser() throws SQLException {
		super.connect();
		connection= super.getConnection();
		statement= super.getStatement();
		List<String> listaUtenti= new ArrayList<String>();
		
		try {
			String sql=" SELECT u.email_pk FROM utente_cl u ";
			
			ResultSet rs =statement.executeQuery(sql);
			while(rs.next()) {
				listaUtenti.add(rs.getString("email_pk"));
			}
			
		}  catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: " + e.getMessage());
		} finally {
			super.closeConnect();
		}
	return listaUtenti;
	}


	@Override
	public Blob getImagesForUser(String email) throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		Blob b=null;
		
		try {
			String sql= "SELECT u.logo FROM utente_cl u WHERE u.email_pk=? ";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return b=resultSet.getBlob("logo");
			}
			
		} catch (SQLException e) {
				System.out.println("Exception: "+ e.getMessage());
		} 
		
		return b;
	}
	
}
