package yatospace.reservation.state.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import programiranje.ip.admin.database.YatospaceDBConnectionPool;
import yatospace.reservation.state.model.ReservationStateModel;
import yatospace.reservation.state.object.ReservationStateDTO;
import yatospace.reservation.state.util.ReservationState;

/**
 * Адаптер са функционалностима чувања 
 * података и преузимања информације, 
 * када су у питању стања неке резервације. 
 * @author VM
 * @version 1.0
 */
public class ReservationStateDAO {
	private YatospaceDBConnectionPool connectionPool;

	public ReservationStateDAO(YatospaceDBConnectionPool connectionPool) {
		if(connectionPool==null) throw new NullPointerException();
		this.connectionPool = connectionPool; 
	}
	
	public YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	} 
	
	public List<ReservationStateDTO> list() {
		ArrayList<ReservationStateDTO> result = new ArrayList<>(); 
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/reservation/state/sql/reservation_state_list.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine() + "\n"; 
				}
			}
			
			Connection connection = connectionPool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				try(ResultSet resultSet = statement.executeQuery()) {
					while(resultSet.next()) {
						String reservationId    = resultSet.getString("reservation_id"); 
						String username         = resultSet.getString("username"); 
						Date dateReservation    = resultSet.getTimestamp("date_reservation");
						String reservationState = resultSet.getString("reservation_state");
						String reservationClosed = resultSet.getString("reservation_closed"); 
						boolean closed = reservationClosed.contentEquals("YES_CANCELED");
						ReservationStateModel reservation = new ReservationStateModel();
						reservation.setReservationId(reservationId); 
						reservation.setClosed(closed); 
						reservation.setServerDate(dateReservation); 
						reservation.setUsername(username);
						switch(reservationState.intern()) {
							case "NEW":
								reservation.setState(ReservationState.NOVO); 
								break;
							case "ACCEPT": 
								reservation.setState(ReservationState.PRIMLJENO); 
								break;
							case "FORBIDEN":
								reservation.setState(ReservationState.ODBIJENO);
								break;
						}
						ReservationStateDTO dto = new ReservationStateDTO();
						dto.setReservationState(reservation);
						result.add(dto);
					}
				}
			}finally{
				connectionPool.checkIn(connection);  
			}
			return result; 
		}catch(RuntimeException ex) { 
			return null; 
		}catch(Exception ex) {
			return null; 
		}
	}
	
	public ReservationStateDTO get(String reservationId) {
		if(reservationId==null) return null; 
		try {
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/reservation/state/sql/reservation_state_get.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine() + "\n"; 
				}
			}
			Connection connection = connectionPool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, reservationId); 
				try(ResultSet resultSet = statement.executeQuery()) {
					while(resultSet.next()) {
						String reservationID    = resultSet.getString("reservation_id"); 
						String username         = resultSet.getString("username"); 
						Date dateReservation    = resultSet.getTimestamp("date_reservation");
						String reservationState = resultSet.getString("reservation_state");
						String reservationClosed = resultSet.getString("reservation_closed"); 
						ReservationStateModel reservation = new ReservationStateModel();
						boolean closed = reservationClosed.contentEquals("YES_CANCELED");
						reservation.setReservationId(reservationID); 
						reservation.setClosed(closed); 
						reservation.setServerDate(dateReservation);
						reservation.setUsername(username);
						switch(reservationState.intern()) {
							case "NEW":
								reservation.setState(ReservationState.NOVO); 
								break;
							case "ACCEPT": 
								reservation.setState(ReservationState.PRIMLJENO); 
								break;
							case "FORBIDEN":
								reservation.setState(ReservationState.ODBIJENO);
								break;
						}
						ReservationStateDTO dto = new ReservationStateDTO();
						dto.setReservationState(reservation);
						return dto; 
					}
				}
			}finally{
				connectionPool.checkIn(connection);  
			}
			return null; 
		}catch(RuntimeException ex) {
			return null; 
		}catch(Exception ex) {
			return null; 
		}
	}
	
	public void update(ReservationStateDTO dto) {
		if(dto==null) return;
		if(dto.getReservationState()==null) return; 
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/reservation/state/sql/reservation_state_update.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine() + "\n"; 
				}
			}
			Connection connection = connectionPool.checkOut();
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setTimestamp(1, Timestamp.from(dto.getReservationState().getServerDate().toInstant())); 
				switch(dto.getReservationState().getState()) {
					case NOVO:
						statement.setString(2, "NEW"); 
						break;
					case PRIMLJENO: 
						statement.setString(2, "ACCEPT");
						break;
					case ODBIJENO:
						statement.setString(2, "FORBIDEN");
						break;
				}
				if(dto.getReservationState().isClosed())
					statement.setString(3, "YES_CANCELED");
				else
					statement.setString(3, "NO_CANCELED");
				statement.setString(4, dto.getReservationState().getReservationId());
				statement.execute();
			}finally{
				connectionPool.checkIn(connection);  
			}
		}catch(RuntimeException ex) {
			throw ex;
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}	
}
