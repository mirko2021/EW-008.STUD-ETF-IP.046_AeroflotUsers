package yatospace.traffic.relation.io;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import programiranje.ip.admin.database.YatospaceDBConnectionPool;
import yatospace.traffic.relation.object.TrafficRelation;

/**
 * Општи адаптер за податке саобраћајних веза. 
 * @author VM
 * @version 1.0
 */
public class TrafficRelationsDAO {
	private YatospaceDBConnectionPool connectionPool; 

	public TrafficRelationsDAO(YatospaceDBConnectionPool pool) {
		if(pool==null) throw new RuntimeException();
		this.connectionPool = pool; 
	}

	public YatospaceDBConnectionPool getConnectionPool() {
		return connectionPool;
	}
	
	public List<TrafficRelationsDTO> list(String place){
		try {
			String sql = ""; 
			Connection connection = connectionPool.checkOut();
			
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/traffic/relation/sql/get_for_source_location.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			 
			List<TrafficRelationsDTO> result = new ArrayList<>();
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, place);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						String rtWord = rs.getString("rt_word");
						String rtPlace = rs.getString("place");
						String rtNumber = rs.getString("rt_number");
						String rtType = rs.getString("rt_type");
						String rtSubtype = rs.getString("rt_subtype");
						String relation = rs.getString("relation");
						String income = rs.getString("income");
						String outcome = rs.getString("outcome");
						String direction = rs.getString("direction"); 
						String location = rs.getString("location"); 
						String destination = rs.getString("destination");
						String rtNotes = rs.getString("rt_notes"); 
					    TrafficRelationsDTO dto = new TrafficRelationsDTO();
						TrafficRelation obj = new TrafficRelation();
						obj.setDestination(destination);
						obj.setDirection(direction);
						obj.setIncome(income);
						obj.setLocation(location);
						obj.setOutcome(outcome);
						obj.setPlace(rtPlace);
						obj.setRelation(relation);
						obj.setPlace(rtPlace);
						obj.setRtNumber(rtNumber);
						obj.setRtType(rtType);
						obj.setRtSubType(rtSubtype);
						obj.setRtWord(rtWord);
						obj.setRtNotes(rtNotes);
						dto.setObject(obj);
						result.add(dto);
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
	
	public List<String> select(){
		try {
			String sql = ""; 
			Connection connection = connectionPool.checkOut();
			
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/traffic/relation/sql/select.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			 
			List<String> result = new ArrayList<>();
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						String rtWord = rs.getString("rt_word");
						result.add(rtWord);
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
	
	public TrafficRelationsDTO get(String rtWordTarget){
		try {
			String sql = ""; 
			Connection connection = connectionPool.checkOut();
			
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/traffic/relation/sql/get.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			 
			TrafficRelationsDTO result = null;
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, rtWordTarget);
				try(ResultSet rs = statement.executeQuery()){
					while(rs.next()) {
						String rtWord = rs.getString("rt_word");
						String rtPlace = rs.getString("place");
						String rtNumber = rs.getString("rt_number");
						String rtType = rs.getString("rt_type");
						String rtSubtype = rs.getString("rt_subtype");
						String relation = rs.getString("relation");
						String income = rs.getString("income");
						String outcome = rs.getString("outcome");
						String direction = rs.getString("direction"); 
						String location = rs.getString("location"); 
						String destination = rs.getString("destination");
						String rtNotes = rs.getString("rt_notes"); 
					    TrafficRelationsDTO dto = new TrafficRelationsDTO();
						TrafficRelation obj = new TrafficRelation();
						obj.setDestination(destination);
						obj.setDirection(direction);
						obj.setIncome(income);
						obj.setLocation(location);
						obj.setOutcome(outcome);
						obj.setPlace(rtPlace);
						obj.setRelation(relation);
						obj.setPlace(rtPlace);
						obj.setRtNumber(rtNumber);
						obj.setRtType(rtType);
						obj.setRtSubType(rtSubtype);
						obj.setRtWord(rtWord);
						obj.setRtNotes(rtNotes);
						dto.setObject(obj);
						result = dto;
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
	
	public void update(TrafficRelationsDTO dto){
		try {
			String sql = ""; 
			Connection connection = connectionPool.checkOut();
			
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/traffic/relation/sql/update.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, dto.getObject().getRtWord());
				statement.setString(2, dto.getObject().getPlace());
				statement.setString(3, dto.getObject().getRtNumber());
				statement.setString(4, dto.getObject().getRtType());	
				statement.setString(5, dto.getObject().getRtSubType());
				statement.setString(6, dto.getObject().getRelation());
				statement.setString(7, dto.getObject().getIncome());
				statement.setString(8, dto.getObject().getOutcome());
				statement.setString(9, dto.getObject().getDirection());
				statement.setString(10, dto.getObject().getLocation());
				statement.setString(11, dto.getObject().getDestination());
				statement.setString(12, dto.getObject().getRtNotes());
				statement.setString(13, dto.getObject().getRtWord());
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
	
	public void erase(String rtWord){
		try {
			String sql = ""; 
			Connection connection = connectionPool.checkOut();
			
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/traffic/relation/sql/delete.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, rtWord);
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
	
	public void insert(TrafficRelationsDTO dto) {
		try {
			String sql = ""; 
			Connection connection = connectionPool.checkOut();
			
			try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/yatospace/traffic/relation/sql/insert.sql"))){
				while(scanner.hasNextLine()) {
					sql += scanner.nextLine()+"\n";
				}
			}
			
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, dto.getObject().getRtWord());
				statement.setString(2, dto.getObject().getPlace());
				statement.setString(3, dto.getObject().getRtNumber());
				statement.setString(4, dto.getObject().getRtType());	
				statement.setString(5, dto.getObject().getRtSubType());
				statement.setString(6, dto.getObject().getRelation());
				statement.setString(7, dto.getObject().getIncome());
				statement.setString(8, dto.getObject().getOutcome());
				statement.setString(9, dto.getObject().getDirection());
				statement.setString(10, dto.getObject().getLocation());
				statement.setString(11, dto.getObject().getDestination());
				statement.setString(12, dto.getObject().getRtNotes());
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
}
