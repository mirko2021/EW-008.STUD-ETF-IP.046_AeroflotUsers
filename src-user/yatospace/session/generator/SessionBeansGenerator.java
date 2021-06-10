package yatospace.session.generator;

import javax.servlet.http.HttpSession;

import yatospace.session.bean.BaseBean;
import yatospace.session.bean.LoginBean;
import yatospace.session.bean.SessionListBean;
import yatospace.session.constants.SessionBeansNames;
import yatospace.web.ajax.listener.AjaxRegisterListener;


/**
 * Функционалности преузимања и-или постављања зрна у корисичке сесије. 
 * @author MV
 * @version 1.0 
 */
public final class SessionBeansGenerator {
	private SessionBeansGenerator() {}
	
	public static BaseBean baseBean(HttpSession session) {
		BaseBean bean = (BaseBean) session.getAttribute(SessionBeansNames.BASE_BEAN); 
		if(bean==null) {bean = new BaseBean(); bean.initialize(session);}
		session.setAttribute(SessionBeansNames.BASE_BEAN, bean);
		return bean; 
	}
	
	public static LoginBean loginBean(HttpSession session) {
		LoginBean bean = (LoginBean) session.getAttribute(SessionBeansNames.LOGIN_BEAN); 
		if(bean==null) bean = new LoginBean();
		session.setAttribute(SessionBeansNames.LOGIN_BEAN, bean);
		AjaxRegisterListener.getAjaxRegister(session).put(SessionBeansNames.LOGIN_BEAN, bean);
		return bean; 
	}
	
	public static SessionListBean sessionListBean(HttpSession session) {
		SessionListBean bean = (SessionListBean) session.getAttribute(SessionBeansNames.LIST_BEAN); 
		if(bean==null) bean = new SessionListBean();
		session.setAttribute(SessionBeansNames.LIST_BEAN, bean);
		AjaxRegisterListener.getAjaxRegister(session).put(SessionBeansNames.LIST_BEAN, bean);
		return bean; 
	}
}
