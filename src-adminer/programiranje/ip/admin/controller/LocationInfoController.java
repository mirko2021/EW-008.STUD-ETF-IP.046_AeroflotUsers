package programiranje.ip.admin.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import programiranje.ip.admin.io.LocationBaseDAO;
import programiranje.ip.admin.io.LocationBaseDTO;
import programiranje.ip.admin.model.Location;

/**
 * Информације о контролерима локација. 
 * @author VM
 * @version 1.0
 */
public class LocationInfoController {
	private LocationBaseDAO locationBaseDAO;

	public LocationBaseDAO getLocationBaseDAO() {
		return locationBaseDAO;
	}

	public void setLocationBaseDAO(LocationBaseDAO locationBaseDAO) {
		this.locationBaseDAO = locationBaseDAO;
	}

	public void add(Location location) {
		LocationBaseDTO dto = new LocationBaseDTO();
		dto.setLocationAddress(location.getLocationAddress());
		dto.setLocationId(location.getLocationId());
		dto.setLocationName(location.getLocationName());
		dto.setLocationNote(location.getLocationId());
		locationBaseDAO.insert(dto);
	}
	
	public void remove(String locationName) {
		locationBaseDAO.delete(locationName);
	}
	
	public void put(Location location) {
		LocationBaseDTO dto = new LocationBaseDTO();
		dto.setLocationAddress(location.getLocationAddress());
		dto.setLocationId(location.getLocationId());
		dto.setLocationName(location.getLocationName());
		locationBaseDAO.put(dto);
	}
	
	public Location get(String locationName) {
		LocationBaseDTO dto = locationBaseDAO.get(locationName); 
		if(dto==null) return null;
		Location location = new Location();
		location.setLocationAddress(dto.getLocationAddress());
		location.setLocationId(dto.getLocationId());
		location.setLocationName(dto.getLocationName());
		location.setLocationNotes(dto.getLocationNote());
		return location; 
	}
	
	public List<Location> list(){
		List<Location> list = new ArrayList<>();
		List<LocationBaseDTO> dtos = locationBaseDAO.list();
		for(LocationBaseDTO dto: dtos) {
			Location location = new Location();
			location.setLocationAddress(dto.getLocationAddress());
			location.setLocationId(dto.getLocationId());
			location.setLocationName(dto.getLocationName());
			location.setLocationNotes(dto.getLocationNote());
			list.add(location);
		} 
		Collections.sort(list);
		return list;
	}
	
	
	public List<Location> list(String a2c){
		List<Location> list = new ArrayList<>();
		List<LocationBaseDTO> dtos = locationBaseDAO.list(a2c);
		for(LocationBaseDTO dto: dtos) {
			Location location = new Location();
			location.setLocationAddress(dto.getLocationAddress());
			location.setLocationId(dto.getLocationId());
			location.setLocationName(dto.getLocationName());
			location.setLocationNotes(dto.getLocationNote());
			list.add(location);
		}
		Collections.sort(list);
		return list; 
	}
	
	public void update(String id, Location location) {
		LocationBaseDTO dto = new LocationBaseDTO();
		dto.setLocationAddress(location.getLocationAddress());
		dto.setLocationId(location.getLocationId());
		dto.setLocationName(location.getLocationName());
		dto.setLocationNote(location.getLocationNotes());
		locationBaseDAO.update(id, dto);
	}
}
