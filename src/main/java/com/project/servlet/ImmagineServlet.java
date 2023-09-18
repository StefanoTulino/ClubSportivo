package com.project.servlet;

import java.io.IOException;

import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ImmagineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
  
    public ImmagineServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stringId= request.getParameter("id");
		String email= request.getParameter("email");
//		System.out.println("EMAIL: "+ email);
		int id=0;
		if(stringId!=null) {
			id=Integer.parseInt(stringId);
			byte[] immagineCampo= progettoService.getImageCampo(id);
			response.getOutputStream().write(immagineCampo);
		}
		
		if(email!=null) {
		byte[] immagineTorneo= progettoService.getImageUser(email);
		response.getOutputStream().write(immagineTorneo);
		}
		
	}

	
}
