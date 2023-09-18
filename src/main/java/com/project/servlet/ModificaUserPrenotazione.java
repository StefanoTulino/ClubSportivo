package com.project.servlet;

import java.io.IOException;
import java.util.List;

import com.project.dto.TipologiaCampoDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.CampoUtility;
import com.project.utility.UtenteSession;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ModificaUserPrenotazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
       
    
    public ModificaUserPrenotazione() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=null;
		
		int idPrenotazione= Integer.parseInt(request.getParameter("idPrenotazione"));
//		String data_inizio= request.getParameter("dataInizio");
		
		List<TipologiaCampoDTO> listaTipologia = progettoService.listaTipologiaCampo();
		request.setAttribute("listaCampiTipologia", listaTipologia);

		request.setAttribute("prenotazioneId", idPrenotazione);
		rd= request.getRequestDispatcher("jsp/modificaPrenotazioneUser.jsp");
		rd.forward(request, response);
		
		}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		RequestDispatcher rd=null;
		String tipologia="";
		
		UtenteSession utente= (UtenteSession) session.getAttribute("user");
		int idPrenotazione= Integer.parseInt(request.getParameter("idPrenotazione"));
		String dataInizio= request.getParameter("meeting-time-inizio");
		String dataFine= request.getParameter("meeting-time-fine");
		if(!(request.getParameter("selectTipologia").equalsIgnoreCase("---"))) {
			tipologia=request.getParameter("selectTipologia");
		}
		
    	List<CampoUtility> listaCampi = progettoService.getAllInfocampiComplete(utente.getEmail(),dataInizio,tipologia);
    	request.setAttribute("listaCampi", listaCampi);
    	request.setAttribute("prenotazioneChangeId", idPrenotazione);
    	request.setAttribute("dataInizio", dataInizio);
    	request.setAttribute("dataFine", dataFine);
			
		
		rd= request.getRequestDispatcher("jsp/modificaPrenotazioneUser.jsp");
		rd.forward(request, response);
		}
	
	
}
	