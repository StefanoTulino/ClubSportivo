package com.project.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.project.dto.MatchDTO;
import com.project.dto.TorneoDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;


public class AddMatchAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
       
    
    public AddMatchAdmin() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//visualizza tutti i tornei in un menu a tendina --> DA FARE IN GESTIONE MATCH VISTO
//		CHE LA PAG. CHE SI APRE è GESTIONE MATCH
		
//		List<TorneoDTO2> listaTorneo= progettoService.listaTornei();
//		request.setAttribute("listaTornei", listaTorneo);
//		List<String> listaUtenti= progettoService.listaUtenti();
//		request.setAttribute("listaSfidante", listaUtenti);
		
		
		String data= request.getParameter("meeting-time-data");
		String risultato= request.getParameter("risultato");
		int idTorneo= Integer.parseInt(request.getParameter("selectTorneo"));
		String sfidante1= request.getParameter("selectSfidante1");
		String sfidante2= request.getParameter("selectSfidante2");
		progettoService.insertMatchCalendarioTorneo(data,risultato,idTorneo,sfidante1,sfidante2);
		RequestDispatcher rd= request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	


}
