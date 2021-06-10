package yatospace.common.user.web.bean;

import java.io.Serializable;

import yatospace.common.util.FrontPageMenuItem;

/**
 * Оквирно зрно за начелну страницу. 
 * @author VM
 * @version 1.0
 */
public class FrontPageBean implements Serializable{
	private static final long serialVersionUID = -2341882357375636057L;
	
	private FrontPageMenuItem selected = FrontPageMenuItem.OUTCOMING;

	public FrontPageMenuItem getSelected() {
		return selected;
	}

	public void setSelected(FrontPageMenuItem selected) {
		this.selected = selected;
	}
}
