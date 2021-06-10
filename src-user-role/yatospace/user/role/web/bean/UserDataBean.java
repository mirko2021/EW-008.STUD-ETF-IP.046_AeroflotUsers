package yatospace.user.role.web.bean;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;
import yatospace.user.role.dao.UserDataDao;
import yatospace.user.role.dto.UserDataDto;
import yatospace.user.role.web.db.DBRoleDAOListener;

/**
 * Зрно које се односи на корисничке податке. 
 * @author VM
 * @version 1.0
 */
public class UserDataBean implements Serializable{
	private static final long serialVersionUID = -2970481408418356542L;
	private transient UserDataDao dataDAO = DBRoleDAOListener.getUserDataDAO();
	
	private String firstName = ""; 
	private String secondName = ""; 
	private String userNote = "";
	
	private boolean initialized; 
	private LoginBean loginBean; 
	
	public void reset() {
		firstName = ""; 
		secondName = ""; 
		userNote = ""; 
		initialized = false; 
		loginBean = null;
	}
	
	public void initialize(HttpServletRequest request) {
		if(initialized) return;
		LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
		this.loginBean = bean;
		String username = loginBean.getUsername(); 
		UserDataDto dto = dataDAO.get(username);
		if(dto!=null) {
			firstName = dto.getFirstname();
			secondName = dto.getSecondname(); 
			userNote = dto.getUsernote();
		}
		this.initialized = true;
	}
	
	public UserDataDao getDataDAO() {
		return dataDAO;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName==null) firstName = ""; 
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		if(secondName==null) secondName = "";
		this.secondName = secondName;
	}
	

	public String getUserNote() {
		return userNote;
	}

	public void setUserNote(String userNote) {
		if(userNote==null) userNote="";
		this.userNote = userNote;
	}

	public void accept() {
		UserDataDto dto = new UserDataDto(); 
		String username = loginBean.getUsername(); 
		dto.setUsername(username);
		dto.setFirstname(firstName);
		dto.setSecondname(secondName);
		dto.setUsernote(userNote);
		dataDAO.put(dto);
	}
}
