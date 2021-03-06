package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Embeddable
public class GroupKey implements Serializable {
	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.STRING)
	private AppGroupId groupId;
	private String customerId;

/* ****** コンストラクタ *************/
	public GroupKey() {
	}
	public GroupKey(AppGroupId groupId, String customerId) {
		this.groupId = groupId;
		this.customerId = customerId;
	}
/* ****** ゲッター、セッター *************/
	public AppGroupId getGroupId() {
		return groupId;
	}
	public void setGroupId(AppGroupId groupId) {
		this.groupId = groupId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
/* ****** その他*************/
	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + Objects.hashCode(this.groupId);
		hash = 43 * hash + Objects.hashCode(this.customerId);
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
		final GroupKey other = (GroupKey) obj;
		if (!Objects.equals(this.groupId, other.groupId)) {
			return false;
		}
		if (!Objects.equals(this.customerId, other.customerId)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "GroupKey{" + "groupId=" + groupId + ", customerId=" + customerId + '}';
	}

}
