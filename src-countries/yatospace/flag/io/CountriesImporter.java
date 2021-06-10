package yatospace.flag.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import yatospace.flag.model.Country;

/**
 * Контролер који се односи на увоз сачуваних карактеристика и података о државама. 
 * @author VM
 * @version 1.0
 */
public class CountriesImporter {
	public final static String IMPORT_DATA_DIR = "C:\\Eclipse-Workspaces\\EW-008\\003_YatospaceZastava\\StorageImage"; 
	public final static String IMPORT_IMAGE_DIR = "C:\\Eclipse-Workspaces\\EW-008\\003_YatospaceZastava\\StorageData"; 
	
	public List<String> listA2C(){
		ArrayList<String> list = new ArrayList<>();
		File dataDir = new File(IMPORT_DATA_DIR);
		for(File file: dataDir.listFiles()) 
			list.add(file.getName().substring(0, file.getName().length()-".json".length()));
		return list;
	}
	
	public Country dataFor(String a2c) {
		try {
			File dataDir = new File(IMPORT_DATA_DIR);
			File targetFile = new File(dataDir, a2c+".json"); 
			if(!targetFile.exists()) return null; 
			Gson gson = new Gson();
			try(FileInputStream fis = new FileInputStream(targetFile)){
				return gson.fromJson(new InputStreamReader(fis,"UTF-8"), Country.class);
			}
		}catch(Exception ex) {
			return null;
		}
	}
	
	public int countA2C() {
		return listA2C().size(); 
	}
	
	public byte[] imageFor(String a2c) {
		try {
			File dataDir = new File(IMPORT_IMAGE_DIR);
			File targetFile = new File(dataDir, a2c+".json");
			if(!targetFile.exists()) return null; 
			try(FileInputStream fis = new FileInputStream(targetFile)){
				return fis.readAllBytes(); 
			}
		}catch(Exception ex) {
			return null;
		}
	}
}
