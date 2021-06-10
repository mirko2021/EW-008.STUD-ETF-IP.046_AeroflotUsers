package yatospace.web.ajax.security;

/**
 * Права пиступа при провјрама административности корисника. 
 * @author MV
 * @version 1.0
 */
public class AjaxAdminAccess extends AjaxAccess{
	private String administrator = ""; 
	
	public String getAdministrator() {
		return administrator;
	}

	public void setAdministrator(String administrator) {
		if(administrator==null) administrator = ""; 
		this.administrator = administrator;
	}

	@Override
	public boolean canAccess() {
		return administrator.trim().length()!=0;
	}	
}
