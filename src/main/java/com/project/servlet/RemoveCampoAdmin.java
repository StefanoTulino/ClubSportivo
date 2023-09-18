package com.project.servlet;

import java.io.IOException;

import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class RemoveCampoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
       
   
    public RemoveCampoAdmin() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=null;
		
		try {
			int id= Integer.parseInt(request.getParameter("idCampo"));
			int status= Integer.parseInt(request.getParameter("statusCampo"));
			String res=progettoService.setStatusCampo(id, status);
			request.setAttribute("msgStatus", res);
			rd=request.getRequestDispatcher("GestioneCampo");
			rd.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
