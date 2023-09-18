package com.project.servlet;

import java.io.IOException;

import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.Utente;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
	private String email="";
	private Utente utente=null;
       
    
    public DetailServlet() {
        super();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd=null;
		try {
			String email= (String)request.getParameter("email");
			Utente utente= progettoService.dettaglioUtente(email);
			request.setAttribute("dettaglioUtente", utente);
			rd= request.getRequestDispatcher("jsp/profilo.jsp");
			rd.forward(request, response);
			
			} catch(Exception e) {
				System.out.println("Error: "+e.getMessage() );
				}
	}

}
