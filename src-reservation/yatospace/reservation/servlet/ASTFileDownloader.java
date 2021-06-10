package yatospace.reservation.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yatospace.worker.services.file.ArticleTransportSpecificationFileController;

/**
 *  Преузимање спецификације транспорта, по идентификатору транспорта
 *  за сад су све јавне, тако да само по идентификатору треба да постоје, 
 *  нема неке политике доступности, нити ограничњења по кориснику, што се 
 *  самог сервлета тиче, иако у формама корисници ће листати своје резервације, 
 *  па имати приступнице својим спецификацијама. 
 */
@WebServlet("/ASTFileDownloader")
public class ASTFileDownloader extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArticleTransportSpecificationFileController controller = new ArticleTransportSpecificationFileController();
	
    public ASTFileDownloader() {
        super();
    }

	public ArticleTransportSpecificationFileController getController() {
		return controller;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String reservationFile = request.getParameter("reservation_file"); 
		String reservationId   = request.getParameter("reservation_id"); 
		
		if(reservationId==null) {
			response.sendError(404, "Reservation id not found.");
			return;
		}
		if(reservationFile==null) { 
			response.sendError(404, "Reservation file not found.");
			return; 
		}
		if(!reservationFile.startsWith(reservationId)) {
			response.sendError(404 , "Reservation file missmatch.");
			return; 
		}
			
		String encodedURL = URLEncoder.encode(reservationFile, "UTF-8"); 
		String astLink = URLDecoder.decode(reservationFile.substring(reservationId.length()+1), "UTF-8"); 
		
		File file = controller.get(encodedURL); 
		
		if(file==null || !file.exists()) {
			response.sendError(404, "Response AST File not found.");
			return; 
		}
		
		response.setHeader("Content-Disposition", "atachment; filename*=UTF-8''"+astLink);
		byte[] data = Files.readAllBytes(file.toPath()); 
		response.setContentLength(data.length);
		response.getOutputStream().write(data);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
