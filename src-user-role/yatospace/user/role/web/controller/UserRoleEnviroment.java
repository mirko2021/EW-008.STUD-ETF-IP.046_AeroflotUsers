package yatospace.user.role.web.controller;

import yatospace.user.role.dto.UserRoleDto;

/**
 * Контролер који се односи на баратање и препакивање података
 * о кориничким правилима. 
 * @author VM
 * @version 1.0
 */
public class UserRoleEnviroment {
	private UserRoleDto dto; 
	
	public UserRoleEnviroment(UserRoleDto dto) {
		if(dto==null) throw new NullPointerException();
		this.dto = dto; 
	}
	
	public String getApplication() {
		return dto.getApplication(); 
	}
	
	public boolean isUser() {
		return dto.getKey().contentEquals("user") &&  dto.getValue().contentEquals("true"); 
	}
	
	public boolean isWorker() {
		return dto.getKey().contentEquals("worker") &&  dto.getValue().contentEquals("true"); 
	}
	
	public boolean isAdministrator() {
		return dto.getKey().contentEquals("administrator") &&  dto.getValue().contentEquals("true"); 
	}
}
