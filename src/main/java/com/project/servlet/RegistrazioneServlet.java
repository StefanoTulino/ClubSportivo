package com.project.servlet;

import java.io.IOException;

import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.UtenteSession;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class RegistrazioneServlet extends HttpServlet {
	
	private ProgettoService progettoService;
	private static final long serialVersionUID = 1L;
	
	
	
    public RegistrazioneServlet() {
        super();
    }

	
    public void init() {
    	progettoService= new ProgettoServiceImpl();
    }
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        RequestDispatcher rd=null;
        boolean tipo_fk=false;
		
		String email=  request.getParameter("email");
		String password= request.getParameter("password");
		String nome=  request.getParameter("nome");
		String cognome=  request.getParameter("cognome");
		String tipologia=  request.getParameter("tipologia");
		if(tipologia!=null && tipologia.equals("on")) {
			 tipo_fk=true;
		}
		
		try {
			UtenteSession utenteSession=progettoService.registrazione(email, password, nome, cognome,tipo_fk);
			session.setAttribute("user", utenteSession);
			System.out.println("Registrazione con successo");
			rd= request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
	
		} catch(Exception e) {
			System.out.println("Error Registrazione: "+ e.getMessage());
			request.setAttribute("erroreRegistrazione", e.getMessage());
			rd= request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}
	}

}
