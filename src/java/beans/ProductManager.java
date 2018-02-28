package beans;

import entity.AppKind;
import entity.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import util.Pagenation;
import util.Tracer;


@Stateless
public class ProductManager {
	@PersistenceContext
	EntityManager	em;
	
	public  List<Product> getFromDb(int priceItem, AppKind kindItem, Long category, Pagenation productPage){
		TypedQuery<Product> query = null;
		if(kindItem==AppKind.NONE ){
			if(priceItem==1){
				query = em.createNamedQuery(Product.Qall, Product.class);
			}else if(priceItem==2){
				query = em.createNamedQuery(Product.QallASC, Product.class);
			}else{
				query = em.createNamedQuery(Product.QallDESC, Product.class);
			}
		}else if(category==null){
			if(priceItem==1){
				query = em.createNamedQuery(Product.QKind, Product.class);
			}else if(priceItem==2){
				query = em.createNamedQuery(Product.QkindASC, Product.class);
			}else{
				query = em.createNamedQuery(Product.QkindDESC, Product.class);
			}
			query.setParameter("valueOfKind", kindItem);
		}else{
			if(priceItem==1){
				query = em.createNamedQuery(Product.QCate, Product.class);
			}else if(priceItem==2){
				query = em.createNamedQuery(Product.QCateASC, Product.class);
			}else
				query = em.createNamedQuery(Product.QCateDESC, Product.class);
			query.setParameter("valueOfCate", category);
		}
		query.setFirstResult(productPage.firstResult());
		query.setMaxResults(productPage.maxResult());
		return	query.getResultList();
	}
	
	public void counterClear(AppKind kindItem, Long category, Pagenation productPage){
		TypedQuery<Long> count_query = null;
		if(kindItem==AppKind.NONE ){
			count_query	=	em.createNamedQuery(Product.Count_Qall, Long.class);
		}else if(category == null){
			count_query	=	em.createNamedQuery(Product.Count_QKind, Long.class);
			count_query.setParameter("valueOfKind", kindItem);
		}else{
			count_query	=	em.createNamedQuery(Product.Count_QCate, Long.class);
			count_query.setParameter("valueOfCate", category);
		}
		long countResult = count_query.getSingleResult();
		productPage.setup((int)countResult, 5);
	}

	List<Product> getFromDb(Integer priceItem, AppKind kindItem, Long category) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}


}
