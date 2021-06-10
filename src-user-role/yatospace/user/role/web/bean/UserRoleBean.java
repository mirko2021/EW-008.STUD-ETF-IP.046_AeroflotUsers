package yatospace.user.role.web.bean;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;
import yatospace.user.role.dao.UserRoleDao;
import yatospace.user.role.dto.UserRoleDto;
import yatospace.user.role.web.controller.UserRoleEnviroment;
import yatospace.user.role.web.db.DBRoleDAOListener;

/**
 * Зрно које се односи на корисничке улоге. 
 * @author VM
 * @version 1.0
 */
public class UserRoleBean implements Serializable{
	private static final long serialVersionUID = 8786888314407199266L;
	private transient UserRoleDao roleDAO = DBRoleDAOListener.getUserRoleDAO(); 
	private boolean user; 
	private boolean worker; 
	private boolean administrator;
	
	
	private boolean initialized; 
	private LoginBean loginBean; 
	
	public void reset() {
		user = false; 
		worker = false; 
		administrator = false; 
		initialized = false; 
		loginBean = null;
	}
	
	public void initialize(HttpServletRequest request) {
		LoginBean bean = SessionBeansGenerator.loginBean(request.getSession()); 
		if(bean.getUsername()==null) return; 
		if(bean.getUsername().length()==0) return;
		if(initialized) return;
		this.loginBean = bean;
		String username = loginBean.getUsername(); 
		
		UserRoleDto userRule = roleDAO.getUserRole(username); 
		UserRoleDto workerRule = roleDAO.getWorkerRole(username); 
		UserRoleDto administratorRule = roleDAO.getAdministrationRole(username); 
	
		
		if(userRule==null) { 
			userRule = new UserRoleDto();
			userRule.setUsername(username);
			userRule.setApplication("aeroflot_users");
			userRule.setKey("user");
			userRule.setValue("false");
			if(username!=null && username.trim().length()!=0)
				roleDAO.insert(userRule);
		}
		if(workerRule==null) {
			workerRule = new UserRoleDto();
			workerRule.setUsername(username);
			workerRule.setApplication("aeroflot_users");
			workerRule.setKey("worker");
			workerRule.setValue("false");
			if(username!=null && username.trim().length()!=0)
				roleDAO.insert(workerRule);
		}
		if(administratorRule==null) {
			administratorRule = new UserRoleDto();
			administratorRule.setUsername(username);
			administratorRule.setApplication("aeroflot_users");
			administratorRule.setKey("administrator");
			administratorRule.setValue("false");
			if(username!=null && username.trim().length()!=0)
				roleDAO.insert(administratorRule);
		}
		
		UserRoleEnviroment userRE = new UserRoleEnviroment(userRule);
		UserRoleEnviroment workerRE = new UserRoleEnviroment(workerRule);
		UserRoleEnviroment administratorRE = new UserRoleEnviroment(administratorRule);
		
		user = userRE.isUser();
		worker = workerRE.isWorker();
		administrator = administratorRE.isAdministrator();
		
		this.initialized = true;
	}
	
	public boolean isUser() {
		return user;
	}

	public void setUser(boolean user) {
		this.user = user;
	}

	public boolean isWorker() {
		return worker;
	}

	public void setWorker(boolean worker) {
		this.worker = worker;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public UserRoleDao getRoleDAO() {
		return roleDAO;
	}
	
	public void accept() {
		UserRoleDto userRule = new UserRoleDto();
		UserRoleDto workerRule = new UserRoleDto();
		UserRoleDto administratorRule = new UserRoleDto();
		
		userRule.setApplication("aeroflot_users");
		workerRule.setApplication("aeroflot_users");
		administratorRule.setApplication("aeroflot_users");
		
		userRule.setKey("user");
		workerRule.setKey("worker");
		administratorRule.setKey("administrator");
		
		userRule.setValue(Boolean.toString(user));
		workerRule.setValue(Boolean.toString(worker));
		administratorRule.setValue(Boolean.toString(administrator));
		
		userRule.setUsername(loginBean.getUsername());
		workerRule.setUsername(loginBean.getUsername());
		administratorRule.setUsername(loginBean.getUsername());
		
		roleDAO.putUserRole(userRule);
		roleDAO.putWorkerRole(workerRule);
		roleDAO.putAdministrationRole(administratorRule);
	}
}
