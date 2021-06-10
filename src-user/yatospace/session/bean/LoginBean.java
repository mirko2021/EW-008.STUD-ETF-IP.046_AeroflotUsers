package yatospace.session.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import studiranje.ip.admin.jsf.bean.LocationBean;
import studiranje.ip.admin.jsf.bean.UserBean;
import studiranje.ip.admin.jsf.bean.WorkerBean;
import yatospace.gui.session.logic.AppControlCenter;
import yatospace.session.counter.listener.LoginCounterApplicationListener;
import yatospace.session.counter.storage.LoginCounterRepository;
import yatospace.user.role.dao.UserRoleDao;
import yatospace.user.role.dto.UserRoleDto;
import yatospace.user.role.web.db.DBRoleDAOListener;
import yatospace.user.session.model.Session;
import yatospace.web.ajax.anotation.AjaxAccessable;
import yatospace.web.ajax.element.AjaxRequestContext;
import yatospace.web.ajax.model.AjaxExecutable;

/**
 * Зрно које се користи при логовању корисника. 
 * @author MV
 * @version 2.2
 */
public class LoginBean implements Serializable, AjaxExecutable{
	private static final long serialVersionUID = 5001271650055526359L;
	private transient UserRoleDao roleDAO = DBRoleDAOListener.getUserRoleDAO();
	public static final boolean ERROR_REMIX = false; 
	
	
	private String username = "";
	private String sessionId = ""; 
	private int tryLogin = 0; 
	
	public int getTryLogin() {
		return tryLogin;
	}
	
	public String getUsername() {
		return username;
	}
	
	
	public void setUsername(String username) {
		if(username==null) username = ""; 
		this.username = username;
	}

	
	
	public String getSessionId() {
		return sessionId;
	}


	public void setSessionId(String sessionId) {
		if(sessionId==null) sessionId = ""; 
		this.sessionId = sessionId;
	}


	@Override
	public void importFrom(AjaxRequestContext request) {
		username = request.getRequest().get("username").getAsString(); 
		if(username==null) username = ""; 
	}

	@Override
	public void exportTo(AjaxRequestContext request) {
		request.getResponse().addProperty("username", username);
	}
	
	public void balanceLogged() {
		if(AppControlCenter.mainCenter.getController().getRegisterEngine().get(sessionId)==null) {
			username = ""; 
			sessionId = ""; 
		}
	}
	
	public boolean logged() { 
		balanceLogged(); 
		return username.trim().length()!=0 && AppControlCenter.mainCenter.getController().getRegisterEngine().get(sessionId)!=null; 
	}
	
	
	@AjaxAccessable("login")
	public void login(AjaxRequestContext request) {
		if(logged()) {request.getResponse().addProperty("success", false); return; }
		try {
			String username = request.getRequest().get("parameters").getAsJsonObject().get("username").getAsString(); 
			String password = request.getRequest().get("parameters").getAsJsonObject().get("password").getAsString();
			String sessionId = request.getRequest().get("parameters").getAsJsonObject().get("session_id").getAsString(); 
			
			if(AppControlCenter.mainCenter.getController().login(username, password, sessionId)) {
				this.username = username; 
				this.sessionId = sessionId;
				UserRoleDto adminDto = roleDAO.getAdministrationRole(username); 
				UserRoleDto userDto = roleDAO.getUserRole(username);
				UserRoleDto workerDto = roleDAO.getWorkerRole(username);
				
				String usertypeString = ""; 
				if(adminDto!=null && adminDto.getValue().contentEquals("true")) usertypeString += "administrator"; 
				if(userDto!=null && userDto.getValue().contentEquals("true")) usertypeString += usertypeString.length()==0?"user":"#user"; 
				if(workerDto!=null && workerDto.getValue().contentEquals("true")) usertypeString += usertypeString.length()==0?"worker":"#worker"; 
				LoginCounterRepository repository = LoginCounterApplicationListener.lcr;
				repository.insert(username, usertypeString);
				try {
				   HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				   LocationBean locationBean = (LocationBean) session.getAttribute("locationBean");
				   WorkerBean workerAdminBean = (WorkerBean) session.getAttribute("workerAdminBean");
				   UserBean userAdminBean = (UserBean) session.getAttribute("userAdminBean");
				   if(workerAdminBean!=null) workerAdminBean.reset();
				   if(userAdminBean!=null)   userAdminBean.reset();
				   if(locationBean!=null)    locationBean.resetBean();
				}catch(Exception ex) {
					if(ERROR_REMIX) ex.printStackTrace();
				}
				request.getResponse().addProperty("success", true);
			}
			else {
				this.username = "";
				this.sessionId = ""; 
				request.getResponse().addProperty("success", false);
				tryLogin++; 
			}
		}catch(Exception ex) {
			tryLogin++;
			request.getResponse().addProperty("success", false);
		}
	}
	
	@AjaxAccessable("logout")
	public void logout(AjaxRequestContext request) {
		if(logged()) AppControlCenter.mainCenter.getController().getRegisterEngine().logout(sessionId);
		request.getResponse().addProperty("success", true);
		sessionId = "";
		username = "";
	}
	
	
	
	public void resetTryLoginCount() {
		tryLogin = 0; 
	}
	
	private HashMap<String, Runnable> postLoginHeap = new HashMap<>();
	private HashMap<String, Runnable> postLogoutHeap = new HashMap<>();
	private HashMap<String, Runnable> postLogoutAllHeap = new HashMap<>(); 
	
	public void addPostLogin(String id, Runnable action) {
		if(id==null) return; 
		if(action==null) return; 
		postLoginHeap.put(id, action); 
	}
	
	public void addPostLogout(String id, Runnable action) {
		if(id==null) return; 
		if(action==null) return; 
		postLogoutHeap.put(id, action);
	}
	
	public void addPostLogoutAll(String id, Runnable action){
		if(id==null) return; 
		if(action==null) return; 
		postLogoutAllHeap.put(id, action);
	}
	
	public void removePostLogin(String id) {
		this.postLoginHeap.remove(id);
	}
	
	public void removePostLogout(String id) {
		this.postLogoutHeap.remove(id);
	}
	
	public void removePostLogoutAll(String id) {
		this.postLogoutAllHeap.remove(id);
	}
	
	public void clearPostLogin() {
		postLoginHeap.clear(); 
	}
	
	public void clearPostLogout() {
		postLogoutHeap.clear(); 
	}
	
	public void clearPostLogoutAll() {
		postLogoutAllHeap.clear(); 
	}
	
	public void runPostLogin() {
		for(Runnable rnb: postLoginHeap.values()) 
			rnb.run();
	}
	
	public void runPostLogout() {
		for(Runnable rnb: postLogoutHeap.values()) 
			rnb.run();
	}
	
	public void runPostLogoutAll() {
		for(Runnable rnb: postLogoutAllHeap.values())
			rnb.run(); 
	}
	
	public boolean login(String username, String password, String sessionId) {
		if(username==null) {tryLogin++; return false;}
		if(password==null) {tryLogin++; return false;}
		if(sessionId==null) {tryLogin++; return false;} 
		if(username.trim().length()==0) {tryLogin++; return false;} 
		if(password.trim().length()==0) {tryLogin++; return false;} 
		if(sessionId.trim().length()==0) {tryLogin++; return false;} 
		if(logged()) return false;
		try {
			var x = AppControlCenter.mainCenter.getController().getRegisterEngine().login(username, password, sessionId);
			if(x) {
				this.username = username; 
				this.sessionId = sessionId;
				UserRoleDto adminDto = roleDAO.getAdministrationRole(username); 
				UserRoleDto userDto = roleDAO.getUserRole(username);
				UserRoleDto workerDto = roleDAO.getWorkerRole(username); 
				String usertypeString = "";
				if(adminDto!=null && adminDto.getValue().contentEquals("true")) usertypeString += "administrator"; 
				if(userDto!=null && userDto.getValue().contentEquals("true")) usertypeString += usertypeString.length()==0?"user":"#user"; 
				if(workerDto!=null && workerDto.getValue().contentEquals("true")) usertypeString += usertypeString.length()==0?"worker":"#worker"; 
				try { 
				   FacesContext facesContext = FacesContext.getCurrentInstance();
				   HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
				   LocationBean locationBean = (LocationBean) session.getAttribute("locationBean");
				   WorkerBean workerAdminBean = (WorkerBean) session.getAttribute("workerAdminBean");
				   UserBean userAdminBean = (UserBean) session.getAttribute("userAdminBean");
				   if(workerAdminBean!=null)  workerAdminBean.reset();
				   if(userAdminBean!=null)    userAdminBean.reset();
				   if(locationBean!=null)     locationBean.resetBean(); 
				   runPostLogin(); 
				}catch(Exception ex) {
					if(ERROR_REMIX) ex.printStackTrace();
				}
				LoginCounterRepository repository = LoginCounterApplicationListener.lcr;
				repository.insert(username, usertypeString);
				return true; 
			}
			tryLogin++; 
			return false; 
		}catch(Exception ex) {
			tryLogin++;
			return false; 
		}
	}
	
	public void logout() {
		if(logged()) {
			AppControlCenter.mainCenter.getController().getRegisterEngine().logout(sessionId);
			this.username = ""; 
			this.sessionId = "";
			runPostLogout(); 
		}
	}
	
	public void logoutAll() {
		if(logged()) {
			AppControlCenter.mainCenter.getController().getRegisterEngine().logoutAll(username);
			this.username = ""; 
			this.sessionId = "";
			runPostLogoutAll();
		}
	}
	
	public void logout(List<String> sessionIds) {
		if(logged()) {
			for(String sessionId: sessionIds) {
				if(!sessionId.contentEquals(this.sessionId)) {
					Session session = AppControlCenter.mainCenter.getController().getRegisterEngine().get(sessionId); 
					if(!session.getUserId().contentEquals(this.username)) continue;
					AppControlCenter.mainCenter.getController().getRegisterEngine().logout(sessionId); 
				}
				else { 
					logout(); 
				}
			}
		}
	}
}
