package yatospace.session.counter.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import yatospace.session.counter.storage.LoginCounterRepository;

/**
 * Ослушкивач и репозиторијум у који се чувају подаци о бројевима пријава. 
 * @author VM
 * @version 1.0
 */
@WebListener
public class LoginCounterApplicationListener implements ServletContextListener {
    public static final LoginCounterRepository lcr = new LoginCounterRepository();
	
	public LoginCounterApplicationListener() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    }
}
