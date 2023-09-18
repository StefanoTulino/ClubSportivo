package com.project.servlet;

import java.io.IOException;
import java.util.List;

import com.project.dto.PrenotazioneDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.UtenteSession;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService;
       
   
    public LoginServlet() {
        super();
    }
    
    
    public void init() {
    	progettoService=new ProgettoServiceImpl();
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher rd=null;
        String ass="";
		String email=  request.getParameter("email");
		String password= request.getParameter("password");
		
		try {
			UtenteSession utenteSession=progettoService.login(email, password);
			session.setAttribute("user", utenteSession);
			String cognome=utenteSession.getCognome();
			if(cognome==null) {
				ass="Ti diamo il Benvenuto: "+ utenteSession.getNome();
				request.setAttribute("msgLogin",ass);
			}
			else {
			request.setAttribute("msgLogin","Benvenuto  "+ utenteSession.getNome()+" "+utenteSession.getCognome());
			}
			System.out.println("Login con successo");
			List<PrenotazioneDTO> prenotazioniNonValide= progettoService.prenotazioniError(utenteSession.getEmail());
			if(prenotazioniNonValide!=null) {
				session.setAttribute("prenotazioniNonValide", prenotazioniNonValide);
			}
				rd=request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			
			} catch(Exception e) {
				request.setAttribute("errorLogin", "Credenziali errate: login non valido");
				rd= request.getRequestDispatcher("HomePageServlet");
				rd.include(request, response);
				System.out.println("Error: "+ e.getMessage());
		}
	}

}

