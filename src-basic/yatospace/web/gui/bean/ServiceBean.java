package yatospace.web.gui.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import yatospace.session.bean.BaseBean;
import yatospace.session.generator.SessionBeansGenerator;
import yatospace.web.ajax.element.AjaxRequestContext;
import yatospace.web.ajax.model.AjaxExecutable;
import yatospace.web.socket.util.WebSocketDescriptor;

/**
 * Зрно које је при сервисирању и услугама, а при веб дизајну. 
 * @author MV
 * @version 1.0
 */
public class ServiceBean implements Serializable, AjaxExecutable{
	private static final long serialVersionUID = -5546973220854994635L;
	private WebSocketDescriptor serverTimeSocketDescriptor = new WebSocketDescriptor();
	
	public ServiceBean() {
		serverTimeSocketDescriptor.setPath("/server/time");
	}
	
	public WebSocketDescriptor getServerTimeSocketDescriptor() {
		return serverTimeSocketDescriptor;
	}

	@Override
	public void importFrom(AjaxRequestContext request) {
		request.getResponse().addProperty("success", true);
	}

	@Override
	public void exportTo(AjaxRequestContext request) {
		request.getResponse().addProperty("success", true);
	}
	
	public String getCurrentServerDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"); 
		return sdf.format(new Date()); 
	}
	
	public String loggedUser(HttpSession session) {
		BaseBean sessionBean = SessionBeansGenerator.baseBean(session); 
		return sessionBean.getLoginBean().getUsername(); 
	}
	
	public boolean isLoggedUser(HttpSession session) {
		String loggedUser = loggedUser(session); 
		return loggedUser!=null && loggedUser.trim().length()!=0; 
	}
}
