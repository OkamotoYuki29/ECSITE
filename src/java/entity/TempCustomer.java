package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 *
 * @author 岡本　侑貴
 */
@Entity
@Table(name="FIT_TEMP_CUSTOMER")
public class TempCustomer implements Serializable {
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
	/* トークン */
	private String token;

/* ****** コンストラクタ *************/
	public TempCustomer() {
	}
	public TempCustomer(String id, String passwd, String name, String mail, String token) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.mail = mail;
		this.token = token;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
/* ****** その他 *************/
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 53 * hash + Objects.hashCode(this.id);
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
		final TempCustomer other = (TempCustomer) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "TemporaryCustomer{" + "id=" + id + ", passwd=" + passwd + ", name=" + name + ", mail=" + mail + ", token=" + token + '}';
	}
}
