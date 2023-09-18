package com.project.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import com.project.dto.TipologiaCampoDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.CampoUtility;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


    public class PrenotazioneAssAdminServlet extends HttpServlet {
    	private static final long serialVersionUID = 1L;
    	private ProgettoService progettoService=new ProgettoServiceImpl();

           
        public PrenotazioneAssAdminServlet() {
            super();        
        }

    	
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        	List<TipologiaCampoDTO> listaTipologia = progettoService.listaTipologiaCampo();
        	request.setAttribute("listaCampiTipologia", listaTipologia);
        	RequestDispatcher rd= request.getRequestDispatcher("jsp/prenotazioneAssAdmin.jsp");
    		rd.forward(request, response);
    	}
        
        
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        	RequestDispatcher rd=null;
        	List<CampoUtility> listaCampi= new ArrayList<CampoUtility>();
        	
        	List<TipologiaCampoDTO> listaTipologia = progettoService.listaTipologiaCampo();
        	request.setAttribute("listaCampiTipologia", listaTipologia);
    		String dataInizio = request.getParameter("meeting-time-data-inizio");
    		String dataFine = request.getParameter("meeting-time-data-fine");
        	
    		if(!(request.getParameter("selectTipologia").equalsIgnoreCase("---"))) {
        		//int idTipologia= Integer.parseInt(request.getParameter("selectTipologia"));
        		String tipologia=request.getParameter("selectTipologia");
        		 listaCampi = progettoService.getAllInfocampiAssAdminComplete(dataInizio, dataFine, tipologia);
    			for(int i=0;i<listaCampi.size();i++) {
    				Blob b=listaCampi.get(i).getImg();
    				request.setAttribute("immagine", b);	
    			}
    			request.setAttribute("listaCampi", listaCampi);
    			request.setAttribute("idTipologia", tipologia);
    			
        	}
    		
        	else {
    		  listaCampi = progettoService.getAllInfocampiVip(dataInizio, dataFine);
    			for(int i=0;i<listaCampi.size();i++) {
    				Blob b=listaCampi.get(i).getImg();
    				request.setAttribute("immagine", b);	
    				}
    			request.setAttribute("listaCampi", listaCampi);
        	}
    		
    			//Ci vogliono in quanto questi dati li devo passare ad un'altra Servlet che usa però la stessa .jsp
    			//Senza questo passaggio avrei solo valori null di li, anche se li andrei a passare come input type hidden
    			request.setAttribute("listaCampiTipologia", listaTipologia);
    			request.setAttribute("dataInizio", dataInizio);
    			request.setAttribute("dataFine", dataFine);
    			rd= request.getRequestDispatcher("jsp/prenotazioneAssAdmin.jsp");
    			rd.forward(request, response);
    			listaCampi.clear();	
        }
    }
