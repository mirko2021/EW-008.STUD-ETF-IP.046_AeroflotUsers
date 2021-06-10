package yatospace.web.ajax.security;

import java.util.HashMap;
import java.util.Map;

/**
 * Уопштена права приступа заснован на скупу приступних правила. 
 * @author MV
 * @version 1.0
 */
public class AjaxGeneralAccess extends AjaxAccess{
	private HashMap<String, AjaxAccess> accesses = new HashMap<>(); 
	
	public synchronized void put(String name, AjaxAccess access) {
		if(name==null || name.trim().length()==0) return; 
		if(access==null) return; 
		accesses.put(name, access); 
	}
	
	public synchronized void remove(String name) {
		accesses.remove(name);
	}
	
	public synchronized AjaxAccess get(String name) {
		return accesses.get(name); 
	}
	
	public synchronized Map<String, AjaxAccess> get(){
		return new HashMap<>(accesses); 
	}
	
	@Override
	public synchronized boolean canAccess() {
		for(AjaxAccess access: accesses.values()) 
			if(!access.canAccess()) return false; 
		return true;
	}
}
