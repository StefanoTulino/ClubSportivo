package com.project.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.dao.CampoDAO;
import com.project.dto.CampoDTO;
import com.project.dto.TipologiaCampoDTO;
import com.project.exception.CustomException;
import com.project.utility.BaseDAO;
import com.project.utility.Campo;
import com.project.utility.CampoUtility;

public class CampoDAOImpl extends BaseDAO implements CampoDAO {
	
	private Connection connection = null;
	private Statement statement = null;
	
	
	@Override
	public List<CampoUtility> getAllInfoCampi() throws CustomException, SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		List<CampoUtility> listaAllCampi=new ArrayList<CampoUtility>();
		
		try {
			String sql= "SELECT c.id_pk as Numero_Campo, c.prezzo, c.tipologia_fk as Tip_Campo,ctip.incremento,ctip.nome_tip_campo as Descrizione_Campo,ctip.img FROM tipologia_campo_ct ctip "
					+ "JOIN  campo_cl c ON ctip.id_pk=c.tipologia_fk" ;
			
			 ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					
					listaAllCampi.add(new CampoUtility(resultSet.getInt("Numero_Campo"),
						resultSet.getString("Descrizione_Campo"),
						(resultSet.getFloat("Prezzo")+resultSet.getFloat("Incremento")),
						resultSet.getInt("Tip_Campo"),resultSet.getBlob("img"),false
						));
				
		 		}		 
			} catch(SQLException e) {
 			System.out.println("Exception: "+ e.getMessage());
 			throw new CustomException("La lista ritornata è null");
 		} finally {
 			//MI STUFO DI CAMBIARLO DOVUNQUE
 			
				super.closeConnect();
 		}
		
	return listaAllCampi;
	}
	

	
	
	@Override
	public List<TipologiaCampoDTO> listaTipologia() throws SQLException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		List<TipologiaCampoDTO> listaTipologia=new ArrayList<TipologiaCampoDTO>();

		try {
			ResultSet resultSet = statement.executeQuery("select ctip.id_pk as Id_Tipologia,ctip.incremento,ctip.nome_tip_campo as Descrizione_Campo "
					+ "FROM tipologia_campo_ct ctip");
			ResultSetMetaData md = resultSet.getMetaData();
			System.out.println("Num.colonne: " + md.getColumnCount());
			while (resultSet.next()) {
				listaTipologia.add(new TipologiaCampoDTO(resultSet.getInt("Id_Tipologia"),
						resultSet.getString("Descrizione_Campo")));
			}
		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
		} finally {
			super.closeConnect();
		}
		return listaTipologia;
	}
	
	

	//NON VA BENE
	@Override
	public List<CampoUtility> listaCampiDisponibili(String dI,String dF) throws SQLException,CustomException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		List<CampoUtility> listaDisponibili=new ArrayList<CampoUtility>();

		try {
			
			String sql= "SELECT c.id_pk as Numero_Campo, c.prezzo, c.tipologia_fk as Tip_Campo, ctip.incremento,ctip.nome_tip_campo as Descrizione_Campo,ctip.img "
					+ "FROM tipologia_campo_ct ctip "
					+ "JOIN campo_cl c ON ctip.id_pk=c.tipologia_fk "
					+ "WHERE c.prenotabile=1 AND c.id_pk NOT IN  ( "
					+ "SELECT c1.id_pk "
					+ "FROM campo_cl c1 "
					+ "JOIN prenotazione_cl p ON c1.id_pk=p.campo_fk "
					+ "WHERE to_date(?,'yyyy-mm-dd hh24:mi:ss') BETWEEN p.data_inizio AND p.data_fine "
					+ ") AND c.id_pk NOT IN ( "
					+ "SELECT c2.id_pk "
					+ "FROM campo_cl c2 "
					+ "JOIN accesso_cl a ON c2.id_pk=a.campo_fk "
					+ "JOIN torneo_cl t ON a.torneo_fk=t.id_pk "
					+ "WHERE to_date(?,'yyyy-mm-dd hh24:mi:ss') BETWEEN t.data_inizio AND t.data_fine "
					+ ")";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, dI);
			preparedStatement.setString(2, dI);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				listaDisponibili.add(new CampoUtility(resultSet.getInt("Numero_Campo"),
						resultSet.getString("Descrizione_Campo"),
						(resultSet.getFloat("Prezzo")+resultSet.getFloat("Incremento")),
						resultSet.getInt("Tip_Campo"),resultSet.getBlob("img"),false
						));
				
				/*
				 * new CampoUtility(resultSet.getInt("Numero_Campo"),
						resultSet.getString("Descrizione_Campo"),
						(resultSet.getFloat("Prezzo")+resultSet.getFloat("Incremento")),
						resultSet.getInt("Tip_Campo"),resultSet.getBlob("img"),false
						)
				 */
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new CustomException("La lista ritornata e' null");
		} finally {
			super.closeConnect();
		}
		return listaDisponibili;
		
	}

	
	
	//NON POSSO INSERIRE LA KEYWORD FINALLY,ALTRIMENTI MI Dà ERRORE
	@Override
	public Blob getImagesForCampo(int id) throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		Blob b=null;
		
		try {
			String sql= "SELECT img FROM tipologia_campo_ct t WHERE t.id_pk=? ";
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return b=resultSet.getBlob("img");
			}
			
		} catch (SQLException e) {
				System.out.println("Exception: "+ e.getMessage());
		} 
		
		return b;
	}
	
	
	
	//ADMIN CAMPO
	@Override
	public List<Campo> getAllCampi() throws CustomException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		List<Campo> listaAllCampi=new ArrayList<Campo>();
		
		try {
			String sql= "SELECT c.id_pk as Numero_Campo, c.prezzo, c.tipologia_fk as Tip_Campo,c.prenotabile,ctip.id_pk,ctip.nome_tip_campo as Descrizione_Campo,ctip.incremento,ctip.img FROM tipologia_campo_ct ctip "
					+ "JOIN  campo_cl c ON ctip.id_pk=c.tipologia_fk" ;
			
			 ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					TipologiaCampoDTO t = new TipologiaCampoDTO(resultSet.getInt("id_pk"),
							resultSet.getString("Descrizione_Campo"), resultSet.getFloat("incremento"),null);
					listaAllCampi.add(new Campo(resultSet.getInt("Numero_Campo"),
						resultSet.getString("Descrizione_Campo"),
						resultSet.getFloat("Prezzo"),
						resultSet.getInt("prenotabile"),
						resultSet.getBlob("img"),t
						));
				
		 		}		 
			} catch(SQLException e) {
 			System.out.println("Exception: "+ e.getMessage());
 			throw new CustomException("La lista ritornata è null");
 		}  		
	return listaAllCampi;
	}



	@Override
	public void changePrezzoCampo(int id, float prezzo) throws SQLException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("update CAMPO_CL SET prezzo=? WHERE id_pk=?");
			preparedStatement.setFloat(1,prezzo);
			preparedStatement.setInt(2,id);
			int result=preparedStatement.executeUpdate();
			if(result==1) {
				System.out.println("Record Prezzo Modifcato");
				} 
				else {
					System.out.println("Record Prezzo NON modificato");
				}
					} catch(SQLException e) {
						System.out.println("Exception: "+ e.getMessage());
			} finally {
				super.closeConnect();
			}
		}
		



	@Override
	public void changeTipologiaCampo(int id, int tipologia) throws SQLException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("update CAMPO_CL SET tipologia_fk=? WHERE id_pk=?");
			preparedStatement.setInt(1,tipologia);
			preparedStatement.setInt(2,id);
			int result=preparedStatement.executeUpdate();
			if(result==1) {
				System.out.println("Record Campo Tipologia Modifcato");
				} 
				else {
					System.out.println("Record Campo Tipologia NON modificato");
				}
					} catch(SQLException e) {
						System.out.println("Exception: "+ e.getMessage());
			} finally {
				super.closeConnect();
			}
		}



	@Override
	public CampoDTO searchCampo(int id) throws SQLException {
		super.connect();
		CampoDTO campo = null;
		connection=super.getConnection();
		statement= super.getStatement();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from campo_cl where id_pk=?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				campo = new CampoDTO(rs.getInt("id_pk"), rs.getFloat("prezzo"), rs.getInt("prenotabile"),rs.getInt("tipologia_fk"));
					
				return campo;
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
	public String addCampo(float prezzo, int tipologia) throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CAMPO_CL(prezzo, "
					+ "tipologia_fk) VALUES(?,?) ");
					preparedStatement.setFloat(1,prezzo);
					preparedStatement.setInt(2,tipologia);
					int result=preparedStatement.executeUpdate();
					if(result==1) {
						System.out.println("Record Campo Inserito");
						return "OK";
						} 
						else {
							System.out.println("Record Campo NON Inserito");
						}
							} catch(SQLException e) {
								System.out.println("Exception: "+ e.getMessage());
					} finally {
						super.closeConnect();
				}
		return null;
		
	}



	@Override
	public String addTipologiaCampo(String descrizione, float incremento, InputStream i,int size) throws SQLException, IOException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(" "
					+ "INSERT INTO TIPOLOGIA_CAMPO_CT(nome_tip_campo,incremento,img) VALUES(?,?,?) ");
					
					preparedStatement.setString(1,descrizione);
					preparedStatement.setFloat(2,incremento);
					preparedStatement.setBinaryStream(3, i, size);
					int result=preparedStatement.executeUpdate();
					if(result==1) {
						System.out.println("Record Tipologia_Campo Inserito");
						return "OK";
						} 
						else {
							System.out.println("Record Tipologia_Campo NON Inserito");
						}
							} catch(SQLException e) {
								System.out.println("Exception: "+ e.getMessage());
					} finally {
						super.closeConnect();
				}
			return null;
		
	}


	@Override
	public void changeStatusCampo(int id,int prenotabile) throws SQLException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("update CAMPO_CL SET prenotabile=? WHERE id_pk=?");
			preparedStatement.setInt(1,prenotabile);
			preparedStatement.setInt(2,id);
			int result=preparedStatement.executeUpdate();
			if(result==1) {
				System.out.println("Record status MODIFICATO");
			}
		} catch(SQLException e) {
			System.out.println("Exception: "+ e.getMessage());
		}  finally {
			super.closeConnect();
		}
	}
	
	

	
	

	


	@Override
	public void changeMultiples(int id, float prezzo, int tipologia) throws SQLException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		
		try {
			PreparedStatement preparedStatement=connection.prepareStatement("update CAMPO_CL SET tipologia_fk=?,prezzo=? WHERE id_pk=?");
			preparedStatement.setInt(1,tipologia);
			preparedStatement.setFloat(2,prezzo);
			preparedStatement.setInt(3, id);
			int result=preparedStatement.executeUpdate();
			if(result==1) {
				System.out.println("Record Campo  Modifcato");
				} 
				else {
					System.out.println("Record Campo NON modificato");
				}
					} catch(SQLException e) {
						System.out.println("Exception: "+ e.getMessage());
			} finally {
				super.closeConnect();
			}
		}
	

//-------ADD PRENOTAZIONE WITH TIPOLOGIA AND ADD VIP----------------------------------------
	
	
	@Override
	public List<CampoUtility> getAllInfoCampiComplete(String tipologia) throws CustomException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		List<CampoUtility> listaAllCampi=new ArrayList<CampoUtility>();
		
		try {
	
			String sql= "SELECT c.id_pk as Numero_Campo, c.prezzo, c.tipologia_fk as Tip_Campo,ctip.incremento, "
					+ "ctip.nome_tip_campo as Descrizione_Campo,ctip.img "
					+ " FROM tipologia_campo_ct ctip "
					+ "JOIN campo_cl c ON ctip.id_pk=c.tipologia_fk "
					+ "WHERE ctip.nome_tip_campo=? " ;
			
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1,tipologia);
			 ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					listaAllCampi.add(new CampoUtility(resultSet.getInt("Numero_Campo"),
						resultSet.getString("Descrizione_Campo"),
						(resultSet.getFloat("Prezzo")+resultSet.getFloat("Incremento")),
						resultSet.getInt("Tip_Campo"),resultSet.getBlob("img"),false
						));
				
		 		}		 
			} catch(SQLException e) {
 			System.out.println("Exception: "+ e.getMessage());
 			throw new CustomException("La lista Con Tipologia ritornata è null");
 		}
		
	return listaAllCampi;
	}


	
	//NON SERVE, O MEGLIO, PER IL TORNEO MA NON PER LA CLASSICA PRENOTAZIONE
	@Override
	public List<CampoUtility> listaCampiDisponibiliUtentiVip(String dI, String dF) throws CustomException, SQLException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		List<CampoUtility> listaDisponibili=new ArrayList<CampoUtility>();

		try {

//			String sql= "SELECT c.id_pk as Numero_Campo, c.prezzo, c.tipologia_fk as Tip_Campo, ctip.incremento,ctip.nome_tip_campo as Descrizione_Campo "
//					+ "FROM tipologia_campo_ct ctip "
//					+ "JOIN campo_cl c ON ctip.id_pk=c.tipologia_fk "
//					+ "WHERE c.prenotabile=1 AND ctip.ruolo=1 AND  c.id_pk NOT IN  ( "
//					+ "SELECT c1.id_pk "
//					+ "FROM campo_cl c1 "
//					+ "JOIN prenotazione_cl p ON c1.id_pk=p.campo_fk "
//					+ "WHERE p.data_inizio BETWEEN to_date(?,'yyyy-mm-dd hh24:mi:ss') AND "
//					+ "		to_date(?,'yyyy-mm-dd hh24:mi:ss')  AND "
//					+ " p.data_fine BETWEEN to_date(?,'yyyy-mm-dd hh24:mi:ss') AND "
//					+ "		to_date(?,'yyyy-mm-dd hh24:mi:ss') "
//					+ ") AND c.id_pk NOT IN ( "
//					+ "SELECT c2.id_pk "
//					+ "FROM campo_cl c2 "
//					+ "JOIN accesso_cl a ON c2.id_pk=a.campo_fk "
//					+ "JOIN torneo_cl t ON a.torneo_fk=t.id_pk "
//					+ "WHERE t.data_inizio BETWEEN to_date(?,'yyyy-mm-dd hh24:mi:ss') AND "
//					+ "		to_date(?,'yyyy-mm-dd hh24:mi:ss')  AND "
//					+ " t.data_fine BETWEEN to_date(?,'yyyy-mm-dd hh24:mi:ss') AND "
//					+ "		to_date(?,'yyyy-mm-dd hh24:mi:ss') "
//					+ ")";
//
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, dI);
//			preparedStatement.setString(2, dF);
//			preparedStatement.setString(3, dI);
//			preparedStatement.setString(4, dF);
//			preparedStatement.setString(5, dI);
//			preparedStatement.setString(6, dF);
//			preparedStatement.setString(7, dI);
//			preparedStatement.setString(8, dF);
			
			
			String sql= "SELECT c.id_pk as Numero_Campo, c.prezzo as Prezzo, c.tipologia_fk as Tip_Campo, ctip.incremento as Incremento,ctip.nome_tip_campo as Descrizione_Campo,ctip.img "
					+ "FROM tipologia_campo_ct ctip "
					+ "JOIN campo_cl c ON ctip.id_pk=c.tipologia_fk "
					+ "WHERE c.prenotabile=1 AND ctip.ruolo=1 AND c.id_pk NOT IN  ( "
					+ "SELECT c1.id_pk "
					+ "FROM campo_cl c1 "
					+ "JOIN prenotazione_cl p ON c1.id_pk=p.campo_fk "
					+ "WHERE to_date(?,'yyyy-mm-dd hh24:mi:ss') BETWEEN p.data_inizio AND p.data_fine "
					+ ") AND c.id_pk NOT IN ( "
					+ "SELECT c2.id_pk "
					+ "FROM campo_cl c2 "
					+ "JOIN accesso_cl a ON c2.id_pk=a.campo_fk "
					+ "JOIN torneo_cl t ON a.torneo_fk=t.id_pk "
					+ "WHERE to_date(?,'yyyy-mm-dd hh24:mi:ss') BETWEEN t.data_inizio AND t.data_fine "
					+ ")";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, dI);
			preparedStatement.setString(2, dI);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				listaDisponibili.add(new CampoUtility(resultSet.getInt("Numero_Campo"),resultSet.getString("Descrizione_Campo"),
						(resultSet.getFloat("Prezzo")+resultSet.getFloat("Incremento")), 0, resultSet.getInt("Tip_Campo"),
						resultSet.getBlob("img"), false));
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
	public List<CampoUtility> listaCampiDisponibiliCompletoUtentiSpeciali(String dI, String dF,String tipologia) throws CustomException, SQLException {
		super.connect();
		connection = super.getConnection();
		statement = super.getStatement();
		List<CampoUtility> listaDisponibili=new ArrayList<CampoUtility>();

		try {
	
			String sql= "SELECT c.id_pk as Numero_Campo, c.prezzo as Prezzo, c.tipologia_fk as Tip_Campo, ctip.incremento as Incremento,ctip.nome_tip_campo as Descrizione_Campo,ctip.img "
					+ "FROM tipologia_campo_ct ctip "
					+ "JOIN campo_cl c ON ctip.id_pk=c.tipologia_fk "
					+ "WHERE ctip.nome_tip_campo=? AND c.prenotabile=1"
					+ " AND c.id_pk NOT IN  ( "
					+ "SELECT c1.id_pk "
					+ "FROM campo_cl c1 "
					+ "JOIN prenotazione_cl p ON c1.id_pk=p.campo_fk "
					+ "WHERE to_date(?,'yyyy-mm-dd hh24:mi:ss') BETWEEN p.data_inizio AND p.data_fine "
					+ ") AND c.id_pk NOT IN ( "
					+ "SELECT c2.id_pk "
					+ "FROM campo_cl c2 "
					+ "JOIN accesso_cl a ON c2.id_pk=a.campo_fk "
					+ "JOIN torneo_cl t ON a.torneo_fk=t.id_pk "
					+ "WHERE to_date(?,'yyyy-mm-dd hh24:mi:ss') BETWEEN t.data_inizio AND t.data_fine "
					+ ")";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, tipologia);
			preparedStatement.setString(2, dI);
			preparedStatement.setString(3, dI);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				listaDisponibili.add(new CampoUtility(resultSet.getInt("Numero_Campo"),
						resultSet.getString("Descrizione_Campo"),
						(resultSet.getFloat("Prezzo")+resultSet.getFloat("Incremento")),
						resultSet.getInt("Tip_Campo"),resultSet.getBlob("img"),true
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
	public List<CampoUtility> searchListCampiDaScontare() throws SQLException, CustomException {
		super.connect();
		connection=super.getConnection();
		statement= super.getStatement();
		List<CampoUtility> listaSconti=new ArrayList<CampoUtility>();
		
		try {
	
			String sql= "SELECT c.id_pk as Numero_Campo, c.prezzo, c.tipologia_fk as Tip_Campo,ctip.incremento, "
					+ "ctip.nome_tip_campo as Descrizione_Campo,ctip.img "
					+ " FROM tipologia_campo_ct ctip "
					+ "JOIN campo_cl c ON ctip.id_pk=c.tipologia_fk "
					+ "WHERE ctip.nome_tip_campo LIKE ? OR "
					+ "		ctip.nome_tip_campo LIKE ? OR ctip.nome_tip_campo LIKE ? " ;
			
				PreparedStatement preparedStatement=connection.prepareStatement(sql);
				preparedStatement.setString(1, '%' + "pallavolo" + '%');
				preparedStatement.setString(2, '%' + "calc" + '%');
				preparedStatement.setString(3, '%' + "soccer" + '%');
//				IDENTICO
//				preparedStatement.setString(1, "%pallavolo%");
//				preparedStatement.setString(2, "%calc%");
//				preparedStatement.setString(3, "%soccer%");
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					listaSconti.add(new CampoUtility(resultSet.getInt("Numero_Campo"),
						resultSet.getString("Descrizione_Campo"),
						(resultSet.getFloat("Prezzo")+resultSet.getFloat("Incremento")),
						resultSet.getInt("Tip_Campo"),resultSet.getBlob("img"),false
						));
				
		 		}		 
			} catch(SQLException e) {
 			System.out.println("Exception: "+ e.getMessage());
 			throw new CustomException("La lista Con Tipologia ritornata è null");
 		}
		
	return listaSconti;
	}



}	
	