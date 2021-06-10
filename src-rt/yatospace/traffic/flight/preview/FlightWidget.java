package yatospace.traffic.flight.preview;

import java.io.Serializable;

/**
 * Виџети који се односе на летове. 
 * @author VM
 * @version 1.0
 */
public class FlightWidget implements Serializable{
	private static final long serialVersionUID = 1264202925555807239L;
	private String flyId = ""; 
	private String flyDate = "";
	private String direction = ""; 
	private String destination = "";
	private String place = "";
	
	public String getFlyId() {
		return flyId;
	}
	public String getFlyDate() {
		return flyDate;
	}
	public String getDirection() {
		return direction;
	}
	public String getDestination() {
		return destination;
	}
	public String getPlace() {
		return place;
	}
	
	public void setFlyId(String flyId) {
		if(flyId==null) flyId = ""; 
		this.flyId = flyId;
	}
	public void setFlyDate(String flyDate) {
		if(flyDate==null) flyDate = "";
		this.flyDate = flyDate;
	}
	public void setDirection(String direction) {
		if(direction==null) direction = ""; 
		this.direction = direction;
	}
	public void setDestination(String destination) {
		if(destination==null) destination = ""; 
		this.destination = destination;
	}
	public void setPlace(String place) {
		if(place==null) place = "";
		this.place = place;
	}
}
