package yatospace.web.ajax.security.impl;

import yatospace.web.ajax.security.AjaxAccess;
import yatospace.web.ajax.security.AjaxAdminAccess;
import yatospace.web.ajax.security.AjaxGeneralAccess;

/**
 * Извердба пристуних правила заснованих само на администрацији. 
 * @author MV
 * @version 1.0
 */
public class AdminGeneralAccess extends AjaxGeneralAccess{

	public AdminGeneralAccess() {
		super();
		super.put("administrativity", new AjaxAdminAccess());
	}

	@Override
	public synchronized void put(String name, AjaxAccess access) {
		throw new UnsupportedOperationException(); 
	}

	@Override
	public synchronized void remove(String name) {
		throw new UnsupportedOperationException();
	}
	
	public AjaxAdminAccess administrativity() {
		return (AjaxAdminAccess) get("administrativity");
	}
}
