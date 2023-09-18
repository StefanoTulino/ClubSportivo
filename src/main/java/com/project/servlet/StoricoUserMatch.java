package com.project.servlet;

import java.io.IOException;
import java.util.List;

import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.Match;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class StoricoUserMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProgettoService progettoService= new ProgettoServiceImpl();   
	
   
    public StoricoUserMatch() {
        super();
    }

	
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//NON SI DEVE PASSARE IL NOME DELL'UTENTE(tramite Session) MA IL NOME DELLA SQUADRA clickata
		
		String nomeSquadra= request.getParameter("nomeSquadra");
		System.out.println("NOME SQ. "+ nomeSquadra);

		//	ESCE COMUNQUE NULL!!!
//		String torneo1= (String) request.getAttribute("valTorneo");
//		System.out.println("TORNEO1: "+ torneo1);

		
		List<Match> lista= progettoService.listaMatchUser(nomeSquadra);
		System.out.println("StoricoUserMatch, SIZE: "+ lista.size());
		request.setAttribute("listaMatchUserForTorneo", lista);
		//ST new
		for(Match m: lista) {
			System.out.println("StoricoUserMatch, match: "+ m);
		}
		
//		RequestDispatcher rd= request.getRequestDispatcher("jsp/storicoUserMatch.jsp");
		RequestDispatcher rd= request.getRequestDispatcher("jsp/storicoUser.jsp");
		rd.forward(request, response);
	}


}
