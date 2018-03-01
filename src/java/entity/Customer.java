package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="FIT_CUSTOMER")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ID */
	@Id
	private String id;
	/** PW */
	@Column(length = 16, nullable = false) @Pattern(regexp = "^\\w+$")
	private String passwd;
	/** 氏名 */
	@Column(nullable = false)
	private String name;
	/** メールアドレス */
	@Column(nullable = false) @Pattern(regexp = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$")
	private String mail;
	/** ユーザーグループ */
	@OneToOne(cascade = {CascadeType.ALL})
	private AppGroup group;
	/** 宛先情報 */
	@OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
	private Destination destination;
	
/* ****** コンストラクタ *************/
	public Customer() {
	}
	public Customer(String id, String passwd, String name, String mail, AppGroup group, Destination destination) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.mail = mail;
		this.group = group;
		this.destination = destination;
	}
/* ****** ゲッター、セッター *************/	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public AppGroup getGroup() {
		return group;
	}
	public void setGroup(AppGroup group) {
		this.group = group;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
/* ****** その他 *************/	
	@Override
	public int hashCode() {
		int hash = 5;
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
		final Customer other = (Customer) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "Customer{" + "id=" + id + ", passwd=" + passwd + ", name=" + name + ", mail=" + mail + ", group=" + group + ", destination=" + destination + '}';
	}
	
	
}
