package yatospace.io.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;
import yatospace.web.bean.UserImagesBean;


/**
 * Преузимање корисничке слике.
 * @author VM
 * @version 1.0
 */
@WebServlet("/UserPictureDownloadServlet")
public class UserPictureDownloadServlet extends HttpServlet {
	public final String LOCATION_DIR_CONFIGURATION = "C:\\Eclipse-Workspaces\\EW-008\\005_UserSpace\\DataImages\\user";
	private static final long serialVersionUID = 1L;
       
    public UserPictureDownloadServlet() {
        super();
        File dir = new File(LOCATION_DIR_CONFIGURATION);
        if(!dir.exists()) dir.mkdirs();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image");
		
		LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
		String username = bean.getUsername(); 
		
		if(username==null) {
			response.sendError(404, "User not found.");
			return; 
		}
		
		UserImagesBean messageBean = (UserImagesBean) request.getSession().getAttribute("userImagesBean");
		if(messageBean==null) messageBean = new UserImagesBean();
		request.getSession().setAttribute("userImagesBean", messageBean);
		
		File image = messageBean.getUserImageLocation(request);
		if(image==null || !image.exists()) {
			response.sendError(404, "Picture not found.");
			return; 
		}
		
		try(FileInputStream fis = new FileInputStream(image)){
			byte[] by = new byte[fis.available()];
			fis.read(by);
			response.getOutputStream().write(by);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
