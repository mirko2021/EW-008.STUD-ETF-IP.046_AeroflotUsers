package yatospace.ip_messaging.web.bean;

import java.io.Serializable;

/**
 * Зрно у којој се чува и управља својство странице
 * топ одлазака и долазака. 
 * @author VM
 * @version 1.0
 */
public class MessagePropertiesBean implements Serializable{
	private static final long serialVersionUID = -2702077693320989862L;
	private int pageNo = 0; 
	private int pageSize = 5;
	
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
		if(pageSize<0) pageSize = 1;
		this.pageSize = pageSize;
	} 
}
