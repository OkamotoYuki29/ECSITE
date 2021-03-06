package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * カテゴリは追加できる仕様のため、enum型にはしていません。<br/>
 * サブカテゴリを追加する(OneToMany)可能性があるため、テーブルに保存。
 * @author 岡本　侑貴
 */

/** 商品カテゴリエンティティ */
@Entity
@Table(name = "FIT_PRODUCT_CATEGORY")
public class ProductCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** 商品カテゴリ名 */
	private String cateName;

	/** 商品情報 */
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL)
	private List<Product> pro = new ArrayList<>();

	/* ***** コンストラクタ *******/
	public ProductCategory() {
	}
	public ProductCategory(String cateName) {
		this.cateName = cateName;
	}
	/* ***** getter,setter *******/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public List<Product> getPro() {
		return pro;
	}

	public void setPro(List<Product> pro) {
		this.pro = pro;


	}

	@Override
	public String toString() {
		return "ProductCategory{" + "cateName=" + cateName + '}';
	}




}
