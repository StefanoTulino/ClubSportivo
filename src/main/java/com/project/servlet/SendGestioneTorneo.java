package com.project.servlet;

import java.io.IOException;
import java.util.List;

import com.project.dto.TipologiaTorneoDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class SendGestioneTorneo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
       
    
    public SendGestioneTorneo() {
        super();
       
    }

	
    //Con post esplode
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
//			//ST new
//Add la tipologia del torneo nella Servlet GestioneTorneo, ovvero al click del link dal quale poi si apre la schermata dedicata interamente al torneo
			//Questi qui invece sono i dati che riceviamo dopo aver finito il form(incluso il campo)!
			
			String nomeTorneo=request.getParameter("nomeT");
			String dataInizio=request.getParameter("dataI");
			System.out.println("DI: "+ dataInizio);
			String dataFine=request.getParameter("dataF");
			System.out.println("DF: "+ dataFine);
			int numeroSquadre= Integer.parseInt(request.getParameter("numS"));
			System.out.println("num: "+ numeroSquadre);
			
			int idCampo= Integer.parseInt(request.getParameter("idCampo"));
			
			int tipologiaTorneo=-1;
			//ST new
//			if(!(request.getParameter("selectTipologiaTorneo").equalsIgnoreCase("---"))) {
//				tipologiaTorneo= Integer.parseInt(request.getParameter("selectTipologiaTorneo"));
				String tipologiaTorneoStr= request.getParameter("selectTipologiaTorneo");
				System.out.println("tipologiaTorneoStr: "+ tipologiaTorneoStr);
//			} 
			
			if(tipologiaTorneo!=-1) {
			String msg= progettoService.addTorneo(nomeTorneo, dataInizio, dataFine, numeroSquadre, idCampo,tipologiaTorneo);
				if(msg!=null) {
			
					request.setAttribute("addTorneoAdmin","Il torneo è stato creato");
					RequestDispatcher rd= request.getRequestDispatcher("HomePageServlet");
					rd.forward(request, response);
					}
			}
		
		} catch(Exception e) {
			System.out.println("Error MAIN: "+e.getMessage() );
		}
		
	}

	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}

}
