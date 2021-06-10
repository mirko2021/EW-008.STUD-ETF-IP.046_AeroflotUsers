package yatospace.common.user.web.bean;

import java.io.Serializable;

import yatospace.common.util.FrontPageMenuItem;

/**
 * Оквирно зрно за преглед података о резервацијама на 
 * корисничкој страници за резервације. 
 * @author VM
 * @version 1.0
 */
public class UserFrontPageBean implements Serializable{
	private static final long serialVersionUID = 6460911528889184696L;
	
	private FrontPageMenuItem selected = FrontPageMenuItem.OUTCOMING;

	public FrontPageMenuItem getSelected() {
		return selected;
	}

	public void setSelected(FrontPageMenuItem selected) {
		this.selected = selected;
	}
}
