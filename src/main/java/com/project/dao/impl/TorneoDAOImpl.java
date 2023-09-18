package com.project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.dao.TorneoDAO;
import com.project.dto.TipologiaTorneoDTO;
import com.project.dto.TorneoDTO;
import com.project.exception.CustomException;
import com.project.utility.BaseDAO;
import com.project.utility.CampoUtility;
import com.project.utility.Squadra;
import com.project.utility.Torneo;


public class TorneoDAOImpl extends BaseDAO implements TorneoDAO {

	private Connection connection = null;
	private Statement statement = null;
	
	
	@Override
	public List<Torneo> getAllTorneiInCorso() throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		List<Torneo> listaTornei=new ArrayList<Torneo>();
		
		try {
			String sql=" SELECT t.id_pk,t.nome,t.data_inizio,t.data_fine,t.numero_squadre, "
						+ " t.numero_squadre - count(p.torneo_fk) as Posti_Disponibili,a.campo_fk "
						+ "FROM TORNEO_CL t "
						+" JOIN accesso_cl a ON t.id_pk=a.torneo_fk "
						+" LEFT JOIN prenotazione_cl p ON t.id_pk=p.torneo_fk " 
						+" JOIN campo_cl c ON a.campo_fk=c.id_pk "
						+" JOIN tipologia_campo_ct ctip ON c.tipologia_fk=ctip.id_pk "
						+ "WHERE t.data_inizio>SYSDATE "
						+ "GROUP BY t.id_pk,t.nome,t.data_inizio,t.data_fine,t.numero_squadre,a.campo_fk";
			
			
//			String sql= "SELECT t.id_pk,t.nome,t.data_inizio as DataInizioTorneo,t.data_fine as DataFineTorneo,t.numero_squadre "
//					+ "FROM prenotazione_cl p "
//					+ "JOIN torneo_cl t ON p.torneo_fk=t.id_pk "
//					+ "WHERE t.data_inizio> SYSDATE AND p.utente_fk NOT IN ( "
//					+ "select u.email_pk "
//					+ " from utente_cl u "
//					+ "JOIN prenotazione_cl p ON u.email_pk=p.utente_fk "
//					+ " WHERE u.email_pk=? "
//					+ ")";
			
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()) {
				listaTornei.add(new Torneo(rs.getInt("id_pk"),rs.getString("nome"),rs.getTimestamp("data_inizio"),
						rs.getTimestamp("data_fine"),rs.getInt("numero_squadre"),rs.getInt("Posti_Disponibili"),
						rs.getInt("campo_fk")
						));
				}
			
			System.out.println("listaTornei SIZE: "+ listaTornei.size());
			for(int i=0;i<listaTornei.size();i++) {
				//chiama il toString automaticamente
				System.out.println(listaTornei.get(i));
			}
				
		} catch(SQLException e) {
			
		} finally {
			super.closeConnect();
		}
		
	return listaTornei;
	}
	
	
	
	@Override
	public int getLastIdTorneo() throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		int id=0;
		
		try {
			String sql= "select DISTINCT LAST_VALUE(t.id_pk) OVER "
						+ "(ORDER BY t.id_pk ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS ultimoValore "
						+ "FROM torneo_cl t "; 
					
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()) {
					id=rs.getInt("ultimoValore");
					return id;
				}
			System.out.println("ID: "+ id);
				
			} catch(SQLException e) {
				System.out.println("Exception: "+ e.getMessage());
				
			} finally {
				super.closeConnect();
		}
	return id;
	}
	
	
	@Override
	public String insertTorneo(int idTorneo,int idCampo) throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		int id=0;
		
		try {
			String sql= "INSERT INTO ACCESSO_CL(torneo_fk,campo_fk) VALUES(?,?) " ;
					
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setInt(1, idTorneo);
			preparedStatement.setInt(2, idCampo);
			int rs= preparedStatement.executeUpdate();
			if(rs==1) {
					return "OK";
				}
				
			} catch(SQLException e) {
				System.out.println("Exception: "+ e.getMessage());
				
			} finally {
				super.closeConnect();
		}
	return null;
	}
	
	


	@Override
	public String insertValueTorneo(String nome, String dataInizio, String dataFine, int num, int tipologiaTorneo) throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		Torneo torneo=new Torneo();
		
		try {
			//ST new
			//Add tipologia_torneo!
			
			String sql="INSERT INTO TORNEO_CL(nome,data_inizio,data_fine,numero_squadre,tipologia_torneo_fk) VALUES "
					+ "(?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?) ";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, dataInizio);
			preparedStatement.setString(3, dataFine);
			preparedStatement.setInt(4, num);
			preparedStatement.setInt(5, tipologiaTorneo);
			int res= preparedStatement.executeUpdate();
			if(res==1) {
					
//				SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//				Date date1 = (Date)formatter2.parse(dataInizio);
//				java.sql.Date dataI = new java.sql.Date(date1.getTime());
//				
//				Date date2 = (Date)formatter2.parse(dataFine);
//				java.sql.Date dataF = new java.sql.Date(date2.getTime());
				return "OK";
				}
				
			} catch(SQLException e) {
				
				
			} finally {
				super.closeConnect();
			}
	return null;
	}


	@Override
	public List<CampoUtility> getAllInfocampiForTorneo(String dI, String dF) throws SQLException, CustomException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		List<CampoUtility> listaDisponibili=new ArrayList<CampoUtility>();

		try {
			
			String sql= "SELECT c.id_pk as Numero_Campo, c.prezzo, c.tipologia_fk as Tip_Campo, ctip.incremento,ctip.nome_tip_campo as Descrizione_Campo FROM campo_cl c "
					+ "JOIN  tipologia_campo_ct ctip ON c.tipologia_fk=ctip.id_pk "
					+ "WHERE c.prenotabile=1 "
					//+ "AND ctip.ruolo=0"
					+ "AND c.id_pk NOT IN  ( "
					+ "SELECT c1.id_pk "
					+ " FROM campo_cl c1 "
					+ "JOIN prenotazione_cl p ON c1.id_pk=p.campo_fk "
					+ " WHERE p.data_inizio BETWEEN to_date(?,'yyyy-mm-dd hh24:mi:ss') AND "
					+ "		to_date(?,'yyyy-mm-dd hh24:mi:ss')  AND "
					+ " p.data_fine BETWEEN to_date(?,'yyyy-mm-dd hh24:mi:ss') AND "
					+ "		 to_date(?,'yyyy-mm-dd hh24:mi:ss') "
					+ ") AND c.id_pk NOT IN ( "
					+ "SELECT c2.id_pk "
					+ "FROM campo_cl c2 "
					+ "JOIN accesso_cl a ON c2.id_pk=a.campo_fk "
					+ "JOIN torneo_cl t ON a.torneo_fk=t.id_pk "
					+ " WHERE t.data_inizio BETWEEN to_date(?,'yyyy-mm-dd hh24:mi:ss') AND "
					+ "		to_date(?,'yyyy-mm-dd hh24:mi:ss')  AND	"
					+ "t.data_fine BETWEEN to_date(?,'yyyy-mm-dd hh24:mi:ss') AND "
					+ "		to_date(?,'yyyy-mm-dd hh24:mi:ss') "
					+ " )" ; 
									

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, dI);
			preparedStatement.setString(2, dF);
			preparedStatement.setString(3, dI);
			preparedStatement.setString(4, dF);
			preparedStatement.setString(5, dI);
			preparedStatement.setString(6, dF);
			preparedStatement.setString(7, dI);
			preparedStatement.setString(8, dF);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				listaDisponibili.add(new CampoUtility(resultSet.getInt("Numero_Campo"),
						resultSet.getString("Descrizione_Campo"),
						(resultSet.getFloat("Prezzo") + resultSet.getFloat("Incremento"))
						));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new CustomException("La lista ritornata e' null");
		} finally {
			super.closeConnect();
		}
		return listaDisponibili;
		
	}


	//QUESTO RIMANE COSI CON TORNEO UTILITY
	@Override
	public List<Torneo> TorneoForVipDisponibili(String emailUtente) throws SQLException, CustomException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		List<Torneo> listaDisponibili=new ArrayList<Torneo>();

		try {
			
			String sql=	"SELECT t.id_pk as TorneoId,t.nome,t.data_inizio,t.data_fine,t.numero_squadre, "
					+ " t.numero_squadre - count(p.torneo_fk) as Posti_Disponibili,a.campo_fk "
				+" FROM TORNEO_CL t "
				+" JOIN accesso_cl a ON t.id_pk=a.torneo_fk "
				+	" LEFT JOIN prenotazione_cl p ON t.id_pk=p.torneo_fk " 
				+" JOIN campo_cl c ON a.campo_fk=c.id_pk "
				+" JOIN tipologia_campo_ct ctip ON c.tipologia_fk=ctip.id_pk "  
				+" WHERE ctip.ruolo=1 AND t.data_inizio > SYSDATE "
				+" AND t.id_pk NOT IN ( "
				+" SELECT t.id_pk "
				+" FROM torneo_cl t "
				+"   JOIN prenotazione_cl p ON t.id_pk=p.torneo_fk "
				+"       JOIN accesso_cl a ON t.id_pk=a.torneo_fk "
				+" WHERE p.utente_fk=? ) "
				+ " GROUP BY t.id_pk,t.nome,t.numero_squadre,t.data_inizio,t.data_fine,p.torneo_fk,a.campo_fk "
				+ "HAVING count(p.torneo_fk)< t.numero_squadre"   ;
			
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, emailUtente);
			ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					listaDisponibili.add(new Torneo(resultSet.getInt("TorneoId"),
						resultSet.getString("nome"),
						resultSet.getTimestamp("data_inizio"),
						resultSet.getTimestamp("data_fine"),resultSet.getInt("numero_squadre"),
						resultSet.getInt("Posti_Disponibili"),resultSet.getInt("campo_fk")
						));
				}
			
				} catch (SQLException e) {
					e.printStackTrace();
					throw new CustomException("La lista ritornata e' null");
				} finally {
					super.closeConnect();
				}
	return listaDisponibili;	
	}
	
	
	@Override
	public List<TorneoDTO> TorneoUtentePrenotati(String emailUtente) throws SQLException, CustomException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		List<TorneoDTO> listaTorneiPrenotati=new ArrayList<TorneoDTO>();

		try {
			
		
			String sql= "SELECT DISTINCT t.id_pk as TorneoId,t.nome,t.data_inizio,t.data_fine,t.numero_squadre "
						+ "FROM prenotazione_cl p "
						+ "JOIN torneo_cl t ON p.torneo_fk=t.id_pk "
						+ "WHERE p.utente_fk=?" ;
					
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, emailUtente);
					ResultSet resultSet = preparedStatement.executeQuery();
						while (resultSet.next()) {
							listaTorneiPrenotati.add(new TorneoDTO(resultSet.getInt("TorneoId"),
								resultSet.getString("nome"),
								resultSet.getTimestamp("data_inizio"),
								resultSet.getTimestamp("data_fine"),resultSet.getInt("numero_squadre")
								));
						}
			
				} catch (SQLException e) {
					e.printStackTrace();
					throw new CustomException("La lista ritornata e' null");
				} finally {
					super.closeConnect();
				}
	return listaTorneiPrenotati;	
	}
	
	
	//RIMANE COSI
	@Override
	public List<Torneo> TorneoForVipDisponibiliComplete(String tipologia) throws SQLException, CustomException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		List<Torneo> listaDisponibili=new ArrayList<Torneo>();

		try {
			
		
			String sql= "SELECT t.id_pk as TorneoId,t.nome,t.data_inizio,t.data_fine,t.numero_squadre,a.campo_fk "
					+ "FROM TORNEO_CL T "
					+ "JOIN accesso_cl a ON t.id_pk=a.torneo_fk "
					+ "JOIN campo_cl c ON a.campo_fk=c.id_pk "
					+ "JOIN tipologia_campo_ct ctip ON c.tipologia_fk=ctip.id_pk "
					+ "WHERE ctip.nome_tip_campo=? AND ctip.ruolo=1 AND t.data_inizio > SYSDATE " ;
				
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, tipologia);
					ResultSet resultSet = preparedStatement.executeQuery();
						while (resultSet.next()) {
							listaDisponibili.add(new Torneo(resultSet.getInt("TorneoId"),
								resultSet.getString("nome"),
								resultSet.getTimestamp("data_inizio"),
								resultSet.getTimestamp("data_fine"),resultSet.getInt("numero_squadre"),
								resultSet.getInt("campo_fk")
								));
						}
			
				} catch (SQLException e) {
					e.printStackTrace();
					throw new CustomException("La lista ritornata e' null");
				} finally {
					super.closeConnect();
				}
	return listaDisponibili;	
	}



	@Override
	public TorneoDTO searchTorneo(int id) throws SQLException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		TorneoDTO torneo=new TorneoDTO();

		try {
			
			String sql= "SELECT * FROM TORNEO_CL T  WHERE t.id_pk=? ";

					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, id);
					ResultSet resultSet = preparedStatement.executeQuery();
						if (resultSet.next()) {
							torneo=new TorneoDTO(resultSet.getInt("id_pk"),
								resultSet.getString("nome"),
								resultSet.getTimestamp("data_inizio"),
								resultSet.getTimestamp("data_fine"),resultSet.getInt("numero_squadre"),
								resultSet.getInt("tipologia_torneo_fk")
								);
						}
			
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					super.closeConnect();
				}
		return torneo;
	}



	//RIMANE COSI
	@Override
	public List<Torneo> TorneoForAssDisponibili(String emailUtente) throws SQLException, CustomException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		List<Torneo> listaDisponibili=new ArrayList<Torneo>();

		try {
			
			String sql=	"SELECT t.id_pk as TorneoId,t.nome,t.data_inizio,t.data_fine,t.numero_squadre, "
					+ " t.numero_squadre - count(p.torneo_fk) as Posti_Disponibili,a.campo_fk "
				+" FROM TORNEO_CL t "
				+" JOIN accesso_cl a ON t.id_pk=a.torneo_fk "
				+	" LEFT JOIN prenotazione_cl p ON t.id_pk=p.torneo_fk " 
				+" JOIN campo_cl c ON a.campo_fk=c.id_pk "
				+" JOIN tipologia_campo_ct ctip ON c.tipologia_fk=ctip.id_pk "  
				+" WHERE  t.data_inizio > SYSDATE "
				+" AND t.id_pk NOT IN ( "
				+" SELECT t.id_pk "
				+" FROM torneo_cl t "
				+"   JOIN prenotazione_cl p ON t.id_pk=p.torneo_fk "
				+"       JOIN accesso_cl a ON t.id_pk=a.torneo_fk "
				+" WHERE p.utente_fk=? ) "
				+ " GROUP BY t.id_pk,t.nome,t.numero_squadre,t.data_inizio,t.data_fine,p.torneo_fk,a.campo_fk "
				+ "HAVING count(p.torneo_fk)< t.numero_squadre"   ;
			
			
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, emailUtente);
				ResultSet resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						listaDisponibili.add(new Torneo(resultSet.getInt("TorneoId"),
							resultSet.getString("nome"),
							resultSet.getTimestamp("data_inizio"),
							resultSet.getTimestamp("data_fine"),resultSet.getInt("numero_squadre"),
							resultSet.getInt("Posti_Disponibili"),resultSet.getInt("campo_fk")
							));
					}
				
					} catch (SQLException e) {
						e.printStackTrace();
						throw new CustomException("La lista ritornata e' null");
					} finally {
						super.closeConnect();
					}
	return listaDisponibili;
	}


	//RIMANE COSI
	@Override
	public List<Torneo> TorneoForAssTipologiaDisponibili(String emailUtente,String tipologia) throws SQLException, CustomException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		List<Torneo> listaDisponibili=new ArrayList<Torneo>();

		try {
			
			String sql=	"SELECT t.id_pk as TorneoId,t.nome,t.data_inizio,t.data_fine,t.numero_squadre, "
					+ " t.numero_squadre - count(p.torneo_fk) as Posti_Disponibili,a.campo_fk "
				+" FROM TORNEO_CL t "
				+" JOIN accesso_cl a ON t.id_pk=a.torneo_fk "
				+	" LEFT JOIN prenotazione_cl p ON t.id_pk=p.torneo_fk " 
				+" JOIN campo_cl c ON a.campo_fk=c.id_pk "
				+" JOIN tipologia_campo_ct ctip ON c.tipologia_fk=ctip.id_pk "  
				+" WHERE ctip.nome_tip_campo=? AND t.data_inizio > SYSDATE "
				+" AND t.id_pk NOT IN ( "
				+" SELECT t.id_pk "
				+" FROM torneo_cl t "
				+"   JOIN prenotazione_cl p ON t.id_pk=p.torneo_fk "
				+"       JOIN accesso_cl a ON t.id_pk=a.torneo_fk "
				+" WHERE p.utente_fk=? ) "
				+ " GROUP BY t.id_pk,t.nome,t.numero_squadre,t.data_inizio,t.data_fine,p.torneo_fk,a.campo_fk "
				+ "HAVING count(p.torneo_fk)< t.numero_squadre"   ;
			
			
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, tipologia);
				preparedStatement.setString(2, emailUtente);
				ResultSet resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						listaDisponibili.add(new Torneo(resultSet.getInt("TorneoId"),
							resultSet.getString("nome"),
							resultSet.getTimestamp("data_inizio"),
							resultSet.getTimestamp("data_fine"),resultSet.getInt("numero_squadre"),
							resultSet.getInt("Posti_Disponibili"),resultSet.getInt("campo_fk")
							));
					}
				
					} catch (SQLException e) {
						e.printStackTrace();
						throw new CustomException("La lista ritornata e' null");
					} finally {
						super.closeConnect();
					}
	return listaDisponibili;
	}



	@Override
	public List<TorneoDTO> getAllTornei() throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		List<TorneoDTO> listaTornei=new ArrayList<TorneoDTO>();
		
		try {
			String sql=" SELECT * FROM TORNEO_CL t";
			
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()) {

				listaTornei.add(new TorneoDTO(rs.getInt("id_pk"),rs.getString("nome"),rs.getTimestamp("data_inizio"),
						rs.getTimestamp("data_fine"),rs.getInt("numero_squadre")
						));
				}
			
		} catch(SQLException e) {
			
		} finally {
			super.closeConnect();
		}
		
	return listaTornei;
	}



	@Override
	public void updateTorneoDataInizio(int id, String dI) throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement=super.getStatement();
		
		try {
			String sql="UPDATE torneo_cl set data_inizio=TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss') "
						+ "WHERE id_pk=? ";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, dI);
			preparedStatement.setInt(2, id);
			int res=preparedStatement.executeUpdate();
			if(res!=0) {
				System.out.println("Update dataInizio completato");
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: "+ e.getMessage());
		} finally {
			super.closeConnect();
		}
		
	}



	@Override
	public void updateTorneoDataFine(int id, String df) throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement=super.getStatement();
		
		try {
			String sql="UPDATE torneo_cl set data_fine=TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss') "
						+ "WHERE id_pk=? ";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, df);
			preparedStatement.setInt(2, id);
			int res=preparedStatement.executeUpdate();
			if(res!=0) {
				System.out.println("Update dataFine completato");
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: "+ e.getMessage());
		} finally {
			super.closeConnect();
		}
		
	}



	@Override
	public String updateTorneoDate(int id, String dI, String dF) throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement=super.getStatement();
		
		try {
			String sql="UPDATE torneo_cl SET data_inizio=TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss'), "
					+ "	data_fine=TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss') "
						+ "WHERE id_pk=? ";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, dI);
			preparedStatement.setString(2, dF);
			preparedStatement.setInt(3, id);
			int res=preparedStatement.executeUpdate();
			if(res!=0) {
				System.out.println("Update dataFine completato");
				return "Update Torneo Completato";
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: "+ e.getMessage());
		} finally {
			super.closeConnect();
		}
		return null;
	}



	@Override
	public String convertiTimestampInDate(String str) throws SQLException {
		super.connect();
		connection= super.getConnection();
		statement= super.getStatement();
		String s="";
		
		try {
			
			String sql= "SELECT SUBSTR(?,0,(LENGTH(?)-5) ) AS Risultato "
						+ "FROM DUAL";
			
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, str);
			preparedStatement.setString(2, str);
			ResultSet rs= preparedStatement.executeQuery();
			if(rs.next()) {
				s=rs.getString("Risultato");
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: "+ e.getMessage());
		} finally {
			super.closeConnect();
		}
		
	return s;
		
	}



	@Override
	public List<Squadra> getUserTorneo(int idTorneo) throws SQLException {
		super.connect();
		connection= super.getConnection();
		statement= super.getStatement();
		List<Squadra> listaUtenti= new ArrayList<Squadra>();
		
		try {
			String sql="SELECT u.email_pk,u.nome,u.logo "
							+"FROM utente_cl u "
							+"JOIN prenotazione_cl p ON u.email_pk=p.utente_fk "
							+"WHERE p.torneo_fk=? " ;
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setInt(1, idTorneo);
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()) {
				listaUtenti.add(new Squadra(rs.getString("email_pk"),rs.getString("nome"),rs.getBlob("logo"),0, 0, 0, 0, 0));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: "+ e.getMessage());
		} finally {
			super.closeConnect();
		}
	return listaUtenti;	
	}



	@Override
	public int getIdTorneoByName(String nameTorneo) throws SQLException {
		super.connect();
		connection= super.getConnection();
		statement= super.getStatement();
		int idTorneo= 0;
		
		try {
			String sql="SELECT t.id_pk "
							+"FROM torneo_cl t "
							+"WHERE t.nome=? " ;
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, nameTorneo);
			ResultSet rs= preparedStatement.executeQuery();
			if(rs.next()) {
				idTorneo= rs.getInt("id_pk");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception: "+ e.getMessage());
		} finally {
			super.closeConnect();
		}
	return idTorneo;	
	}



	@Override
	public List<TipologiaTorneoDTO> getTipologiaTorneo() {
		super.connect();
		connection= super.getConnection();
		statement= super.getStatement();
		
		List<TipologiaTorneoDTO> lista= new ArrayList<TipologiaTorneoDTO>();
		try {
			String sql= "select * from tipologia_torneo_ct";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			ResultSet rs= preparedStatement.executeQuery();
			while(rs.next()) {
				lista.add(new TipologiaTorneoDTO(rs.getInt("id_pk"),rs.getString("nome")));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Exception DAO: "+ e.getMessage());
		}
	return lista;
	}

	
}
