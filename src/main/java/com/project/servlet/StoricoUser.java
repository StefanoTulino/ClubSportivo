package com.project.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import com.project.dto.MatchDTO;
import com.project.dto.TorneoDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.ClassificaMatch;
import com.project.utility.Squadra;
import com.project.utility.TorneoEliminazione;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class StoricoUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
       
    
    public StoricoUser() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//visualizza tutti i tornei in un menu a tendina
		List<TorneoDTO> listaTorneo= progettoService.listaTornei();
		request.setAttribute("listaTornei", listaTorneo);
		
		
		RequestDispatcher rd= request.getRequestDispatcher("jsp/storicoUser.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//visualizza tutti i tornei in un menu a tendina
				List<TorneoDTO> listaTorneo= progettoService.listaTornei();
				request.setAttribute("listaTornei", listaTorneo);
//	VERSIONE CON ID E TORNEO COME VIEW NEL BUTTON	
//		String torneo= request.getParameter("selectTorneo");	
//		int idTorneo= progettoService.estraiIdTorneo(torneo);

//		VERSIONE DOVE MI TROVAVO L'ID IN BASE AL NOME --> NON CORRETTISSIMO
//		String torneo= request.getParameter("selectTorneo");
//		int idTorneo= progettoService.idTorneoByName(torneo);
				
				
		int idTorneo= Integer.parseInt(request.getParameter("valoreTorneo"));
		List<Squadra> listaUtentiForTorneo= progettoService.listaUtentiForTorneo(idTorneo);
		//ADD di Blob
		for(int i=0;i<listaUtentiForTorneo.size();i++) {
			Blob b=listaUtentiForTorneo.get(i).getLogo();
			request.setAttribute("immagine", b);	
		}
		request.setAttribute("listaUtenti", listaUtentiForTorneo);
		List <MatchDTO> listaMatch= progettoService.getMatchFromIdTorneo(idTorneo);
		request.setAttribute("listaMatch", listaMatch);
		
		ClassificaMatch classificaMatchs= progettoService.classificaTorneoCampionato(idTorneo);
		List<TorneoEliminazione> torneoEliminazione= progettoService.tabelloneTorneo(idTorneo, listaMatch);
		//ST new
		for(TorneoEliminazione t: torneoEliminazione) {
			System.out.println("TorneoEliminazione element: "+ t);
		}
		
		System.out.println("StoricoUser, SIZE: "+torneoEliminazione.size());
		TorneoDTO torneoSelezionato= progettoService.cercaTorneo(idTorneo);
		request.setAttribute("torneoEliminazione", torneoEliminazione);
		
		if(torneoSelezionato.getTipologiaTorneo()==2) {
			request.setAttribute("tipologiaEliminazione", "Eliminazione Diretta");
		} else {
			request.setAttribute("tipologiaEliminazione", null);
		}
		request.setAttribute("classificaTorneo", classificaMatchs);
//		request.setAttribute("valTorneo", torneo);
//		response.sendRedirect("StoricoUserMatch?torneo="+idTorneo);
		RequestDispatcher rd= request.getRequestDispatcher("jsp/storicoUser.jsp");
		rd.forward(request,response);
		
	}
	

}
