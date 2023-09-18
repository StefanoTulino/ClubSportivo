package com.project.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.project.dto.TipologiaCampoDTO;
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


public class TorneoAssAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
       
    
    public TorneoAssAdmin() {
        super();
    }

	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TipologiaCampoDTO> listaTipologia = progettoService.listaTipologiaCampo();
    	request.setAttribute("listaCampiTipologia", listaTipologia);
		RequestDispatcher rd= request.getRequestDispatcher("jsp/torneoAssAdmin.jsp");
		rd.forward(request, response);	
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Torneo> listaTornei= new ArrayList<Torneo>();
		boolean result=false;
		
		HttpSession session= request.getSession();
		UtenteSession utente= (UtenteSession) session.getAttribute("user");
		
		if(!(request.getParameter("selectTipologia").equalsIgnoreCase("---"))) {
    		String tipologia=request.getParameter("selectTipologia");
    		listaTornei= progettoService.TorneoAssTipologiaDisponibili(utente.getEmail(),tipologia);
    		request.setAttribute("messaggeDefault", "Non esistono tornei disponibili per questa categoria");
			} else {
				listaTornei= progettoService.TorneoAssDisponibili(utente.getEmail());
			}
		
		if(listaTornei!=null) {
			result=true;
		}
		request.setAttribute("listaTornei", listaTornei);
		request.setAttribute("result", result);
        RequestDispatcher rd = request.getRequestDispatcher("jsp/torneoAssAdmin.jsp");
    	rd.forward(request, response);
	}

}
