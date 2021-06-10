package yatospace.common.tt.web.bean;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import programiranje.ip.admin.controller.LocationInfoController;
import programiranje.ip.admin.listener.DatabaseListener;
import programiranje.ip.admin.model.Location;
import yatospace.reservation.state.controller.ReservationStateController;
import yatospace.reservation.state.io.ReservationStateDAO;
import yatospace.reservation.state.util.ReservationState;
import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;
import yatospace.traffic.relation.io.TrafficRelationsDAO;
import yatospace.traffic.relation.io.TrafficRelationsDTO;
import yatospace.worker.services.file.ArticleTransportSpecificationFileController;
import yatospace.worker.services.file.ReservationTransportSpecificationFileController;
import yatospace.worker.services.io.FligthsDAO;
import yatospace.worker.services.io.ReservationsDAO;
import yatospace.worker.services.jpa.Flight;
import yatospace.worker.services.jpa.Reservation;

/**
 * Зрна која се односе на податке о 
 * транспорту робе.
 * @author VM
 * @version 1.0
 */
public class TransportBean implements Serializable{
	private static final long serialVersionUID = 1939448716701511770L;
	
	private String sourceCountry = ""; 
	private String sourceCity = ""; 
	
	private String destinationCountry = "";
	private String destinationCity = ""; 
	
	private String flight = ""; 
	private String articleDescription = "";
	
	private String username = ""; 
	private boolean visited = false; 
	private String message = ""; 
	
	private transient LoginBean login; 
	private transient LocationInfoController location; 
	
	private transient ReservationStateController reservationStateCTRL;

	public ReservationStateController getReservationStateCTRL() {
		return reservationStateCTRL;
	}
	
	private List<String> sourceAddresses      = new ArrayList<>();
	private List<String> destinationAddresses = new ArrayList<>();
	
	private  String reservation = ""; 
	
	public String getSourceCountry() {
		return sourceCountry;
	}
	public void setSourceCountry(String sourceCountry) {
		if(this.sourceCountry==null) sourceCountry = "";
		this.sourceCountry = sourceCountry;
	}
	public String getSourceCity() {
		return sourceCity;
	}
	public void setSourceCity(String sourceCity) {
		if(this.sourceCity==null) sourceCity  = ""; 
		this.sourceCity = sourceCity;
	}
	public String getDestinationCountry() {
		return destinationCountry;
	}
	public void setDestinationCountry(String destinationCountry) {
		if(this.destinationCountry==null) destinationCountry = ""; 
		this.destinationCountry = destinationCountry;
	}
	public String getDestinationCity() {
		return destinationCity;
	}
	public void setDestinationCity(String destinationCity) {
		if(this.destinationCity==null) destinationCity = ""; 
		this.destinationCity = destinationCity;
	}
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		if(flight==null) flight=""; 
		this.flight = flight;
	}
	public String getArticleDescription() {
		return articleDescription;
	}
	public void setArticleDescription(String articleDescription) {
		if(this.articleDescription==null) articleDescription = "";  
		this.articleDescription = articleDescription;
	}
	
	public void setSource(HttpServletRequest request) {
		String a2c = request.getParameter("flag_transport_src_io_country");
		if(a2c==null) return;
		if(a2c.length()<2) return;
		sourceCountry = a2c.substring(0, 2);
	}
	
	public void resetSource(HttpServletRequest request) {
		sourceCountry = "";
		sourceAddresses.clear(); 
		sourceCity = ""; 
	}
	
	public String getUsername() {
		return username;
	}
	public boolean isVisited() {
		return visited;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		if(message==null) message = ""; 
		this.message = message;
	}
	public LoginBean getLogin() {
		return login;
	}
	
	public void resetMessage() {
		message = ""; 
	}
	
	public void resetData() {
		sourceCountry = ""; 
		sourceCity = ""; 
		
		destinationCountry = "";
		destinationCity = ""; 
		
		flight = ""; 
		articleDescription = "";
	}
	
	public void reset() {
		resetMessage(); 
		resetData();
	}
	
	public void initialize(HttpServletRequest request) {
		if(login==null) {
			login = SessionBeansGenerator.loginBean(request.getSession());
			login.addPostLogin(getClass().getName()+"#initialize^set", ()->{});
			login.addPostLogout(getClass().getName()+"#initialize^reset", ()->{reset(); visited=false;});
			login.addPostLogoutAll(getClass().getName()+"#initialize^reset", ()->{reset(); visited=false;});
		}
		
		if(location==null) {
			location = DatabaseListener.getLocationController(request.getSession()); 
		}
		
		if(sourceCountry.contentEquals(""))      sourceCity=""; 
		if(destinationCountry.contentEquals("")) destinationCity = ""; 
		
		if(reservationStateCTRL==null) {
			ReservationStateDAO dao = DatabaseListener.getReservationStateDAO(request.getSession()); 
			reservationStateCTRL = new ReservationStateController(dao);	
		}
		
		addressesSourceLoad(); 
		addressDestinationLoad();
		
		username = login.getUsername(); 
		visited = true; 
	}
	
	public void setDestination(HttpServletRequest request) {
		String a2c = request.getParameter("flag_transport_dest_io_country");
		if(a2c==null) return;
		if(a2c.length()<2) return;
		destinationCountry = a2c.substring(0, 2);
		addressDestinationLoad(); 
	}
	
	public void resetDestination(HttpServletRequest request) {
		destinationCountry = "";
		destinationAddresses.clear(); 
		destinationCity = "";
	}
	
	public LocationInfoController getLocation() {
		return location;
	}
	

	public List<String> getSourceAddresses() {
		ArrayList<String> result = new ArrayList<>(sourceAddresses);
		Collections.sort(result); 
		return result;
	}
	
	public List<String> getDestinationAddresses() {
		ArrayList<String> result = new ArrayList<>(destinationAddresses);
		Collections.sort(result); 
		return result;
	}
	
	public void addressSourceReset() {
		sourceAddresses = new ArrayList<>();
	}
	
	public void addressDestinationReset() {
		destinationAddresses = new ArrayList<>();
	}
	
	public List<String> addressesDestinationReset() {
		ArrayList<String> result = new ArrayList<>(destinationAddresses);
		Collections.sort(result);
		return result;
	}
	
	public void addressesSourceLoad() {
		addressSourceReset();
		if(sourceAddresses==null || sourceAddresses.size()!=0) { 
			ArrayList<String> list = new ArrayList<>();
			for(Location location: location.list()) {
				list.add(location.getLocationName());
			}
			sourceAddresses = new ArrayList<>(list); 
		}
		else { 
			ArrayList<String> list = new ArrayList<>();
			for(Location location: location.list(sourceCountry)) {
				list.add(location.getLocationName()); 
			}
			sourceAddresses = new ArrayList<>(list);
		}
	}
	
	public void addressDestinationLoad() {
		addressDestinationReset();
		if(destinationAddresses==null || destinationAddresses.size()!=0) {
			ArrayList<String> list = new ArrayList<>();
			for(Location location: location.list()) {
				list.add(location.getLocationName());
			}
			destinationAddresses = new ArrayList<>(list);
		}
		else {
			ArrayList<String> list = new ArrayList<>();
			for(Location location: location.list(destinationCountry)) {
				list.add(location.getLocationName());
			}
			destinationAddresses = new ArrayList<>(list);
		}
	}
	
	public void acceptFlight(HttpServletRequest request) {
		flight = request.getParameter("transport_flight");
		if(flight==null) flight=""; 
	}
	
	public void resetFlight() {
		flight = ""; 
	}
	
	public String encodeASTFilename(String astLink, String reservationId) {
		try {
			astLink = URLDecoder.decode(astLink, "UTF-8").substring(reservationId.length()+1); 
			return astLink;
		}catch(Exception ex) {
			return ""; 
		}
	}
	
	public String decodeURL(String text) {
		try {
			return URLEncoder.encode(text, "UTF-8");
		}catch(Exception ex) {
			return "";
		}
	}
	
	public String getReservation() {
		return reservation;
	}
	
	public void setReservation(String reservation) {
		if(reservation == null) reservation = ""; 
		this.reservation = reservation;
	}
	
	public void acceptReservation(HttpServletRequest request) {
		String reservationParameter = request.getParameter("reservation_transport_choose_form_actual_data"); 
		if(reservationParameter==null) reservationParameter = ""; 
		this.reservation = reservationParameter;
	}
	
	public void resetReservation() {
		this.reservation = ""; 
	}
	
	
	private boolean isMultipart;
	private int maxFileSize = 5000 * 1024;
	private int maxMemSize = 5000 * 1024;
	
	public void resetAST(HttpServletRequest request) {
		try {
			var dao = DatabaseListener.getReservationsDAO(request.getSession()); 
			ArticleTransportSpecificationFileController astfc = new ArticleTransportSpecificationFileController();
			ReservationTransportSpecificationFileController rstfc = new ReservationTransportSpecificationFileController(astfc, dao);
			if(reservation.trim().length()==0) return; 
			String urlEncoded = URLEncoder.encode(reservation, "UTF-8")+"_"; 
			for(File f: new File(ArticleTransportSpecificationFileController.ROOT_DIR).listFiles()) {
				if(f.getName().startsWith(urlEncoded)) {
					rstfc.remove(reservation, f.getName().substring(urlEncoded.length()));
					if(f.exists()) f.delete();
				}
			}
		}catch(Exception ex) {
			return;
		}
	}
	
	public void setAST(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
			isMultipart = ServletFileUpload.isMultipartContent(request);
			if(!isMultipart) {return;}
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
		    factory.setSizeThreshold(maxMemSize);
		    factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		    
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    upload.setSizeMax(maxFileSize);
		    
		    List<Part> fileItems = new ArrayList<>(request.getParts());
		   	    
			if(reservation.trim().length()==0) return;
			
	        for (Part p : fileItems) {
	        	var dao = DatabaseListener.getReservationsDAO(request.getSession()); 
	        	if(p.getName().contentEquals("article_transport_spec_file")) {
	        		String name = URLEncoder.encode(reservation+"_"+p.getSubmittedFileName(), "UTF-8"); 
	        		byte[] content = new byte[0];
	        		try(InputStream is = p.getInputStream()){
	        			content = is.readAllBytes();
	        		}
	        		ArticleTransportSpecificationFileController astfc = new ArticleTransportSpecificationFileController();
	    			ReservationTransportSpecificationFileController rstfc = new ReservationTransportSpecificationFileController(astfc, dao);
	    			rstfc.put(reservation, name, content);
	        		break; 
	        	}
	        }
		}catch(Exception ex){
			return;
		}
	}
	
	public boolean reservate(HttpServletRequest request) {
		try {
			username = login.getUsername(); 
			if(flight.trim().length()==0) return false;
			if(username.trim().length()==0) return false;
			String articleDescription = request.getParameter("article_description");
			ReservationsDAO dao = DatabaseListener.getReservationsDAO(request.getSession());
			Reservation reservation = new Reservation(); 
			reservation.setArticleDescription(articleDescription);
			String reservationId = ""; 
			do{
				Random random = new Random();
				reservationId = "RVT."+ String.format("%06d", Math.abs(random.nextInt()%100000));
			}while(dao.get(reservationId)!=null);
			reservation.setReservationId(reservationId);
			reservation.setFlyId(flight);
			reservation.setUsername(username);
			FligthsDAO fDao = DatabaseListener.getFlightsDAO(request.getSession()); 
			Flight flight = fDao.get(this.flight); 
			if(flight==null) return false; 
			TrafficRelationsDAO tDao = DatabaseListener.getRt(request.getSession()); 
			TrafficRelationsDTO dto = tDao.get(flight.getRelation()); 
			if(dto==null) return false; 
			if(dto.getObject()==null) return false; 
			if(!dto.getObject().getRtType().contentEquals("АВИОСАОБРАЋАЈ")) return false; 
			if(!dto.getObject().getRtSubType().contentEquals("ТЕРЕТНИ")) return false; 
			if(reservationId.trim().length()==0) return false; 
			dao.insert(reservation);
			rId = reservationId; 
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	private String rId = "";

	public String getrId() {
		return rId;
	}
	
	public boolean delete(HttpServletRequest request) {
		try {
			username = login.getUsername();
			if(username.length()==0) return false;
			String reservationId = request.getParameter("transport_reservation_deleter_data"); 
			if(reservationId==null) return false; 
			if(reservationStateCTRL.getReservationStateDAO().get(reservationId)==null) return false; 
			if(reservationStateCTRL.getReservationStateDAO().get(reservationId).getReservationState()==null) return false; 
			if(reservationStateCTRL.getReservationStateDAO().get(reservationId).getReservationState().getState()!=ReservationState.NOVO) return false;
			ReservationsDAO dao = DatabaseListener.getReservationsDAO(request.getSession());
			FligthsDAO fDao = DatabaseListener.getFlightsDAO(request.getSession()); 
			TrafficRelationsDAO tDao = DatabaseListener.getRt(request.getSession()); 
			Reservation reservation = dao.get(reservationId); 
			if(reservation==null) return false; 
			Flight flight = fDao.get(reservation.getFlyId());
			if(flight==null) return false; 
			TrafficRelationsDTO dto = tDao.get(flight.getRelation()); 
			if(dto==null) return false; 
			if(dto.getObject()==null) return false; 
			if(!dto.getObject().getRtType().contentEquals("АВИОСАОБРАЋАЈ")) return false; 
			if(!dto.getObject().getRtSubType().contentEquals("ТЕРЕТНИ")) return false; 
			if(!username.contentEquals(reservation.getUsername())) return false; 
			dao.delete(reservationId);
			rId = reservationId; 
			return true;
		}catch(Exception ex) {
			return false; 
		}
	}
}
