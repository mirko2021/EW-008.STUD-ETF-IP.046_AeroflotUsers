package yatospace.common.user.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import programiranje.ip.admin.database.YatospaceDBConnectionPool;
import yatospace.common.user.model.UserLocation;
import yatospace.common.user.object.UserLocationDTO;

/**
 * Адаптер за примопредају података када су у питању локације. 
 * @author VM
 * @version 1.0
 */
public class UserLocationDAO {
	private YatospaceDBConnectionPool connectionPool;
	
	public UserLocationDAO(YatospaceDBConnectionPool connectionPool) {
		if(connectionPool==null) throw new RuntimeException();
		this.connectionPool = connectionPool;
	}

	public YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	}
	
	public void add(UserLocationDTO dto) {
		if(dto==null) return;
		if(dto.getLocation()==null) return;
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_location.add.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, dto.getLocation().getLocationId());
				statement.setString(2, null);
				statement.setString(3, dto.getLocation().getUsername());
				statement.setString(4, dto.getLocation().getCity());
				statement.setString(5, dto.getLocation().getCountry()); 
				statement.setString(6, dto.getLocation().getAddress());
				statement.execute(); 
			}finally {
				connectionPool.checkIn(conn); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public UserLocationDTO getByUsername(String username) {
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_location.get_by_username.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, username);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						UserLocationDTO dto = new UserLocationDTO();
						String userName = rs.getString("username"); 
						String locationId = rs.getString("location_id");
						String country = rs.getString("location_country");
						String note = rs.getString("location_note");
						String city = rs.getString("location_address");
						UserLocation location = new UserLocation();
						location.setAddress(note);
						location.setCity(city); 
						location.setCountry(country); 
						location.setUsername(userName); 
						location.setLocationId(locationId);
						dto.setLocation(location); 
						return dto; 
					}
				}
				return null;
			}finally {
				connectionPool.checkIn(conn); 
			}
		}catch(RuntimeException ex) { 
			return null; 
		}catch(Exception ex) { 
			return null; 
		}
	}
	
	public UserLocationDTO get(String locationId) {
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_location.get_by_id.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, locationId);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						UserLocationDTO dto = new UserLocationDTO();
						String userName = rs.getString("username"); 
						String locationID = rs.getString("location_id");
						String country = rs.getString("location_country");
						String note = rs.getString("location_note");
						String city = rs.getString("location_address");
						UserLocation location = new UserLocation();
						location.setAddress(note);
						location.setCity(city); 
						location.setCountry(country); 
						location.setUsername(userName); 
						location.setLocationId(locationID);
						dto.setLocation(location); 
						return dto; 
					}
					return null;
				}
			}finally {
				connectionPool.checkIn(conn); 
			}
		}catch(RuntimeException ex) {
			return null; 
		}catch(Exception ex) {
			return null; 
		}
	}
	
	public void remove(String locationId) {
		if(locationId==null) return; 
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_location.remove.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, locationId);
				statement.execute(); 
			}finally {
				connectionPool.checkIn(conn); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void reset(String username) {
		if(username==null) return; 
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_location.reset.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, username);
				statement.execute(); 
			}finally {
				connectionPool.checkIn(conn); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void set(String locationId, UserLocationDTO dto) {
		if(locationId==null) return; 
		if(dto==null) return; 
		if(dto.getLocation()==null) return; 
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_location.set.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, dto.getLocation().getLocationId());
				statement.setString(2, null);
				statement.setString(3, dto.getLocation().getUsername());
				statement.setString(4, dto.getLocation().getCity());
				statement.setString(5, dto.getLocation().getCountry());
				statement.setString(6, dto.getLocation().getAddress());
				statement.setString(7, dto.getLocation().getUsername());
				statement.execute(); 
			}finally {
				connectionPool.checkIn(conn); 
			}
		}catch(RuntimeException ex) {
			throw ex;
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
