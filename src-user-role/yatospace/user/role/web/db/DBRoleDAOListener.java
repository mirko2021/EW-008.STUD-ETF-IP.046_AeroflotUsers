package yatospace.user.role.web.db;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import yatospace.user.role.dao.UserDataDao;
import yatospace.user.role.dao.UserRoleDao;
import yatospace.user.role.db.ConnectionPool;

/**
 * Ослушкивач за формирање адаптера за адаптере и конекциону пулу 
 * при бази података кад су у питању улоге.
 * @author VM
 * @version 1.0
 */
@WebListener
public class DBRoleDAOListener implements ServletContextListener {
    private static ConnectionPool connectionPoolMySQL; 
    private static UserDataDao userDataDAO; 
    private static UserRoleDao userRoleDAO; 
	
	public DBRoleDAOListener() {}
    
    public void contextDestroyed(ServletContextEvent sce)  { 
        connectionPoolMySQL = null; 
        userDataDAO = null; 
        userRoleDAO = null;
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	connectionPoolMySQL = ConnectionPool.get();
    	userDataDAO = new UserDataDao(connectionPoolMySQL);
    	userRoleDAO = new UserRoleDao(connectionPoolMySQL); 
    }

	public static ConnectionPool getConnectionPoolMySQL() {
		return connectionPoolMySQL;
	}

	public static UserDataDao getUserDataDAO() {
		return userDataDAO;
	}

	public static UserRoleDao getUserRoleDAO() {
		return userRoleDAO;
	}
}
