package studiranje.ip.admin.jsf.bean;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import programiranje.ip.admin.model.User;
import yatospace.gui.program.shell.AppLogicCenter;
import yatospace.session.bean.BaseBean;
import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;
import yatospace.user.controller.UserCredentialsController;
import yatospace.user.database.data.MySQLUserRegisterDTO;
import yatospace.user.role.dao.UserDataDao;
import yatospace.user.role.dao.UserRoleDao;
import yatospace.user.role.dto.UserDataDto;
import yatospace.user.role.dto.UserRoleDto;
import yatospace.user.role.web.db.DBRoleDAOListener;
import yatospace.user.session.model.Session;
import yatospace.user.util.Page;
import yatospace.user.util.UserCredentials;
import yatospace.web.gui.bean.MessageBean;
import yatospace.web.gui.lang.MessageType;
import yatospace.web.gui.util.DesignBeansGenerator;

/**
 * Зрно за администрирање обичних корисника. 
 * @author VM
 * @version 1.0
 */
@ManagedBean(name = "userAdminBean", eager = true)
@SessionScoped
public class UserBean implements Serializable{
	private static final long serialVersionUID = -2493970362663117774L;
	
	private String firstName  = "";
	private String secondName = "";
	private String userName = "";
	private String userNotes = "";
	private String userPassword = ""; 
	private User current;
	private HttpSession session;
	
	public void initializeSession(HttpSession session) {
		if(this.session!=null) return; 
		this.session = session; 
	}
	
	public HttpSession getSession() {
		return session;
	}

	public void setFirstName(String firstName) {
		if(firstName==null) firstName = ""; 
		this.firstName = firstName;
	}
	public void setSecondName(String secondName) {
		if(secondName==null) secondName = ""; 
		this.secondName = secondName;
	}
	public void setUserName(String userName) {
		if(userName==null) userName = ""; 
		this.userName = userName;
	}
	public void setUserNotes(String userNotes) {
		if(userNotes==null) userNotes = ""; 
		this.userNotes = userNotes;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserNotes() {
		return userNotes;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		if(userPassword==null) userPassword=""; 
		this.userPassword = userPassword;
	}
	
	public List<User> list(){
		try {
			ArrayList<User> workers = new ArrayList<>();
			Page page = new Page();
			page.setPageSize(AppLogicCenter.appLogicCenter.getEngine().getDataSource().count());
			for(var data : AppLogicCenter.appLogicCenter.getEngine().getDataSource().list(page)) {
				UserRoleDao roleDAO = DBRoleDAOListener.getUserRoleDAO();
				if(roleDAO.getUserRole(data.getCredentials().getUser().getUsername())==null) continue;
				if(!roleDAO.getUserRole(data.getCredentials().getUser().getUsername()).getValue().contentEquals("true"))
					continue; 
				User w = new User();
				UserDataDao dataDAO = DBRoleDAOListener.getUserDataDAO();
				UserDataDto dataDTO = dataDAO.get(data.getCredentials().getUser().getUsername()); 
				if(dataDTO==null) dataDTO = new UserDataDto();
				w.setFirstName(dataDTO.getFirstname());
				w.setSecondName(dataDTO.getSecondname());
				w.setPassword("");
				w.setUserNotes(dataDTO.getUsernote());
				w.setUserName(data.getCredentials().getUser().getUsername());
				workers.add(w);
			}
			return workers; 
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public String url(String text) {
		try {
			return URLEncoder.encode(text, "UTF-8"); 
		}catch(Exception ex) {
			return "";
		}
	}
	
	public User current() {
		return current; 
	}
	
	public boolean isSelected() {
		return current!=null;
	}
	
	public void set(String username) {
		try {
			if(username==null) return; 
			UserDataDao dataDAO = DBRoleDAOListener.getUserDataDAO();
			UserDataDto dataDTO = dataDAO.get(username);
			if(dataDTO==null) dataDTO = new UserDataDto();
			userName   = username; 
			firstName  = dataDTO.getFirstname(); 
			secondName = dataDTO.getSecondname();
			userNotes  = dataDTO.getUsernote(); 
			userPassword = ""; 
		}catch(Exception ex) {
			reset();
		}
	}
	
	public void reset() {
		firstName  = ""; 
		secondName = ""; 
		userName = ""; 
		userNotes = "";
		userPassword = ""; 
		current = null;
	}
	
	public void select(String username) {
		try {
			if(username==null) return; 
			UserDataDao dataDAO = DBRoleDAOListener.getUserDataDAO();
			UserDataDto dataDTO = dataDAO.get(username);
			if(dataDTO==null) throw new RuntimeException();
			User user = new User();
			user.setUserName(dataDTO.getUsername()); 
			user.setFirstName(dataDTO.getFirstname()); 
			user.setSecondName(dataDTO.getSecondname());
			user.setUserNotes(dataDTO.getUsernote());
			user.setPassword(userPassword);
			current = user; 
		}catch(Exception ex) {
			reset();
		}
	}
	
	public void select() {
		select(userName);
	}
	
	public void unselect() {
		current = null;
	}
	
	
	public User getUser() {
		if(userName.trim().length()==0) return null; 
		User worker = new User();
		worker.setFirstName(firstName);
		worker.setSecondName(secondName);
		worker.setUserName(userName);
		worker.setPassword(userPassword);
		worker.setUserNotes(userNotes);
		return worker;
	}
	
	public void add() {
		try {
			if(getUser()==null) return;
			var w = getUser();
			if(AppLogicCenter.appLogicCenter.getEngine().getDataSource().get(w.getUserName())!=null) return; 			
			MySQLUserRegisterDTO dto = new MySQLUserRegisterDTO();
			UserCredentials userCredentials = new UserCredentials();
			userCredentials.setPasswordPlain(userPassword);
			var user = new  yatospace.user.object.User();
			user.setUsername(userName);
			userCredentials.setUser(user); 
			dto.setCredentials(userCredentials);
			UserDataDao dataDAO = DBRoleDAOListener.getUserDataDAO();
			UserRoleDao roleDAO = DBRoleDAOListener.getUserRoleDAO();
			if(dataDAO==null) return;
			if(roleDAO==null) return;
			UserCredentialsController ucc = new UserCredentialsController(); 
			String testPassword = ucc.setGoodPassword(userPassword); 
			String testUsername = ucc.setGoodUsername(userName); 
			if(testPassword==null || testPassword.trim().length()==0) return;
			if(testUsername==null || testUsername.trim().length()==0) return; 
			if(ucc.setGoodUsername(userName)==null)     return; 
			if(!AppLogicCenter.appLogicCenter.getController().register(dto.getCredentials().getUser().getUsername(), userPassword)) return;
			UserDataDto dDto = new UserDataDto();
			dDto.setUsername(userName);
			dDto.setFirstname(firstName);
			dDto.setSecondname(secondName);
			dDto.setUsernote(userNotes);
			dataDAO.insert(dDto);
			dataDAO.bind(userName);
			UserRoleDto rDto = new UserRoleDto();
			rDto.setApplication("aeroflot_users");
			rDto.setKey("worker");
			rDto.setValue("false");
			rDto.setUsername(userName);
			roleDAO.insert(rDto);
			rDto.setKey("administrator");
			rDto.setValue("false");
			roleDAO.insert(rDto);
			rDto.setKey("user");
			rDto.setValue("true");
			roleDAO.insert(rDto);
			userPassword = "";
		}catch(Exception ex) {
			return; 
		}
	}
	
	public void remove() {
		if(getUser()==null) return;
		try {
			LoginBean sessionState = SessionBeansGenerator.loginBean(session); 
			UserRoleDao roleDAO = DBRoleDAOListener.getUserRoleDAO();
			UserRoleDto roleDTO = roleDAO.getUserRole(userName); 
			if(roleDTO == null) return; 			
			if(!roleDTO.getValue().contentEquals("true")) return; 
			roleDAO.deleteRole(userName);
			String sessionId = sessionState.getSessionId();
			if(sessionState.getUsername().contentEquals(userName)) {
				BaseBean sessionBean = SessionBeansGenerator.baseBean(session);
				sessionBean.getLoginBean().logoutAll();
			}
			sessionState.setSessionId(sessionId);
			AppLogicCenter.appLogicCenter.getEngine().getDataSource().remove(userName);
			reset();
		}catch(Exception ex) {
			return;
		}
	}
	
	public void revokeRole() {
		try {
			UserRoleDao roleDAO = DBRoleDAOListener.getUserRoleDAO();
			if(roleDAO!=null) {
				UserRoleDto dto = new UserRoleDto();
				dto.setApplication("aeroflot_users");
				dto.setKey("user");
				dto.setValue("false");
				dto.setUsername(userName);
				roleDAO.putUserRole(dto);
			}
			MessageBean messageBean = DesignBeansGenerator.messageBean(session); 
			messageBean.setType(MessageType.SUCCESS);
			messageBean.setException(null);
			messageBean.setMessage("Повлачење привилегије корисника за "+userName+" је успјешно.");
		}catch(Exception ex) {
			MessageBean messageBean = DesignBeansGenerator.messageBean(session); 
			messageBean.setType(MessageType.ERROR);
			messageBean.setException(ex);
			messageBean.setMessage("Повлачење привилегије корисника за "+userName+" није успјешно.");
		}
	}
	
	public void updateData() {
		try {
			User w = getUser();
			if(w==null) throw new RuntimeException("User not found.");
			if(AppLogicCenter.appLogicCenter.getEngine().getDataSource().get(w.getUserName())==null)
				throw new RuntimeException("User not found."); 
			UserRoleDao workerDAO = DBRoleDAOListener.getUserRoleDAO();
			UserRoleDto workerDTO = workerDAO.getWorkerRole(w.getUserName()); 
			if(workerDTO==null) throw new RuntimeException("No user privileges.");
			if(!workerDTO.getValue().contentEquals("true")) throw new RuntimeException("No worker privileges.");
			
			UserDataDao wInfoDao = DBRoleDAOListener.getUserDataDAO(); 
			UserDataDto wInfoDto = new UserDataDto(); 
			wInfoDto.setUsername(w.getUserName());
			wInfoDto.setFirstname(w.getFirstName());
			wInfoDto.setSecondname(w.getSecondName());
			wInfoDto.setUsernote(w.getUserNotes());
			wInfoDao.put(wInfoDto);
			
			current = null;
			MessageBean messageBean = DesignBeansGenerator.messageBean(session); 
			messageBean.setType(MessageType.SUCCESS);
			messageBean.setException(null);
			messageBean.setMessage("Измјена основних података за корисника "+userName+" је успјешна.");
		}catch(Exception ex) {
			MessageBean messageBean = DesignBeansGenerator.messageBean(session); 
			messageBean.setType(MessageType.ERROR);
			messageBean.setException(ex);
			messageBean.setMessage("Измјена основних података за корисника "+userName+" није успјешна.");
		}
	}
		
	public void updateUsername() {
		try {
			User w = current;
			if(w==null) throw new RuntimeException("Worker not found.");
			if(AppLogicCenter.appLogicCenter.getEngine().getDataSource().get(w.getUserName())==null)
				throw new RuntimeException("Worker not found."); 
			UserRoleDao workerDAO = DBRoleDAOListener.getUserRoleDAO();
			UserRoleDto workerDTO = workerDAO.getWorkerRole(w.getUserName());
			if(workerDTO==null) throw new RuntimeException("No worker privileges.");
			if(!workerDTO.getValue().contentEquals("true")) throw new RuntimeException("No worker privileges.");
			
			UserCredentialsController ucc = new UserCredentialsController();
			User neo = getUser();
			
			if(neo==null) throw new RuntimeException("New user data not found");
			if(!neo.getUserName().contentEquals(w.getUserName())) {
				if(AppLogicCenter.appLogicCenter.getEngine().getDataSource().get(neo.getUserName())!=null)
					throw new RuntimeException("New user username already exists. Duplication is error.");
				
				String testUsername = ucc.setGoodUsername(neo.getUserName());
				if(testUsername==null) throw new RuntimeException("New user. Username not good.");
				if(testUsername.trim().length()==0) throw new RuntimeException("New user. Username not good.");
				
				try {
					BaseBean baseBean = SessionBeansGenerator.baseBean(session); 
					LoginBean sessionState = SessionBeansGenerator.loginBean(session); 
					List<String> sessionList = new ArrayList<>();
					if(w.getUserName().contentEquals(sessionState.getUsername())) sessionState.logoutAll(); 
					for(Session s: baseBean.getSessionListBean().listForUser(w.getUserName())) 
						sessionList.add(s.getSessionId()); 
					sessionState.logout(sessionList);
					
					if(!AppLogicCenter.appLogicCenter.getController().getDataSource().updateUsername(w.getUserName(), neo.getUserName())) 
						throw new RuntimeException();
				}catch(Exception ex) {
					throw new RuntimeException("User update username not successfull."); 
				}
			}


			current = null;
			
			MessageBean messageBean = DesignBeansGenerator.messageBean(session); 
			messageBean.setType(MessageType.SUCCESS);
			messageBean.setException(null);
			messageBean.setMessage("Измјена корисничког имена за корисника "+userName+" је успјешна.");
		}catch(Exception ex) {
			MessageBean messageBean = DesignBeansGenerator.messageBean(session); 
			messageBean.setType(MessageType.ERROR);
			messageBean.setException(ex);
			messageBean.setMessage("Измјена корисничког имена за корисника "+userName+" није успјешна.");
		}
	}
	
	public void updatePasswordUser() {
		try {
			User w = current;
			if(w==null) throw new RuntimeException("User not found.");
			if(AppLogicCenter.appLogicCenter.getEngine().getDataSource().get(w.getUserName())==null)
				throw new RuntimeException("User not found."); 
			UserRoleDao workerDAO = DBRoleDAOListener.getUserRoleDAO();
			UserRoleDto workerDTO = workerDAO.getUserRole(w.getUserName()); 
			if(workerDTO==null) throw new RuntimeException("No user privileges.");
			if(!workerDTO.getValue().contentEquals("true")) throw new RuntimeException("No worker privileges.");
			UserCredentialsController ucc = new UserCredentialsController();
			User neo = getUser();  
			if(neo==null) throw new RuntimeException("User not found");
			if(!neo.getUserName().contentEquals(w.getUserName())) throw new RuntimeException("Update workers username not synchronized.");
			
			String testPassword = ucc.setGoodPassword(w.getPassword());
			if(testPassword==null) throw new RuntimeException("User. Password not good.");
			if(testPassword.trim().length()==0) throw new RuntimeException("User. Password not good.");
			
			try {
				if(!AppLogicCenter.appLogicCenter.getController().updatePassword(w.getUserName(), w.getPassword(), neo.getPassword()))
					throw new RuntimeException();
			}catch(Exception ex) {
				throw new RuntimeException("User current password not correct."); 
			}
			
			current = null;
			
			MessageBean messageBean = DesignBeansGenerator.messageBean(session); 
			messageBean.setType(MessageType.SUCCESS);
			messageBean.setException(null);
			messageBean.setMessage("Измјена лозинке за корисника "+userName+" је успјешна.");
		}catch(Exception ex) {
			MessageBean messageBean = DesignBeansGenerator.messageBean(session); 
			messageBean.setType(MessageType.ERROR);
			messageBean.setException(ex);
			messageBean.setMessage("Измјена лозинке за корисника "+userName+" није успјешна.");
		}
	}
	
	public void updatePassword() {
		try {
			User w = getUser();
			if(w==null) throw new RuntimeException("User not found.");
			if(AppLogicCenter.appLogicCenter.getEngine().getDataSource().get(w.getUserName())==null)
				throw new RuntimeException("User not found."); 
			UserRoleDao workerDAO = DBRoleDAOListener.getUserRoleDAO();
			UserRoleDto workerDTO = workerDAO.getWorkerRole(w.getUserName()); 
			if(workerDTO==null) throw new RuntimeException("No user privileges.");
			if(!workerDTO.getValue().contentEquals("true")) throw new RuntimeException("No worker privileges.");
			UserCredentialsController ucc = new UserCredentialsController();
			
			String testPassword = ucc.setGoodPassword(w.getPassword());
			if(testPassword==null) throw new RuntimeException("User. Password not good.");
			if(testPassword.trim().length()==0) throw new RuntimeException("User. Password not good.");
			
			try {
				if(!AppLogicCenter.appLogicCenter.getController().getDataSource().updatePassword(w.getUserName(), w.getPassword()))
					throw new RuntimeException();
			}catch(Exception ex) {
				throw new RuntimeException("User current password not correct."); 
			}
			
			current = null;
			
			MessageBean messageBean = DesignBeansGenerator.messageBean(session); 
			messageBean.setType(MessageType.SUCCESS);
			messageBean.setException(null);
			messageBean.setMessage("Измјена лозинке за корисника "+userName+" је успјешна.");
		}catch(Exception ex) {
			MessageBean messageBean = DesignBeansGenerator.messageBean(session); 
			messageBean.setType(MessageType.ERROR);
			messageBean.setException(ex);
			messageBean.setMessage("Измјена лозинке за корисника "+userName+" није успјешна.");
		}
	}
}
