package backingBean;
import annotation.KanaPattern;
import db.ProductDb;
import entity.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.servlet.http.Part;
import javax.validation.constraints.Size;
/*
 * ・フィールド変数とアクセサメソッドを持つ
 * ・初期化処理を行う
 * ・ページング処理を行う
 * ・ユーティリティメソッドもスーパークラスに用意する
 */
public class SuperBb implements Serializable {
	/* *****（変数）********************************************/

	  /* ***(商品情報) ************/
		/* 商品名 */
		@Column(nullable = false) @Size(min = 1, max = 30)
		private String name;
		/* 商品名かな */
		@Column(nullable = false) @Size(min = 1, max = 50) @KanaPattern(charaType = "かな")
		private String name_kana;
		/* 商品詳細 */
		@Lob
		private String text;
		/* 価格 */
		@Column(nullable = false)
		private Integer price;
		/* 商品カテゴリ */
		private Map<String, Integer> categories;
		private Integer category;
		/* 商品画像 */
		private Part pic;
		/* ピックアップ情報 */
		private Map<String,AppKind> kinds;
		private AppKind kind = AppKind.NONE;
		
	/* *****（データベース処理）*******************************/
		@EJB
		ProductDb productDb;		// 商品データベース
	/* *****（ユーティリティのインジェクト）********************/
		@Inject
		protected transient Logger log;		// ロガー
	/* *****（初期化）******************************************/
	@PostConstruct
	public void init() {
		categories = new LinkedHashMap<>();			// 並べ替え選択肢
		categories.put("なし", 1);
		categories.put("安い順", 2);
		categories.put("高い順", 3);

		kinds = new LinkedHashMap<>();			// 種類選択肢
		kinds.put("全商品", AppKind.NONE);
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

	public Map<String, Integer> getCategories() {
		return categories;
	}

	public void setCategories(Map<String, Integer> categories) {
		this.categories = categories;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
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