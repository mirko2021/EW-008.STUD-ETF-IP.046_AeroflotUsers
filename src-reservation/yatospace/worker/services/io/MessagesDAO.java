package yatospace.worker.services.io;

import programiranje.ip.admin.database.YatospaceDBConnectionPool;

/**
 * Опис о порукама. 
 * @author VM
 * @version 1.0
 */
public class MessagesDAO {
	private YatospaceDBConnectionPool pool;
	
	public MessagesDAO(YatospaceDBConnectionPool pool) {
		if(pool==null) throw new NullPointerException();
		this.pool = pool; 
	}

	public YatospaceDBConnectionPool getPool() {
		return pool;
	}
}
