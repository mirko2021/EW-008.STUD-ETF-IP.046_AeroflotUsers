package yatospace.web.user.basic.util;

import javax.servlet.http.HttpSession;

import yatospace.web.user.basic.bean.UserListingBean;
import yatospace.web.user.basic.bean.UserRegistrationBean;
import yatospace.web.user.basic.lang.BeanNamesConstants;

/**
 * Алатка преузимања и/или генерисања зрна. 
 * @author MV
 * @version 1.0
 */
public final class BeansGeneratorUtil {
	private BeansGeneratorUtil() {}
	
	public static final UserRegistrationBean gengetUserRegistrationBean(HttpSession session) {
		UserRegistrationBean registrationBean = (UserRegistrationBean) session.getAttribute(BeanNamesConstants.USER_REGISTRATION_BEAN);
		if(registrationBean==null) registrationBean = new UserRegistrationBean();
		registrationBean.register(session);
		return registrationBean; 
	}
	
	public static final UserListingBean gengetUserListingBean(HttpSession session) {
		UserListingBean listingBean = (UserListingBean) session.getAttribute(BeanNamesConstants.USER_LISTING_BEAN);
		if(listingBean==null) listingBean = new UserListingBean();
		listingBean.register(session);
		return listingBean; 
	}
}
