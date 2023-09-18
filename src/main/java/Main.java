import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.dto.MatchDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.Squadra;
import com.project.utility.TorneoEliminazione;

//SI DEVE USARE IL CAST!!!!

public class Main {
	
	
	 
//	 	for(int i=0;i<3;i++) {
//	 		for(int j=0;j<5;j++) 
//	 			System.out.println("Tipo: "+ i+ " , "+ j);
//	 		
//	 	}
	 		

		
//		 CampoDAO campoDao=new CampoDAOImpl();
//		 Connection connection=null;
//		 Statement statement=null;
//		 ResultSet resultSet = null;
//		 PreparedStatement preparedStatement = null;
//		 Scanner s = new Scanner(System.in);
//		 List<Utente> listaDettagli=new ArrayList<>();
//		 int count=0;
//		 
//	 	try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSW);
//			statement = connection.createStatement();
//			System.out.println("connesso");
//			
//			ProgettoService p= new ProgettoServiceImpl();
//			
//			
//			
//	 	} catch(Exception e) {
//	 		e.printStackTrace();
//	 		System.out.println("Erroreee: "+ e.getMessage());
//	 	}
//	
//	}
		
		
// -----------------------------------------------------------------------------------------------------------
		
	public class Tuple<E, F> {
	    public E First;
	    public F Second;
	}
	
	
	
	public static void main(String [] args) throws Exception {
	
		ProgettoService progettoService= new ProgettoServiceImpl();
		int numColonne=0;
		TorneoEliminazione tab= new TorneoEliminazione();
		List<Integer> listaNumColonne= new ArrayList<Integer>();
		TorneoEliminazione turno= new TorneoEliminazione();
		
		List<MatchDTO> listaMatch= progettoService.getMatchFromIdTorneo(1);
		List<TorneoEliminazione> colonneTabellone= progettoService.tabelloneTorneo(1, listaMatch);
//		METODO STATICO PER STAMPARE LE COLONNE!!!!
//		for(TorneoEliminazione2 t1: listaTorneoEliminazione) {
//			if(t1.getNumColonna()==1) {
//				tab1= t1;
//				System.out.println(tab1);
//			}
//			if(t1.getNumColonna()==2) {
//				tab2= t1;
//				System.out.println(tab2);
//			}
//			if(t1.getNumColonna()==3) {
//				tab3= t1;
//				System.out.println(tab3);
//			}
//			if(t1.getNumColonna()==4) {
//				tab4= t1;
//				System.out.println(tab4);
//			}
//		}
		
		
		
		
//		for(TorneoEliminazione t1: colonneTabellone) {
//			listaNumColonne.add(t1.getNumColonna());
//		}
//		
//		for(TorneoEliminazione t1: colonneTabellone) {
//			for(int indice: listaNumColonne) {
//				if(t1.getNumColonna()==indice) {
//					tab=t1;
//					System.out.println("Turno: "+ indice);
//					//System.out.println(tab);
//					for(int i=0;i<tab.getListaSquadra().size();i++) {
//						System.out.print(tab.getListaSquadra().get(i)+"\t");
//						
//					}
//					System.out.println();
//				}
//			}
//		}
		
		
		
		
//		TorneoEliminazione t= new TorneoEliminazione();
//		List<HashMap<String, Integer>> listaHash= new ArrayList<HashMap<String,Integer>>();
//		for(int i=0;i<colonneTabellone.size();i++) {
//			 t= colonneTabellone.get(i);
//			 for(int j=0;j<t.getNumColonna();j++) {
//				  listaHash= t.getListaSquadra();
//				 for(HashMap<String, Integer> m: listaHash) {
////					 ArrayList<String> keyList= (ArrayList<String>) listaHash.get(j).keySet();
//						 System.out.println("INDICE: "+ i+", con colonna numero: "+j+" con valori: "+ m);
//						 System.out.println();
//					 }
//				 }
//			 }
		
	}
}



			
			
	
