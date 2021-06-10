package yatospace.user.role.dto;

import java.io.Serializable;

/**
 * Подаци о корисничким правилима. 
 * @author VM
 * @version 1.0
 */
public class UserRoleDto implements Serializable{
	private static final long serialVersionUID = -8351586123626103623L;
	
	private String application = ""; 
	private String key  = ""; 
	private String value = "";
	private String username = ""; 
	
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		if(application == null) application = "";
		this.application = application;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		if(key==null) key = "";
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		if(value==null) value = "";
		this.value = value;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		if(username==null) username = ""; 
		this.username = username;
	}
}
