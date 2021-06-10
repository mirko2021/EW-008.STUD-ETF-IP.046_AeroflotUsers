package yatospace.session.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;

import yatospace.gui.session.logic.AppControlCenter;
import yatospace.user.session.model.Session;
import yatospace.web.ajax.anotation.AjaxAccessable;
import yatospace.web.ajax.anotation.AjaxSecurity;
import yatospace.web.ajax.element.AjaxRequestContext;
import yatospace.web.ajax.model.AjaxExecutable;

/**
 * Зрно за потребе листања сесија. 
 * @author MV
 * @version 1.1.
 */
public class SessionListBean implements Serializable, AjaxExecutable{
	private static final long serialVersionUID = 7174862997608878720L;
	private SessionPageBean page = new SessionPageBean();
	
	public SessionPageBean getPage() {
		return page;
	}

	@Override
	public void importFrom(AjaxRequestContext request) {
		try {
			int pageNo =  request.getRequest().get("parameters").getAsJsonObject().get("page_no").getAsInt();
			int pageSize = request.getRequest().get("parameters").getAsJsonObject().get("page_size").getAsInt();
			String  sessionStartFilter = request.getRequest().get("parameters").getAsJsonObject().get("session_start_filter").getAsString();
			String  userStartFilter = request.getRequest().get("parameters").getAsJsonObject().get("user_start_filter").getAsString();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setSessionIdStartFilter(sessionStartFilter);
			page.setUsernameStartFilter(userStartFilter);
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(); 
		}
	}

	@Override
	public void exportTo(AjaxRequestContext request) {	
		try {
			request.getResponse().addProperty("page_no", page.getPageNo());
			request.getResponse().addProperty("page_size", page.getPageSize());
			request.getResponse().addProperty("user_start_filter", page.getUsernameStartFilter());
			request.getResponse().addProperty("session_start_filter", page.getSessionIdStartFilter());
			request.getResponse().addProperty("user_start_filter", page.getUsernameStartFilter());
		}catch(RuntimeException ex) {
			throw ex;
		}catch(Exception ex) {
			throw new RuntimeException(); 
		}
	}
	
	@AjaxAccessable("next")
	public void next(AjaxRequestContext request) {
		try { 
			int pageNo =  request.getRequest().get("parameters").getAsJsonObject().get("page_no").getAsInt();
			int pageSize = request.getRequest().get("parameters").getAsJsonObject().get("page_size").getAsInt();
			String  sessionStartFilter = request.getRequest().get("parameters").getAsJsonObject().get("session_start_filter").getAsString();
			String  userStartFilter = request.getRequest().get("parameters").getAsJsonObject().get("user_start_filter").getAsString();
			page.setPageNo(pageNo+1);
			page.setPageSize(pageSize);
			page.setSessionIdStartFilter(sessionStartFilter);
			page.setUsernameStartFilter(userStartFilter);
			request.getResponse().addProperty("success", true);
		}catch(RuntimeException ex) {
			request.getResponse().addProperty("success", false);
		}catch(Exception ex) {
			request.getResponse().addProperty("success", false);
		}
	}
	
	@AjaxAccessable("refresh")
	public void refresh(AjaxRequestContext request) {
		try {
			int pageNo =  request.getRequest().get("parameters").getAsJsonObject().get("page_no").getAsInt();
			int pageSize = request.getRequest().get("parameters").getAsJsonObject().get("page_size").getAsInt();
			String  sessionStartFilter = request.getRequest().get("parameters").getAsJsonObject().get("session_start_filter").getAsString();
			String  userStartFilter = request.getRequest().get("parameters").getAsJsonObject().get("user_start_filter").getAsString();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setSessionIdStartFilter(sessionStartFilter);
			page.setUsernameStartFilter(userStartFilter);
			request.getResponse().addProperty("success", true);
		}catch(RuntimeException ex) {
			request.getResponse().addProperty("success", false);
		}catch(Exception ex) {
			request.getResponse().addProperty("success", false);
		}
	}
	
	@AjaxAccessable("previous")
	public void previous(AjaxRequestContext request) {
		try {
			int pageNo =  request.getRequest().get("parameters").getAsJsonObject().get("page_no").getAsInt();
			int pageSize = request.getRequest().get("parameters").getAsJsonObject().get("page_size").getAsInt();
			String  sessionStartFilter = request.getRequest().get("parameters").getAsJsonObject().get("session_start_filter").getAsString();
			String  userStartFilter = request.getRequest().get("parameters").getAsJsonObject().get("user_start_filter").getAsString();
			page.setPageNo(pageNo-1);
			page.setPageSize(pageSize);
			page.setSessionIdStartFilter(sessionStartFilter);
			page.setUsernameStartFilter(userStartFilter);
			request.getResponse().addProperty("success", true);
		}catch(RuntimeException ex) {
			request.getResponse().addProperty("success", false);
		}catch(Exception ex) {
			request.getResponse().addProperty("success", false);
		}
	}
	
	public int count() {
		return AppControlCenter.mainCenter.getController().getRegisterEngine().count();
	}
	
	public List<Session> list(){
		return AppControlCenter.mainCenter.getController().getRegisterEngine().list(page.getPageNo(), page.getPageSize(), page.getSessionIdStartFilter(), page.getUsernameStartFilter());
	}
	
	public int countForUser(String username) {
		return AppControlCenter.mainCenter.getController().getRegisterEngine().countFor(username); 
	}
	
	public List<Session> listForUser(String username){
		ArrayList<Session> result = new ArrayList<>(); 
		for(Session session: AppControlCenter.mainCenter.getController().getRegisterEngine().list(page.getPageNo(), page.getPageSize(), page.getSessionIdStartFilter(), page.getUsernameStartFilter())) 
			if(session.getUserId().contentEquals(username))
				result.add(session); 
		return result; 
	}

	@AjaxAccessable("delete_sessions")
	@AjaxSecurity
	public void deleteSessions(AjaxRequestContext request) {
		try {
			int countDeleted = 0; 
			JsonArray sessions = request.getRequest().get("parameters").getAsJsonObject().get("sessions").getAsJsonArray(); 
			for(int i=0; i<sessions.size(); i++) {
				String sessionId = sessions.get(i).getAsString();
				try {
					AppControlCenter.mainCenter.getController().getRegisterEngine().logout(sessionId);
					countDeleted++;
				}
				catch(Exception ex) {continue; }
			}
			request.getResponse().addProperty("count_sessions", countDeleted);
			request.getResponse().addProperty("success", true);
		}catch(RuntimeException ex) {
			request.getResponse().addProperty("success", false);
		}catch(Exception ex) {
			request.getResponse().addProperty("success", false);
		}
	}
	
	@AjaxAccessable("delete_users")
	@AjaxSecurity
	public void deleteUsers(AjaxRequestContext request) {
		try {
			int countDeletedSessions = 0; 
			int countDeletedUsers = 0;
			JsonArray users = request.getRequest().get("parameters").getAsJsonObject().get("users").getAsJsonArray(); 
			for(int i=0; i<users.size(); i++) {
				String username = users.get(i).getAsString();
				try {
					int countSessions = AppControlCenter.mainCenter.getController().getRegisterEngine().countFor(username); 
					AppControlCenter.mainCenter.getController().getRegisterEngine().logoutAll(username);
					countDeletedUsers++;
					countDeletedSessions+=countSessions;
				}
				catch(Exception ex) {continue; }
			}
			request.getResponse().addProperty("count_users", countDeletedUsers);
			request.getResponse().addProperty("count_sessions", countDeletedSessions);
			request.getResponse().addProperty("success", true);
		}catch(RuntimeException ex) {
			request.getResponse().addProperty("success", false);
		}catch(Exception ex) {
			request.getResponse().addProperty("success", false);
		}
	}
}
