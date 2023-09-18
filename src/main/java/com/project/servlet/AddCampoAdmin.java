package com.project.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


@MultipartConfig
public class AddCampoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService=new ProgettoServiceImpl();
       
  
    public AddCampoAdmin() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	RequestDispatcher rd=null;
		try {
		float prezzoForm= Float.parseFloat(request.getParameter("prezzo"));
		int tipologiaForm= Integer.parseInt(request.getParameter("tipologia_fk"));
		
			String res= progettoService.addCampo(prezzoForm, tipologiaForm);
			if(res!=null) {
				request.setAttribute("addCampo", "Record inserito correttamente");
				rd= request.getRequestDispatcher("GestioneCampo");
				rd.forward(request, response);
			}
		} catch(Exception e) {
			request.setAttribute("errorAddCampo", "I dati inseriti non sono corretti");
			rd= request.getRequestDispatcher("GestioneCampo");
			rd.include(request, response);
		}
	}
		

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=null;
		//Ritorna il path del web a cui mi sto riferendo
		String url = request.getRequestURL().toString();
		
		try {
		String descrizioneForm= request.getParameter("descrizione");
		float incrementoForm=  Float.parseFloat(request.getParameter("incremento"));
		
		Part imgPart= request.getPart("img");
		String filePath = imgPart.getSubmittedFileName();//Retrieves complete file name with path and directories 
        Path p = Paths.get(filePath); //creates a Path object
        String fileName = p.getFileName().toString();//Retrieves file name from Path object
        InputStream fileContent = imgPart.getInputStream();//converts Part data to input stream
        int  len=(int) imgPart.getSize();
        
        
		String res= progettoService.addTipologiaCampo(descrizioneForm, incrementoForm,fileContent,len);
		if(res!=null) {
			request.setAttribute("addTipologia", "Record inserito correttamente");
			rd= request.getRequestDispatcher("GestioneCampo");
			rd.forward(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("errorAddTipologia", "I dati inseriti non sono corretti");
			rd= request.getRequestDispatcher("GestioneCampo");
//			rd= request.getRequestDispatcher("modalGestioneCampo1.jsp");
			rd.include(request, response);
		}

	}
	
}
