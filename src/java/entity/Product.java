package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Column(name = "name_kana", nullable = false)
	@Size(max = 50)
	private String nameKana;
	
	/** 価格 */
	@Column(nullable = false)
	private Integer price;
	
	/** 商品カテゴリ */
	@ManyToOne
	private ProductCategory category;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
