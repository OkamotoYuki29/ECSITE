package beans;

import entity.AppKind;
import entity.Product;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class AdminProductBb extends AdminProductSuperBb implements Serializable{
	/* ******（productRegist）**************************************/
	public String registProduct(){
		create();
		return null;
	}
	
	public void create(){
		Product product = new Product(null, null, null, Integer.SIZE, null, null, AppKind.NONE);
		productDb.create(product);
	}
	
}
