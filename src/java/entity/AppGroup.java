package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class AppGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private GroupKey groupKey;
	@OneToOne(mappedBy = "group")
	private Customer customer;
	
/* ****** コンストラクタ *************/
	public AppGroup() {
	}
	public AppGroup(String groupId, Customer customer){
		this.groupKey = new GroupKey(groupId, customer.getId());
	}
	public AppGroup(GroupKey groupKey, Customer customer) {
		this.groupKey = groupKey;
		this.customer = customer;
	}
/* ****** ゲッター、セッター *************/
	public GroupKey getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(GroupKey groupKey) {
		this.groupKey = groupKey;
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
		int hash = 5;
		hash = 13 * hash + Objects.hashCode(this.groupKey);
		hash = 13 * hash + Objects.hashCode(this.customer);
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
		final AppGroup other = (AppGroup) obj;
		if (!Objects.equals(this.groupKey, other.groupKey)) {
			return false;
		}
		if (!Objects.equals(this.customer, other.customer)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "AppGroup{" + "groupKey=" + groupKey + ", customer=" + customer + '}';
	}
	
	
}
