package yatospace.web.ajax.execute;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Сервклет за преузимање 
 * @author MV
 * @version 1.0 
 */
@WebServlet("/AjaxDownloader")
public class AjaxDownloader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxDownloader() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String path = request.getParameter("path"); 
		if(path==null) { response.sendError(404, "Resource not found."); return; }
		path = URLDecoder.decode(new String(Base64.getDecoder().decode(path.getBytes("UTF-8"))), "UTF-8"); 
		RequestDispatcher dispatcher = request.getRequestDispatcher(path); 
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
