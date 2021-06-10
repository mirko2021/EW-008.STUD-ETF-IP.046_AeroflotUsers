package yatospace.web.user.basic.model;

import java.io.Serializable;

/**
 * Форма података за страничење. 
 * @author MV
 * @version 1.0 
 */
public class Page implements Serializable{
	private static final long serialVersionUID = -1432878363998478895L;
	private int pageNo = 1; 
	private int pageSize = 10;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		if(pageNo<0) pageNo = 1; 
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize<1) pageSize = 10; 
		this.pageSize = pageSize;
	} 
	public int countPages(int totalItems) {
		if(totalItems==0) return 0; 
		if(totalItems%pageSize==0) return totalItems/pageSize; 
		return totalItems/pageSize + 1; 
	}
}
