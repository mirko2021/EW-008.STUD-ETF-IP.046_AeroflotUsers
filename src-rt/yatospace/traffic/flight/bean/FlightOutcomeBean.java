package yatospace.traffic.flight.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import programiranje.ip.admin.listener.DatabaseListener;
import yatospace.traffic.flight.preview.FlightWidget;
import yatospace.traffic.flight.state.FlightOutcomeState;
import yatospace.worker.services.io.FligthsDAO;

/**
 * Зрно за потребе прегледа одлазака. 
 * @author VM
 * @version 1.0
 */
public class FlightOutcomeBean implements Serializable{
	private static final long serialVersionUID = -7130451541617235260L;
	private transient FligthsDAO dao; 
	
	public void initialize(HttpSession session) {
		dao = DatabaseListener.getFlightsDAO(session); 
	}
	
	public FligthsDAO getDao() {
		return dao;
	}

	public String datetimeString(HttpServletRequest request) {
		SimpleDateFormat sdfDateOutput = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
		SimpleDateFormat sdfTimeOutput = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
		sdfDateOutput = new SimpleDateFormat("dd.MM.yyyy.");
		sdfTimeOutput = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdfDateInput_SQL_HTML = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfTimeInput_SQL_HTML = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdfDateInput_SQL_HTML = new SimpleDateFormat("yyyy-MM-dd");
		sdfTimeInput_SQL_HTML = new SimpleDateFormat("HH:mm");
		String acceptedDate = request.getParameter("day_outcome_flight_date"); 
		String acceptedTimeFrom = request.getParameter("day_outcome_flight_time_from"); 
		String acceptedTimeTo = request.getParameter("day_outcome_flight_time_to"); 
		String realDate = ""; 
		String realTimeFrom = ""; 
		String realTimeTo = "";
		if(acceptedDate!=null && acceptedDate.trim().length()!=0)
			try {
				Date date = sdfDateInput_SQL_HTML.parse(acceptedDate); 
				realDate = sdfDateOutput.format(date); 
			} catch(Exception ex) {
				realDate = ""; 
			}
		if(acceptedTimeFrom!=null && acceptedTimeFrom.trim().length()!=0)
			try {
				Date timeFrom = sdfTimeInput_SQL_HTML.parse(acceptedTimeFrom); 
				realTimeFrom = sdfTimeOutput.format(timeFrom); 
			}catch(Exception ex) {
				realTimeFrom = ""; 
			}
		
		if(acceptedTimeTo!=null && acceptedTimeTo.trim().length()!=0)
			try {
				Date timeTo = sdfTimeInput_SQL_HTML.parse(acceptedTimeTo); 
				realTimeTo = sdfTimeOutput.format(timeTo); 
			}catch(Exception ex) {
				realTimeTo = ""; 
			}
		return realDate + " "+ realTimeFrom + " - "+ realTimeTo;
	}
	
	public String string_SQL_HTML_currentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date()); 
	}
	
	public List<FlightWidget> order(Map<?,?>[] resultSet, HttpServletRequest request){
		ArrayList<FlightWidget> result = new ArrayList<>();
		SimpleDateFormat sdfDateOutput = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
		SimpleDateFormat sdfTimeOutput = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
		sdfDateOutput = new SimpleDateFormat("dd.MM.yyyy.");
		sdfTimeOutput = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdfDateInput_SQL_HTML = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfTimeInput_SQL_HTML = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdfDateInput_SQL_HTML = new SimpleDateFormat("yyyy-MM-dd");
		sdfTimeInput_SQL_HTML = new SimpleDateFormat("HH:mm");
		String acceptedDate = request.getParameter("day_outcome_flight_date"); 
		String acceptedTimeFrom = request.getParameter("day_outcome_flight_time_from"); 
		String acceptedTimeTo = request.getParameter("day_outcome_flight_time_to"); 
		String realDate = ""; 
		String realTimeFrom = ""; 
		String realTimeTo = "";
		if(acceptedDate!=null && acceptedDate.trim().length()!=0)
			try {
				Date date = sdfDateInput_SQL_HTML.parse(acceptedDate); 
				realDate = sdfDateOutput.format(date); 
			} catch(Exception ex) {
				realDate = ""; 
			}
		if(acceptedTimeFrom!=null && acceptedTimeFrom.trim().length()!=0)
			try {
				Date timeFrom = sdfTimeInput_SQL_HTML.parse(acceptedTimeFrom); 
				realTimeFrom = sdfTimeOutput.format(timeFrom); 
			}catch(Exception ex) {
				realTimeFrom = ""; 
			}
		
		if(acceptedTimeTo!=null && acceptedTimeTo.trim().length()!=0)
			try {
				Date timeTo = sdfTimeInput_SQL_HTML.parse(acceptedTimeTo); 
				realTimeTo = sdfTimeOutput.format(timeTo); 
			}catch(Exception ex) {
				realTimeTo = ""; 
			}
		HashMap<FlightWidget, Date> dates = new HashMap<>();
		for(Map<?,?> flightMap: resultSet) {
			FlightWidget flight = new FlightWidget();
			flight.setFlyDate(flightMap.get("fly_date").toString());
			flight.setFlyId(flightMap.get("fly_id").toString());
			flight.setDirection(flightMap.get("direction").toString());
			flight.setDestination(flightMap.get("destination").toString());
			flight.setPlace(flightMap.get("place").toString());
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy.");
			SimpleDateFormat sdfTime = new SimpleDateFormat("dd.MM.yyyy. HH:mm");  
			SimpleDateFormat sdfDetailTime = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
			DatetimeType timestampType = DatetimeType.NONE; 
			Date target = null;
			dates.put(flight, target); 
			try {
				target = sdfDetailTime.parse(flight.getFlyDate()); 
				timestampType = DatetimeType.DETAIL;
				dates.put(flight, target); 
			}catch(Exception ex) {
				try {
					target = sdfTime.parse(flight.getFlyDate()); 
					timestampType = DatetimeType.TIME;
					dates.put(flight, target); 
				}catch(Exception exa) {
					try {
						target = sdfDate.parse(flight.getFlyDate()); 
						timestampType = DatetimeType.DAY;
						dates.put(flight, target); 
					}catch(Exception exb) {
						target = null;
						timestampType = DatetimeType.NONE; 
						dates.put(flight, target);
					}
				}
			}
			if(realDate.trim().length()==0) 
				result.add(flight);
			else {
				try {
					if(timestampType == DatetimeType.NONE)
						result.add(flight);
					else if(timestampType == DatetimeType.DAY) {
						if(target==null) continue; 
						if(target.equals(sdfDateOutput.parse(realDate)))
							result.add(flight);
					}else {
						String date = sdfDateOutput.format(target); 
						String targetTime = sdfTimeOutput.format(target); 
						if(!date.contentEquals(realDate)) continue;
						if(targetTime.compareTo(realTimeFrom)<0) continue; 
						if(targetTime.compareTo(realTimeTo)>0) continue;
						result.add(flight);
					}
				}catch(Exception ex) {
					continue;
				}
			}
		}
		
		for(int i=0; i<result.size()-1; i++) {
			for(int j=i+1; j<result.size(); j++) {
				FlightWidget fwa = result.get(i);
				FlightWidget fwb = result.get(j); 
				Date fwaDate = dates.get(fwa); 
				Date fwbDate = dates.get(fwb); 
				if(fwaDate!=null && fwbDate!=null) {
					if(fwaDate.compareTo(fwbDate)<0){
						result.set(i, fwb); 
						result.set(j, fwa);
					}
				}
				if(fwaDate==null && fwbDate!=null) {
					result.set(i, fwb); 
					result.set(j, fwa);
				}
				if(fwaDate==null && fwbDate==null) {
					if(fwa.getFlyId().compareTo(fwb.getFlyId())>0) {
						result.set(i, fwb); 
						result.set(j, fwa);
					}
				}
			}
		}
		return result;
	}	
	
	public FlightOutcomeState getOutcome(String flyId) {
		return dao.getOutcomeState(flyId); 
	}
	
	public void putOutcome(HttpServletRequest request) {
		try {
			String flyId      = request.getParameter("flight_state_operation");
			String flyOutcome = request.getParameter("flight_state_outcome"); 
			FlightOutcomeState outcome = FlightOutcomeState.valueOf(flyOutcome);
			if(flyId==null)   throw new NullPointerException();
			if(outcome==null) throw new NullPointerException();
			dao.putOutcomeState(flyId, outcome);
		}catch(Exception ex) {
			return;
		}
	}
	
	enum DatetimeType {NONE, DAY, TIME, DETAIL}
}
