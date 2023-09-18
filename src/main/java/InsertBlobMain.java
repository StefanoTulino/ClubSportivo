import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.project.constant.Constant;
import com.project.dto.PrenotazioneDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;

public class InsertBlobMain {

	public static void main(String [] args) throws Exception {
		
			//codice per prendere byteArray da un file(con path specifico)
			
			//chiamo il metodo del service
			
			//check con SYSOUT
		
		 Connection connection=null;
		 Statement statement=null;
		 ResultSet resultSet = null;
		 PreparedStatement preparedStatement = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSW);
			statement = connection.createStatement();
			System.out.println("connesso");
			
			
			ProgettoService progetto= new ProgettoServiceImpl();
			File f=new File("C:/Users/s.tulino/Desktop/BLOB/campo1.jpg");
			System.out.println(f);
			
			//CON FILE COME PARAMETRO,USARE UN METODO VISTO NEL PROJECT PROGETTOCAMPO(QUASI COMPLETO)
			//progetto.addTipologiaCampo("ciaoo", 11, f);
			
			

		
	} catch (Exception e) {
		e.getStackTrace();
	  System.out.println("Causa: " + e.getMessage() );
		}
			
			
		}
	
	
	public static byte[] method(File file)
	        throws IOException
	    {
	  
	        // Creating an object of FileInputStream to
	        // read from a file
	        FileInputStream fl = new FileInputStream(file);
	  
	        // Now creating byte array of same length as file
	        byte[] arr = new byte[(int)file.length()];
	  
	        // Reading file content to byte array
	        // using standard read() method
	        fl.read(arr);
	  
	        // lastly closing an instance of file input stream
	        // to avoid memory leakage
	        fl.close();
	  
	        // Returning above byte array
	        return arr;
	    }
	
}
