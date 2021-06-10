package yatospace.traffic.relation.bean;

import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import programiranje.ip.admin.listener.DatabaseListener;
import studiranje.ip.admin.jsp.bean.LocationBean;
import yatospace.traffic.relation.controller.TrafficRelationIOEngine;
import yatospace.traffic.relation.io.TrafficRelationsDTO;
import yatospace.traffic.relation.object.TrafficRelation;

/**
 * Зрно које се користи за баратање саобраћајем и везама. 
 * @author VM
 * @version 1.0
 */
public class TrafficRelationsBean implements Serializable{
	private static final long serialVersionUID = 8210692228616159935L;
	private String message = ""; 
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if(message==null) message = "";
		this.message = message;
	}
	
	public void resetMessage() {
		this.message = ""; 
	}

	public List<TrafficRelation> list(HttpSession session){
		try {
			String place = ((LocationBean)session.getAttribute("locationTrafficRelations")).getLocationValue(session); 
			ArrayList<TrafficRelation> result = new ArrayList<>();
			for(TrafficRelationsDTO dto: DatabaseListener.getRt(session).list(place))
				result.add(dto.getObject());
			return result; 
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public List<String> listPassbook(HttpSession session){
		try {
			ArrayList<String> result = new ArrayList<>();
			for(String dto: DatabaseListener.getRt(session).select())
				result.add(dto);
			return result; 
		}catch(Exception ex) {
			return new ArrayList<>();
		}
	}
	
	public TrafficRelation get(HttpSession session, String rtWord) {
		try {
			return DatabaseListener.getRt(session).get(rtWord).getObject(); 
		}catch(Exception ex) {
			return null;
		}
	}
	
	public void update(HttpServletRequest request) {
		try {request.setCharacterEncoding("UTF-8");}catch(Exception ex) {}
		String place       = unescapeHTML(request.getParameter("rt_place_fm").trim());
		String rtWord      = unescapeHTML(request.getParameter("rt_word_fm").trim()); 
		String rtNumber    = unescapeHTML(request.getParameter("rt_number_fm").trim()); 
		String rtType      = unescapeHTML(request.getParameter("rt_type_fm").trim());
		String rtSubtype   = unescapeHTML(request.getParameter("rt_subtype_fm").trim());
		String relation    = unescapeHTML(request.getParameter("rt_relation_fm").trim()); 
		String outcome     = unescapeHTML(request.getParameter("rt_outcome_fm").trim());
		String income      = unescapeHTML(request.getParameter("rt_income_fm").trim());
		String direction   = unescapeHTML(request.getParameter("rt_direction_fm").trim());
		String destination = unescapeHTML(request.getParameter("rt_destination_fm").trim());
		String notes       = unescapeHTML(request.getParameter("rt_notes_fm"));
		String rtWordOld   = unescapeHTML(request.getParameter("rt_word_fm_old").trim()); 
		TrafficRelationIOEngine engine = new TrafficRelationIOEngine(DatabaseListener.getRt(request.getSession()));
		TrafficRelation rt = new TrafficRelation();
		rt.setDestination(destination);
		rt.setDirection(direction);
		rt.setIncome(income);
		rt.setRelation(relation);
		rt.setRtNotes(notes);
		rt.setRtNumber(rtNumber);
		rt.setPlace(place);
		rt.setRelation(relation);
		rt.setRtWord(rtWord);
		rt.setOutcome(outcome);
		rt.setRtType(rtType);
		rt.setRtSubType(rtSubtype);
		boolean result = engine.update(rtWordOld, rt);
		if(result==true) message = "измјена података релације успјешна."; 
		else message = "измјена података релације неуспјешна."; 
	}
	
	public void erase(HttpServletRequest request) {
		try {request.setCharacterEncoding("UTF-8");}catch(Exception ex) {}
		String rtWordOld   = unescapeHTML(request.getParameter("rt_word_fm_old").trim()); 
		TrafficRelationIOEngine engine = new TrafficRelationIOEngine(DatabaseListener.getRt(request.getSession()));
		boolean result = engine.delete(rtWordOld);
		if(result==true) message = "брисање података релације успјешно."; 
		else message = "брисање података релације неуспјешно.";
	}
	
	public String unescapeHTML(String htmlEscaped) {
		return htmlEscaped.replaceAll("&#039;", "'").replaceAll("&quot;", "\"").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&");
	}
	
	public void insert(HttpServletRequest request) {
		try {request.setCharacterEncoding("UTF-8");}catch(Exception ex) {}
		String place       = unescapeHTML(request.getParameter("rt_place_fm").trim());
		String rtWord      = unescapeHTML(request.getParameter("rt_word_fm").trim()); 
		String rtNumber    = unescapeHTML(request.getParameter("rt_number_fm").trim()); 
		String rtType      = unescapeHTML(request.getParameter("rt_type_fm").trim());
		String rtSubtype   = unescapeHTML(request.getParameter("rt_subtype_fm").trim());
		String relation    = unescapeHTML(request.getParameter("rt_relation_fm").trim()); 
		String outcome     = unescapeHTML(request.getParameter("rt_outcome_fm").trim());
		String income      = unescapeHTML(request.getParameter("rt_income_fm").trim());
		String direction   = unescapeHTML(request.getParameter("rt_direction_fm").trim());
		String destination = unescapeHTML(request.getParameter("rt_destination_fm").trim());
		String notes       = unescapeHTML(request.getParameter("rt_notes_fm"));
		TrafficRelationIOEngine engine = new TrafficRelationIOEngine(DatabaseListener.getRt(request.getSession()));
		TrafficRelation rt = new TrafficRelation();
		rt.setDestination(destination);
		rt.setDirection(direction);
		rt.setIncome(income);
		rt.setRelation(relation);
		rt.setRtNotes(notes);
		rt.setRtNumber(rtNumber);
		rt.setPlace(place);
		rt.setRelation(relation);
		rt.setRtWord(rtWord);
		rt.setOutcome(outcome);
		rt.setRtType(rtType);
		rt.setRtSubType(rtSubtype);
		boolean result = engine.insert(rt);
		if(result==true) message = "унос података релације успјешан.";
		else message = "унос података релације неуспјешан.";
	}
	
	public String urlEncode(String code) {
		try {
			return URLEncoder.encode(code, "UTF-8");
		}catch(Exception ex) {
			return code; 
		}
	}
}
