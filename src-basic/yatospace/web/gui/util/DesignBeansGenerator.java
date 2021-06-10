package yatospace.web.gui.util;

import javax.servlet.http.HttpSession;

import yatospace.web.ajax.listener.AjaxRegisterListener;
import yatospace.web.gui.bean.GeneralBean;
import yatospace.web.gui.bean.MessageBean;
import yatospace.web.gui.bean.OperationalBean;
import yatospace.web.gui.bean.ServiceBean;
import yatospace.web.gui.constants.DesignBeansConstants;

/**
 * Генерисање зрна. 
 * @author MV
 * @version 1.0
 */
public final class DesignBeansGenerator {
	private DesignBeansGenerator() {}
	
	public static final GeneralBean generalBean(HttpSession session) {
		GeneralBean bean = (GeneralBean) session.getAttribute(DesignBeansConstants.DESIGN_GENERAL_BEAN); 
		if(bean==null) {bean = new GeneralBean(); bean.initialize(session);}
		session.setAttribute(DesignBeansConstants.DESIGN_GENERAL_BEAN, bean);
		return bean; 
	}
	
	public static final ServiceBean serviceBean(HttpSession session) {
		ServiceBean bean = (ServiceBean) session.getAttribute(DesignBeansConstants.DESIGN_SERVICE_BEAN); 
		if(bean==null) {
			bean = new ServiceBean();
			AjaxRegisterListener.getAjaxRegister(session).put(DesignBeansConstants.DESIGN_SERVICE_BEAN, bean);
		}
		session.setAttribute(DesignBeansConstants.DESIGN_SERVICE_BEAN, bean);
		return bean; 
	}
	
	public static final MessageBean messageBean(HttpSession session) {
		MessageBean bean = (MessageBean) session.getAttribute(DesignBeansConstants.DESIGN_MESSAGE_BEAN); 
		if(bean==null) {
			bean = new MessageBean();
			AjaxRegisterListener.getAjaxRegister(session).put(DesignBeansConstants.DESIGN_MESSAGE_BEAN, bean);
		}
		session.setAttribute(DesignBeansConstants.DESIGN_MESSAGE_BEAN, bean);
		return bean; 
	}
	
	public static final OperationalBean operationalBean(HttpSession session) {
		OperationalBean bean = (OperationalBean) session.getAttribute(DesignBeansConstants.DESIGN_OPERATIONAL_BEAN); 
		if(bean==null) 
			bean = new OperationalBean();
		session.setAttribute(DesignBeansConstants.DESIGN_OPERATIONAL_BEAN, bean);
		return bean; 
	}
}
