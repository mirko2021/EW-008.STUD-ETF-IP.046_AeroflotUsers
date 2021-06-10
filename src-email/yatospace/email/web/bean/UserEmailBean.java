package yatospace.email.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import programiranje.ip.admin.listener.DatabaseListener;
import yatospace.email.data.controller.UserEmailController;
import yatospace.email.data.model.UserEmail;
import yatospace.email.data.object.EmailDTO;
import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;

/**
 * Зрно које се односи на баратање са 
 * корисничком коонтакт адресом то јест електронском поштом.
 * @author VM
 * @version 1.0
 */
public class UserEmailBean implements Serializable{
	private static final long serialVersionUID = 300745787366697861L;
	private transient UserEmailController emailController; 
	private String message = "";
	private String username = ""; 
	private String email = ""; 
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if(username==null) username = "";  
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email==null) email = "";
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if(message==null) message = ""; 
		this.message = message;
	}
	
	public void resetMessage() {
		this.message = ""; 
	}
	
	public void reset() {
		email = ""; 
	}

	public void initialize(HttpServletRequest request) {
		if(emailController==null) 
			emailController = new UserEmailController(DatabaseListener.getEmail(request.getSession()));
		LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
		String username = bean.getUsername(); 
		this.username = username; 
	}

	public UserEmailController getEmailController() {
		return emailController;
	}
	
	public void put(HttpServletRequest request) {		
		try {
			LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
			String username = bean.getUsername();
			String email = request.getParameter("uc_email");
			String emailId = "EMAIL:"+email;
			UserEmail userEmail = new UserEmail();
			EmailDTO emailDTO = new EmailDTO();
			userEmail.setEmailAddress(email);
			userEmail.setEmailId(emailId);
			userEmail.setUsername(username);
			emailDTO.setUserEmail(userEmail);
			emailController.put(emailDTO);
			this.username = username; 
			this.email = email; 
			message = "Постављање подтака о адреси електронске поште за корисника је успјело.";
		}catch(Exception ex) {
			LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
			String username = bean.getUsername(); 
			this.username = username;
			this.email = ""; 
			message = "Постављање подтака о адреси електронске поште за корисника није успјело."; 
		}
	}
	
	public String escapeUsername() {
		return username.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'","&apos;");
	}
	
	public String escapeEmail() {
		return email.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;").replaceAll("'","&apos;");
	}
	
	public void reset(HttpServletRequest request) {
		try {
			LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
			String username = bean.getUsername();
			EmailDTO email = emailController.getEmailDataSource().getEmailByUsername(username); 
			if(email==null) throw new RuntimeException(); 
			emailController.getEmailDataSource().remove(email.getUserEmail().getEmailId());
			this.username = username; 
			this.email = "";
			message = "Поништавање подтака о адреси електронске поште за корисника је успјело."; 
		}catch(Exception ex) {
			LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
			String username = bean.getUsername(); 
			this.username = username;
			this.email = ""; 
			message = "Поништавање подтака о адреси електронске поште за корисника није успјело."; 
		}
	}
	
	public List<UserEmail> listEmails(){
		ArrayList<UserEmail> emails = new ArrayList<>();
		for(EmailDTO dto: emailController.getEmailDataSource().listEmails()){
			emails.add(dto.getUserEmail()); 
		}
		return emails; 
	}
}
