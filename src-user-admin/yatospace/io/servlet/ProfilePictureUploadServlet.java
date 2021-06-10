package yatospace.io.servlet;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import yatospace.session.bean.LoginBean;
import yatospace.session.generator.SessionBeansGenerator;
import yatospace.web.bean.UserImagesBean;

/**
 * Подизање профилне слике за корисника. 
 * @author VM
 * @version 1.0
 */
@WebServlet("/ProfilePictureUploadServlet")
public class ProfilePictureUploadServlet extends HttpServlet {
	public final String LOCATION_DIR_CONFIGURATION = "C:\\Eclipse-Workspaces\\EW-008\\005_UserSpace\\DataImages\\profile"; 
	private static final long serialVersionUID = 1L;
   
    public ProfilePictureUploadServlet() {
        super();
        File dir = new File(LOCATION_DIR_CONFIGURATION);
        if(!dir.exists()) dir.mkdirs();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		int maxFileSize = 5000*1024;
		int maxMemSize =  5000*1024;  
		
		String contentType = request.getContentType(); 
		contentType = (contentType==null)? "": contentType; 
		
		
		if(contentType.indexOf("multipart/form-data")>=0) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(maxMemSize);
			
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			
			ServletFileUpload upload = new ServletFileUpload(factory); 
			upload.setSizeMax(maxFileSize);
			
			try {
				List<?> fileItems = upload.parseRequest(request); 
				
				String backward = ""; 
				
				Iterator<?> i = fileItems.iterator(); 
				int count = 0; 
				while(i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					
					if(fi.getFieldName().contentEquals("backward"))
						backward = fi.getString();
					
					if(!fi.isFormField()) {
						if(fi.getSize()==0L) continue; 
						if(fi.getName()==null) continue; 
						if(fi.getName().trim().length()==0) continue;
						count++;
					}
				}
				
				i = fileItems.iterator();
				
				if(count!=1) {
					response.sendError(404, "Non single data upload.");
					return; 
				}
				
				while(i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					
					if(!fi.isFormField()) {
						if(fi.getSize()==0L) continue;
						if(fi.getName()==null) continue;
						if(fi.getName().trim().length()==0) continue;
					
						String fileName = fi.getName(); 
						
						if(fileName==null){
							response.sendError(404, "Filename not found.");
							return;
						}
						if(fileName.trim().length()==0){
							response.sendError(404, "Filename not found.");
							return;
						}
						if(fileName.contains(File.separator)){
							response.sendError(404, "Filename not found.");
							return;
						}
						if(fileName.contains("/")){
							response.sendError(404, "Filename not found.");
							return;
						}
						
						InputStream fileStream = fi.getInputStream();
						byte by[] = new byte[fileStream.available()];
						
						fileStream.read(by);
						
						try(ByteArrayInputStream bais = new ByteArrayInputStream(by)){
							ImageIO.read(bais);			
						}
						
						LoginBean bean = SessionBeansGenerator.loginBean(request.getSession());
						String username = bean.getUsername(); 
						
						if(username==null) throw new RuntimeException();	
						File dir  = new File(LOCATION_DIR_CONFIGURATION);
						File file = new File(dir, username+"."+fi.getName());
						
						UserImagesBean messageBean = (UserImagesBean) request.getSession().getAttribute("userImagesBean");
						if(messageBean==null) messageBean = new UserImagesBean();
						request.getSession().setAttribute("userImagesBean", messageBean);
						
						File current = messageBean.getProfileImageLocation(request); 
						if(current!=null) current.delete();
						
						try(FileOutputStream fos = new FileOutputStream(file)) {
							fos.write(by);
						}
					}
				}
				
				if(backward==null || backward.trim().length()==0) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/util/page-close.jsp"); 
					dispatcher.forward(request, response);
				}else {
					response.sendRedirect(backward);
				}
				
				UserImagesBean messageBean = (UserImagesBean) request.getSession().getAttribute("userImagesBean");
				if(messageBean==null) messageBean = new UserImagesBean();
				request.getSession().setAttribute("userImagesBean", messageBean);
				messageBean.setProfileImageMessage("Постављање слике је успјешно.");
			}catch(Exception ex) {
				response.sendError(404, "MIME Type of data is wrong, action not found.");
				return; 
			}
		}else {
			response.sendError(404, "MIME Type of data is wrong, action not found.");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
