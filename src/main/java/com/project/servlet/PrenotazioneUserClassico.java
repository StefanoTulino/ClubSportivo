// NON DEVO FARE TRY CATCH QUI, MA LI DEVO GESTIRE NEL SERVICE()
package com.project.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import com.project.dto.TipologiaCampoDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.CampoUtility;
import com.project.utility.UtenteSession;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class PrenotazioneUserClassico extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService=new ProgettoServiceImpl();

       
    public PrenotazioneUserClassico() {
        super();        
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    	List<TipologiaCampoDTO> listaTipologia = progettoService.listaTipologiaCampo();
    	request.setAttribute("listaCampiTipologia", listaTipologia);
    	RequestDispatcher rd= request.getRequestDispatcher("jsp/prenotazioneUserClassico.jsp");
		rd.forward(request, response);
	}
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    	RequestDispatcher rd=null;
    	HttpSession session= request.getSession();
    	UtenteSession utente= (UtenteSession) session.getAttribute("user");
    	
    	List<CampoUtility> listaCampi= new ArrayList<CampoUtility>();
    	
    	List<TipologiaCampoDTO> listaTipologia = progettoService.listaTipologiaCampo();
    	request.setAttribute("listaCampiTipologia", listaTipologia);
		String d = request.getParameter("meeting-time");
    	
		if(!(request.getParameter("selectTipologia").equalsIgnoreCase("---"))) {
    		//int idTipologia= Integer.parseInt(request.getParameter("selectTipologia"));
    		String tipologia=request.getParameter("selectTipologia");
    		 listaCampi = progettoService.getAllInfocampiComplete(utente.getEmail(),d,tipologia);
			for(int i=0;i<listaCampi.size();i++) {
				Blob b=listaCampi.get(i).getImg();
				request.setAttribute("immagine", b);	
			}
			request.setAttribute("listaCampi", listaCampi);
			request.setAttribute("idTipologia", tipologia);
    	}
		
    	else {
		  listaCampi = progettoService.getAllInfocampi(utente.getEmail(),d);
			for(int i=0;i<listaCampi.size();i++) {
				Blob b=listaCampi.get(i).getImg();
				request.setAttribute("immagine", b);	
				}
			request.setAttribute("listaCampi", listaCampi);
    	}
			request.setAttribute("listaCampiTipologia", listaTipologia);
			request.setAttribute("data", d);
			rd= request.getRequestDispatcher("jsp/prenotazioneUserClassico.jsp");
			rd.forward(request, response);
			listaCampi.clear();	
    }
}
