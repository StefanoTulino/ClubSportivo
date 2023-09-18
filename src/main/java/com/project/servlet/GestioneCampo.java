//DEVE AVERE ENTRAMBI I VALORI, ALTRIMENTI NON VA IL SINGOLO METODO!!!

package com.project.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import com.project.dto.TipologiaCampoDTO;
import com.project.service.ProgettoService;
import com.project.service.impl.ProgettoServiceImpl;
import com.project.utility.Campo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class GestioneCampo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProgettoService progettoService=new ProgettoServiceImpl();
       
    
    public GestioneCampo() {
        super();
        
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd=null;
        
		try {
			List<Campo> listaCampi=progettoService.campiAll();
			for(int i=0;i<listaCampi.size();i++) {
				Blob b=listaCampi.get(i).getImg();
				request.setAttribute("immagine", b);	
			}
			
			List<TipologiaCampoDTO> listaTipologia=progettoService.listaTipologiaCampo();
			request.setAttribute("listaCampi", listaCampi);
			request.setAttribute("listaTipologia", listaTipologia);
			rd= request.getRequestDispatcher("jsp/gestioneCampo.jsp");
			rd.forward(request, response);
			listaCampi.clear();
			//listaTipologia.clear();
	
			} catch(Exception e) {
				System.out.println("Error: "+e.getMessage() );
				}
		}

}
