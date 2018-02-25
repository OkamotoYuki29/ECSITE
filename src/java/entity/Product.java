package entity;

import annotation.KanaPattern;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author 岡本　侑貴
 */

/** 商品エンティティ */
@Entity
@Table(name="FIT_PRODUCT")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/** 商品名 */
	@Column(nullable = false)
	@Size(max = 30)
	private String name;
	
	/** 商品名かな */
	@Column(nullable = false)
	@Size(max = 50)
	@KanaPattern(charaType = "かな")
	private String name_kana;
	
	/** 商品詳細 */
	@Lob
	private String text;
	
	/** 価格 */
	@Column(nullable = false)
	private Integer price;
	
	/** 商品カテゴリ */
	@ManyToOne
	private ProductCategory category;
	
	/** 商品画像 小 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] pic_S;
	
	/** 商品画像 大 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] pic_L;
	
	/** ピックアップ情報 */
	@Enumerated(EnumType.STRING)
	private AppKind kind;
	
	
	
	/** getter setter */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public byte[] getPic_S() {
		return pic_S;
	}

	public void setPic_S(byte[] pic_S) {
		this.pic_S = pic_S;
	}

	public byte[] getPic_L() {
		return pic_L;
	}

	public void setPic_L(byte[] pic_L) {
		this.pic_L = pic_L;
	}

	public AppKind getKind() {
		return kind;
	}

	public void setKind(AppKind kind) {
		this.kind = kind;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 67 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Product other = (Product) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", kind=" + kind + '}';
	}
	
	
	
}
