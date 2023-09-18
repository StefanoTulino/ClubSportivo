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


public class SendPrenotazioneAltriUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
   
	
	
    public SendPrenotazioneAltriUtentiServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
    	String dataInizio = request.getParameter("dataInizio");
    	String dataFine = request.getParameter("dataFine");
    	UtenteSession utente= (UtenteSession) session.getAttribute("user");
    	
    	String emailUtente= utente.getEmail();
    	int idCampo= Integer.parseInt(request.getParameter("idCampo"));
    	
    	String msg= progettoService.addPrenotazioneVip(emailUtente, idCampo, dataInizio,dataFine);
    	if(msg!=null ) {
    	request.setAttribute("msgPrenotazione", msg);
    	List<PrenotazioneDTO> lista=progettoService.prenotazioniAttiveForUtente(emailUtente);
    	utente.setListaPrenotazione(lista);
    	session.setAttribute("user", utente);
        RequestDispatcher rd = request.getRequestDispatcher("HomePageServlet");
    	rd.forward(request, response);
    	
    	}
    		
	}
}
//session.setAttribute("prenotazione", progettoService.addPrenotazione(emailUtente, idCampo, data));
