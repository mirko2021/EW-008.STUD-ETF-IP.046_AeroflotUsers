package yatospace.user.role.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import yatospace.user.role.db.ConnectionPool;
import yatospace.user.role.dto.UserRoleDto;

/**
 * Адаптер према правилима корисника.
 * @author VM
 * @version 1.0
 */
public class UserRoleDao {
	public static String APPLICATION_ID = "aeroflot_users"; 
	
	public static String USER_KEY          = "user"; 
	public static String WORKER_KEY        = "worker"; 
	public static String ADMINISTRATOR_KEY = "administrator"; 
	
	private ConnectionPool pool; 
	
	public UserRoleDao(ConnectionPool pool) {
		if(pool==null) throw new NullPointerException();
		this.pool = pool; 
	}
	
	public ConnectionPool getPool() {
		return pool;
	}
	
	public UserRoleDto getUserRole(String username) {
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/user/role/web/sql/user_role_get_for.sql"))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine()+"\n";
			}
			Connection conn = pool.checkOut();
			UserRoleDto dto = null;
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, username);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						dto = new UserRoleDto();
						dto.setUsername(username);
						dto.setApplication(rs.getString("application"));
						dto.setKey(rs.getString("key"));
						dto.setValue(rs.getString("value"));
						if(!dto.getApplication().contentEquals(APPLICATION_ID)) {
							dto = null;
							continue; 
						}
						if(!dto.getKey().contentEquals(USER_KEY)) {
							dto = null;
							continue; 
						}
						break;
					}
				}
				return dto;
			}catch(RuntimeException ex) {
				throw ex;
			}catch(Exception ex) {
				throw new RuntimeException(ex);
			}finally {
				pool.checkIn(conn);
			}
		}catch(RuntimeException ex) {
			throw ex;
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public UserRoleDto getWorkerRole(String username) {
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/user/role/web/sql/user_role_get_for.sql"))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine()+"\n";
			}
			Connection conn = pool.checkOut();
			UserRoleDto dto = null;
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, username);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						dto = new UserRoleDto();
						dto.setUsername(username);
						dto.setApplication(rs.getString("application"));
						dto.setKey(rs.getString("key"));
						dto.setValue(rs.getString("value"));
						if(!dto.getApplication().contentEquals(APPLICATION_ID)) {
							dto = null;
							continue; 
						}
						if(!dto.getKey().contentEquals(WORKER_KEY)) {
							dto = null;
							continue; 
						}
						break;
					}
				}
				return dto;
			}catch(RuntimeException ex) {
				throw ex;
			}catch(Exception ex) {
				throw new RuntimeException(ex);
			}finally {
				pool.checkIn(conn);
			}
		}catch(RuntimeException ex) {
			throw ex;
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public UserRoleDto getAdministrationRole(String username) {
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/user/role/web/sql/user_role_get_for.sql"))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine()+"\n";
			}
			Connection conn = pool.checkOut();
			UserRoleDto dto = null;
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, username);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						dto = new UserRoleDto();
						dto.setUsername(username);
						dto.setApplication(rs.getString("application"));
						dto.setKey(rs.getString("key"));
						dto.setValue(rs.getString("value"));
						if(!dto.getApplication().contentEquals(APPLICATION_ID)) {
							dto = null;
							continue; 
						}
						if(!dto.getKey().contentEquals(ADMINISTRATOR_KEY)) {
							dto = null;
							continue; 
						}
						break;
					}
				}
				return dto;
			}catch(RuntimeException ex) {
				throw ex;
			}catch(Exception ex) {
				throw new RuntimeException(ex);
			}finally {
				pool.checkIn(conn);
			}
		}catch(RuntimeException ex) {
			throw ex;
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public String id(String username) { 
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/user/role/web/sql/user_role_get_db_id_user.sql"))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine()+"\n";
			}
			Connection conn = pool.checkOut();
			String dto = null;
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, username);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) 
						dto = rs.getString("id_user");
				}
				return dto;
			}finally {
				pool.checkIn(conn);
			}
		}catch(RuntimeException ex) {
			throw ex;
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public void insert(UserRoleDto dto) {
		if(dto==null) return; 
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/user/role/web/sql/user_role_insert.sql"))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine()+"\n";
			}
			Connection conn = pool.checkOut();
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				String id = id(dto.getUsername());
				statement.setString(1, dto.getApplication());
				statement.setString(2, dto.getKey());
				statement.setString(3, dto.getValue());
				statement.setString(4, id);
				statement.execute();
			}finally {
				pool.checkIn(conn);
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
	}
	
	public void update(UserRoleDto dto) {
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/user/role/web/sql/user_role_update.sql"))){
				while(scanner.hasNextLine()) 
					sql += scanner.nextLine()+"\n";
			}
			Connection conn = pool.checkOut();
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				String id = id(dto.getUsername());
				statement.setString(1, dto.getApplication());
				statement.setString(2, dto.getKey());
				statement.setString(3, dto.getValue());
				statement.setString(4, id);
				statement.setString(5, dto.getKey());
				statement.execute();
			}finally {
				pool.checkIn(conn);
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
	}
	
	public void putUserRole(UserRoleDto dto) {
		if(getUserRole(dto.getUsername())==null) insert(dto);
		else update(dto);
	}
	
	public void putWorkerRole(UserRoleDto dto) {
		if(getWorkerRole(dto.getUsername())==null) insert(dto);
		else update(dto);
	}
	
	public void putAdministrationRole(UserRoleDto dto) {
		if(getAdministrationRole(dto.getUsername())==null) insert(dto);
		else update(dto);
	}
	
	
	public void deleteRole(String username) {
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/user/role/web/sql/user_role_delete.sql"))){
				while(scanner.hasNextLine()) 
					sql += scanner.nextLine()+"\n";
			}
			Connection conn = pool.checkOut();
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				String id = id(username);
				statement.setString(1, id);
				statement.execute();
			}finally {
				pool.checkIn(conn);
			}
		}catch(RuntimeException ex) {
			throw ex;
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
