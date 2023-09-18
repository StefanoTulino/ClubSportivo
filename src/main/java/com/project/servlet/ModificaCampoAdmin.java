package com.project.servlet;

import java.io.IOException;

import com.project.dto.CampoDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ModificaCampoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService= new ProgettoServiceImpl();
       
   
    public ModificaCampoAdmin() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=null;

		try {
			int id = Integer.parseInt(request.getParameter("idCampo"));
			int tipologia = Integer.parseInt(request.getParameter("tipologia_fk"));
			float prezzo = Float.parseFloat(request.getParameter("costo"));
			System.out.println(tipologia);
			CampoDTO campo = progettoService.modificheAdminCampo(id, prezzo, tipologia);
			request.setAttribute("msgCampo","Il campo con id: "+ campo.getId()+" e' stato modificato con successo");
			rd = request.getRequestDispatcher("GestioneCampo");
			rd.forward(request, response);
			
			} catch(Exception e) {
				System.out.println("Error: "+e.getMessage() );
				}
		
	}

		
}
