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


public class ModificaTorneoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
       
    
    public ModificaTorneoAdmin() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MODIFICA TORNEO
		
		int idTorneo= Integer.parseInt(request.getParameter("idTorneo"));
		String dataInizio= request.getParameter("dataInizio1");
		System.out.println("Servlet: "+ dataInizio);
		String dataFine= request.getParameter("dataFine1");
		TorneoDTO torneoMofificato= progettoService.modificheAdminTorneo(idTorneo, dataInizio, dataFine);
		if(torneoMofificato!=null) {
			request.setAttribute("updateTorneo", "Il torneo con id: "+ torneoMofificato.getId()+" e' stato modificato");
		}
		
		//mi sposto nella Servlet gestioneViewTorneo, in modo tale da ricaricare la pagina con le info modificate
		RequestDispatcher rd= request.getRequestDispatcher("GestioneTorneo");
		rd.forward(request, response);
		
	}
	

}
