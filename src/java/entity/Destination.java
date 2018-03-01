package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FIT_DESTINATION")
public class Destination implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ID */
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** 宛名 */
	@Column(nullable = false)
	private String addressee;
	/** 郵便番号 */
	@Column(length = 7, nullable = false)
	private Integer postal;
	/** 住所 */
	@Column(nullable = false)
	private String address;
	/** 連絡先番号 */
	@Column(length = 11, nullable = false)
	private String number;
	/** 顧客エンティティ */
	@ManyToOne
	private Customer customer;
/* ****** コンストラクタ *************/
	public Destination() {
	}
	public Destination(Long id, String addressee, Integer postal, String address, String number, Customer customer) {
		this.id = id;
		this.addressee = addressee;
		this.postal = postal;
		this.address = address;
		this.number = number;
		this.customer = customer;
	}
/* ****** ゲッター、セッター *************/	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAddressee() {
		return addressee;
	}
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	public Integer getPostal() {
		return postal;
	}
	public void setPostal(Integer postal) {
		this.postal = postal;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
/* ****** その他 *************/	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 79 * hash + Objects.hashCode(this.id);
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
		final Destination other = (Destination) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "Destination{" + "id=" + id + ", addressee=" + addressee + ", postal=" + postal + ", address=" + address + ", number=" + number + ", customer=" + customer + '}';
	}
	
	
}
