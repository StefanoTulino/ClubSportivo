package com.project.servlet;

import java.io.IOException;
import java.util.List;

import com.project.dto.PrenotazioneDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.Torneo;
import com.project.utility.UtenteSession;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class TorneoVip extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
       
    
    public TorneoVip() {
        super(); 
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		UtenteSession utente= (UtenteSession) session.getAttribute("user");
		
		List<Torneo> listaTornei= progettoService.TorneoVipDisponibili(utente.getEmail());
		request.setAttribute("listaTornei", listaTornei);
		RequestDispatcher rd= request.getRequestDispatcher("jsp/torneoVip.jsp");
		rd.forward(request, response);
		listaTornei.clear();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		UtenteSession utente= (UtenteSession) session.getAttribute("user");
		
		int idTorneo= Integer.parseInt(request.getParameter("idTorneo"));
		int idCampo= Integer.parseInt(request.getParameter("idCampo"));
		System.out.println(idTorneo+"    as: "+ idCampo);
		
		String msg2= progettoService.addPrenotazioneTorneo(utente.getEmail(), idCampo,idTorneo);
    	if(!(msg2.equalsIgnoreCase("")) ) {
    	request.setAttribute("msgPrenotazioneTorneo", "Prenotazione del Torneo effettuata");
    	List<PrenotazioneDTO> lista=progettoService.prenotazioniAttiveForUtente(utente.getEmail());
    	utente.setListaPrenotazione(lista);
    	session.setAttribute("user", utente);
        RequestDispatcher rd = request.getRequestDispatcher("HomePageServlet");
    	rd.forward(request, response);
    	lista.clear();
		
    	}
	}

}