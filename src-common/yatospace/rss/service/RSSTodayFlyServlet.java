package yatospace.rss.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndEntryImpl;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.SyndFeedOutput;

import programiranje.ip.admin.listener.DatabaseListener;
import yatospace.worker.services.io.FligthsDAO;
import yatospace.worker.services.jpa.Flight;

/**
 * Сервиси који се користе за пружање списка летова.
 * @author VM
 * @version 1.0
 */
@WebServlet("/yatospace/rss/fly/today")
public class RSSTodayFlyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RSSTodayFlyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType("rss_2.0");
		feed.setTitle("fly-today");
		feed.setTitle("FLY TODAY");
		feed.setDescription("Spisak danasnjih letova sa probnom stranicom/stranicama.");
		feed.setLink("https://www.yatospace.com");
		feed.setLink("http://localhost:8085/046_AeroflotUsers");
		FligthsDAO flightDAO = DatabaseListener.getFlightsDAO(request.getSession()); 
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy."); 
		String stratDate = sdf.format(today); 
		List<SyndEntry> entries = new ArrayList<>();
		for (Flight f : flightDAO.list()) {
			if(!f.getFlightDate().startsWith(stratDate)) continue;
			SyndEntry item = new SyndEntryImpl();
			item.setTitle("Let " + f.getFlightId());
			item.setLink("http://localhost:8085/046_AeroflotUsers/fly-today.jsp?fly_id="+f.getFlightId());
			entries.add(item);
		}
		feed.setEntries(entries);
		try {
			response.getWriter().println(new SyndFeedOutput().outputString(feed));
		} catch (Exception ex) {
			response.sendError(500);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void setContentType(HttpServletResponse response) {
		response.setContentType("text/rss+xml"); 
	}
}
