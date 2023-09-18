package com.project.servlet;

import java.io.IOException;

import com.project.dto.UtenteDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class CheckTipologiaAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
       
    
    public CheckTipologiaAdmin() {
        super();
        
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd=null;
		try {
			String email= (String)request.getParameter("email");
			int tipologia = Integer.parseInt(request.getParameter("tipologia"));
			UtenteDTO utente= progettoService.modificaTipologiaUtente(tipologia, email);
			request.setAttribute("dettaglioUtente", utente);
			rd= request.getRequestDispatcher("GestioneUtente");
			rd.forward(request, response);
			
			} catch(Exception e) {
				System.out.println("Error: "+e.getMessage() );
				}
	}

}
