package db;

import javax.ejb.Stateless;
import util.TryCatchDb;

@Stateless
public class TempCustomerDb extends TryCatchDb {
	public TempCustomerDb(){
		super(TempCustomerDb.class);
	}
}
