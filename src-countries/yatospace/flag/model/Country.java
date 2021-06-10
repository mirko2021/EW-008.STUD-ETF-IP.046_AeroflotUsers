package yatospace.flag.model;

import java.io.Serializable;

/**
 * Информације о држави.
 * @author VM
 * @version 1.0
 */
public class Country implements Serializable, Cloneable, Comparable<Country>{
	private static final long serialVersionUID = 2084710590412802413L;
	
	private String alpha2Code = ""; 
	private String alpha3Code = "";
	private String name = "";
	private String flagHref = ""; 
	
	public String getAlpha2Code() {
		return alpha2Code;
	}
	public void setAlpha2Code(String alpha2Code) {
		if(alpha2Code==null) alpha2Code = ""; 
		this.alpha2Code = alpha2Code;
	}
	public String getAlpha3Code() {
		return alpha3Code;
	}
	public void setAlpha3Code(String alpha3Code) {
		if(alpha3Code==null) alpha3Code = ""; 
		this.alpha3Code = alpha3Code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name==null) name="";
		this.name = name;
	}
	public String getFlagHref() {
		return flagHref;
	}
	public void setFlagHref(String flagHref) {
		if(flagHref==null) flagHref=""; 
		this.flagHref = flagHref;
	}
	
	@Override
	public String toString() {
		return alpha2Code+"("+name+")"; 
	}
	
	@Override
	public int hashCode() {
		return alpha2Code.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Country) {
			Country c = (Country) o;
			return alpha2Code.contentEquals(c.alpha2Code);
		}
		return false;
	}
	
	@Override
	public int compareTo(Country o) {
		return alpha2Code.compareTo(o.alpha2Code);
	}
	
	@Override
	public Country clone() {
		Country c = new Country();
		c.alpha2Code = alpha2Code; 
		c.alpha3Code = alpha3Code;
		c.name = name; 
		c.flagHref = c.flagHref;
		return c;
	}
}
