package yatospace.flag.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import yatospace.flag.bean.CountriesBean;
import yatospace.flag.model.Country;

/**
 * Сервлет којим се враћа листа држава са основним подацима у облику којим је и узет. 
 * @author VM
 * @version 1.0
 */
@WebServlet("/CountriesServlet")
public class CountriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CountriesServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		CountriesBean bean = (CountriesBean) request.getSession().getAttribute("countriesBean"); 
		if(bean==null) bean = new CountriesBean();
		request.setAttribute("countriesBean", bean);
		List<Country> countries = bean.get();
		JsonArray root = new JsonArray();
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		for(Country c: countries) root.add(parser.parse(gson.toJson(c)));
		response.getWriter().println(root.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
