package yatospace.user.role.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import yatospace.user.role.db.ConnectionPool;
import yatospace.user.role.dto.UserDataDto;

/**
 * Адаптер који се односи на корисничке податке. 
 * @author VM
 * @version 1.0
 */
public class UserDataDao {
	private ConnectionPool pool; 
	
	public UserDataDao(ConnectionPool pool) {
		if(pool==null) throw new RuntimeException();
		this.pool = pool; 
	}
	
	public ConnectionPool getPool() {
		return pool;
	}
	
	public UserDataDto get(String username) {
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/user/role/web/sql/user_data_get_for.sql"))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine()+"\n";
			}
			Connection conn = pool.checkOut();
			UserDataDto dto = null;
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				statement.setString(1, username);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						dto = new UserDataDto();
						dto.setUsername(username);
						dto.setFirstname(rs.getString("first_name"));
						dto.setSecondname(rs.getString("second_name"));
						dto.setUsernote(rs.getString("user_notes"));
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
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/user/role/web/sql/user_data_get_db_id_user.sql"))){
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
	
	
	public void bind(String username) {
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/user/role/web/sql/user_data_bind_to_user.sql"))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine()+"\n";
			}
			Connection conn = pool.checkOut();
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				String id = id(username);
				statement.setString(1, id);
				statement.setString(2, username);
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
	
	public void insert(UserDataDto dto) {
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/user/role/web/sql/user_data_insert.sql"))){
				while(scanner.hasNextLine())
					sql += scanner.nextLine()+"\n";
			}
			Connection conn = pool.checkOut();
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				String id = id(dto.getUsername());
				statement.setString(1, id);
				statement.setString(2, dto.getFirstname());
				statement.setString(3, dto.getSecondname());
				statement.setString(4, dto.getUsernote());
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
	
	public void update(UserDataDto dto) {
		try {
			String sql = ""; 
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/user/role/web/sql/user_data_update.sql"))){
				while(scanner.hasNextLine()) 
					sql += scanner.nextLine()+"\n";
			}
			Connection conn = pool.checkOut();
			try(PreparedStatement statement = conn.prepareStatement(sql)){
				String id = id(dto.getUsername());
				statement.setString(1, dto.getFirstname());
				statement.setString(2, dto.getSecondname());
				statement.setString(3, dto.getUsernote());
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
	
	public void put(UserDataDto dto) {
		if(get(dto.getUsername())==null) {insert(dto); bind(dto.getUsername());}
		else update(dto);
	}
}
