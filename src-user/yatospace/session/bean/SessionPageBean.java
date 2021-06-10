package yatospace.session.bean;

import java.io.Serializable;
import java.net.URLEncoder;

/**
 * Страничење при листање корисничних сесија.
 * @author MV
 * @version 1.0
 */
public class SessionPageBean implements Serializable{
	private static final long serialVersionUID = 4304179120269874042L;
	private int pageNo = 1; 
	private int pageSize = 10; 
	private String usernameStartFilter = ""; 
	private String sessionIdStartFilter = "";
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		if(pageNo<0) pageNo = 0; 
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize<1) pageSize = 1; 
		this.pageSize = pageSize;
	}
	public String getUsernameStartFilter() {
		return usernameStartFilter;
	}
	public void setUsernameStartFilter(String userIdStartFilter) {
		if(userIdStartFilter==null) userIdStartFilter = ""; 
		this.usernameStartFilter = userIdStartFilter;
	}
	public String getSessionIdStartFilter() {
		return sessionIdStartFilter;
	}
	public void setSessionIdStartFilter(String sessionIdStartFilter) {
		if(sessionIdStartFilter==null) sessionIdStartFilter = ""; 
		this.sessionIdStartFilter = sessionIdStartFilter;
	} 
	
	public int countPages(int totalItems) {
		if(totalItems<=0) return 0; 
		if(totalItems%pageSize==0) return totalItems/pageSize; 
		return totalItems/pageSize+1; 
	}
	
	public void refresh(int pageNo) {
		setPageNo(pageNo); 
	}
	
	public void next() {
		setPageNo(pageNo+1); 
	}
	
	public void next(int pageNo) {
		setPageNo(pageNo+1); 
	}
	
	public void previous() {
		setPageNo(pageNo-1); 
	}
	
	public void previous(int pageNo) {
		setPageNo(pageNo-1); 
	}
	
	public String encodeSessionIdStartFilter() {
		try {return URLEncoder.encode(sessionIdStartFilter, "UTF-8");}
		catch(Exception ex) {throw new RuntimeException(ex);}
	}
	
	public String encodeUsernameStartFilter() {
		try {return URLEncoder.encode(usernameStartFilter, "UTF-8");}
		catch(Exception ex) {throw new RuntimeException(ex);}
	}
}
