package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;


@Embeddable
public class GroupKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long groupId;
	private Long userId;
	
/* ****** コンストラクタ *************/
	public GroupKey() {
	}
	public GroupKey(Long groupId, Long userId) {
		this.groupId = groupId;
		this.userId = userId;
	}
/* ****** ゲッター、セッター *************/	
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
/* ****** その他*************/
	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + Objects.hashCode(this.groupId);
		hash = 43 * hash + Objects.hashCode(this.userId);
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
		if (!Objects.equals(this.userId, other.userId)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "GroupKey{" + "groupId=" + groupId + ", userId=" + userId + '}';
	}
	
}
