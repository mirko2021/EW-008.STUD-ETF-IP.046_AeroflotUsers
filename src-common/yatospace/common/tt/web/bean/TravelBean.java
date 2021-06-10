package yatospace.common.tt.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

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
import yatospace.worker.services.io.FligthsDAO;
import yatospace.worker.services.io.ReservationsDAO;
import yatospace.worker.services.jpa.Flight;
import yatospace.worker.services.jpa.Reservation;

/**
 * Зрна која се односе на путовања. 
 * @author VM
 * @version 1.0
 */
public class TravelBean implements Serializable{
	private static final long serialVersionUID = -2071207559646471327L;
	
	private String sourceCountry = ""; 
	private String sourceCity = ""; 
	
	private String destinationCountry = "";
	private String destinationCity = ""; 
	
	private String flight = ""; 
	private int travelCount = 0;
	
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
		this.addressesSourceLoad();
	}
	public String getDestinationCountry() {
		return destinationCountry;
	}
	public void setDestinationCountry(String destinationCountry) {
		if(this.destinationCountry==null) destinationCountry = ""; 
		this.destinationCountry = destinationCountry;
		addressDestinationLoad();
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
	public int getTravelCount() {
		return travelCount;
	}
	public void setTravelCount(int travelCount) {
		if(travelCount==0) travelCount = 0; 
		this.travelCount = travelCount;
	}
	
	public void setSource(HttpServletRequest request) {
		String a2c = request.getParameter("flag_travel_src_io_country");
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
		travelCount = 0; 
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
		
		if(reservationStateCTRL==null) {
			ReservationStateDAO dao = DatabaseListener.getReservationStateDAO(request.getSession()); 
			reservationStateCTRL = new ReservationStateController(dao);	
		}
		
		username = login.getUsername(); 
		visited = true; 
		
		if(sourceCountry.trim().length()==0)      sourceCity = ""; 
		if(destinationCountry.trim().length()==0) destinationCity = ""; 
		
		addressesSourceLoad();
		addressDestinationLoad();
	}
	
	public void setDestination(HttpServletRequest request) {
		String a2c = request.getParameter("flag_travel_dest_io_country");
		if(a2c==null) return;
		if(a2c.length()<2) return;
		destinationCountry = a2c.substring(0, 2);
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
		flight = request.getParameter("travel_flight");
		if(flight==null) flight=""; 
	}
	
	public void resetFlight() {
		flight = ""; 
	}
	
	public boolean reservate(HttpServletRequest request) {
		try {
			username = login.getUsername(); 
			if(flight.trim().length()==0) return false;
			if(username.trim().length()==0) return false;
			String placeCount = request.getParameter("place_count");
			ReservationsDAO dao = DatabaseListener.getReservationsDAO(request.getSession());
			Reservation reservation = new Reservation(); 
			reservation.setPlaceCount(Integer.parseInt(placeCount));
			String reservationId = ""; 
			do{
				Random random = new Random();
				reservationId = "RV."+ String.format("%06d", Math.abs(random.nextInt()%100000));
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
			if(!dto.getObject().getRtSubType().contentEquals("ПУТНИЧКИ")) return false; 
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
			String reservationId = request.getParameter("travel_reservation_deleter_data"); 
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
			if(!dto.getObject().getRtSubType().contentEquals("ПУТНИЧКИ")) return false; 
			if(!username.contentEquals(reservation.getUsername())) return false; 
			dao.delete(reservationId);
			rId = reservationId; 
			return true;
		}catch(Exception ex) {
			return false; 
		}
	}
}
