package yatospace.reservation.state.controller;

import yatospace.reservation.state.io.ReservationStateDAO;
import yatospace.reservation.state.model.ReservationStateModel;
import yatospace.reservation.state.object.ReservationStateDTO;
import yatospace.reservation.state.util.ReservationState;

/**
 * Контролер и функционалности које се односе 
 * на баратање стањем резервације. 
 * @author VM
 * @version 1.0
 */
public class ReservationStateController {
	private ReservationStateDAO reservationStateDAO;

	public ReservationStateController(ReservationStateDAO reservationStateDAO) {
		if(reservationStateDAO==null) throw new NullPointerException(); 
		this.reservationStateDAO = reservationStateDAO; 
	}
	
	public ReservationStateDAO getReservationStateDAO() {
		return reservationStateDAO;
	}
	
	public ReservationState state(String reservationId) {
		try {
			return reservationStateDAO.get(reservationId).getReservationState().getState();
		}catch(Exception ex) {
			return null;
		}
	}
	
	public void accept(String reservationId) {
		ReservationState state = state(reservationId);
		if(state==null) return; 
		if(state==ReservationState.ODBIJENO) return;
		if(state==ReservationState.PRIMLJENO) return;
		ReservationStateModel reservation = reservationStateDAO.get(reservationId).getReservationState(); 
		reservation.setState(ReservationState.PRIMLJENO);
		ReservationStateDTO dto = new ReservationStateDTO();
		dto.setReservationState(reservation);
		reservationStateDAO.update(dto);
	}
	
	public void forbide(String reservationId) {
		ReservationState state = state(reservationId);
		if(state==null) return;
		if(state==ReservationState.ODBIJENO) return; 
		ReservationStateModel reservation = reservationStateDAO.get(reservationId).getReservationState(); 
		reservation.setState(ReservationState.ODBIJENO);
		ReservationStateDTO dto = new ReservationStateDTO();
		dto.setReservationState(reservation);
		reservationStateDAO.update(dto);
	}
	
	public void rewind(String reservationId) {
		ReservationState state = state(reservationId);
		if(state!=ReservationState.PRIMLJENO) return;
		ReservationStateModel reservation = reservationStateDAO.get(reservationId).getReservationState(); 
		reservation.setState(ReservationState.NOVO);
		ReservationStateDTO dto = new ReservationStateDTO();
		dto.setReservationState(reservation);
		reservationStateDAO.update(dto);
	}
}
