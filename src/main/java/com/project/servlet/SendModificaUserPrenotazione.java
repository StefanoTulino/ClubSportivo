package com.project.servlet;

import java.io.IOException;
import java.util.List;

import com.project.dto.PrenotazioneDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.UtenteSession;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class SendModificaUserPrenotazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
       
   
    public SendModificaUserPrenotazione() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
    	String data = request.getParameter("data");
    	UtenteSession utente= (UtenteSession) session.getAttribute("user");
//    	List<PrenotazioneDTO> lista= utente.getListaPrenotazione();
    	
    	String emailUtente= utente.getEmail();
    	int idCampo= Integer.parseInt(request.getParameter("idCampo"));
    	int idPrenotazione= Integer.parseInt(request.getParameter("idPrenotazione"));
		String dataInizio= request.getParameter("dataInizio");
		String dataFine= request.getParameter("dataFine");
		
    	
    	String msg= progettoService.modificaPrenotazione(idPrenotazione, idCampo, dataInizio, dataFine);
    	if(msg!=null ) {
//        lista.add(prenotazione);
//        utente.setListaPrenotazione(lista);
    	List<PrenotazioneDTO> lista=progettoService.prenotazioniAttiveForUtente(emailUtente);
    	List<PrenotazioneDTO> prenotazioniNonValide= progettoService.prenotazioniError(emailUtente);
    	utente.setListaPrenotazione(lista);
    	session.setAttribute("user", utente);
    	session.setAttribute("prenotazioniNonValide", prenotazioniNonValide);
        RequestDispatcher rd = request.getRequestDispatcher("HomePageServlet");
    	rd.forward(request, response);
    	
    	}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
