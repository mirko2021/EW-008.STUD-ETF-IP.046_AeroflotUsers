package yatospace.web.ajax.execute;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import yatospace.web.ajax.anotation.AjaxAccessable;
import yatospace.web.ajax.anotation.AjaxSecurity;
import yatospace.web.ajax.element.AjaxRequestContext;
import yatospace.web.ajax.error.AjaxException;
import yatospace.web.ajax.listener.AjaxRegisterListener;
import yatospace.web.ajax.model.AjaxExecutable;
import yatospace.web.ajax.security.AjaxAccess;

/**
 * Функционалност за решавање АЈАКСа.
 * @author MV
 * @version 1.0 
 */
@WebServlet("/AjaxResolver")
public class AjaxResolver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxResolver() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		
		try {
			JsonParser parser = new JsonParser(); 
			
			String base64 = "";
			
			Scanner scanner = new Scanner(request.getInputStream()); 
			while(scanner.hasNextLine()) base64+=scanner.nextLine()+"\n"; 
			base64 = base64.trim(); 
 
			
			String json = URLDecoder.decode(new String(Base64.getDecoder().decode(base64)),"UTF-8"); 
			JsonObject all = parser.parse(json).getAsJsonObject(); 
			
			JsonObject req = all.get("request").getAsJsonObject(); 
			JsonObject type = all.get("type").getAsJsonObject(); 
			
			AjaxRequestContext context = new AjaxRequestContext();	
			context.setRequest(req);
			
			if(type.get("type").getAsString().equals("GET")) {
				resolveGet(context, request.getSession());
			}else if(type.get("type").getAsString().equals("SET")) {
				resolveSet(context, request.getSession());
			}else if(type.get("type").getAsString().equals("EXE")) {
				resolveExe(context, request.getSession()); 
			}else if(type.get("type").getAsString().equals("RUN")) {
				resolveRun(context, request.getSession());
			}else {
				context.getResponse().get("result").getAsJsonObject().addProperty("success", false);
				context.getResponse().get("result").getAsJsonObject().addProperty("code", "UNDEFINED_ACTION_TYPE");
			}
			
			scanner.close();
			response.getWriter().println(new Gson().toJson(context.getResponseContext())); 
		}catch(Exception ex) {
			response.sendError(404, "Data not found.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void resolveGet(AjaxRequestContext context, HttpSession session) {
		String beanName = context.getRequest().get("beanName").getAsString(); 
		AjaxExecutable executable = AjaxRegisterListener.getAjaxRegister(session).get(beanName); 
		if(executable==null) throw new AjaxException("Resolver not found."); 
		executable.importFrom(context);
	}
	
	public void resolveSet(AjaxRequestContext context, HttpSession session) {
		String beanName = context.getRequest().get("beanName").getAsString(); 
		AjaxExecutable executable = AjaxRegisterListener.getAjaxRegister(session).get(beanName); 
		if(executable==null) throw new AjaxException("Resolver not found."); 
		executable.exportTo(context);
	}
	
	public void resolveExe(AjaxRequestContext context, HttpSession session) {
		String beanName = context.getRequest().get("beanName").getAsString(); 
		String functionName = context.getRequest().get("functionName").getAsString();
		AjaxExecutable executable = AjaxRegisterListener.getAjaxRegister(session).get(beanName); 
		if(executable==null) throw new AjaxException("Resolver not found."); 
		try {
			for(Method method: executable.getClass().getDeclaredMethods()) {
				AjaxAccessable accessible = method.getAnnotation(AjaxAccessable.class);
				AjaxSecurity security = method.getAnnotation(AjaxSecurity.class);
				if(accessible!=null && accessible.value().contentEquals(functionName)) {
					if(security!=null) {
						AjaxAccess access = AjaxRegisterListener.getAjaxAccess(session);
						if(!access.canAccess())
							throw new AjaxException("Ajax secourity, no access."); 
					}
					method.invoke(executable, context); 
					return; 
				}
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
		throw new AjaxException("Function not found."); 
	}
	
	public void resolveRun(AjaxRequestContext context, HttpSession session) {
		String beanName = context.getRequest().get("beanName").getAsString(); 
		String functionName = context.getRequest().get("functionName").getAsString();
		AjaxExecutable executable = AjaxRegisterListener.getAjaxRegister(session).get(beanName); 
		if(executable==null) throw new AjaxException("Resolver not found."); 
		try {
			for(Method method: executable.getClass().getDeclaredMethods()) {
				AjaxAccessable accessible = method.getAnnotation(AjaxAccessable.class); 
				if(accessible!=null && accessible.value().contentEquals(functionName)) {
					method.invoke(executable, context); 
					return; 
				}
			}
		}catch(RuntimeException ex) {
			throw ex; 
		}catch(Exception ex) {
			throw new RuntimeException(ex); 
		}
		throw new AjaxException("Function not found."); 
	}
}
