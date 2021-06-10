package programiranje.ip.admin.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import programiranje.ip.admin.database.YatospaceDBConnectionPool;

/**
 * Адаптер који се односи на локације базе података. 
 * @author VM
 * @version 1.0
 */
public class LocationBaseDAO {
	private YatospaceDBConnectionPool connectionPool; 

	public LocationBaseDAO(YatospaceDBConnectionPool pool) {
		if(pool==null) throw new RuntimeException();
		this.connectionPool = pool; 
	}

	public YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	}
	
	public void insert(LocationBaseDTO dto) {
		try {
			String sql = ""; 
			Connection connection = connectionPool.checkOut(); 
			
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/programiranje/ip/admin/sql/location_insert.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, dto.getLocationName());
				statement.setString(2, dto.getLocationAddress());
				statement.setString(3, dto.getLocationNote());
				statement.execute();
			}finally {
				connectionPool.checkIn(connection);
			}
			
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void update(String oldName, LocationBaseDTO dto) {
		try {
			String sql = ""; 
			Connection connection = connectionPool.checkOut(); 
			
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/programiranje/ip/admin/sql/location_update.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, dto.getLocationName());
				statement.setString(2, dto.getLocationAddress());
				statement.setString(3, dto.getLocationNote());
				statement.setString(4, oldName);
				statement.execute();
			}finally {
				connectionPool.checkIn(connection);
			}
			
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void delete(String locationName) {
		try {
			String sql = ""; 
			Connection connection = connectionPool.checkOut(); 
			
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/programiranje/ip/admin/sql/location_delete.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, locationName);
				statement.execute();
			}finally {
				connectionPool.checkIn(connection);
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public LocationBaseDTO get(String location) {
		try {
			String sql = ""; 
			Connection connection = connectionPool.checkOut(); 
			
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/programiranje/ip/admin/sql/location_get.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			LocationBaseDTO result = null;
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, location);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						String locationId      = rs.getString("location_id");
						String locationName    = rs.getString("location_name");
						String locationAddress = rs.getString("location_address"); 
						String locationNote    = rs.getString("location_note"); 
						result = new LocationBaseDTO();
						result.setLocationAddress(locationAddress);
						result.setLocationName(locationName);
						result.setLocationId(locationId);
						result.setLocationNote(locationNote);
					}
				}
				return result; 
			}finally {
				connectionPool.checkIn(connection);
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public List<LocationBaseDTO> list(){
		try {
			String sql = ""; 
			Connection connection = connectionPool.checkOut();
			
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/programiranje/ip/admin/sql/location_list.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			List<LocationBaseDTO> result = new ArrayList<>();
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						String locationId      = rs.getString("location_id");
						String locationName    = rs.getString("location_name");
						String locationAddress = rs.getString("location_address"); 
						String locationNote    = rs.getString("location_note"); 
						LocationBaseDTO res = new LocationBaseDTO();
						res.setLocationAddress(locationAddress);
						res.setLocationName(locationName);
						res.setLocationId(locationId);
						res.setLocationNote(locationNote);
						result.add(res);
					}
				}
				return result;
			}finally {
				connectionPool.checkIn(connection);
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void put(LocationBaseDTO dto) {		
		if(get(dto.getLocationName())==null) {insert(dto);}
		else {update(dto.getLocationName(), dto);}
	}
	
	public List<LocationBaseDTO> list(String a2c){
		try {
			String sql = ""; 
			Connection connection = connectionPool.checkOut();
		
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/programiranje/ip/admin/sql/location_list_by_a2c.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			List<LocationBaseDTO> result = new ArrayList<>();
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, a2c);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						String locationId      = rs.getString("location_id");
						String locationName    = rs.getString("location_name");
						String locationAddress = rs.getString("location_address"); 
						String locationNote    = rs.getString("location_note");
						LocationBaseDTO res = new LocationBaseDTO();
						res.setLocationAddress(locationAddress);
						res.setLocationName(locationName);
						res.setLocationId(locationId);
						res.setLocationNote(locationNote);
						result.add(res);
					}
				}
				return result;
			}finally {
				connectionPool.checkIn(connection);
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
