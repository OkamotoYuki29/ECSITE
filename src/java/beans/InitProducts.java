package beans;

import db.ProductCategoryDb;
import entity.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import util.BinFileUtil;
import util.Tracer;

@Interceptors(Tracer.class)
@Stateless
public class InitProducts {

    @EJB
    ProductCategoryDb db;
	
	ProductCategory protein = new ProductCategory("タンパク質");
	ProductCategory carbo = new ProductCategory("炭水化物");
	ProductCategory supple = new ProductCategory("サプリ");
	
	
    String[] name = {
					"ホエイプロテイン・コンセントレート", "ホエイプロテイン・アイソレート", "ホエイプロテイン・ハイドロライズド", "カゼインプロテイン", "ソイプロテイン", "ビーフプロテイン", 
					"マルトデキストリン", "デキストロース", "オートミール", "インスタントオーツ", 
					"オメガ3", "スーパーオメガ3", "CLA", "Lカルニチン", "BCAA", "HMB", "ビタミン&ミネラル", "グルタミン"
    };
	String[] nameKana = {
					"ほえいぷろていんこんせんとれーと", "ほえいぷろていんあいそれーと", "ほえいぷろていんはいどろらいずど", "かぜいんぷろていん", "そいぷろていん", "びーふぷろていん", 
					"まるとできすとりん", "できすとろーす", "おーとみーる", "いんすたんとおーつ", 
					"おめがすりー", "すーぱーおめがすりー", "しーえるえー", "えるかるにちん", "びーしーえーえー", "えいちえむびー", "びたみんあんどみねらる", "ぐるたみん"
	};
	String[] text = {
					"（タンパク質含有率85%）スタンダードな製法のホエイプロテイン。", "（タンパク質含有率92%）余計なものを除いた製法のホエイプロテイン", "（タンパク質含有率89%）吸収がはやいホエイプロテイン", "（タンパク質含有率80%）吸収がおそいホエイプロテイン", "（タンパク質含有率65%）大豆で出来たプロテイン。吸収が遅い", "（タンパク質含有率70%）牛肉で出来たプロテイン。ビタミンBなどの栄養素が豊富", 
					"吸収の早い炭水化物。トレ前中にBCAAと一緒に、トレ後にプロテインと一緒に摂取するとよい。", "水溶性食物繊維。美容にもいい。", "大人気の健康食品。オーツ麦でできており、食物繊維が豊富であり、GI値が低い。", "オートミールを摂取しやすいよう粉状にした食品。", 
					"大人気の健康食品。フィッシュオイル。良質な脂質であり、ダイエット中に積極的に摂取したい。", "オメガ3よりも栄養素が豊富", "脂肪が燃えやすくなる脂質として、注目を集めている。", "脂肪が燃えやすくなる脂質として、注目を集めている。", "必須アミノ酸。トレ前やトレ中に摂るのがおすすめ。", "筋肉が減るカタボリックを抑制してくれる。", "三大栄養素の次に大事な栄養素。", "免疫力向上などの効果がある。"
	};
    Integer[] price = {
					1000, 1500, 2000, 1700, 800, 1000,
					500, 500, 500, 650, 
					800, 1000, 800, 800, 2000, 2500, 800, 800
    };
	AppKind[] kind = {
					AppKind.RECOMMEND, AppKind.PREMIUM, AppKind.PREMIUM, AppKind.RECOMMEND, AppKind.NONE, AppKind.NONE, 
					AppKind.RECOMMEND, AppKind.BARGAIN, AppKind.BARGAIN, AppKind.RECOMMEND, 
					AppKind.RECOMMEND, AppKind.PREMIUM, AppKind.BARGAIN, AppKind.BARGAIN, AppKind.RECOMMEND, AppKind.PREMIUM, AppKind.RECOMMEND, AppKind.NONE
	};
    ProductCategory[] categories = {
					protein, protein, protein, protein, protein, protein, 
					carbo, carbo, carbo, carbo, 
					supple, supple, supple, supple, supple, supple, supple, supple
	};

    String[] url = {
		"/resources/images/Product01.jpg", "/resources/images/Product02.jpg", "/resources/images/Product03.jpg", "/resources/images/Product04.jpg", "/resources/images/Product05.jpg", "/resources/images/Product06.jpg", 
		"/resources/images/Product07.jpg", "/resources/images/Product08.jpg", "/resources/images/Product09.jpg", "/resources/images/Product10.jpg",
        "/resources/images/Product11.jpg", "/resources/images/Product12.jpg", "/resources/images/Product13.jpg", "/resources/images/Product14.jpg", "/resources/images/Product15.jpg", "/resources/images/Product16.jpg", "/resources/images/Product17.jpg", "/resources/images/Product18.jpg"
	};

    public void init() {
		List<ProductCategory> nonRepeList = removeRepe(categories);
		
		createAddCategory(nonRepeList);
		
		//nonRepeListをテーブルに追加
		for(ProductCategory addCategory: nonRepeList){  
			db.create(addCategory);
		}
    }
	
	/**
	 * カテゴリリスト配列を重複なしのListに変換するメソッド
	 * @param category
	 * @return 重複のないList
	 */
	private List<ProductCategory> removeRepe(ProductCategory[] category){
		List<ProductCategory> list = new ArrayList<>(Arrays.asList(category));
		return list.stream().distinct().collect(Collectors.toList());
	};
	
	/**
	 * フィールドの配列よりProductエンティティを作成
	 * ProductエンティティをProductCategoryエンティティにセット。
	 * @param nonRepeList 
	 */
	private void createAddCategory(List<ProductCategory> nonRepeList){
			for (int i = 0; i < name.length; i++) {
				// URL ⇒ byte[] に変換
				byte[] pic1 = BinFileUtil.getBinary(url[i]);
				// 配列の要素よりProductを生成
				Product product = new Product(name[i], nameKana[i], text[i], price[i], categories[i], pic1, kind[i]);
 				// 重複なしのカテゴリリスト(nonRepeList)のカテゴリと、
				// productのカテゴリが一致していれば、
				// nonRepeListのカテゴリの、カテゴリのProductリストにproductを追加
				for(int j = 0; j < nonRepeList.size(); j++){
					if(categories[i].equals(nonRepeList.get(j))){
						nonRepeList.get(j).getPro().add(product);
					}
				}
			}
	}

	public ProductCategoryDb getDb() {
		return db;
	}

	public void setDb(ProductCategoryDb db) {
		this.db = db;
	}
	
	
}
