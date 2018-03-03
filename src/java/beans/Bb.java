package beans;
import entity.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
@Named
@SessionScoped
public class Bb extends SuperBb implements Serializable {
	/* ******（content-product）**************************************/
	// 1ページ分の商品データを取得する（content-product.xhtmlから利用される）
	public List<Product> getFromDb() {
		List<Product> ls = null;
		try {
			ls = pm.getFromDb(priceItem, kindItem, category, productPage);
		} catch (Exception e) {
			facesMessage("商品の検索処理でエラーが発生しました");
		}
		return ls;
	}
	// 総件数と最大取得件数を再設定する
	public void counterClear() {
		try {
			pm.counterClear(kindItem, category,productPage);
		} catch (Exception e) {
			facesMessage("商品情報の初期化でエラーが発生しました");
		}
	}
	
	
	/* ***** 未実装 *******/
	public String dispCart(){
		return null;
	}
	public String detail(Product item){
		sel = item;
		return "details.xhtml?faces-redirect=true ";
	}
}
