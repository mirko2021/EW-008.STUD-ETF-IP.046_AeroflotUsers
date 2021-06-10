package yatospace.reservation.state.web.bean;

import java.io.Serializable;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import programiranje.ip.admin.listener.DatabaseListener;
import yatospace.reservation.state.controller.ReservationStateController;
import yatospace.reservation.state.io.ReservationStateDAO;
import yatospace.reservation.state.model.ReservationStateModel;
import yatospace.reservation.state.object.ReservationStateDTO;
import yatospace.reservation.state.util.ReservationState;

/**
 * Стање резервација.
 * @author VM
 * @version 1.0
 */
public class ReservationsStatesBean implements Serializable{
	private static final long serialVersionUID = -6367099921520728586L;
	
	private transient ReservationStateController reservationStateCTRL;

	public ReservationStateController getReservationStateCTRL() {
		return reservationStateCTRL;
	}
	
	public void initialize(HttpServletRequest request) {
		ReservationStateDAO dao = DatabaseListener.getReservationStateDAO(request.getSession()); 
		reservationStateCTRL = new ReservationStateController(dao);	
	}
	
	public List<ReservationStateModel> list(){
		try {
			ArrayList<ReservationStateModel> result = new ArrayList<>(); 
			for(ReservationStateDTO dto: reservationStateCTRL.getReservationStateDAO().list()) {
				ReservationStateModel reservationState = dto.getReservationState(); 
				result.add(reservationState);
			}
			return result; 
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public void accept(HttpServletRequest request) {
		try {
			String reservationId = URLDecoder.decode(request.getParameter("reservation_state_id"), "UTF-8"); 
			if(!isNew(reservationId)) return;
			reservationStateCTRL.accept(reservationId);
		}catch(Exception ex) {
			return;
		}
	}
	
	public void forbide(HttpServletRequest request) {
		try {
			String reservationId = URLDecoder.decode(request.getParameter("reservation_state_id"), "UTF-8"); 
			if(isCanceled(reservationId)) return; 
			reservationStateCTRL.forbide(reservationId);
		}catch(Exception ex) {
			return;
		}
	}
	
	public void rewind(HttpServletRequest request) {
		try {
			String reservationId = URLDecoder.decode(request.getParameter("reservation_state_id"), "UTF-8"); 
			if(!isAccept(reservationId)) return; 
			reservationStateCTRL.rewind(reservationId);
		}catch(Exception ex) {
			return; 
		}
	}
	
	public boolean isNew(String reservationId) {
		try {
			return reservationStateCTRL.getReservationStateDAO().get(reservationId).getReservationState().getState()==ReservationState.NOVO; 
		}catch(Exception ex) {
			return false;
		}
	}
	
	public boolean isAccept(String reservationId) {
		try {
			return reservationStateCTRL.getReservationStateDAO().get(reservationId).getReservationState().getState()==ReservationState.PRIMLJENO; 
		}catch(Exception ex) {
			return false;
		}
	}
	
	public boolean isCanceled(String reservationId) {
		try {
			return reservationStateCTRL.getReservationStateDAO().get(reservationId).getReservationState().getState()==ReservationState.ODBIJENO;
		}catch(Exception ex) {
			return false;
		}
	}
}
