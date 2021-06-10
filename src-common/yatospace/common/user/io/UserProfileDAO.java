package yatospace.common.user.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import programiranje.ip.admin.database.YatospaceDBConnectionPool;
import yatospace.common.user.model.UserProfileMode;
import yatospace.common.user.object.UserProfileDTO;

/**
 * Адаптер према бази података кад су у питању подаци 
 * о профилима за резервације које корисник кориснити.
 * @author VM
 * @version 1.0
 */
public class UserProfileDAO {
	private YatospaceDBConnectionPool connectionPool;

	public YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	}
	
	public UserProfileDAO(YatospaceDBConnectionPool connectionPool) {
		if(connectionPool==null) throw new RuntimeException();
		this.connectionPool = connectionPool; 
	}
	
	public void addTransport(UserProfileDTO dto) {
		if(dto==null) return;
		if(dto.getUserProfileMode()==null) return;
		dto.getUserProfileMode().setModeId(dto.getUserProfileMode().getUsername()+".PROFILE.TRANSPORT");
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_profile.insert.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, dto.getUserProfileMode().getModeId());
				statement.setString(2, dto.getUserProfileMode().isTransportModeEnabled()?"TRANSPORT":"");
				statement.setString(3, dto.getUserProfileMode().getUsername());
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
	
	public void addTravel(UserProfileDTO dto) {
		if(dto==null) return;
		if(dto.getUserProfileMode()==null) return;
		dto.getUserProfileMode().setModeId(dto.getUserProfileMode().getUsername()+".PROFILE.TRAVEL");
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_profile.insert.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, dto.getUserProfileMode().getModeId());
				statement.setString(2, dto.getUserProfileMode().isTransportModeEnabled()?"TRAVEL":"");
				statement.setString(3, dto.getUserProfileMode().getUsername());
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
	
	public void add(UserProfileDTO dto) {
		try {
			addTravel(dto);
			addTransport(dto);
		}catch(Exception ex) {
		}
	}
	
	public boolean isTransport(String username) {
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_profile.get_by_comb.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, "TRANSPORT");
				statement.setString(2, username);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						String type = rs.getString("nianse_type");
						if(type==null) continue;
						if(type.contentEquals("TRANSPORT")) return true; 
					}
				}
				return false; 
			}finally {
				connectionPool.checkIn(conn); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public boolean isTravel(String username) {
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_profile.get_by_comb.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, "TRAVEL");
				statement.setString(2, username);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						String type = rs.getString("nianse_type");
						if(type==null) continue;
						if(type.contentEquals("TRAVEL")) return true; 
					}
				}
				return false; 
			}finally {
				connectionPool.checkIn(conn); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	
	public UserProfileDTO getById(String id) {
		if(id==null) return null; 
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_profile.get_by_id.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			Connection conn = connectionPool.checkOut(); 
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, id);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						UserProfileDTO dto = new UserProfileDTO();
						UserProfileMode profile = new UserProfileMode();
						String type = rs.getString("nianse_type");
						String username = rs.getString("username");
						String nianseId = rs.getString("nianse_id");
						profile.setUsername(username);
						profile.setModeId(nianseId); 
						if(type!=null && type.contentEquals("TRANSPORT"))
							profile.setTransportModeEnabled(true);
						else
							profile.setTransportModeEnabled(false);
						
						if(type!=null && type.contentEquals("TRAVEL"))
							profile.setTravelModeEnables(true);
						else
							profile.setTravelModeEnables(false);
						dto.setUserProfileMode(profile); 
						return dto; 
					}
				}
				return null; 
			}finally {
				connectionPool.checkIn(conn); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public UserProfileDTO getByCombination(String username) { 
			if(username==null) return null; 
			String profileId = username+".PROFILE"; 
			String transportId = username+".PROFILE.TRANSPORT"; 
			String travelId = username+".PROFILE.TRAVEL";
			UserProfileDTO dto = new UserProfileDTO();
			UserProfileMode profile = new UserProfileMode();
			profile.setModeId(profileId);
			dto.setUserProfileMode(profile);
			if(getById(transportId)==null) return null;
			if(getById(travelId)==null) return null;
			profile.setTransportModeEnabled(isTransport(username));
			profile.setTravelModeEnables(isTravel(username));
			return dto;
	}
	
	public void delete(String username) {
		if(username == null) return; 
		
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_profile.delete.sql"))){
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
	
	public void updateTransport(UserProfileDTO dto) { 
		if(dto==null) return; 
		dto.getUserProfileMode().setModeId(dto.getUserProfileMode().getUsername()+".PROFILE.TRANSPORT");
		
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_profile.update.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			Connection conn = connectionPool.checkOut();
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, dto.getUserProfileMode().getModeId());
				statement.setString(2, dto.getUserProfileMode().isTransportModeEnabled()?"TRANSPORT":"");
				statement.setString(3, dto.getUserProfileMode().getUsername());
				statement.setString(4, dto.getUserProfileMode().getModeId()); 
				statement.execute(); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void updateTravel(UserProfileDTO dto) { 
		if(dto==null) return; 
		dto.getUserProfileMode().setModeId(dto.getUserProfileMode().getUsername()+".PROFILE.TRAVEL");
		
		try {
			String sql = ""; 
			try(Scanner scanner= new Scanner(getClass().getResourceAsStream("/yatospace/common/user/sql/user_profile.update.sql"))){
				while(scanner.hasNext()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			Connection conn = connectionPool.checkOut();
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, dto.getUserProfileMode().getModeId());
				statement.setString(2, dto.getUserProfileMode().isTravelModeEnables()?"TRAVEL":"");
				statement.setString(3, dto.getUserProfileMode().getUsername());
				statement.setString(4, dto.getUserProfileMode().getModeId()); 
				statement.execute(); 
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void update(UserProfileDTO dto) {
		updateTransport(dto);
		updateTravel(dto);
	}
}
