package com.project.constant;

public class Constant {
	
	public static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    public static final String DB_USER = "c##progetto_finale_eustema_formazione";
    public static final String DB_PASSW = "root1";
    
    public static final String EX_LOGIN_MESSAGE="Credenziali errate: login non valido";
	
		
// -------------------------------------------------------------------------------
	
	// -- MYSQL CONFIGURATION PLAUSIBILE
//	jdbc:mysql://indirizzo:PORT/nomeConnessioneDelDb(non della connessione) --> FRAZIONATO IN 3
//		public static final String DB_URL = "jdbc:mysql://localhost:3306/progetto0";
//		public static final String DB_USER = "root";
//		public static final String DB_PASSW = "root";
//	jdbc:mysql://indirizzo:PORT/nomeConnessione?user=nomeUser(in genere ora è root) --> INTERO(NON SO SE VA)
	
	
// -------------------------
	
	//(ORACLE OLD)
//	public static final String DB_URL = "jdbc:oracle:thin:@//192.168.244.83:1521/xepdb1"; --> SAMSUNG
	
//	public static final String DB_URL = "jdbc:oracle:thin:@//192.168.1.61:1521/xepdb1"; --> CASA MIA1
//	public static final String DB_URL = "jdbc:oracle:thin:@//192.168.43.83:1521/xepdb1"; --> XIAOMI
//	public static final String DB_URL = "jdbc:oracle:thin:@//192.168.0.150:1521/xepdb1"; --> ROUTER AZIENDALE
//	public static final String DB_URL = "jdbc:oracle:thin:@//192.168.1.226:1521/xepdb1"; --> NONNA
	
}
