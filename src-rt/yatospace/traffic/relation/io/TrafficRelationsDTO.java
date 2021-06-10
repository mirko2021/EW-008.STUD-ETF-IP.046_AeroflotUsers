package yatospace.traffic.relation.io;

import java.io.Serializable;

import yatospace.traffic.relation.object.TrafficRelation;

/**
 * Општи модел за саобраћајне везе, при подацима. 
 * @author VM
 * @version 1.0
 */
public class TrafficRelationsDTO implements Serializable{
	private static final long serialVersionUID = 1826998457893375323L;
	
	private TrafficRelation object = new TrafficRelation();

	public TrafficRelation getObject() {
		return object;
	}

	public void setObject(TrafficRelation object) {
		if(object==null) object = new TrafficRelation();
		this.object = object;
	}
}
