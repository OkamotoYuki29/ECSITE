package db;

import javax.ejb.Stateless;
import util.TryCatchDb;

@Stateless
public class CustomerDb extends TryCatchDb {
	public CustomerDb(){
		super(CustomerDb.class);
	}
}
