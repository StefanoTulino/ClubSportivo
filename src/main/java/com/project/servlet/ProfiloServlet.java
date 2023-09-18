package com.project.servlet;

import java.io.IOException;
import java.sql.Blob;

import com.project.dto.UtenteDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.Utente;
import com.project.utility.UtenteSession;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class ProfiloServlet extends HttpServlet {
	
	private ProgettoService progettoService;
	private static final long serialVersionUID = 1L;
       
 
    public ProfiloServlet() {
        super();
    }
    
    
    public void init() {
    	progettoService= new ProgettoServiceImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		UtenteSession u= (UtenteSession) session.getAttribute("user");
		System.out.println("User from Session: "+u.toString());
		try {
			String email= u.getEmail();
				Blob b= u.getLogo();
				if(b!=null) {
				request.setAttribute("immagine", b);
					} else {
						request.setAttribute("immagine", null);
					}
				RequestDispatcher rd= request.getRequestDispatcher("jsp/profilo.jsp");
				rd.forward(request, response);

			} catch(Exception e) {
				System.out.println("Error: "+e.getMessage() );
		}
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=null;
		UtenteDTO utenteDTO=new UtenteDTO();
		
			try {
				String email= (String)request.getParameter("email");
				Utente utente= progettoService.dettaglioUtente(email);
					if(email!=null) {
					String password= utente.getPassword();
						if(password!=null) {
							String setPassword= (String)request.getParameter("setPassword");
							utenteDTO= progettoService.modificaPasswordUtente(setPassword,email);
							
							request.setAttribute("msgProfilo", "Password modificata");
							rd= request.getRequestDispatcher("index.jsp");
							rd.forward(request, response);
							}			
					}
					
				} catch(Exception e) {
					System.out.println("Error: "+e.getMessage() );
			}
	}
	
}

