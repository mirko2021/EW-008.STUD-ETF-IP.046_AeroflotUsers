package yatospace.web.socket.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

import javax.websocket.Session;

import yatospace.web.socket.server.ServerTimeWebSocket;

/**
 * Задатак за часовник, који се односи на пропагирање 
 * северског времена на веб прикључнице, односно 
 * странице сваке секунде. 
 * @author mirko
 * @version 1.0
 */
public class ServerTimePropagationTask extends TimerTask{	
	@Override
	public void run() {
		for(Session session : new ArrayList<>(ServerTimeWebSocket.sessions)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			session.getAsyncRemote().sendText(sdf.format(new Date())); 
		}
	}
}
