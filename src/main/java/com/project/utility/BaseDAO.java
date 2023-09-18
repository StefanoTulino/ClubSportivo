package com.project.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.project.constant.Constant;

public class BaseDAO {

	private Connection connection=null;
	private Statement statement=null;
	
	
	protected Connection connect() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSW);
			 statement = connection.createStatement();
			System.out.println("connesso");
		} catch (Exception e) {
			System.out.println("Causa: " + e.getMessage());
		} 
	return connection;
	}

	
	protected void closeConnect() throws SQLException {
		statement.close();
		connection.close();
		System.out.println("\n\nconnessione chiusa");
	}


	public Connection getConnection() {
		return connection;
	}


	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	public Statement getStatement() {
		return statement;
	}


	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	
	
}
