package yatospace.gui.session.util;

import java.io.Serializable;

/**
 * Параметри страничења. 
 * @author MV
 * @version 1.0
 */
public class PageParameters implements Serializable{
	private static final long serialVersionUID = 6292555167476325252L;
	private int pageNo = 1; 
	private int pageSize = 10; 
	private String userStartFilter = ""; 
	private String sessionStartFilter = "";
	
	
	public int getPageNo() {
		return pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public String getUserStartFilter() {
		return userStartFilter;
	}
	public String getSessionStartFilter() {
		return sessionStartFilter;
	}
	public void setPageNo(int pageNo) {
		if(pageNo<0) pageNo = 1; 
		this.pageNo = pageNo;
	}
	public void setPageSize(int pageSize) {
		if(pageSize<1) pageSize = 1; 
		this.pageSize = pageSize;
	}
	public void setUserStartFilter(String userStartFilter) {
		if(userStartFilter==null) userStartFilter = ""; 
		this.userStartFilter = userStartFilter;
	}
	public void setSessionStartFilter(String sessionStartFilter) {
		if(sessionStartFilter==null) sessionStartFilter = ""; 
		this.sessionStartFilter = sessionStartFilter;
	} 
	
	
}
