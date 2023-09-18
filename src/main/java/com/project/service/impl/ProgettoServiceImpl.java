package com.project.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.project.dao.CampoDAO;
import com.project.dao.MatchDAO;
import com.project.dao.PrenotazioneDAO;
import com.project.dao.TorneoDAO;
import com.project.dao.UtenteDAO;
import com.project.dao.impl.CampoDAOImpl;
import com.project.dao.impl.MatchDAOImpl;
import com.project.dao.impl.PrenotazioneDAOImpl;
import com.project.dao.impl.TorneoDAOImpl;
import com.project.dao.impl.UtenteDAOImpl;
import com.project.dto.CampoDTO;
import com.project.dto.MatchDTO;
import com.project.dto.PrenotazioneDTO;
import com.project.dto.TipologiaCampoDTO;
import com.project.dto.TipologiaTorneoDTO;
import com.project.dto.TipologiaUtenteDTO;
import com.project.dto.TorneoDTO;
import com.project.dto.UtenteDTO;
import com.project.exception.CustomException;
import com.project.exception.LoginException;
import com.project.exception.RegistrazioneException;
import com.project.service.ProgettoService;
import com.project.utility.Campo;
import com.project.utility.CampoUtility;
import com.project.utility.ClassificaMatch;
import com.project.utility.Match;
import com.project.utility.Squadra;
import com.project.utility.Torneo;
import com.project.utility.TorneoEliminazione;
import com.project.utility.Utente;
import com.project.utility.UtenteSession;

public class ProgettoServiceImpl implements ProgettoService {

	private UtenteDAO utenteDAO = new UtenteDAOImpl();
	private CampoDAO campoDAO = new CampoDAOImpl();
	private PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAOImpl();
	private TorneoDAO torneoDAO = new TorneoDAOImpl();
	private MatchDAO matchDAO = new MatchDAOImpl();

	// BASE
	@Override
	public UtenteSession login(String email, String password) throws LoginException, SQLException {
		UtenteDTO utente = utenteDAO.loginUtente(email, password);
		List<PrenotazioneDTO> lista = prenotazioneDAO.listaPrenotazioniForUtente(email);
		UtenteSession utenteS = new UtenteSession(utente.getEmail(), utente.getNome(), utente.getCognome(),
				utente.getTipologiaUtente(), lista, utente.getLogo());
		return utenteS;
	}

	@Override
	public UtenteSession registrazione(String email, String password, String nome, String cognome, boolean tipologia)
			throws RegistrazioneException, SQLException {
		UtenteDTO utente = new UtenteDTO();
		try {
			// REGEX
			boolean checkEmail = Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
			boolean checkPassword = Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$", password);
			if (checkEmail && checkPassword) {
				utente.setEmail(email);
				utente.setPassword(password);
				utente.setNome(nome);
				utente.setCognome(cognome);
				utente = utenteDAO.registrazioneUtente(utente, tipologia);

				UtenteSession utenteS = new UtenteSession(utente);
				return utenteS;
			}

			if (checkEmail == false) {
				throw new RegistrazioneException("Email non corretta: riprovare");
			}
			if (checkPassword == false) {
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
	}

	@Override
	public List<Utente> gestioneUtenti2() {
		List<Utente> listaUtente = new ArrayList<Utente>();
		try {
			listaUtente = utenteDAO.listaUtenti();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore: " + e.getMessage());
		}
		return listaUtente;
	}

	@Override
	public Utente dettaglioUtente(String email) {
		Utente utente = null;
		try {
			utente = utenteDAO.dettaglioUtente(email);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore: " + e.getMessage());
		}
		return utente;
	}

	@Override
	public UtenteDTO modificaPasswordUtente(String nuovaPassword, String email) {
		UtenteDTO utente = new UtenteDTO();
		try {
			utente = utenteDAO.searchUtente(email);

			if (!(utente.getPassword().equals(nuovaPassword))) {
				utenteDAO.modificaPasswordUtente(nuovaPassword, email);
				utente.setPassword(nuovaPassword);
			}

		} catch (SQLException e) {
			System.out.println("Errore: " + e.getMessage());
		}
		return utente;
	}

	@Override
	public List<TipologiaUtenteDTO> listaTipologia() {
		List<TipologiaUtenteDTO> lista = new ArrayList<TipologiaUtenteDTO>();
		try {
			lista = utenteDAO.listaTipologia();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore: " + e.getMessage());
		}
		return lista;
	}

	@Override
	public UtenteDTO modificaTipologiaUtente(int tipologiaUtente, String email) {
		UtenteDTO utente = new UtenteDTO();
		try {
			utente = utenteDAO.searchUtente(email);

			utenteDAO.modificaTipologiaUtente(tipologiaUtente, email);
			utente.setTipologiaUtente(tipologiaUtente);

		} catch (SQLException e) {
			System.out.println("Errore: " + e.getMessage());
		}
		return utente;
	}

//-----------------------------------------------------------------------------------------------------------------

	// CAMPI E PRENOTAZIONE

	// User REGISTRATO classico
	@Override
	public List<CampoUtility> getAllInfocampi(String idUtente, String d) {
		List<CampoUtility> listaCampi = new ArrayList<CampoUtility>();
		List<CampoUtility> listaCampiDisp = new ArrayList<CampoUtility>();

		try {
			String dataFine = addDataFine(idUtente, d);
			listaCampi = campoDAO.getAllInfoCampi();
			listaCampiDisp = campiDisponibili(d, dataFine);

			for (CampoUtility p : listaCampi) {
				for (CampoUtility p1 : listaCampiDisp) {
					if (p.getId() == (p1.getId()))
						p.setDisponibilità(true);
				}
			}

		} catch (CustomException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCampi;
	}

	
	@Override
	public List<CampoUtility> getAllInfocampiComplete(String idUtente, String d, String tipologia) {
		List<CampoUtility> listaCampi = new ArrayList<CampoUtility>();
		List<CampoUtility> listaCampiDisp = new ArrayList<CampoUtility>();

		try {
			String dataFine = addDataFine(idUtente, d);
			System.out.println("Get All Info " + dataFine);

			listaCampi = campoDAO.getAllInfoCampiComplete(tipologia);
			listaCampiDisp = campiDisponibili(d, dataFine);

			for (CampoUtility p : listaCampi) {
				for (CampoUtility p1 : listaCampiDisp) {
					if (p.getId() == (p1.getId()))
						p.setDisponibilità(true);
				}
			}

		} catch (CustomException e) {
			e.printStackTrace();
		}
		return listaCampi;
	}

	
	@Override
	public byte[] getImageCampo(int id) {
		byte[] immagine = null;

		try {
			Blob b = campoDAO.getImagesForCampo(id);
			immagine = b.getBytes(1l, (int) b.length());
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return immagine;
	}

	
	// QUESTO ERA PRIVATE!!!!!!!!!!!
	public List<CampoUtility> campiDisponibili(String d, String d2) {
		List<CampoUtility> listaCampiDisp = new ArrayList<CampoUtility>();
		try {
			if (d.contains("T")) {
				d = d.replace('T', ' ');
			}
			if (d2.contains("T")) {
				d2 = d2.replace('T', ' ');
			}
			listaCampiDisp = campoDAO.listaCampiDisponibili(d, d2);
			return listaCampiDisp;
		} catch (SQLException | CustomException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public List<TipologiaCampoDTO> listaTipologiaCampo() {
		List<TipologiaCampoDTO> lista = new ArrayList<TipologiaCampoDTO>();
		try {
			lista = campoDAO.listaTipologia();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	
	private String addDataFine(String email, String d) {
		String dataFine = "";
		try {
			if (d.contains("T")) {
				d = d.replace('T', ' ');
			}
			Utente utente = utenteDAO.dettaglioUtente(email);
			switch (utente.getTipologiaUtente().getNome()) {
			case "registrato": {
				Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(d);
				System.out.println("Date format:" + date1);
				date1.setHours(date1.getHours() + 1);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime ldate = LocalDateTime.ofInstant(Instant.ofEpochMilli(date1.getTime()),
						ZoneId.systemDefault());
				dataFine = ldate.format(formatter);
				System.out.println(dataFine);
			}
			default:
				;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataFine;
	}

	
	// VIP E ASS
	@Override
	public List<Torneo> TorneoVipDisponibili(String emailUtente) {
		try {
			List<Torneo> lista = torneoDAO.TorneoForVipDisponibili(emailUtente);
//			List<Torneo> listaTorneiPrenotati= torneoDAO.TorneoUtentePrenotati(emailUtente);
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		} catch (CustomException e) {
			e.printStackTrace();
			System.out.println("Error Custom: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Torneo> TorneoAssDisponibili(String emailUtente) {
		try {
			List<Torneo> lista = torneoDAO.TorneoForAssDisponibili(emailUtente);
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		} catch (CustomException e) {
			e.printStackTrace();
			System.out.println("Error Custom: " + e.getMessage());
		}
		return null;
	}

	
	// lo stato rimane sempre ROSSO A MENO CHE NON FACCIA PARTE DELLE CATEGORIE
	// SCONTATE!!!
	@Override
	public List<CampoUtility> getAllInfocampiVipComplete(String dataInizio, String dataFine, String tipologia) {
		List<CampoUtility> listaCampiScontati = new ArrayList<CampoUtility>();
		List<CampoUtility> listaCampiDisp = new ArrayList<CampoUtility>();
		if (dataInizio.contains("T")) {
			dataInizio = dataInizio.replace('T', ' ');
		}
		if (dataFine.contains("T")) {
			dataFine = dataFine.replace('T', ' ');
		}

		try {
			listaCampiScontati = campoDAO.searchListCampiDaScontare();
			listaCampiDisp = campoDAO.listaCampiDisponibiliCompletoUtentiSpeciali(dataInizio, dataFine, tipologia);

			for (CampoUtility p1 : listaCampiDisp) {
				for (CampoUtility p2 : listaCampiScontati) {
					p1.setDisponibilità(true);
					if (p1.getId() == p2.getId()) {
						float x = p1.getCosto();
						float percentuale = x * 10 / 100;
						x -= percentuale;
						p1.setPrezzoScontato(x);
					}
				}
			}

		} catch (CustomException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCampiDisp;
	}

	
	@Override
	public List<CampoUtility> getAllInfocampiVip(String dataInizio, String dataFine) {
		List<CampoUtility> listaCampiScontati = new ArrayList<CampoUtility>();
		List<CampoUtility> listaCampiDisp = new ArrayList<CampoUtility>();
		if (dataInizio.contains("T")) {
			dataInizio = dataInizio.replace('T', ' ');
		}
		if (dataFine.contains("T")) {
			dataFine = dataFine.replace('T', ' ');
		}

		try {
			listaCampiScontati = campoDAO.searchListCampiDaScontare();
			listaCampiDisp = campiDisponibili(dataInizio, dataFine);

			for (CampoUtility p1 : listaCampiDisp) {
				for (CampoUtility p2 : listaCampiScontati) {
					p1.setDisponibilità(true);
					if (p1.getId() == p2.getId()) {
						float x = p1.getCosto();
						float percentuale = x * 10 / 100;
						x -= percentuale;
						p1.setPrezzoScontato(x);
					}
				}
			}

		} catch (CustomException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCampiDisp;
	}

	@Override
	public List<CampoUtility> getAllInfocampiAssAdminComplete(String dataInizio, String dataFine, String tipologia) {
		List<CampoUtility> listaCampiDisp = new ArrayList<CampoUtility>();
		if (dataInizio.contains("T")) {
			dataInizio = dataInizio.replace('T', ' ');
		}
		if (dataFine.contains("T")) {
			dataFine = dataFine.replace('T', ' ');
		}

		try {
			listaCampiDisp = campoDAO.listaCampiDisponibiliCompletoUtentiSpeciali(dataInizio, dataFine, tipologia);

		} catch (CustomException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCampiDisp;
	}

//--------------------------PRENOTAZIONI------------------------------------	

	// DA USARE SOLO PER I TORNEI, NON PER LE PRENOTAZIONI CLASSICHE
	@Override
	public String dataFormattata(String str) {
		String s = "";
		try {
			s = torneoDAO.convertiTimestampInDate(str);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return s;
	}

	
	@Override
	public String addPrenotazione(String idUtente, int idCampo, String d) {
		if (d.contains("T")) {
			d = d.replace('T', ' ');
		}

		String res;
		try {
			String dataFine = addDataFine(idUtente, d);
			res = prenotazioneDAO.addPrenotazione(idUtente, idCampo, d, dataFine);
			if (res != null && res.equalsIgnoreCase("OK")) {
				return "Prenotazione effettuata del campo " + idCampo + ", in data " + d;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public String addPrenotazioneVip(String idUtente, int idCampo, String dI, String dF) {
		if (dI.contains("T")) {
			dI = dI.replace('T', ' ');
		}
		if (dF.contains("T")) {
			dF = dF.replace('T', ' ');
		}

		String res;
		try {
			res = prenotazioneDAO.addPrenotazione(idUtente, idCampo, dI, dF);
			if (res != null && res.equalsIgnoreCase("OK")) {
				return "Prenotazione effettuata del campo " + idCampo + ", con data di inizio " + dI + " e "
						+ " data di fine" + dF;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	
	@Override
	public List<PrenotazioneDTO> prenotazioniAttiveForUtente(String email) {
		List<PrenotazioneDTO> lista = new ArrayList<PrenotazioneDTO>();
		try {
			lista = prenotazioneDAO.listaPrenotazioniForUtente(email);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return lista;
	}

//-------------------------------------------------------------------------------------------------------------------

	// GESTIONE CAMPI ADMIN
	@Override
	public List<Campo> campiAll() {
		List<Campo> listaCampi = new ArrayList<Campo>();

		try {
			listaCampi = campoDAO.getAllCampi();
			return listaCampi;
		} catch (CustomException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	
	@Override
	public CampoDTO modificaPrezzoCampo(int id, float prezzo) {
		CampoDTO campo = new CampoDTO();
		String prezzoCheck = Float.toString(prezzo);
		try {
			campo = campoDAO.searchCampo(id);
			if (prezzoCheck.contains(",")) {
				prezzoCheck = prezzoCheck.replace(',', '.');
				prezzo = Float.parseFloat(prezzoCheck);
			}

			campoDAO.changePrezzoCampo(id, prezzo);
			campo.setPrezzo(prezzo);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore: " + e.getMessage());
		}
		return campo;
	}

	
	@Override
	public CampoDTO modificaTipologiaCampo(int id, int tipologia) {
		CampoDTO campo = new CampoDTO();
		try {
			campo = campoDAO.searchCampo(id);
			campoDAO.changeTipologiaCampo(id, tipologia);
			campo.setTipologia(tipologia);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore: " + e.getMessage());
		}
		return campo;
	}

	
	@Override
	public String addCampo(float prezzo, int tipologia) {
		try {
			String res = campoDAO.addCampo(prezzo, tipologia);
			if (res != null && res.equalsIgnoreCase("OK")) {
				return "Inserimento del campo effettuato";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	
	@Override
	public String addTipologiaCampo(String descrizione, float incremento, InputStream i, int size) {
		try {
			String res = campoDAO.addTipologiaCampo(descrizione, incremento, i, size);
			if (res != null && res.equalsIgnoreCase("OK")) {
				return "Inserimento di una tipologia campo effettuata";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	
	@Override
	public String setStatusCampo(int id, int prenotabile) {
		try {
			CampoDTO campo = campoDAO.searchCampo(id);
			if (campo.getPrenotabile() == 1) {
				prenotabile = 0;
				campo.setPrenotabile(0);
				campoDAO.changeStatusCampo(id, prenotabile);
				return "Il campo con id: " + campo.getId() + " e' stato disabilitato con successo";

			} else if (campo.getPrenotabile() == 0) {
				prenotabile = 1;
				campo.setPrenotabile(1);
				campoDAO.changeStatusCampo(id, prenotabile);
				return "Il campo con id: " + campo.getId() + " e' stato abilitato con successo";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	
	@Override
	public CampoDTO modificheAdminCampo(int id, float prezzo, int tipologia) {
		CampoDTO campo = new CampoDTO();
		try {
			campo = campoDAO.searchCampo(id);

			if (campo.getPrezzo() != prezzo && campo.getTipologia() != tipologia) {
				campoDAO.changeMultiples(id, prezzo, tipologia);
				campo.setPrezzo(prezzo);
				campo.setTipologia(tipologia);
				return campo;
			}

			else if (campo.getPrezzo() != prezzo) {
				campoDAO.changePrezzoCampo(id, prezzo);
				campo.setPrezzo(prezzo);
				return campo;
			} else if (campo.getTipologia() != tipologia) {
				campoDAO.changeTipologiaCampo(id, tipologia);
				campo.setTipologia(tipologia);
				return campo;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}

		return null;
	}

//---------------------------MODIFICHE USER PRENOTAZIONI------------------------------------------------
	@Override
	public List<PrenotazioneDTO> prenotazioniError(String email) {
		List<PrenotazioneDTO> lista = new ArrayList<PrenotazioneDTO>();
		try {
			lista = prenotazioneDAO.prenotazioniError(email);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return lista;
	}

	
	@Override
	public String modificaPrenotazione(int idPrenotazione, int idCampo, String dataInizio, String dataFine) {
		PrenotazioneDTO prenotazione = new PrenotazioneDTO();
		String msg = "";

		try {
			if (dataInizio.contains("T")) {
				dataInizio = dataInizio.replace('T', ' ');
			}
			if (dataFine.contains("T")) {
				dataFine = dataFine.replace('T', ' ');
			}
			msg = prenotazioneDAO.changePrenotazione(idPrenotazione, idCampo, dataInizio, dataFine);
			System.out.println(msg);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}

		return msg;
	}

	
	@Override
	public String eliminaPrenotazione(int idPrenotazione) {
		String msg = null;
		try {
			msg = prenotazioneDAO.deletePrenotazione(idPrenotazione);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return msg;
	}

//---------------------GESTIONE TORNEO, VIEW E MODIFICA ---------------------------------------

	public List<CampoUtility> campiDisponibiliForTorneo(String dI, String dF) {
		List<CampoUtility> listaCampiDisp = new ArrayList<CampoUtility>();
		try {
			if (dI.contains("T") && dF.contains("T")) {
				dI = dI.replace('T', ' ');
				dF = dF.replace('T', ' ');
			}
			listaCampiDisp = torneoDAO.getAllInfocampiForTorneo(dI, dF);
			return listaCampiDisp;
		} catch (SQLException | CustomException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	
	@Override
	public List<CampoUtility> getAllInfocampiForTorneo(String dI, String dF) {
		List<CampoUtility> listaCampi = new ArrayList<CampoUtility>();
		List<CampoUtility> listaCampiDisp = new ArrayList<CampoUtility>();

		try {
			listaCampi = campoDAO.getAllInfoCampi();
			listaCampiDisp = campiDisponibiliForTorneo(dI, dF);

			for (CampoUtility p : listaCampi) {
				for (CampoUtility p1 : listaCampiDisp) {
					if (p.getId() == (p1.getId()))
						p.setDisponibilità(true);
				}
			}

		} catch (CustomException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCampi;
	}

	
	@Override
	public String addTorneo(String nome, String dataInizio, String dataFine, int numSquadra, int idCampo, int tipologiaTorneo) {
		try {
			if (dataInizio.contains("T") && dataFine.contains("T")) {
				dataInizio = dataInizio.replace('T', ' ');
				dataFine = dataFine.replace('T', ' ');
			}
			String msg = torneoDAO.insertValueTorneo(nome, dataInizio, dataFine, numSquadra,tipologiaTorneo);
			//ST new
			System.out.println("Messaggio post insert Torneo: "+ msg);
			if (msg.equalsIgnoreCase("OK")) {
				int id = torneoDAO.getLastIdTorneo();
				String msg2 = torneoDAO.insertTorneo(id, idCampo);
				
				//ST new
				System.out.println("Messaggio post insert Accesso: "+ msg2);
				
				return "Inserimento Completato";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	
	@Override
	public List<Torneo> getAllTorneiAttivi() {
		try {
			List<Torneo> lista = torneoDAO.getAllTorneiInCorso();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	
	@Override
	public List<TorneoDTO> listaTornei() {
		try {
			List<TorneoDTO> lista = torneoDAO.getAllTornei();
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	
	@Override
	public String addPrenotazioneTorneo(String idUtente, int idCampo, int idTorneo) {
		String res = "";
		try {
			TorneoDTO torneo = cercaTorneo(idTorneo);
			String dI = torneo.getDataInizio().toString();
			String dF = torneo.getDataFine().toString();

			String dataInizio = dataFormattata(dI);
			String dataFine = dataFormattata(dF);

			res = prenotazioneDAO.addPrenotazioneTorneo(idUtente, idCampo, idTorneo, dataInizio, dataFine);
			if (res != null && res.equalsIgnoreCase("OK")) {
				return "Prenotazione al torneo con id " + idTorneo + " effettuata ";
			}
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	
	@Override
	public TorneoDTO cercaTorneo(int id) {
		TorneoDTO torneo = new TorneoDTO();
		try {
			torneo = torneoDAO.searchTorneo(id);
			return torneo;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return torneo;
	}
	

	@Override
	public List<Torneo> TorneoAssTipologiaDisponibili(String email, String tipologia) {
		List<Torneo> lista = new ArrayList<Torneo>();
		try {
			lista = torneoDAO.TorneoForAssTipologiaDisponibili(email, tipologia);
		} catch (SQLException | CustomException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return lista;
	}

	
	@Override
	public TorneoDTO modificheAdminTorneo(int idTorneo, String dI, String dF) {
		TorneoDTO torneo = new TorneoDTO();
		try {

			// CONVERSIONE DA TIPO STRING(FORMATO TIMESTAMP ESSENDO PROVENIENTE DAL DB) IN
			// TIMESTAMP, POI IN LOCALDATETIME ED INFINE DI NUOVO IN STRING
			Date d1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dI);
			Timestamp tInizio = new java.sql.Timestamp(d1.getTime());
			LocalDateTime ldInizio = LocalDateTime.ofInstant(Instant.ofEpochMilli(tInizio.getTime()),
					ZoneId.systemDefault());
			// Format LocalDateTime
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String dataInizio = ldInizio.format(dateTimeFormatter);
			System.out.println("Data Inizio: " + dataInizio);

			Date d2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dF);
			Timestamp tFine = new java.sql.Timestamp(d2.getTime());
			LocalDateTime ldFine = LocalDateTime.ofInstant(Instant.ofEpochMilli(tFine.getTime()),
					ZoneId.systemDefault());
			String dataFine = ldFine.format(dateTimeFormatter);
			System.out.println("Data Fine: " + dataFine);

			torneo = torneoDAO.searchTorneo(idTorneo);
			// ESCE SEMPRE TRUE, ANCHE SE NON SI CAMBIANO QUINDI GLI ALTRI 2 IF DIVENTANO INUTILI
			// IN QUESTO CASO I RETURN ALL'INTERNO DEGLI IF,ESSENDO LA STESSA COSA ED
			// ESSENDO DICHIARATA FUORI, SONO INUTILI
			if (torneo.getDataInizio() != tInizio && torneo.getDataFine() != tFine) {
				torneoDAO.updateTorneoDate(idTorneo, dataInizio, dataFine);
				torneo.setDataInizio(tInizio);
				torneo.setDataFine(tFine);
			}
//			else if(torneo.getDataInizio()!=tInizio) {
//				torneoDAO.updateTorneoDataInizio(idTorneo, dataInizio);
//				torneo.setDataInizio(tInizio);		
//			}
//			else if(torneo.getDataFine()!=tFine) {
//				torneoDAO.updateTorneoDataFine(idTorneo, dataFine);
//				torneo.setDataFine(tFine);	
//			}	

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	return torneo;
	}

//--------------------MATCH---------------------------------------------------	

	@Override
	public int estraiIdTorneo(String selectTorneo) {
		String[] infoTorneo = null;
		if (selectTorneo.contains(":")) {
			infoTorneo = selectTorneo.split(":", 2);
		}
		int id = Integer.parseInt(infoTorneo[0]);
		return id;
	}

	
	@Override
	public List<MatchDTO> getMatchFromIdTorneo(int idTorneo) {
		List<MatchDTO> lista = new ArrayList<MatchDTO>();
		try {
			lista = matchDAO.getMatchOfNameTorneo(idTorneo);
			for(MatchDTO m: lista) {
				String s1= m.getSfidante1();
				Utente utente1= utenteDAO.dettaglioUtente(s1);
				m.setSfidante1(utente1.getNome());
				String s2= m.getSfidante2();
				Utente utente2= utenteDAO.dettaglioUtente(s2);
				m.setSfidante2(utente2.getNome());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
	return lista;
	}

	
	@Override
	public List<MatchDTO> listaMatchAttivi() {
		List<MatchDTO> lista = new ArrayList<MatchDTO>();
		try {
			lista = matchDAO.matchDaDisputare();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return lista;
	}

	
	@Override
	public void insertMatchCalendarioTorneo(String data, String risultato, int torneo_fk, String sfidante1_fk,
			String sfidante2_fk) {
		if (data.contains("T")) {
			data = data.replace("T", "");
		}
		if (risultato.length() < 3) {
			risultato = null;
		}

		try {
			matchDAO.insertMatchCalendario(data, risultato, torneo_fk, sfidante1_fk, sfidante2_fk);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
	}

	
	@Override
	public List<String> listaUtenti() {
		List<String> lista = new ArrayList<String>();
		try {
			lista = utenteDAO.getAllUser();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
	return lista;
	}

	
	@Override
	public List<Squadra> listaUtentiForTorneo(int idTorneo) {
		List<Squadra> lista = new ArrayList<Squadra>();
		try {
			lista = torneoDAO.getUserTorneo(idTorneo);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
	return lista;
	}

	
	@Override
	public List<MatchDTO> listaMatchUserPerTorneo(int idTorneo, String utente) {
		List<MatchDTO> lista = new ArrayList<MatchDTO>();
		try {
			lista = matchDAO.getMatchUserForTorneo(idTorneo, utente);
			//ST new
			System.out.println("LISTA con size: "+ lista.size());
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
	return lista;
	}

	
	@Override
	public List<Match> listaMatchUser(String nomeSquadra) {
		List<Match> lista = new ArrayList<Match>();
		try {
			lista = matchDAO.getMatchUser(nomeSquadra);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
	return lista;
	}

	
	@Override
	public ClassificaMatch classificaTorneoCampionato(int idTorneo) {
		ClassificaMatch classifica = new ClassificaMatch();
		List<Match> listaMatch = new ArrayList<Match>();
		try {
			listaMatch = matchDAO.getMatchUserCampionato(idTorneo);
			List<Squadra> listaSquadra = listaUtentiForTorneo(idTorneo);

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

					}

					if (m.getRisultatoSfidante1() != null && m.getRisultatoSfidante2() != null) {
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

					}

					if (m.getRisultatoSfidante1() != null && m.getRisultatoSfidante2() != null) {
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
			if (listaSquadra.size() > 0) {
				classifica.setSquadra(listaSquadra);
				return classifica;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	
	
	

	@Override
	public List<TorneoEliminazione> tabelloneTorneo(int idTorneo, List<MatchDTO> listaMatch) {
		List<Squadra> listaSquadra = listaUtentiForTorneo(idTorneo);
		List<TorneoEliminazione> listaTorneoEliminazione = new ArrayList<TorneoEliminazione>();

		List<HashMap<String, Integer>> hashmapSquadra = new ArrayList<HashMap<String, Integer>>();
		// ADD ELEMENTS
		for (Squadra s : listaSquadra) {
			HashMap<String, Integer> m = new HashMap<String, Integer>();
			m.put(s.getNome(), 1);
			hashmapSquadra.add(m);
		}

		// TabelloneTorneo
		listaTorneoEliminazione = oggettiColonna(hashmapSquadra, listaMatch);
		for (TorneoEliminazione t1 : listaTorneoEliminazione) {
			System.out.println(t1);
		}

	return listaTorneoEliminazione;
	}

	
	// Metodo privato che ritorna Tabellone
	private List<TorneoEliminazione> oggettiColonna(List<HashMap<String, Integer>> hashmapSquadra,
			List<MatchDTO> listaMatch) {
		List<TorneoEliminazione> colonneTabellone = new ArrayList<TorneoEliminazione>();
		TorneoEliminazione t = new TorneoEliminazione();
		// Non ammette duplicati
		Set<String> listaSquadreVincenti = new HashSet<String>();
		boolean b = false;
		int numColonna = 1;
		String s = "";
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		int turni = 1;

		// colonna 1
		t.setListaSquadra(hashmapSquadra);
		t.setNumColonna(numColonna++);
		turni++;
		colonneTabellone.add(t);
		hashmapSquadra = new ArrayList<HashMap<String, Integer>>();

		// ciclo sui match per riempire tutte le altre colonne
		for (MatchDTO match : listaMatch) {
			t = new TorneoEliminazione();
			if ((match.getRisultatoSfidante1() != null && match.getRisultatoSfidante2() != null)
					&& (match.getRisultatoSfidante1() != "-" && match.getRisultatoSfidante2() != "-")) {

				if (Integer.parseInt(match.getRisultatoSfidante1()) > Integer.parseInt(match.getRisultatoSfidante2())) {
					s = match.getSfidante1();
					m.put(s, turni);
					// guarda il tipo e non il reale valore
					// usa l'equals
					b = hashmapSquadra.contains(m);
					listaSquadreVincenti.add(s);

				} else {
					s = match.getSfidante2();
					m.put(s, turni);
					b = hashmapSquadra.contains(m);
					listaSquadreVincenti.add(s);

				}
				// evita duplicati all'interno delle singole liste e ne permette la loro
				// creazione
				if (b) {
					int index = hashmapSquadra.indexOf(m);
					turni = m.get(s) + 1;
					m.replace(s, hashmapSquadra.get(index).get(s), turni);
					t.setListaSquadra(hashmapSquadra);
					t.setNumColonna(numColonna++);
					((ArrayList<TorneoEliminazione>) colonneTabellone).ensureCapacity((colonneTabellone.size() + 1));
					colonneTabellone.add(colonneTabellone.size(), t);
					b = false;
					// con listaSquadra2.clear() mi viene eliminato anche il contenuto di
					// colonneTabellone(WHY???)
					hashmapSquadra = new ArrayList<HashMap<String, Integer>>();
				}
				hashmapSquadra.add(m);
				m = new HashMap<String, Integer>();
			}

		}
		t = new TorneoEliminazione();
		t.setListaSquadra(hashmapSquadra);
		t.setNumColonna(numColonna);
		((ArrayList<TorneoEliminazione>) colonneTabellone).ensureCapacity((colonneTabellone.size() + 1));
		colonneTabellone.add(colonneTabellone.size(), t);

		// Creo un oggetto HashMap con i valori finali(turni) per ogni singola squadra
		int max = 1;
		HashMap<String, Integer> listaTurniUpdate = new HashMap<String, Integer>();
		for (TorneoEliminazione t1 : colonneTabellone) {
			for (HashMap<String, Integer> lista : t1.getListaSquadra()) {
				for (Map.Entry<String, Integer> map : lista.entrySet()) {

					for (String s1 : listaSquadreVincenti) {
						if (map.getKey().equals(s1)) {
							if (map.getValue() > max) {
								max = map.getValue();
								map.setValue(max);
								listaTurniUpdate.put(map.getKey(), map.getValue());
								max = 1;
							}
						}
					}
				}
			}
		}

		// Setto,attraverso l'oggetto HashMap creato sopra, i turni disputati da
		// ciascuna squadra dalla
		// prima all'ultima lista del tabellone
		for (int i = 0; i < colonneTabellone.size(); i++) {
			List<HashMap<String, Integer>> list = colonneTabellone.get(i).getListaSquadra();
			for (HashMap<String, Integer> m1 : list) {
				for (Map.Entry<String, Integer> map : listaTurniUpdate.entrySet()) {
					if (m1.containsKey(map.getKey())) {
						int indexUpdate = list.indexOf(m1);
						if (indexUpdate != -1) {
							list.get(indexUpdate).replace(map.getKey(), map.getValue());
						}
					}
				}
			}
		}
	return colonneTabellone;
	}

	
	@Override
	public int idTorneoByName(String nomeTorneo) {
		int idTorneo = 0;
		try {
			idTorneo = torneoDAO.getIdTorneoByName(nomeTorneo);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
	return idTorneo;
	}

	
	// Metodo per avere l'immagine di ogni singolo utente
	@Override
	public byte[] getImageUser(String email) throws IOException {
		byte[] immagine = null;
		try {
			Blob b = utenteDAO.getImagesForUser(email);
			//ST new
			if(b==null) {
				System.out.println("Inserire immagini dei loghi!!!!");
//				("C:/Users/stefa/Lato_Programmazione/BACKUP_CHANGE_PC/eclipse2/Eustema-Project-Final/src/main/webapp/img/immagineUserDefault.png");
				Path p=Paths.get("C:/Users/stefa/Lato_Programmazione/BACKUP_CHANGE_PC/eclipse2/Eustema-Project-Final/src/main/webapp/img/immagineUserDefault.png");
				immagine = Files.readAllBytes(p);
				
			} else {
			immagine = b.getBytes(1l, (int) b.length());
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	return immagine;
	}

	@Override
	public List<TipologiaTorneoDTO> listaTipologiaTorneo() {
		List<TipologiaTorneoDTO> lista= new ArrayList<TipologiaTorneoDTO>();
		try {
			lista= torneoDAO.getTipologiaTorneo();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}

}
