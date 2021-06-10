package yatospace.traffic.relation.controller;

import yatospace.traffic.relation.io.TrafficRelationsDAO;
import yatospace.traffic.relation.io.TrafficRelationsDTO;
import yatospace.traffic.relation.object.TrafficRelation;

/**
 * Уопштена контрола када је у питању машинерија за 
 * баратањем, уносом, измјенама брисањем релација саобраћања. 
 * @author VM
 * @version 1.0
 */
public class TrafficRelationIOEngine {
	private TrafficRelationsDAO dao;

	public TrafficRelationIOEngine(TrafficRelationsDAO dao) {
		this.dao = dao; 
	}
	
	public TrafficRelationsDAO getDao() {
		return dao;
	}
	
	public synchronized boolean insert(TrafficRelation rt) {
		try {
			TrafficRelation rtr = rt.swapCopy(); 
			rt.setRtWord(rt.getRtWord()+"-ОП");
			if(dao.get(rt.getRtWord())!=null) return false;
			if(dao.get(rtr.getRtWord())!=null) return false;
			TrafficRelationsDTO rtDTO  = new TrafficRelationsDTO();
			TrafficRelationsDTO rtrDTO = new TrafficRelationsDTO();
			rtDTO.setObject(rt);
			rtrDTO.setObject(rtr);
			dao.insert(rtDTO);
			dao.insert(rtrDTO);
			return true;
		}catch(Exception ex) {
			throw new RuntimeException();
		}
	}
	
	public synchronized boolean delete(String rtWord) {
		try {
			if(!rtWord.endsWith("-ОП") && !rtWord.endsWith("-ПП")) 
				 dao.erase(rtWord);
			else {
				rtWord = rtWord.substring(0, rtWord.length()-3); 
				if(dao.get(rtWord+"-ОП")!=null) dao.erase(rtWord+"-ОП");
				if(dao.get(rtWord+"-ПП")!=null) dao.erase(rtWord+"-ПП");
				return true; 
			}
			return true;
		}catch(Exception ex) {
			return false; 
		}
	}
	
	public synchronized boolean update(String rtWord, TrafficRelation rt) {
		try {
			if(dao.get(rtWord)==null) return false;
			if(dao.get(rt.getRtWord()+"-ОП")!=null) return false; 
			if(dao.get(rt.getRtWord()+"-ПП")!=null) return false;
			if(rtWord.contentEquals(rt.getRtWord()))
				if(rtWord.endsWith("-ОП") || rtWord.endsWith("-ПП"))
					rt.setRtWord(rtWord.substring(0, rtWord.length()-3));
			delete(rtWord);
			insert(rt);
			return true; 
		}catch(Exception ex) {
			return false;
		}
	}
}
