package yatospace.reservation.state.object;

import java.io.Serializable;

import yatospace.reservation.state.model.ReservationStateModel;

/**
 * Транспортни објекат (према базама/бази) за податке и 
 * објекте за стања резервације. 
 * @author VM
 * @version 1.0
 */
public class ReservationStateDTO implements Serializable{
	private static final long serialVersionUID = -538901150951002905L;
	private ReservationStateModel reservationState;
	
	public ReservationStateModel getReservationState() {
		return reservationState;
	}
	public void setReservationState(ReservationStateModel reservationState) {
		this.reservationState = reservationState;
	}
	
}
