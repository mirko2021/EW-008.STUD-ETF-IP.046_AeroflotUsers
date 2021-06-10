package yatospace.reservation.state.model;

import java.io.Serializable;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import yatospace.reservation.state.util.ReservationState;

/**
 * Модел објекта информација/података о стањима ресервације. 
 * @author VM
 * @version 1.0
 */
public class ReservationStateModel implements Serializable, Cloneable, Comparable<ReservationStateModel>{
	private static final long serialVersionUID = -6048886031779845164L;
	private String reservationId = ""; 
	private boolean closed = false; 
	private String username = ""; 
	private Date serverDate = new Date();
	private ReservationState state = ReservationState.NOVO; 
	
	public String getReservationId() {
		return reservationId;
	}

	public boolean isClosed() {
		return closed;
	}

	public Date getServerDate() {
		return serverDate;
	}

	public void setReservationId(String reservationId) {
		if(reservationId==null) reservationId = ""; 
		this.reservationId = reservationId;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public void setServerDate(Date serverDate) {
		if(serverDate==null) return; 
		this.serverDate = serverDate;
	}

	public ReservationState getState() {
		return state;
	}

	public void setState(ReservationState state) {
		if(state==null) return;
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if(username==null) username = ""; 
		this.username = username;
	}

	@Override
	public String toString() {
		return reservationId; 
	}
	
	@Override
	public int hashCode() {
		return reservationId.hashCode(); 
	}
	
	@Override
	public ReservationStateModel clone() {
		ReservationStateModel reservationStateModel = new ReservationStateModel(); 
		reservationStateModel.setReservationId(reservationId);
		reservationStateModel.setClosed(closed);
		reservationStateModel.setServerDate(serverDate);
		reservationStateModel.setState(state);
		reservationStateModel.setUsername(username);
		return reservationStateModel; 
	}
	
	@Override
	public int compareTo(ReservationStateModel o) {
		return reservationId.compareTo(o.reservationId);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ReservationStateModel) {
			ReservationStateModel rsm = (ReservationStateModel) o; 
			return rsm.reservationId.contains(reservationId); 
		}
		return false; 
	}
	
	public String standardFormatedServerDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss"); 
		return sdf.format(serverDate); 
	}
	
	public String idEncodedURI() {
		try {
			return URLEncoder.encode(reservationId, "UTF-8");
		}catch(Exception ex) {
			return "";
		}
	}
}
