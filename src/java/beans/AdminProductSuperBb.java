package beans;
import db.ProductCategoryDb;
import db.ProductDb;
import entity.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.Lob;
import javax.servlet.http.Part;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
/*
 * ・フィールド変数とアクセサメソッドを持つ
 * ・初期化処理を行う
 * ・ページング処理を行う
 * ・ユーティリティメソッドもスーパークラスに用意する
 */
public class AdminProductSuperBb implements Serializable {
	/* *****（変数）********************************************/

	  /* ***(商品情報) ************/
		/* 商品名 */
		@Size(min = 1, max = 30)
		protected String name;
		/* 商品名かな */
		@Size(min = 1, max = 50) @Pattern(regexp = "^[ぁ-ん]+$")
		protected String name_kana;
		/* 商品詳細 */
		@Lob
		protected String text;
		/* 価格 */
		protected Integer price;
		/* 商品カテゴリ */
		protected Map<String, Long> categories;
		protected Long category;
		/* 商品画像 */
		protected Part pic;
		/* ピックアップ情報 */
		protected Map<String,AppKind> kinds;
		protected AppKind kind = AppKind.NONE;
		
	/* *****（データベース処理）*******************************/
		@EJB
		protected ProductDb productDb;		// 商品データベース
		@EJB
		protected ProductCategoryDb productCateDb;		// 商品カテゴリデータベース
	/* *****（ユーティリティのインジェクト）********************/
		@Inject
		protected transient Logger log;		// ロガー
		@EJB
		protected InitProducts productBundle;		// 商品情報一括登録
	/* *****（初期化）******************************************/
		@PostConstruct
		public void init() {
			categories = new LinkedHashMap<>();		// カテゴリ選択肢
			List<ProductCategory> list = productCateDb.getAll();
			for(ProductCategory cate : list){
				String key = cate.getCateName();
				Long value = cate.getId();
				categories.put(key, value);
			}

			kinds = new LinkedHashMap<>();			// 種類選択肢
			kinds.put("なし", AppKind.NONE);
			kinds.put("おすすめ品", AppKind.RECOMMEND);
			kinds.put("超安値", AppKind.BARGAIN);
			kinds.put("プレミアム商品", AppKind.PREMIUM);

		}
	/* *****（getter setter）******************************************/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_kana() {
		return name_kana;
	}

	public void setName_kana(String name_kana) {
		this.name_kana = name_kana;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Map<String, Long> getCategories() {
		return categories;
	}

	public void setCategories(Map<String, Long> categories) {
		this.categories = categories;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Part getPic() {
		return pic;
	}

	public void setPic(Part pic) {
		this.pic = pic;
	}

	public Map<String, AppKind> getKinds() {
		return kinds;
	}

	public void setKinds(Map<String, AppKind> kinds) {
		this.kinds = kinds;
	}

	public AppKind getKind() {
		return kind;
	}

	public void setKind(AppKind kind) {
		this.kind = kind;
	}

	public ProductDb getProductDb() {
		return productDb;
	}

	public void setProductDb(ProductDb productDb) {
		this.productDb = productDb;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}
	
}