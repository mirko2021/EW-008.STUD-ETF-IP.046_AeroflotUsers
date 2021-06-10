package yatospace.common.util.bean;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

/**
 * Тестирање доступности, појединих сервиса. 
 * @author VM
 * @version 1.0
 */
public class InterentConnectionBean implements Serializable{
	private static final long serialVersionUID = -6290964704270789635L;
	
	public boolean googleAvailable() {
		try {
			URL url = new URL("https://www.google.com");
			try(InputStream is = url.openStream()){
				return true;
			}
		}
		catch(Exception ex) {
			return false; 
		}
	}
	
	public boolean yandexAvailable() {
		try {
			URL url = new URL("https://www.yandex.ru");
			try(InputStream is = url.openStream()){
				return true;
			}
		}
		catch(Exception ex) {
			return false; 
		}
	}
}
