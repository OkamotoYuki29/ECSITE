
package db;

import entity.*;
import javax.ejb.Stateless;
import util.TryCatchDb;

@Stateless
public class ProductCategoryDb extends TryCatchDb{

	public ProductCategoryDb() {
		super(ProductCategory.class);
	}

}
