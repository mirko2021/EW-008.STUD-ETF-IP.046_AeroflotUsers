package yatospace.common.user.controller;

import yatospace.common.user.io.UserLocationDAO;
import yatospace.common.user.model.UserLocation;
import yatospace.common.user.object.UserLocationDTO;
import yatospace.ip_messaging.io.IPUserDAO;

/**
 * Контролер који се односи на корисничку адресу и локацију. 
 * @author VM
 * @version 1.0
 */
public class UserLocationController {
	private UserLocationDAO dao; 
	private IPUserDAO userDAO; 
	
	public UserLocationController(UserLocationDAO dao, IPUserDAO userDAO) {
		if(dao==null) throw new NullPointerException();
		if(userDAO==null) throw new NullPointerException();
		this.dao = dao;
		this.userDAO = userDAO; 
	}

	public UserLocationDAO getDao() {
		return dao;
	}
	
	public void set(UserLocation location) {
		if(location==null) return;
		if(userDAO.getUser(location.getUsername())==null) 
			throw new RuntimeException("User not exists.");
		UserLocationDTO dto = dao.getByUsername(location.getUsername());
		if(dto==null) {
			dto = new UserLocationDTO();
			dto.setLocation(location);
			dao.add(dto);
		}else {
			if(dao.get(location.getLocationId())!=null) 
				throw new RuntimeException("Duplicate location.");
			String oldId = dto.getLocation().getLocationId();  
			dto = new UserLocationDTO();
			dto.setLocation(location);
			dao.set(oldId, dto);
		}
	}
	
	public void reset(String username) {
		if(username==null) return; 
		dao.reset(username);
	}
}
