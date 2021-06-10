package yatospace.flag.web.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import programiranje.ip.admin.database.YatospaceDBConnectionPool;

/**
 * Адаптер који служи за повезивање локације и земље. 
 * @author VM
 * @version 1.0
 */
public class LocationCountryDAO {
	private YatospaceDBConnectionPool connectionPool; 
	
	public LocationCountryDAO(YatospaceDBConnectionPool connectionPool) {
		if(connectionPool==null) throw new NullPointerException();
		this.connectionPool = connectionPool; 
	}
	
	public YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	}


	public void link(String location, String a2c) {
		if(a2c==null) return;
		if(location==null) return;
		if(a2c.length()>2) a2c = a2c.substring(0,2);
		try {
			Connection connection = connectionPool.checkOut();
			String sql = "";
			try(Scanner scanner=new Scanner(getClass().getResourceAsStream("/yatospace/flag/web/sql/link.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine();
				}
			}finally {
				connectionPool.checkIn(connection);
			}
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, a2c);
				statement.setString(2, location);
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
	
	public void unlink(String location) {
		try {
			Connection connection = connectionPool.checkOut();
			String sql = "";
			try(Scanner scanner=new Scanner(getClass().getResourceAsStream("/yatospace/flag/web/sql/ulink.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine();
				}
			}
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, location);
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
	
	public String get(String location) {
		try {
			Connection connection = connectionPool.checkOut();
			String sql = ""; 
			try(Scanner scanner=new Scanner(getClass().getResourceAsStream("/yatospace/flag/web/sql/get.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine(); 
				}
			}
			String result = null; 
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, location);
				try(ResultSet resultSet = statement.executeQuery()){
					while(resultSet.next()) {
						String a2c = resultSet.getString(1);
						result = a2c;
					}
					return result; 
				}
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
