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


public class UpdateAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
  
	
    public UpdateAdminServlet() {
        super();
    }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=null;	
		rd= request.getRequestDispatcher("GestioneUtente");
		rd.forward(request, response);
		}			
			
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		RequestDispatcher rd=null;
		UtenteDTO utente=new UtenteDTO();
		
			try {
				String email= (String)request.getParameter("email");
					if(email!=null) {
					String password= (String)request.getParameter("password");
						if(password!=null) {
							String setPassword= (String)request.getParameter("setPassword");
							utente= progettoService.modificaPasswordUtente(setPassword,email);
					
							request.setAttribute("msgChangePasswordAdmin", "Password modificata");
							rd= request.getRequestDispatcher("GestioneUtente");
							rd.forward(request, response);
							}			
					}
					
				} catch(Exception e) {
					System.out.println("Error: "+e.getMessage() );
			}
	}

}
