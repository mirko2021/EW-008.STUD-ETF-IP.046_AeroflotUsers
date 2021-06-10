package yatospace.web.ajax.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yatospace.web.ajax.model.AjaxExecutable;

/**
 * Регистровање компоненти које су за АЈАКС обслуживање. 
 * @author MV
 * @version 1.0
 */
public class AjaxElementRegister implements Serializable{ 
	private static final long serialVersionUID = -3185706772418686196L;
	private HashMap<String, AjaxExecutable> map = new HashMap<>(); 
	
	public AjaxElementRegister() {}
	
	public synchronized AjaxExecutable get(String id) {
		return map.get(id); 
	}
	
	public synchronized List<String> list(){
		ArrayList<String> list = new ArrayList<>();
		return new ArrayList<>(list);
	}
	
	public synchronized void put(String id, AjaxExecutable value) {
		if(map.get(id)!=null) return; 
		map.put(id, value); 
	}
	
	public synchronized void remove(String id) {
		map.remove(id); 
	}
}
