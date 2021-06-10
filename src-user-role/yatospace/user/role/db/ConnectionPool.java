package yatospace.user.role.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Конекциона пула за MySQL елементе. 
 * @author VM
 * @version 1.0
 */
public class ConnectionPool {
	  public static final String DATABASE = "yatospace_db"; 
	  public static final String HOST = "localhost"; 
	  public static final String PORT = "3306"; 
	  public static final String USER = "root";
	  public static final String PASSWORD = "root"; 
	  public static final String DRIVER = "com.mysql.cj.jdbc.Driver"; 
	  public static final int PRECONNECT_COUNT = 0;
	  public static final int MAX_IDLE_CONNECTIONS = 10; 
	  public static final int MAX_CONNECTIONS = 10; 
	  
	  public static final boolean  ERROR_REMIX = true;
	  private static ConnectionPool connectionPool;

	  public static ConnectionPool get() {
		  if(connectionPool == null) {
			  try {
				    Class.forName(DRIVER);
	    		    String jdbcURL = "jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE+"?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&characterSetResults=utf8&connectionCollation=utf8_general_ci";
	    		    String username = USER;
	    		    String password = PASSWORD;
	    		    String database = DATABASE; 
	    		    connectionPool = new ConnectionPool(); 
	    		    connectionPool.jdbcURL = jdbcURL; 
	    		    connectionPool.username = username; 
	    		    connectionPool.password = password;
	    		    connectionPool.database = database;
	    		    connectionPool.maxIdleConnections = MAX_IDLE_CONNECTIONS; 
	    		    connectionPool.maxConnections = MAX_CONNECTIONS; 
	    		    connectionPool.connectCount = PRECONNECT_COUNT;
	    	    } catch (Exception ex) {
	    	       if(ERROR_REMIX) ex.printStackTrace();
	    	    }
		  }
		  return connectionPool; 
	  }

	  public synchronized Connection checkOut()
	    throws SQLException {

	    Connection conn = null;
	    if (freeConnections.size() > 0) {
	      conn = (Connection)freeConnections.elementAt(0);
	      freeConnections.removeElementAt(0);
	      usedConnections.addElement(conn);
	    } else {
	      if (connectCount < maxConnections) {
	        conn = DriverManager.getConnection(
	          jdbcURL, username, password);
	        usedConnections.addElement(conn);
	        connectCount++;
	      } else {
	        try {
	          wait();
	          conn = (Connection)freeConnections.elementAt(0);
	          freeConnections.removeElementAt(0);
	          usedConnections.addElement(conn);
	        } catch (InterruptedException ex) {
	          if(ERROR_REMIX) ex.printStackTrace();
	        }
	      }
	    }
	    return conn;
	  }

	  public synchronized void checkIn(Connection aConn) {
	    if (aConn ==  null)
	      return;
	    if (usedConnections.removeElement(aConn)) {
	      freeConnections.addElement(aConn);
	      while (freeConnections.size() > maxIdleConnections) {
	        int lastOne = freeConnections.size() - 1;
	        Connection conn = (Connection)
	          freeConnections.elementAt(lastOne);
	        try { conn.close(); } catch (SQLException ex) { }
	        freeConnections.removeElementAt(lastOne);
	      }
	      notify();
	    }
	  }

	  private String jdbcURL;
	  private String username;
	  private String password;
	  private String database; 
	  private int preconnectCount;
	  private int connectCount;
	  private int maxIdleConnections;
	  private int maxConnections;
	  private Vector<Connection> usedConnections = new Vector<>();
	  private Vector<Connection> freeConnections = new Vector<>();


	  public String getJdbcURL() {
		 return jdbcURL;
	  }

	  public String getUsername() {
		  return username;
	  }

	  public String getPassword() {
		  return password;
	  }

	  public String getDatabase() {
		  return database;
	  }

	  public int getPreconnectCount() {
		  return preconnectCount;
	  }

	  public int getMaxIdleConnections() {
		  return maxIdleConnections;
	  }

	  public int getMaxConnections() {
		  return maxConnections;
	  }
}
