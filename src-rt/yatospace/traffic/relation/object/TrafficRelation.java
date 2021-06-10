package yatospace.traffic.relation.object;

import java.io.Serializable;
import java.net.URLEncoder;

public class TrafficRelation implements Serializable, Cloneable, Comparable<TrafficRelation>{
	private static final long serialVersionUID = 1L;
	private String rtWord = ""; 
	private String place = "";
	private String rtNumber = ""; 
	private String rtType = ""; 
	private String rtSubType = ""; 
	private String relation = ""; 
	private String income = ""; 
	private String outcome = ""; 
	private String direction = "";
	private String location = ""; 
	private String destination = "";
	private String rtNotes = ""; 
	
	public String getRtWord() {
		return rtWord;
	}

	public String getPlace() {
		return place;
	}

	public String getRtNumber() {
		return rtNumber;
	}

	public String getRtType() {
		return rtType;
	}

	public String getRtSubType() {
		return rtSubType;
	}

	public String getRelation() {
		return relation;
	}

	public String getIncome() {
		return income;
	}

	public String getOutcome() {
		return outcome;
	}

	public String getDirection() {
		return direction;
	}

	public String getLocation() {
		return location;
	}

	public String getDestination() {
		return destination;
	}

	public String getRtNotes() {
		return rtNotes;
	}

	public void setRtWord(String rtWord) {
		if(rtWord==null) rtWord = ""; 
		this.rtWord = rtWord;
	}

	public void setPlace(String place) {
		if(place==null) place = ""; 
		this.place = place;
	}

	public void setRtNumber(String rtNumber) {
		if(rtNumber==null) rtNumber=""; 
		this.rtNumber = rtNumber;
	}

	public void setRtType(String rtType) {
		if(rtType==null) rtType=""; 
		this.rtType = rtType;
	}

	public void setRtSubType(String rtSubType) {
		if(rtSubType=="") rtSubType = ""; 
		this.rtSubType = rtSubType;
	}

	public void setRelation(String relation) {
		if(relation=="") relation = ""; 
		this.relation = relation;
	}

	public void setIncome(String income) {
		if(income==null) income = ""; 
		this.income = income;
	}

	public void setOutcome(String outcome) {
		if(outcome==null) outcome = ""; 
		this.outcome = outcome;
	}

	public void setDirection(String direction) {
		if(direction==null) direction = ""; 
		this.direction = direction;
	}

	public void setLocation(String location) {
		if(location==null) location = ""; 
		this.location = location;
	}

	public void setDestination(String destination) {
		if(destination==null) destination = ""; 
		this.destination = destination;
	}
	
	public void setRtNotes(String rtNotes) {
		this.rtNotes = rtNotes;
	}

	@Override
	public String toString() {
		return rtWord;
	}
	
	@Override 
	public int hashCode() {
		return rtWord.hashCode();
	}
	
	@Override
	public int compareTo(TrafficRelation o) {
		return rtWord.compareTo(o.rtWord);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof TrafficRelation) {
			TrafficRelation rt = (TrafficRelation) o; 
			return rtWord.contentEquals(rt.rtWord); 
		}
		return false; 
	}
	
	@Override
	public TrafficRelation clone() {
		TrafficRelation rt = new TrafficRelation(); 
		rt.destination = destination; 
		rt.direction   = direction; 
		rt.rtNumber    = rtNumber; 
		rt.income      = income; 
		rt.location    = location; 
		rt.outcome     = outcome; 
		rt.place       = place; 
		rt.relation    = relation;
		rt.rtSubType   = rtSubType;
		rt.rtType      = rtType;
		rt.rtWord      = rtWord; 
		rt.rtNotes     = rtNotes; 
		return rt; 
	}
	
	public TrafficRelation swapCopy() {
		TrafficRelation rt = new TrafficRelation(); 
		rt.destination = place; 
		rt.rtNumber    = rtNumber; 
		rt.direction   = direction; 
		rt.income      = income;
		rt.location    = location; 
		rt.outcome     = outcome; 
		rt.place       = destination; 
		rt.relation    = relation;
		rt.rtSubType   = rtSubType;
		rt.rtType      = rtType;
		rt.rtWord      = rtWord + "-ПП"; 
		rt.rtNotes     = rtNotes; 
		if(direction.trim().contentEquals("долазак")) rt.direction = "одлазак"; 
		if(direction.trim().contentEquals("одлазак")) rt.direction = "долазак";
		return rt; 
	}
	
	public void reset() {
		destination = ""; 
		direction   = ""; 
		income      = ""; 
		location    = ""; 
		outcome     = ""; 
		place       = ""; 
		relation    = "";
		rtSubType   = "";
		rtType      = "";
		rtWord      = "";
		rtNotes     = ""; 
	}
	
	public void apply(TrafficRelation rt) {
		if(rt==null) {reset(); return; }
		destination = rt.destination; 
		direction   = rt.direction; 
		income      = rt.location; 
		location    = rt.location; 
		outcome     = rt.outcome; 
		place       = rt.place; 
		relation    = rt.relation;
		rtSubType   = rt.rtSubType;
		rtType      = rt.rtType;
		rtWord      = rt.rtWord;
		rtNotes     = rt.rtNotes; 
	}
	
	public boolean empty() {
		return rtWord.trim().length()==0;
	}
	
	public String urlRtWord() {
		try {
			return URLEncoder.encode(rtWord, "UTF-8"); 
		}catch(Exception ex) {
			return ""; 
		}
	}
}
