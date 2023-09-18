package com.project.servlet;

import java.io.IOException;

import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class HomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService=new ProgettoServiceImpl();
       
    
    public HomePageServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd= request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String errorLogin=(String) request.getAttribute("errorLogin");
		request.setAttribute("errorLogin2", errorLogin);
		RequestDispatcher rd= request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
		
	}

}
