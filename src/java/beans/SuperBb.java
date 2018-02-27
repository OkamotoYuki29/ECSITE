package beans;
import db.*;
import entity.*;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import util.Pagenation;
/*
 * ・フィールド変数とアクセサメソッドを持つ
 * ・初期化処理を行う
 * ・ページング処理を行う
 * ・ユーティリティメソッドもスーパークラスに用意する
 */
public class SuperBb implements Serializable {
	/* *****（変数）********************************************/
	protected Map<String, Integer> priceItems;	// 並べ替え選択肢	
	protected Map<String, AppKind> kindItems;	// 種類選択肢
	protected AppKind kindItem = AppKind.NONE;	// 種類選択結果
	protected Map<String, Long> categories;		//カテゴリ選択肢
	protected Long category;					//カテゴリ選択結果
	protected Integer priceItem = 1;			// 並べ替え選択結果

	protected Product sel;						// 詳細画面に表示する商品						

	/* *****（データベース処理）*******************************/
	@EJB
	ProductDb productDb;		// 商品データベース
	@EJB
	protected ProductCategoryDb productCateDb;		// 商品カテゴリデータベース
	/* *****（ユーティリティのインジェクト）********************/
	@EJB
	ProductManager pm;					// 商品マネージャー
	@Inject
	protected transient Logger log;		// ロガー
	@Inject
	protected Pagenation productPage;	// ページングマネージャー
	/* *****（初期化）******************************************/
	@PostConstruct
	public void init() {

		priceItems = new LinkedHashMap<>();			// 並べ替え選択肢
		priceItems.put("なし", 1);
		priceItems.put("安い順", 2);
		priceItems.put("高い順", 3);

		kindItems = new LinkedHashMap<>();			// 種類選択肢
		kindItems.put("全商品", AppKind.NONE);
		kindItems.put("おすすめ品", AppKind.RECOMMEND);
		kindItems.put("超安値", AppKind.BARGAIN);
		kindItems.put("プレミアム商品", AppKind.PREMIUM);
		
		categories = new LinkedHashMap<>();		// カテゴリ選択肢
		List<ProductCategory> list = productCateDb.getAll();
		for(ProductCategory cate : list){
			String key = cate.getCateName();
			Long value = cate.getId();
			categories.put(key, value);
		}

		productPage.setup(productDb.dataCount(), 5);// ページングマネージャー
	}
	/* *****（画像表示処理＜小画像＞）************************/
	public StreamedContent getPic() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			ExternalContext sv = context.getExternalContext();
			Map<String, String> map = sv.getRequestParameterMap();
			String key = map.get("productId");
			Product e = (Product) (productDb.find(Long.valueOf(key)));
			ByteArrayInputStream in = new ByteArrayInputStream(e.getPic());
			DefaultStreamedContent ds = new DefaultStreamedContent(in);
			return ds;
		}
	}
	/* *****（メッセージを作成しキューに入れる）**************/
	public void facesMessage(String s) {
		FacesMessage msg = new FacesMessage(s);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	/* *****（エラーメッセージを作成しキューに入れる）**************/
	public void facesErrorMsg(String s) {
		FacesMessage msg = new FacesMessage(s);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	/* *****（メッセージを作成しキューに入れる）*****************
		FacesMessage.SEVERITY_FATAL		致命的エラー(4)
		FacesMessage.SEVERITY_ERROR		エラー(3)
		FacesMessage.SEVERITY_WARN		警告(2)
		FacesMessage.SEVERITY_WARN		情報(1)   
	*************************************************************/
	public void facesMessage(FacesMessage.Severity severity, String s) {
		FacesMessage msg = new FacesMessage(s);
		msg.setSeverity(severity);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	/* *****（サーブレット環境を取得する）**************/
	public ExternalContext getServlet() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	/* *****（リクエストオブジェクトを取得する）**************/
	public HttpServletRequest getRequest() {
		return (HttpServletRequest) getServlet().getRequest();
	}
	/* *****（ログインしているユーザーのIDを返す */
	public String getUserId() {
		return getRequest().getRemoteUser();
	}
	/* *****（セッターとゲッター）***************************/

	public Map<String, Integer> getPriceItems() {
		return priceItems;
	}

	public void setPriceItems(Map<String, Integer> priceItems) {
		this.priceItems = priceItems;
	}

	public Map<String, AppKind> getKindItems() {
		return kindItems;
	}

	public void setKindItems(Map<String, AppKind> kindItems) {
		this.kindItems = kindItems;
	}

	public AppKind getKindItem() {
		return kindItem;
	}

	public void setKindItem(AppKind kindItem) {
		this.kindItem = kindItem;
	}

	public Integer getPriceItem() {
		return priceItem;
	}

	public void setPriceItem(Integer priceItem) {
		this.priceItem = priceItem;
	}

	public Product getSel() {
		return sel;
	}

	public void setSel(Product sel) {
		this.sel = sel;
	}

	public ProductDb getProductDb() {
		return productDb;
	}

	public void setProductDb(ProductDb productDb) {
		this.productDb = productDb;
	}

	public ProductManager getPm() {
		return pm;
	}

	public void setPm(ProductManager pm) {
		this.pm = pm;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public Pagenation getProductPage() {
		return productPage;
	}

	public void setProductPage(Pagenation productPage) {
		this.productPage = productPage;
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
	
}
