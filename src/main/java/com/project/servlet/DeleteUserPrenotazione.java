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


public class DeleteUserPrenotazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
       
    
    public DeleteUserPrenotazione() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		RequestDispatcher rd=null;
		
		UtenteSession utente= (UtenteSession) session.getAttribute("user");
		int idPrenotazione= Integer.parseInt(request.getParameter("idPrenotazione"));
		String msg= progettoService.eliminaPrenotazione(idPrenotazione);
		
		if(msg!=null){
		List<PrenotazioneDTO> lista=progettoService.prenotazioniAttiveForUtente(utente.getEmail());
    	List<PrenotazioneDTO> prenotazioniNonValide= progettoService.prenotazioniError(utente.getEmail());
    	utente.setListaPrenotazione(lista);
    	session.setAttribute("user", utente);
    	session.setAttribute("prenotazioniNonValide", prenotazioniNonValide);
    	request.setAttribute("msgDeletePrenotazioneUser", "La prenotazione e' stata cancellata");
    	rd=request.getRequestDispatcher("HomePageServlet");
    	rd.forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
