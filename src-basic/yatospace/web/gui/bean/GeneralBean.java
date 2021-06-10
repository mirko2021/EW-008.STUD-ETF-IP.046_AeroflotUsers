package yatospace.web.gui.bean;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import yatospace.web.gui.util.DesignBeansGenerator;

/**
 * Општа приступна тачка када су зрна дизајна у питању. 
 * @author MV
 * @version 1.0
 */
public class GeneralBean implements Serializable{
	private static final long serialVersionUID = -6945387408597609719L;
	
	private MessageBean messageBean = new MessageBean(); 
	private ServiceBean serviceBean = new ServiceBean();
	private OperationalBean operationalBean = new OperationalBean();
	private boolean initialized;
	
	public void initialize(HttpSession session) {
		if(initialized) return; 
		messageBean = DesignBeansGenerator.messageBean(session); 
		serviceBean = DesignBeansGenerator.serviceBean(session); 
		operationalBean = DesignBeansGenerator.operationalBean(session);
		initialized = true;
	}
	public void avoidSyntaxLexicalStream() {}
	
	
	public MessageBean getMessageBean() {
		return messageBean;
	}
	public ServiceBean getServiceBean() {
		return serviceBean;
	}
	public OperationalBean getOperationalBean() {
		return operationalBean;
	}
	public boolean isInitialized() {
		return initialized;
	}
}
