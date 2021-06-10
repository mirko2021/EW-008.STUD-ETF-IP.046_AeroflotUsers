package yatospace.worker.services.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import programiranje.ip.admin.database.YatospaceDBConnectionPool;
import yatospace.traffic.flight.state.FlightIncomeState;
import yatospace.traffic.flight.state.FlightOutcomeState;
import yatospace.worker.services.jpa.Flight;

/**
 * Адаптер према бази података који се односи на летове. 
 * @author VM
 * @version 1.0 
 */
public class FligthsDAO {
	private YatospaceDBConnectionPool pool;
	
	public FligthsDAO(YatospaceDBConnectionPool pool) {
		if(pool==null) throw new NullPointerException();
		this.pool = pool; 
	}

	public YatospaceDBConnectionPool getPool() {
		return pool;
	}
	
	public List<Flight> list(){
		try {
			Connection connection = pool.checkOut();
			ArrayList<Flight> flights = new ArrayList<>(); 
			
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/list_flight.sql"))){
				while(scanner.hasNext()) {
					sql+= scanner.nextLine();
				}
			}
			
			
			if(sql.trim().length()==0) return new ArrayList<>(); 
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						Flight flight = new Flight();
						flight.setFlightDate(rs.getString("fly_date")); 
						flight.setFlightId(rs.getString("fly_id"));
						flight.setRelation(rs.getString("relation")); 
						flights.add(flight); 
					}
				}
			}finally {
				pool.checkIn(connection);
			}
		
			return flights;
		}catch(RuntimeException ex) {
			ex.printStackTrace(); 
			return new ArrayList<>(); 
		}catch(Exception ex) {
			ex.printStackTrace(); 
			return new ArrayList<>();
		}
	}
	
	public Flight get(String fligthId) {
		try {
			Connection connection = pool.checkOut();
			
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/get_flight.sql"))){
				while(scanner.hasNext()) {
					sql+= scanner.nextLine();
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, fligthId);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						Flight flight = new Flight();
						flight.setFlightDate(rs.getString("fly_date")); 
						flight.setFlightId(rs.getString("fly_id"));
						flight.setRelation(rs.getString("relation"));
						return flight; 
					}
				}
			}finally {
				pool.checkIn(connection); 
			}
			
			return null; 
		}catch(Exception ex) {
			return null; 
		}
	}
	
	public void insert(Flight flight) {
		try {
			Connection connection = pool.checkOut();
			
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/insert_flight.sql"))){
				while(scanner.hasNext()) {
					sql+= scanner.nextLine();
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, flight.getFlightId());
				statement.setString(2, flight.getFlightDate());
				statement.setString(3, flight.getRelation());
				statement.execute(); 
			}finally {
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException();
		}
	}
	
	public void update(String oldId, Flight flight) {
		try {
			Connection connection = pool.checkOut();
			
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/update_flight.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine();
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, flight.getFlightId());
				statement.setString(2, flight.getFlightDate());
				statement.setString(3, flight.getRelation()); 
				statement.setString(4, oldId);
				statement.execute();
			}finally {
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException();
		}
	}
	
	public void delete(String flightId) {
		try {
			Connection connection = pool.checkOut();
			
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/remove_flight.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine();
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, flightId);
				statement.execute();
			}finally {
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException();
		}
	}
	
	public void put(Flight flight) {
		try {
			if(get(flight.getFlightId())==null) insert(flight);
			else update(flight.getFlightId(), flight);
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException();
		}
	}
	
	public FlightIncomeState getIncomeState(String flyId) {
		if(flyId==null) return null;
		try {			
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/flight_state_get_income_state.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine();
				}
			}
			
			Connection connection = pool.checkOut();
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, flyId); 
				try(ResultSet resultSet = statement.executeQuery()){
					while(resultSet.next()) {
						String result = resultSet.getString("income_state");
						switch(result.intern()) {
							case "SLETEO": 
								return FlightIncomeState.DOSAO;
							case "CEKA SE": 
								return FlightIncomeState.PUTUJE; 
							case "NEPOZNATO": 
								return FlightIncomeState.NEPOZNATO; 
						}
					}
				}
			}finally {
				pool.checkIn(connection); 
			}
			return null; 
		}catch(RuntimeException ex) { 
			return null; 
		}catch(Exception ex) { 
			return null; 
		}
	}
	
	public FlightOutcomeState getOutcomeState(String flyId) {
		if(flyId==null) return null;
		try {			
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/flight_state_get_outcome_state.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine();
				}
			}
		
			Connection connection = pool.checkOut();
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, flyId); 
				try(ResultSet resultSet = statement.executeQuery()){
					while(resultSet.next()) {
						String result = resultSet.getString("outcome_state");
						switch(result.intern()) {
							case "POLETEO": 
								return FlightOutcomeState.OTISAO;
							case "CEKA SE": 
								return FlightOutcomeState.CEKA_SE; 
							case "NEPOZNATO": 
								return FlightOutcomeState.NEPOZNATO; 
						}
					}
				}
			}finally {
				pool.checkIn(connection); 
			}
			return null; 
		}catch(RuntimeException ex) {
			return null; 
		}catch(Exception ex) {
			return null; 
		}
	}
	
	public void putIncomeState(String flyId, FlightIncomeState incomeState) {
		if(flyId==null)       return; 
		if(incomeState==null) return; 
		try {
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/flight_state_put_income_state.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine();
				}
			}
			Connection connection = pool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				switch(incomeState) {
					case DOSAO:
						statement.setString(1, "SLETEO"); 
						break;
					case PUTUJE:
						statement.setString(1, "CEKA SE");
						break;
					case NEPOZNATO: 
						statement.setString(1, "NEPOZNATO"); 
						break;
				}
				statement.setString(2, flyId);
				statement.execute(); 
			}finally {
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
	}
	
	public void putOutcomeState(String flyId, FlightOutcomeState outcomeState) {
		if(flyId==null)        return;
		if(outcomeState==null) return;
		try {
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/flight_state_put_outcome_state.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine();
				}
			}
			
			Connection connection = pool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				switch(outcomeState) {
					case OTISAO:
						statement.setString(1, "POLETEO"); 
						break;
					case CEKA_SE:
						statement.setString(1, "CEKA SE");
						break;
					case NEPOZNATO: 
						statement.setString(1, "NEPOZNATO"); 
						break;
				}
				statement.setString(2, flyId);
				statement.execute();
			}finally {
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
	}
	
	public void resetIncomeState(String flyId) {
		if(flyId==null)       return; 
		try {
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/flight_state_reset_income_state.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine();
				}
			}
			Connection connection = pool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, flyId);
				statement.execute(); 
			}finally {
				pool.checkIn(connection); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
	}
	
	public void resetOutcomeState(String flyId) {
		if(flyId==null)  return;
		try {
			String sql = "";
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/worker/services/sql/flight_state_put_outcome_state.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine();
				}
			}
			
			Connection connection = pool.checkOut(); 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, flyId);
				statement.execute(); 
			}finally {
				pool.checkIn(connection);
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
	}
}
