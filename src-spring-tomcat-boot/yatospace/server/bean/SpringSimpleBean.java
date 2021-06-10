package yatospace.server.bean;

import java.io.Serializable;

/**
 * Примјер зрна за Спринг. 
 * @author VM
 * @version 1.0 
 */
public class SpringSimpleBean implements Serializable{
	private static final long serialVersionUID = -5986621139550856712L;
	private String string = "";

	public String getString() {
		return string;
	}

	public void setString(String string) {
		if(string==null) string = ""; 
		this.string = string;
	} 
}
