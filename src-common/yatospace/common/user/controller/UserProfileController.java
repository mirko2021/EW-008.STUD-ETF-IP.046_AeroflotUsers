package yatospace.common.user.controller;

import yatospace.common.user.io.UserProfileDAO;
import yatospace.common.user.model.UserProfileMode;
import yatospace.common.user.object.UserProfileDTO;

/**
 * Корнтролер корисничког профила. 
 * @author VM
 * @vesrsion 1.0
 */
public class UserProfileController {
	private UserProfileDAO dao;
	
	public UserProfileController(UserProfileDAO dao) {
		if(dao==null) throw new RuntimeException();
		this.dao = dao; 
	}
	
	public void put(UserProfileMode profile) {
		try {
			if(profile==null) return;
			UserProfileDTO dto = new UserProfileDTO();
			dto.setUserProfileMode(profile); 
			
			if(dao.getByCombination(dto.getUserProfileMode().getUsername())==null) {
				dao.add(dto);
			}else {
				dao.update(dto);
			}
		}catch(RuntimeException ex) {
			throw new RuntimeException(ex);
		}
		catch(Exception ex) {
			throw ex;
		}
	}

	public UserProfileDAO getDao() {
		return dao;
	}
}
