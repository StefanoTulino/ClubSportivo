package com.project.utility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UTILITY {

	//1)COSE SU HASHMAP E LIST<HashMap<String,Integer>> !!!!!!!!!
		//In progettoServiceImpl è servita questa base
	
	
	/*
	 * //		  for (HashMap<String, Integer> h: hashmapSquadra){
		//			      ArrayList<String> keyList = new ArrayList<String>(h.keySet());
		//			      ArrayList<Integer> valueList = new ArrayList<Integer>(h.values());
		//			  Set<Entry<String, Integer>> theHouse=null;
			  	//solo stringa
		//			  	System.out.println(h.keySet());
			    //string,integer
		//	            System.out.println("sasd: "+h.entrySet());
	            //string,integer
		//			  	theHouse=h.entrySet();
		//	            System.out.println("HOUSE: "+ theHouse);
	            
		//	            System.out.println(keyList);
		//	            System.out.println();
		//	            System.out.println(valueList);
		//	        }
		  
		  
		  		//ACCESSO AD UN SINGOLO ELEMENTO E VERIFICA SE C'è O MENO UN ELEMENTO
		//		  for (HashMap<String, Integer> h: hashmapSquadra){
		//		  for(Map.Entry<String,Integer> theHouse : h.entrySet()){
		//	            System.out.println(theHouse.getKey() +" = "+theHouse.getValue());
		//		  	}
		//		  }
		//		  
		//		  HashMap<String,Integer> m1= new HashMap<String, Integer>();
		//		  m1.put("juvefc4@gmail.com", 1);
		//		  if(hashmapSquadra.contains(m1)) {
		//		  		System.out.println("Ciao");
		//		  	}
	 */
	
	
//....................................................................................
	
	//2)Set<String>,esempio pratico!!!!!!!!!!
	
	
	/*
	 * // NON AMMETTE DUPLICATI
		Set<String> listaSquadreVincenti = new HashSet<String>();
		String s="";
		listaSquadreVincenti.add(s);
	
	*/
	
	
//..............................................................................................
	
	//3) Conversione Data forse utilizzabile(Metodo Rapido)!!!!
	
	/*
	 * 	Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(dI);
		Date dateProva = Date.valueOf(timestamp.toLocalDateTime().toLocalDate());
	*/
	
	
//.............................................................................................	
	
	//4) USO TRY/CATCH nel ServiceImpl con una Exception creata
	
	/*
	 * try {
	 * ...
	 * if (checkPassword == false) {
				throw new RegistrazioneException("Password non corretta: deve contenere almeno un numero,"
						+ "almeno un carattere maiuscolo,almeno un carattere minuscolo e dev'essere almeno di"
						+ "8 caratteri");
			}

			else {
				throw new RegistrazioneException();
			}

		} catch (SQLException e) {
			System.out.println("RegistrazioneException: " + e.getMessage());
			throw new RegistrazioneException("Credenziali errate: registrazione non avvenuta");
		}
	 */

	
//...........................................................................................
	
	// 5) USO DI COMPARATOR per comparare ed ordinare poi una lista di oggetti
	
	/*
	 * // ORDINAMENTO SQUADRE PER POI VEDERLE COME UNA CLASSIFICA
			Comparator<Squadra> comparator = new Comparator<Squadra>() {
				@Override
				public int compare(Squadra o1, Squadra o2) {
					if (o1.getPuntiTotali() > o2.getPuntiTotali()) {
						return -1;
					} else if (o1.getPuntiTotali() < o2.getPuntiTotali()) {
						return +1;
					}
					return 0;
				}
			};
			
			Collections.sort(listaSquadra, comparator);
	 */
	
	
	
//...........................................................................................
	
		// 6) Metodo utilizzato in precedenza con List<String>, ora non serve piu
			//In pratica prende la lista di squadre e seleziona(tramite un remove) solo le prime 4 
				//con punteggio Totale Migliore
	
	
	/*
	 * // VERSIONE MIGLIORABILE
	@Override
	public ClassificaMatch classificaTorneoEliminazione(int idTorneo) {
		ClassificaMatch classifica = new ClassificaMatch();
		List<Match> listaMatch = new ArrayList<Match>();
		List<Squadra> listaSquadraFinale = new ArrayList<Squadra>();
		try {
			listaMatch = matchDAO.getMatchUserTorneo(idTorneo);
			List<Squadra> listaSquadra = listaUtentiForTorneo(idTorneo);

			// ...

			for (int i = 0; i < listaSquadra.size(); i++) {
				Squadra s = listaSquadra.get(i);

				for (int j = 0; j < listaMatch.size(); j++) {
					Match m = listaMatch.get(j);
					if ((m.getRisultatoSfidante1() != null && m.getRisultatoSfidante2() != null)
							&& (m.getRisultatoSfidante1() != "-" && m.getRisultatoSfidante2() != "-")) {
						if (Integer.parseInt(m.getRisultatoSfidante1()) > Integer.parseInt(m.getRisultatoSfidante2())) {
							if (m.getSfidante1().equals(s.getEmail())) {
								s.setEmail(m.getSfidante1());
								s.setPartiteGiocate(s.getPartiteGiocate() + 1);
								s.setVittoria(s.getVittoria() + 1);
								s.setPuntiTotali(s.getPuntiTotali() + 3);

							} else if (m.getSfidante2().equals(s.getEmail())) {
								s.setEmail(m.getSfidante2());
								s.setPartiteGiocate(s.getPartiteGiocate() + 1);
								s.setSconfitta(s.getSconfitta() + 1);
								s.setPuntiTotali(s.getPuntiTotali() + 0);
							}

						}

						if (Integer.parseInt(m.getRisultatoSfidante1()) == Integer
								.parseInt(m.getRisultatoSfidante2())) {
							if (m.getSfidante1().equals(s.getEmail())) {
								s.setEmail(m.getSfidante1());
								s.setPartiteGiocate(s.getPartiteGiocate() + 1);
								s.setPareggio(s.getPareggio() + 1);
								s.setPuntiTotali(s.getPuntiTotali() + 1);

							} else if (m.getSfidante2().equals(s.getEmail())) {
								s.setEmail(m.getSfidante2());
								s.setPartiteGiocate(s.getPartiteGiocate() + 1);
								s.setPareggio(s.getPareggio() + 1);
								s.setPuntiTotali(s.getPuntiTotali() + 1);
							}

						}

						if (Integer.parseInt(m.getRisultatoSfidante1()) < Integer.parseInt(m.getRisultatoSfidante2())) {
							if (m.getSfidante1().equals(s.getEmail())) {
								s.setEmail(m.getSfidante1());
								s.setPartiteGiocate(s.getPartiteGiocate() + 1);
								s.setSconfitta(s.getSconfitta() + 1);
								s.setPuntiTotali(s.getPuntiTotali() + 0);

							} else if (m.getSfidante2().equals(s.getEmail())) {
								s.setEmail(m.getSfidante2());
								s.setPartiteGiocate(s.getPartiteGiocate() + 1);
								s.setVittoria(s.getVittoria() + 1);
								s.setPuntiTotali(s.getPuntiTotali() + 3);
							}
						}

					}
				}
			}
			// ORDINAMENTO SQUADRE PER POI VEDERLE COME UNA CLASSIFICA
			Comparator<Squadra> comparator = new Comparator<Squadra>() {
				@Override
				public int compare(Squadra o1, Squadra o2) {
					if (o1.getPuntiTotali() > o2.getPuntiTotali()) {
						return -1;
					} else if (o1.getPuntiTotali() < o2.getPuntiTotali()) {
						return +1;
					}
					return 0;
				}
			};

			Collections.sort(listaSquadra, comparator);
			if (listaSquadra.size() > 3) {
				for (int i = 0; i < 4; i++) {
					// VOGLIO SAPERE I PRIMI 4 DI UN TORNEO
					listaSquadraFinale.add(listaSquadra.get(i));
				}
				System.out.println("SIZE: " + listaSquadra.size());
				classifica.setSquadra(listaSquadraFinale);
				return classifica;

			} else if (listaSquadra.size() > 0 && listaSquadra.size() < 4) {
				classifica.setSquadra(listaSquadra);
				return classifica;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}

	return null;
	}
	 */
}
