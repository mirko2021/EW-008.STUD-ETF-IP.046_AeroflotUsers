package programiranje.ip.admin.listener;

import java.util.HashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import programiranje.ip.admin.controller.LocationInfoController;
import programiranje.ip.admin.database.YatospaceDBConnectionPool;
import programiranje.ip.admin.io.LocationBaseDAO;
import yatospace.common.user.io.UserLocationDAO;
import yatospace.common.user.io.UserProfileDAO;
import yatospace.email.user.data.io.EmailInfoDAO;
import yatospace.ip_messaging.io.IPMessageDAO;
import yatospace.ip_messaging.io.IPUserDAO;
import yatospace.ip_messaging.io.SQLTimestampDAO;
import yatospace.reservation.state.io.ReservationStateDAO;
import yatospace.traffic.relation.io.TrafficRelationsDAO;
import yatospace.worker.services.io.FligthsDAO;
import yatospace.worker.services.io.MessagesDAO;
import yatospace.worker.services.io.ReservationsDAO;

/**
 * Ослушкивачи који се вежу за базу података. 
 * @author VM
 * @version 1.1
 */
@WebListener
public class DatabaseListener implements HttpSessionListener {
	private static final YatospaceDBConnectionPool connectionPool = YatospaceDBConnectionPool.getConnectionPool();
	
	private static final HashMap<HttpSession, LocationBaseDAO> adapterMap = new HashMap<>();
	private static final HashMap<HttpSession, LocationInfoController> locationControllerMap = new HashMap<>(); 
	private static final HashMap<HttpSession, TrafficRelationsDAO> rtMap = new HashMap<>();
	private static final HashMap<HttpSession, EmailInfoDAO> emailMap = new HashMap<>();
	private static final HashMap<HttpSession, IPUserDAO> ipUserMap = new HashMap<>(); 
	private static final HashMap<HttpSession, IPMessageDAO> ipMessageMap = new HashMap<>();
	private static final HashMap<HttpSession, SQLTimestampDAO> sqlTimestampMap = new HashMap<>();
	private static final HashMap<HttpSession, UserProfileDAO> userProfileMap = new HashMap<>();
	private static final HashMap<HttpSession, UserLocationDAO> userLocationMap = new HashMap<>();
	private static final HashMap<HttpSession, FligthsDAO> flightsMap = new HashMap<>();
	private static final HashMap<HttpSession, MessagesDAO> messagesMap = new HashMap<>();
	private static final HashMap<HttpSession, ReservationsDAO> reservationsMap = new HashMap<>();
	private static final HashMap<HttpSession, ReservationStateDAO> reservationStateMap = new HashMap<>();
	
	
	private boolean started = false;
	
	public boolean isStarted() {
		return started;
	}

	public synchronized static HashMap<HttpSession, LocationInfoController> getLocationMap(){
		return new HashMap<>(locationControllerMap);
	}
	
	public static synchronized LocationInfoController getLocationController(HttpSession session) {
		return locationControllerMap.get(session); 
	}
	
	
    public static synchronized HashMap<HttpSession, LocationBaseDAO> getAdapterMap() {
    	return new HashMap<>(adapterMap);
	}
    
    public synchronized static LocationBaseDAO getAdapter(HttpSession session) {
    	return adapterMap.get(session);
    }
    
    
	public synchronized static HashMap<HttpSession, EmailInfoDAO> getEmailMap() {
		return new HashMap<>(emailMap);
	}
	
	public synchronized static EmailInfoDAO getEmail(HttpSession session) {
		return emailMap.get(session); 
	}
	
	public synchronized static HashMap<HttpSession, IPUserDAO> getIpUserMap() {
		return new HashMap<>(ipUserMap);
	}
	
	public synchronized static IPUserDAO getIpUser(HttpSession session) {
		return ipUserMap.get(session); 
	}
	
	public synchronized static HashMap<HttpSession, TrafficRelationsDAO> getRtMap() {
		return new HashMap<>(rtMap);
	}
	
	public synchronized static TrafficRelationsDAO getRt(HttpSession session) {
		return rtMap.get(session); 
	}
	
	public synchronized static HashMap<HttpSession, IPMessageDAO> getIpMessageMap() {
		return new HashMap<>(ipMessageMap);
	}
	
	public synchronized static IPMessageDAO getIpMessage(HttpSession session) {
		return ipMessageMap.get(session); 
	}
	
	public synchronized static HashMap<HttpSession, SQLTimestampDAO> getSQLTimestampMap() {
		return new HashMap<>(sqlTimestampMap);
	}
	
	public synchronized static SQLTimestampDAO getSQLTimestamp(HttpSession session) {
		return sqlTimestampMap.get(session); 
	}
	
	public synchronized static HashMap<HttpSession, UserLocationDAO> getUserLocationMap() {
		return new HashMap<>(userLocationMap);
	}
	
	public synchronized static UserLocationDAO getUserLocation(HttpSession session) {
		return userLocationMap.get(session); 
	}
	
	public synchronized static HashMap<HttpSession, UserProfileDAO> getUserProfileMap() {
		return new HashMap<>(userProfileMap);
	}
	
	public synchronized static UserProfileDAO getUserProfile(HttpSession session) {
		return userProfileMap.get(session); 
	}
	
	public synchronized static HashMap<HttpSession, FligthsDAO> getFlightsMap() {
		return new HashMap<>(flightsMap);
	}
	
	public synchronized static FligthsDAO getFlightsDAO(HttpSession session) {
		return flightsMap.get(session); 
	}
	
	
	public synchronized static HashMap<HttpSession, MessagesDAO> getMessagesMap() {
		return new HashMap<>(messagesMap);
	}
	
	public synchronized static MessagesDAO getMessagesDAO(HttpSession session) {
		return messagesMap.get(session); 
	}
	
	public synchronized static HashMap<HttpSession, ReservationsDAO> getReservationsMap() {
		return new HashMap<>(reservationsMap);
	}
	
	public synchronized static ReservationsDAO getReservationsDAO(HttpSession session) {
		return reservationsMap.get(session); 
	}
	
	public synchronized static HashMap<HttpSession, ReservationStateDAO> getReservationStatesMap() {
		return new HashMap<>(reservationStateMap);
	}
	
	public synchronized static ReservationStateDAO getReservationStateDAO(HttpSession session) {
		return reservationStateMap.get(session); 
	}
	
	public static YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	}
	
	public DatabaseListener() {}

    public void sessionCreated(HttpSessionEvent se)  {
    	synchronized(DatabaseListener.class) {
    		LocationBaseDAO dao = new LocationBaseDAO(connectionPool); 
    		LocationInfoController controller = new LocationInfoController();
    		TrafficRelationsDAO rtDao = new TrafficRelationsDAO(connectionPool);
    		IPUserDAO ipUserDao = new IPUserDAO(connectionPool);
    		IPMessageDAO ipMessageDao = new IPMessageDAO(connectionPool);
    		SQLTimestampDAO  sqlTimestampDao = new  SQLTimestampDAO(connectionPool);
    		EmailInfoDAO emailDao = new EmailInfoDAO(connectionPool);
    		UserLocationDAO userLocationDao = new UserLocationDAO(connectionPool);
    		UserProfileDAO userProfileDao = new UserProfileDAO(connectionPool);
    		FligthsDAO flightsDao = new FligthsDAO(connectionPool); 
    		MessagesDAO messagesDao = new MessagesDAO(connectionPool);
    		ReservationsDAO reservationsDao = new ReservationsDAO(connectionPool);
    		ReservationStateDAO reservationStateDao = new ReservationStateDAO(connectionPool);
    		
    		locationControllerMap.put(se.getSession(), controller);
    		adapterMap.put(se.getSession(), dao);
    		rtMap.put(se.getSession(), rtDao);
    		emailMap.put(se.getSession(), emailDao);
    		ipUserMap.put(se.getSession(), ipUserDao);
    		ipMessageMap.put(se.getSession(), ipMessageDao);
    		sqlTimestampMap.put(se.getSession(), sqlTimestampDao);
    		userLocationMap.put(se.getSession(), userLocationDao);
    		userProfileMap.put(se.getSession(), userProfileDao);
    		reservationsMap.put(se.getSession(), reservationsDao);
    		messagesMap.put(se.getSession(), messagesDao); 
    		flightsMap.put(se.getSession(), flightsDao);
    		reservationStateMap.put(se.getSession(), reservationStateDao);
    		
    		controller.setLocationBaseDAO(dao);
    		DatabaseListener.class.notifyAll();
    		started = true;
    	}
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	synchronized(DatabaseListener.class) {
    		rtMap.remove(se.getSession());
    		adapterMap.remove(se.getSession()); 
    		locationControllerMap.remove(se.getSession()); 
    		sqlTimestampMap.remove(se.getSession()); 
    		ipMessageMap.remove(se.getSession()); 
    		ipUserMap.remove(se.getSession());
    		emailMap.remove(se.getSession());
    		userLocationMap.remove(se.getSession());
    		userProfileMap.remove(se.getSession());
    		flightsMap.remove(se.getSession());
    		messagesMap.remove(se.getSession());
    		reservationsMap.remove(se.getSession());
    		reservationStateMap.remove(se.getSession()); 
    	}
    }
}
