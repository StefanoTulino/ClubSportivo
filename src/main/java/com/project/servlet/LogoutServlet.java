package com.project.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
   
    public LogoutServlet() {
        super();
    }

	
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Stiamo per uscire");
		
		// Invalidate the session.
        request.getSession(false).invalidate();
       
        RequestDispatcher rd= request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);  
	}

}
