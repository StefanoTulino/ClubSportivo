package com.project.servlet;

import java.io.IOException;
import java.util.List;

import com.project.dto.TipologiaTorneoDTO;
import com.project.dto.TorneoDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.CampoUtility;
import com.project.utility.Torneo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class GestioneTorneo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService=new ProgettoServiceImpl();
       
    
    public GestioneTorneo() {
        super();   
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//visualizza tutti i tornei ANCORA ATTIVI in un menu a tendina
		List<Torneo> listaTorneo1= progettoService.getAllTorneiAttivi();
		request.setAttribute("listaTorneiAttivi", listaTorneo1);
		
		//visualizza tutti i tornei
		List<TorneoDTO> listaTorneo2= progettoService.listaTornei();
		request.setAttribute("listaTornei", listaTorneo2);
		
		//ST new
		List<TipologiaTorneoDTO> listaTipologia = progettoService.listaTipologiaTorneo();
    	System.out.println("listaTipologiaTorneo return dal service: "+ listaTipologia.size());
		request.setAttribute("listaTipologiaTorneo", listaTipologia);
		
    	RequestDispatcher rd= request.getRequestDispatcher("jsp/gestioneTorneo.jsp");
    	rd.forward(request, response);
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//ADD NUOVO TORNEO
				String dataInizio=request.getParameter("meeting-time-data-inizio");
				String dataFine=request.getParameter("meeting-time-data-fine");
				int numeroSquadre= Integer.parseInt(request.getParameter("numeroSquadre"));
				String nomeTorneo=request.getParameter("nomeTorneo");
				
				List<CampoUtility> listaCampi=progettoService.getAllInfocampiForTorneo(dataInizio, dataFine);
				request.setAttribute("listaCampi", listaCampi);
				request.setAttribute("dataInizio", dataInizio);
				request.setAttribute("dataFine", dataFine);
				request.setAttribute("numeroSquadre", numeroSquadre);
				request.setAttribute("nomeTorneo", nomeTorneo);
				
				RequestDispatcher rd= request.getRequestDispatcher("jsp/gestioneTorneo.jsp");
				rd.forward(request, response);
				listaCampi.clear();
		//		lista.clear();
				
				} catch(Exception e) {
					System.out.println("Erroreee: "+e.getMessage() );
			}
			
		}
}