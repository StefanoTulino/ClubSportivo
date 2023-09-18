package com.project.servlet;

import java.io.IOException;
import java.util.List;

import com.project.dto.MatchDTO;
import com.project.dto.TorneoDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class GestioneMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
       
    
    public GestioneMatch() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//visualizza tutti i tornei in un menu a tendina
		List<TorneoDTO> listaTorneo= progettoService.listaTornei();
		request.setAttribute("listaTornei", listaTorneo);
		
		//SERVE PER INSERIRE UN MATCH
		List<String> listaUtenti= progettoService.listaUtenti();
		request.setAttribute("listaSfidante", listaUtenti);
		
		
		//MATCH ANCORA DA DISPUTARE
		List<MatchDTO> listaMatch= progettoService.listaMatchAttivi();
		request.setAttribute("listaMatchFuturi", listaMatch);
		
		RequestDispatcher rd= request.getRequestDispatcher("jsp/gestioneMatch.jsp");
		rd.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//visualizza tutti i tornei in un menu a tendina
				List<TorneoDTO> listaTorneo= progettoService.listaTornei();
				request.setAttribute("listaTornei", listaTorneo);
		
				//MATCH ANCORA DA DISPUTARE
				List<MatchDTO> listaMatch1= progettoService.listaMatchAttivi();
				request.setAttribute("listaMatchFuturi", listaMatch1);		
		
			//Visualizza tutti i match di un torneo
				String infoTorneo= request.getParameter("selectTorneo");
				System.out.println(infoTorneo);
				int idTorneo= progettoService.estraiIdTorneo(infoTorneo);
				System.out.println(idTorneo);
				List <MatchDTO> listaMatch= progettoService.getMatchFromIdTorneo(idTorneo);
				request.setAttribute("listaMatch", listaMatch);
				RequestDispatcher rd= request.getRequestDispatcher("jsp/gestioneMatch.jsp");
				rd.forward(request, response);
	}

}
