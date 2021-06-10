package yatospace.flag.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import com.google.gson.Gson;

import yatospace.flag.model.Country;

/**
 * Чување карактеристика о државама.
 * @author VM
 * @version 1.0
 */
public class CountriesExporter {
	public final static String EXPORT_IMAGE_DIR = "C:\\Eclipse-Workspaces\\EW-008\\003_YatospaceZastava\\StorageImage"; 
	public final static String EXPORT_DATA_DIR = "C:\\Eclipse-Workspaces\\EW-008\\003_YatospaceZastava\\StorageData"; 
	
	public void store(Country c) {
		if(c==null) return; 
		if(!new File(EXPORT_IMAGE_DIR).exists()) new File(EXPORT_IMAGE_DIR).mkdirs();
		if(!new File(EXPORT_DATA_DIR).exists()) new File(EXPORT_DATA_DIR).mkdirs();
		File dataFile = new File(EXPORT_IMAGE_DIR, c.getAlpha2Code()+".json");
		File imageFile = new File(EXPORT_DATA_DIR, c.getAlpha2Code()+".svg");
		if(dataFile.exists()) dataFile.delete(); 
		if(imageFile.exists()) imageFile.delete(); 
		Gson gson = new Gson();
		try(FileOutputStream fos = new FileOutputStream(dataFile)){
			fos.write(gson.toJson(c).getBytes("UTF-8")); 
		}catch(Exception ex) {
			throw new RuntimeException();
		}
		try(FileOutputStream fos = new FileOutputStream(imageFile)){
			URL url = new URL(c.getFlagHref()); 
			try(InputStream is = url.openStream()){
				fos.write(is.readAllBytes());
			}
		}catch(Exception ex) {
			throw new RuntimeException();
		}
	}
}
