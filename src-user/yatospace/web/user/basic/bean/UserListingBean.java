package yatospace.web.user.basic.bean;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import yatospace.gui.program.shell.AppLogicCenter;
import yatospace.user.controller.UserCredentialsController;
import yatospace.user.object.User;
import yatospace.web.ajax.anotation.AjaxAccessable;
import yatospace.web.ajax.anotation.AjaxSecurity;
import yatospace.web.ajax.element.AjaxRequestContext;
import yatospace.web.ajax.listener.AjaxRegisterListener;
import yatospace.web.ajax.model.AjaxExecutable;
import yatospace.web.user.basic.lang.BeanNamesConstants;
import yatospace.web.user.basic.model.Page;

/**
 * Зрно при листању корисника. 
 * @author MV
 * @version 1.0 
 */
public class UserListingBean implements Serializable, AjaxExecutable{
	private static final long serialVersionUID = 7786993393528989477L;
	private Page page = new Page(); 
	private String startFilter = ""; 
	
	public void register(HttpSession session) {
		AjaxRegisterListener.getAjaxRegister(session).put(BeanNamesConstants.USER_LISTING_BEAN, this);
	}
	
	public Page getPage() {
		return page;
	}


	public String getStartFilter() {
		if(startFilter==null) startFilter = ""; 
		return startFilter;
	}

	public void setStartFilter(String startFilter) {
		this.startFilter = startFilter;
	}

	@Override
	public void importFrom(AjaxRequestContext request) {
		if(request==null) throw new NullPointerException(); 
		try {
			int pageNo = request.getRequest().get("parameters").getAsJsonObject().get("page_no").getAsInt(); 
			int pageSize = request.getRequest().get("parameters").getAsJsonObject().get("page_size").getAsInt();
			String startFilter = request.getRequest().get("parameters").getAsJsonObject().get("start_filter").getAsString();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			request.getResponse().get("result").getAsJsonObject().addProperty("success", true); 
			this.startFilter = startFilter; 
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	
	@AjaxAccessable("refresh")
	public void refresh(AjaxRequestContext context) {
		if(context==null) throw new NullPointerException(); 
		try {
			int pageNo = context.getRequest().get("parameters").getAsJsonObject().get("page_no").getAsInt(); 
			int pageSize = context.getRequest().get("parameters").getAsJsonObject().get("page_size").getAsInt();
			String startFilter = context.getRequest().get("parameters").getAsJsonObject().get("start_filter").getAsString();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			this.startFilter = startFilter; 
			context.getResponse().add("result", new JsonObject()); 
			context.getResponse().get("result").getAsJsonObject().addProperty("page_no", page.getPageNo()); 
			context.getResponse().get("result").getAsJsonObject().addProperty("page_size", page.getPageSize()); 
			context.getResponse().get("result").getAsJsonObject().addProperty("start_filter", startFilter);
			context.getResponse().get("result").getAsJsonObject().addProperty("total_items", count()); 
			context.getResponse().get("result").getAsJsonObject().addProperty("success", true);
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@AjaxAccessable("next")
	public void next(AjaxRequestContext context) {
		if(context==null) throw new NullPointerException(); 
		try {
			int pageNo = context.getRequest().get("parameters").getAsJsonObject().get("page_no").getAsInt()+1; 
			int pageSize = context.getRequest().get("parameters").getAsJsonObject().get("page_size").getAsInt();
			String startFilter = context.getRequest().get("parameters").getAsJsonObject().get("start_filter").getAsString();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			this.startFilter = startFilter;
			context.getResponse().add("result", new JsonObject()); 
			context.getResponse().get("result").getAsJsonObject().addProperty("page_no", page.getPageNo()); 
			context.getResponse().get("result").getAsJsonObject().addProperty("page_size", page.getPageSize()); 
			context.getResponse().get("result").getAsJsonObject().addProperty("start_filter", startFilter);
			context.getResponse().get("result").getAsJsonObject().addProperty("total_items", count()); 
			context.getResponse().get("result").getAsJsonObject().addProperty("success", true); 
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@AjaxAccessable("previous")
	public void previous(AjaxRequestContext context) {
		if(context==null) throw new NullPointerException(); 
		try {
			int pageNo = context.getRequest().get("parameters").getAsJsonObject().get("page_no").getAsInt()-1; 
			int pageSize = context.getRequest().get("parameters").getAsJsonObject().get("page_size").getAsInt();
			String startFilter = context.getRequest().get("parameters").getAsJsonObject().get("start_filter").getAsString();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			this.startFilter = startFilter;
			context.getResponse().add("result", new JsonObject()); 
			context.getResponse().add("result", new JsonObject()); 
			context.getResponse().get("result").getAsJsonObject().addProperty("page_no", page.getPageNo()); 
			context.getResponse().get("result").getAsJsonObject().addProperty("page_size", page.getPageSize()); 
			context.getResponse().get("result").getAsJsonObject().addProperty("start_filter", startFilter); 
			context.getResponse().get("result").getAsJsonObject().addProperty("total_items", count()); 
			context.getResponse().get("result").getAsJsonObject().addProperty("success", true);
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public int count() {
		return AppLogicCenter.appLogicCenter.getController().count(); 
	}
	
	public List<User> list(){
		return AppLogicCenter.appLogicCenter.getController().list(page.getPageNo(), page.getPageSize(), startFilter); 
	}
	
	public String passwordRecord(String username) {
		try { return AppLogicCenter.appLogicCenter.getController().getDataSource().getController(username).getCredentialsTool().getPasswordRecord(); }
		catch(Exception ex) { return null; }
	}
	
	@AjaxAccessable("admin_erase_all")
	@AjaxSecurity
	public void adminEraseAll(AjaxRequestContext context) {
		JsonArray usernames = context.getRequest().get("parameters").getAsJsonObject().get("usernames").getAsJsonArray(); 
		JsonArray deleted = new JsonArray(); 
		JsonArray nondeleted = new JsonArray(); 
		for(int i=0; i<usernames.size(); i++) {
			String username = usernames.get(i).getAsString();
			boolean remove = AppLogicCenter.appLogicCenter.getController().getDataSource().remove(username);
			if(remove) deleted.add(username);
			else nondeleted.add(username);
		}
		context.getResponse().add("nondeleted", nondeleted); 
		context.getResponse().add("deleted", deleted); 
		context.getResponse().addProperty("success", true);
	}
	
	@AjaxAccessable("admin_erase_one")
	@AjaxSecurity
	public void adminEraseOne(AjaxRequestContext context) {
		String username = context.getRequest().get("parameters").getAsJsonObject().get("username").getAsString(); 
		if(username==null) throw new NullPointerException(); 
		boolean remove = AppLogicCenter.appLogicCenter.getController().getDataSource().remove(username);
		context.getResponse().addProperty("success", remove);
	}
	
	@AjaxAccessable("admin_update")
	@AjaxSecurity
	public void adminUpdate(AjaxRequestContext context) {
		String username = context.getRequest().get("parameters").getAsJsonObject().get("username").getAsString(); 
		String newPassword = context.getRequest().get("parameters").getAsJsonObject().get("new_password").getAsString();
		if(username==null) throw new NullPointerException(); 
		if(newPassword==null) throw new NullPointerException(); 
		UserCredentialsController userCredentials = new UserCredentialsController(); 
		if(userCredentials.setGoodPassword(newPassword).trim().length()==0) {context.getResponse().addProperty("success", false); return;} 
		boolean update = AppLogicCenter.appLogicCenter.getController().getDataSource().updatePassword(username, newPassword);
		context.getResponse().addProperty("success", update);
	}
	
	@AjaxAccessable("admin_rename")
	@AjaxSecurity
	public void adminRename(AjaxRequestContext context) {
		String username = context.getRequest().get("parameters").getAsJsonObject().get("username").getAsString(); 
		String newUsername = context.getRequest().get("parameters").getAsJsonObject().get("new_username").getAsString();
		if(username==null) throw new NullPointerException(); 
		if(newUsername==null) throw new NullPointerException(); 
		if(AppLogicCenter.appLogicCenter.getController().getDataSource().getController(newUsername)!=null) {context.getResponse().addProperty("success", false); return;} 
		UserCredentialsController credentials = new UserCredentialsController(); 
		if(credentials.setGoodUsername(newUsername).trim().length()==0) {context.getResponse().addProperty("success", false); return;}
		boolean rename = AppLogicCenter.appLogicCenter.getController().getDataSource().updateUsername(username, newUsername);
		context.getResponse().addProperty("success", rename);
	}
	
	@AjaxAccessable("test_password")
	public void testPassword(AjaxRequestContext context) {
		String username = context.getRequest().get("parameters").getAsJsonObject().get("username").getAsString(); 
		String password = context.getRequest().get("parameters").getAsJsonObject().get("password").getAsString();
		if(username==null) throw new NullPointerException(); 
		if(password==null) throw new NullPointerException();
		context.getResponse().addProperty("test", AppLogicCenter.appLogicCenter.getController().check(username, password));
		context.getResponse().addProperty("success", true);
	}
	
	@AjaxAccessable("client_erase_one")
	public void clientEraseOne(AjaxRequestContext context) {
		String username = context.getRequest().get("parameters").getAsJsonObject().get("username").getAsString(); 
		String password = context.getRequest().get("parameters").getAsJsonObject().get("password").getAsString();
		if(username==null) throw new NullPointerException(); 
		if(password==null) throw new NullPointerException();
		if(AppLogicCenter.appLogicCenter.getController().check(username, password)) {
			boolean remove = AppLogicCenter.appLogicCenter.getController().getDataSource().remove(username);
			context.getResponse().addProperty("check_password", true);
			context.getResponse().addProperty("success", remove);
		}else{
			context.getResponse().addProperty("check_password", false);
			context.getResponse().addProperty("success", false);
		}
	}
	
	@AjaxAccessable("client_update")
	public void clientUpdate(AjaxRequestContext context) {
		String username = context.getRequest().get("parameters").getAsJsonObject().get("username").getAsString(); 
		String password = context.getRequest().get("parameters").getAsJsonObject().get("password").getAsString();
		String newPassword = context.getRequest().get("parameters").getAsJsonObject().get("new_password").getAsString();
		if(username==null) throw new NullPointerException(); 
		if(password==null) throw new NullPointerException(); 
		if(newPassword==null) throw new NullPointerException(); 
		if(AppLogicCenter.appLogicCenter.getController().check(username, password)) {
			UserCredentialsController controller = new UserCredentialsController();
			if(controller.setGoodPassword(newPassword).trim().length()>0) { 
				boolean update = AppLogicCenter.appLogicCenter.getController().getDataSource().updatePassword(username, newPassword);
				context.getResponse().addProperty("check_password", true);
				context.getResponse().addProperty("success", update);
			}else {
				context.getResponse().addProperty("check_password", true);
				context.getResponse().addProperty("not_good_password", true);
				context.getResponse().addProperty("success", false);
			}
		}else {
			context.getResponse().addProperty("check_password", false);
			context.getResponse().addProperty("success", false);
		}
	}
	
	@AjaxAccessable("client_rename")
	public void clientRename(AjaxRequestContext context) {
		String username = context.getRequest().get("parameters").getAsJsonObject().get("username").getAsString(); 
		String password = context.getRequest().get("parameters").getAsJsonObject().get("password").getAsString();
		String newUsername = context.getRequest().get("parameters").getAsJsonObject().get("new_username").getAsString();
		if(username==null) throw new NullPointerException(); 
		if(password==null) throw new NullPointerException(); 
		if(newUsername==null) throw new NullPointerException(); 
		if(AppLogicCenter.appLogicCenter.getController().check(username, password)) {			
			if(AppLogicCenter.appLogicCenter.getController().getDataSource().getController(newUsername)!=null) {context.getResponse().addProperty("check_password", true); context.getResponse().addProperty("success", false); return;} 
			UserCredentialsController credentials = new UserCredentialsController(); 
			if(credentials.setGoodUsername(newUsername).trim().length()>0) {
				boolean rename = AppLogicCenter.appLogicCenter.getController().getDataSource().updateUsername(username, newUsername);
				context.getResponse().addProperty("check_password", true);
				context.getResponse().addProperty("success", rename);
			}else {
				context.getResponse().addProperty("check_password", true);
				context.getResponse().addProperty("not_good_username", true);
				context.getResponse().addProperty("success", false);
			}
		}else {
			context.getResponse().addProperty("check_password", false);
			context.getResponse().addProperty("success", false);
		}
	}

	@Override
	public void exportTo(AjaxRequestContext request) {
		if(request==null) throw new NullPointerException(); 
		try {
			request.getResponse().get("result").getAsJsonObject().addProperty("page_no", page.getPageNo());
			request.getResponse().get("result").getAsJsonObject().addProperty("page_size", page.getPageSize());
			request.getResponse().get("result").getAsJsonObject().addProperty("start_filter", startFilter);
			request.getResponse().get("result").getAsJsonObject().addProperty("success", true);
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
