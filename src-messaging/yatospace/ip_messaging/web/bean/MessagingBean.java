package yatospace.ip_messaging.web.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import programiranje.ip.admin.listener.DatabaseListener;
import yatospace.ip_messaging.controller.IPMessagingController;
import yatospace.ip_messaging.io.IPMessageDAO;
import yatospace.ip_messaging.io.IPUserDAO;
import yatospace.ip_messaging.io.SQLTimestampDAO;
import yatospace.ip_messaging.model.UserMessage;
import yatospace.ip_messaging.object.IPMessageDTO;
import yatospace.ip_messaging.util.UserMessageFormatEnviroment;
import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;

/**
 * Зрно које се односи на поруке од корисника према серверу 
 * које би касније полуатоматски обрађивао запослени. Везане 
 * за авиопревоз. 
 * @author VM
 * @version 1.0
 */
public class MessagingBean implements Serializable{
	private static final long serialVersionUID = -4979870149254288906L;
	
	private String notification = "";
	private UserMessage message = new UserMessage();
	private transient IPMessagingController messagingController; 
	
	public void initialize(HttpServletRequest request) {
		if(messagingController==null) {
			IPMessageDAO messageDAO = DatabaseListener.getIpMessage(request.getSession()); 
			IPUserDAO ipUserDAO =  DatabaseListener.getIpUser(request.getSession()); 
			SQLTimestampDAO timestampDAO = DatabaseListener.getSQLTimestamp(request.getSession());
			messagingController = new IPMessagingController(messageDAO, ipUserDAO , timestampDAO);
		}
		LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
		String username = bean.getUsername(); 
		this.message.setUsername(username);
	}
	
	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		if(notification==null) notification = "";
		this.notification = notification;
	}

	public void resetNotification() {
		this.notification = ""; 
	}
	
	public UserMessage getMessage() {
		return message;
	}

	public void setMessage(UserMessage message) {
		if(message==null) message = new UserMessage();
		this.message = message;
	}
	
	public void resetMessage() {
		String username = message.getUsername();
		message = new UserMessage();
		message.setUsername(username);
	}
	
	public void reset() {
		resetMessage();
		resetNotification(); 
	}
	
	public UserMessageFormatEnviroment generateFTM() {
		return new UserMessageFormatEnviroment(message);
	}
	
	public void add(HttpServletRequest request) {
		try {
			LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
			String username = bean.getUsername(); 
			String content = request.getParameter("ipm_message_content");
			if(username==null) username = ""; 
			if(content==null) content = ""; 
			UserMessage message = new UserMessage();
			message.setMessageContent(content);
			message.setUsername(username);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			message.setMessageId("MESSAGE_"+sdf.format(new Date())+"_"+username);
			messagingController.add(message);
			this.message = messagingController.getMessageDAO().get(message.getMessageId()).getMessage(); 
			notification = "Додавање (нове) поруке успјешно."; 
		}catch(Exception ex) {
			notification = "Додавање (нове) поруке неуспјешно."; 
		}
	}
	
	public void put(HttpServletRequest request) {
		try {
			LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
			String username = bean.getUsername();
			String content = request.getParameter("ipm_message_content");
			String messageId = request.getParameter("ipm_message_id");
			messagingController.update(messageId, username, content);
			this.message = messagingController.getMessageDAO().get(messageId).getMessage(); 
			notification = "Потврда (измјена) поруке успјешна."; 
		}catch(Exception ex) {
			notification = "Потврда (измјена) поруке неуспјешна."; 
		}
	}
	
	public void cancel(HttpServletRequest request) {
		try {
			LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
			String username = bean.getUsername();
			if(username==null) throw new RuntimeException();
			if(username.trim().length()==0) throw new RuntimeException(); 
			String messageId = request.getParameter("ipm_message_id");
			if(messageId==null) messageId = ""; 
			messagingController.delete(messageId);
			this.message = new UserMessage(); 
			notification = "Поништавање (брисање) поруке успјешно."; 
		}catch(Exception ex) {
			notification = "Поништавање (брисање) поруке неуспјешно."; 
		}
	}
	
	public List<UserMessageFormatEnviroment> listUnread() {
		ArrayList<UserMessageFormatEnviroment> result = new ArrayList<>(); 
		for(IPMessageDTO messageDTO: messagingController.getMessageDAO().listUnviewedMessage(message.getUsername())){
			result.add(new UserMessageFormatEnviroment(messageDTO.getMessage())); 
		}
		return result;
	}
	
	public List<UserMessageFormatEnviroment> listRead() {
		ArrayList<UserMessageFormatEnviroment> result = new ArrayList<>();
		for(IPMessageDTO messageDTO: messagingController.getMessageDAO().listReviewedMessage(message.getUsername())) {
			result.add(new UserMessageFormatEnviroment(messageDTO.getMessage())); 
		}
		return result;
	}
	
	public void refresh(HttpServletRequest request) {
		LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
		String username = bean.getUsername();  
		String content = request.getParameter("ipm_message_content");
		if(username==null) username = ""; 
		if(content==null) content = ""; 
		UserMessage message = new UserMessage();
		message.setMessageContent(content);
		message.setUsername(username);
		message.setMessageId("");
		this.message = message; 
	}
	
	public boolean testEmailAddress(String email) {
		if(!email.contains("@")) return false; 
		String parts[] = email.split("@"); 
		if(parts.length!=2) return false; 
		if(parts[0].length()==0) return false; 
		if(parts[1].length()==0) return false;
		if(
			!Character.isUpperCase(parts[0].charAt(0)) &&
			!Character.isLowerCase(parts[0].charAt(0)) &&
			parts[0].charAt(0) != '_'
		) return false; 
		if(
				!Character.isUpperCase(parts[0].charAt(parts[0].length()-1)) &&
				!Character.isLowerCase(parts[0].charAt(parts[0].length()-1)) &&
				!Character.isDigit(parts[0].charAt(parts[0].length()-1)) &&
				parts[0].charAt(parts[0].length()-1) != '_'
		) return false; 
	    if(
			!Character.isUpperCase(parts[1].charAt(0)) &&
			!Character.isLowerCase(parts[1].charAt(0)) &&
			parts[1].charAt(0) != '_'
		) return false; 
		if(
				!Character.isUpperCase(parts[1].charAt(parts[1].length()-1)) &&
				!Character.isLowerCase(parts[1].charAt(parts[1].length()-1)) &&
				!Character.isDigit(parts[1].charAt(parts[1].length()-1)) &&
				parts[1].charAt(parts[1].length()-1) != '_'
		) return false;
		for(int i=1; i<parts[0].length()-1; i++) {
			if(Character.isUpperCase(parts[0].charAt(i))) continue; 
			if(Character.isLowerCase(parts[0].charAt(i))) continue; 
			if(Character.isDigit(parts[0].charAt(i))) continue; 
			if(parts[0].charAt(i)=='_') continue;
			if(parts[0].charAt(i)=='-') continue;
			if(parts[0].charAt(i)=='.') continue;
			return false; 
		}
		for(int i=1; i<parts[1].length()-1; i++) {
			if(Character.isUpperCase(parts[1].charAt(i))) continue; 
			if(Character.isLowerCase(parts[1].charAt(i))) continue; 
			if(Character.isDigit(parts[1].charAt(i))) continue; 
			if(parts[1].charAt(i)=='_') continue;
			if(parts[1].charAt(i)=='-') continue;
			if(parts[1].charAt(i)=='.') continue;
			return false;
		}
		if(email.contains("..")) return false; 
		if(email.contains("--")) return false; 
		if(email.contains(".-")) return false; 
		if(email.contains("-.")) return false; 
		return true;
	}
	
	private String frontalMessageNotification = "";
	
	public String getFrontalMessageNotification() {
		return frontalMessageNotification;
	}

	public void setFrontalMessageNotification(String frontalMessageNotification) {
		if(frontalMessageNotification==null) frontalMessageNotification = ""; 
		this.frontalMessageNotification = frontalMessageNotification;
	}
	
	public void resetFrontalMessageNotification() {
		this.frontalMessageNotification = ""; 
	}

	public void addFrontalMessage(HttpServletRequest request) {
		try {
			String email = request.getParameter("frontal_message_email");
			String content = request.getParameter("frontal_message_content");
			if(email==null) throw new NullPointerException("ZERO");
			if(content==null) throw new NullPointerException("ZERO");
			if(email.trim().length()==0) throw new NullPointerException();
			if(content.trim().length()==0) throw new NullPointerException();
			if(!testEmailAddress(email)) throw new RuntimeException("EMAIL FORMAT FAILED.");
			UserMessage message = new UserMessage();
			message.setMessageContent(content);
			message.setUsername("");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			int counter = 1; 
			do {
				message.setMessageId("MSG_"+sdf.format(new Date())+"_"+String.format("%03d", counter));
				counter++; 
			}while(messagingController.getMessageDAO().get(message.getMessageId())!=null); 
			message.setResponseEmail(email);
			messagingController.addFrontal(message);
			frontalMessageNotification = "Корисничка порука (гост) је послана."; 
		}catch(Exception ex) {
			frontalMessageNotification = "Корисничка порука (гост) није послана."; 
		}
	}
	
	public List<UserMessageFormatEnviroment> listUnreadFiltered(HttpServletRequest request) {
		JsonParser parser = new JsonParser();
		String jsonFilters = request.getParameter("message_preview_mf_data");  
		ArrayList<UserMessageFormatEnviroment> result = new ArrayList<>(); 
		for(IPMessageDTO messageDTO: messagingController.getMessageDAO().listUnviewedMessage()){
			result.add(new UserMessageFormatEnviroment(messageDTO.getMessage())); 
		}
		if(jsonFilters==null) return result; 
		try {
			JsonObject object = parser.parse(jsonFilters).getAsJsonObject();
			JsonArray messageFilters = object.get("msg_filters").getAsJsonArray(); 
			JsonArray messageFiltersExclusivity = object.get("msg_filters_exclusive").getAsJsonArray(); 
			ArrayList<String> inclusiveFilters = new ArrayList<>(); 
			ArrayList<String> exclusiveFilters = new ArrayList<>();
			if(messageFilters.size()!=messageFiltersExclusivity.size()) return result; 
			if(messageFilters.size()==0) return result;
			for(int i=0; i<messageFilters.size(); i++) {
				String messageFilter = messageFilters.get(i).getAsString(); 
				boolean exclusive = messageFiltersExclusivity.get(i).getAsBoolean(); 
				boolean inclusive = !exclusive; 
				if(exclusive) exclusiveFilters.add(messageFilter); 
				if(inclusive) inclusiveFilters.add(messageFilter); 
			}
			ArrayList<UserMessageFormatEnviroment> inclusiveResult = new ArrayList<>();
			ArrayList<UserMessageFormatEnviroment> realResult = new ArrayList<>(); 
			
			inclusiveFiltering: for(UserMessageFormatEnviroment target: result) {
				if(inclusiveFilters.size()==0) {inclusiveResult.add(target); continue inclusiveFiltering;}
				for(String contentPart: inclusiveFilters)
					if(target.getMessage().getMessageContent().contains(contentPart))
						{inclusiveResult.add(target); continue inclusiveFiltering;}
			}
			
			exclusiveFiltering: for(UserMessageFormatEnviroment target: inclusiveResult) {
				for(String contentPart: exclusiveFilters) 
					if(!target.getMessage().getMessageContent().contains(contentPart)) continue exclusiveFiltering; 
				realResult.add(target); 
			}
			
			return realResult;
		}catch(Exception ex) {
			return result; 
		}
	}
	
	public List<UserMessageFormatEnviroment> listReadFiltered(HttpServletRequest request) {
		JsonParser parser = new JsonParser();
		String jsonFilters = request.getParameter("message_preview_mf_data");  
		ArrayList<UserMessageFormatEnviroment> result = new ArrayList<>();
		for(IPMessageDTO messageDTO: messagingController.getMessageDAO().listReviewedMessage()) {
			result.add(new UserMessageFormatEnviroment(messageDTO.getMessage())); 
		}
		if(jsonFilters==null) return result;
		try {
			JsonObject object = parser.parse(jsonFilters).getAsJsonObject();
			JsonArray messageFilters = object.get("msg_filters").getAsJsonArray(); 
			JsonArray messageFiltersExclusivity = object.get("msg_filters_exclusive").getAsJsonArray(); 
			ArrayList<String> inclusiveFilters = new ArrayList<>(); 
			ArrayList<String> exclusiveFilters = new ArrayList<>();
			if(messageFilters.size()!=messageFiltersExclusivity.size()) return result; 
			if(messageFilters.size()==0) return result;
			for(int i=0; i<messageFilters.size(); i++) {
				String messageFilter = messageFilters.get(i).getAsString(); 
				boolean exclusive = messageFiltersExclusivity.get(i).getAsBoolean(); 
				boolean inclusive = !exclusive; 
				if(exclusive) exclusiveFilters.add(messageFilter); 
				if(inclusive) inclusiveFilters.add(messageFilter); 
			}
			ArrayList<UserMessageFormatEnviroment> inclusiveResult = new ArrayList<>();
			ArrayList<UserMessageFormatEnviroment> realResult = new ArrayList<>(); 
			
			inclusiveFiltering: for(UserMessageFormatEnviroment target: result) {
				if(inclusiveFilters.size()==0) {inclusiveResult.add(target); continue inclusiveFiltering;}
				for(String contentPart: inclusiveFilters)
					if(target.getMessage().getMessageContent().contains(contentPart))
						{inclusiveResult.add(target); continue inclusiveFiltering;}
			}
			
			exclusiveFiltering: for(UserMessageFormatEnviroment target: inclusiveResult) {
				for(String contentPart: exclusiveFilters) 
					if(!target.getMessage().getMessageContent().contains(contentPart)) continue exclusiveFiltering; 
				realResult.add(target); 
			}
			
			return realResult;
		}catch(Exception ex) {
			return result; 
		}
	}
	
	public void deleteMessage(HttpServletRequest request) {
		try {
			String messageId = request.getParameter("delete_message_id"); 
			if(messageId==null) return; 
			if(messageId.trim().length()==0) return; 
			messagingController.getMessageDAO().delete(messageId);
		}catch(Exception ex) {
			return; 
		}
	}
}
