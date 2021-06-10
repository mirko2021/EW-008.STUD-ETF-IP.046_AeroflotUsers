package yatospace.flag.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yatospace.flag.io.CountriesController;
import yatospace.flag.web.listener.CountryDataEngineListener;

/**
 * Сервлет за преузимање застава држава, које су захтијеване параметром и А2Ц кодом државе. 
 */
@WebServlet("/CountriesImageDistributionServlet")
public class CountriesImageDistributionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  	
    public CountriesImageDistributionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("image/svg");
		CountriesController controller = CountryDataEngineListener.getCountriesController(request.getSession());
		if(request.getParameter("a2c")==null) {response.sendError(404, "Image not found."); return;}
		if(controller.image(request.getParameter("a2c"))==null) {response.sendError(404, "Image not found."); return;}
		response.getOutputStream().write(controller.image(request.getParameter("a2c")));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
