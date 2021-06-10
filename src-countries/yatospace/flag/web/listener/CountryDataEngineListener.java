package yatospace.flag.web.listener;

import java.util.HashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import programiranje.ip.admin.database.YatospaceDBConnectionPool;
import yatospace.flag.io.CountriesController;
import yatospace.flag.web.database.LocationCountryDAO;

/**
 * База и ослушкивач, када су разни контролери у питању. 
 * @author VM
 * @version 1.0
 */
@WebListener
public class CountryDataEngineListener implements HttpSessionListener {
	private static HashMap<HttpSession, CountriesController> countriesControllersMap = new HashMap<>(); 
	private static HashMap<HttpSession, LocationCountryDAO> locationCountryDAOMap = new HashMap<>();
	private static YatospaceDBConnectionPool connectionPool = YatospaceDBConnectionPool.getConnectionPool(); 
	
    public CountryDataEngineListener() {}

    private transient LocationCountryDAO countryDAO;
    
    public LocationCountryDAO getCountryDAO() {
		return countryDAO;
	}

	public void setCountryDAO(LocationCountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public void sessionCreated(HttpSessionEvent se)  { 
		countriesControllersMap.put(se.getSession(), new CountriesController()); 
		locationCountryDAOMap.put(se.getSession(), new LocationCountryDAO(connectionPool));
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	countriesControllersMap.remove(se.getSession()); 
    }
    
    public static synchronized CountriesController getCountriesController(HttpSession session) {
    	return countriesControllersMap.get(session); 
    }
    
    public static synchronized LocationCountryDAO getLocationCountryDAOMap(HttpSession session) {
    	return locationCountryDAOMap.get(session);
    }
}
