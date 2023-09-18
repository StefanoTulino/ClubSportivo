package com.project.servlet;

import java.io.IOException;
import java.util.List;

import com.project.dto.TipologiaUtenteDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.Utente;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class GestioneUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProgettoService progettoService= new ProgettoServiceImpl();  
  
    public GestioneUtente() {
        super();
        
    }
    
    public void init() {
    	progettoService= new ProgettoServiceImpl();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        RequestDispatcher rd=null;
		try {
			List<Utente> listaUtente=progettoService.gestioneUtenti2();
			List<TipologiaUtenteDTO> listaTipologia=progettoService.listaTipologia();
			session.setAttribute("listaUtenti", listaUtente);
			request.setAttribute("listaTipologia", listaTipologia);
//			request.setAttribute("PopupBtn","");
			rd= request.getRequestDispatcher("jsp/gestioneUtente.jsp");
			rd.forward(request, response);
			listaUtente.clear();
			//listaTipologia.clear();
	
			} catch(Exception e) {
				System.out.println("Error: "+e.getMessage() );
				}
		}

}
