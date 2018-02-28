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
	private User user;
	
/* ****** コンストラクタ *************/
	public AppGroup() {
	}
	public AppGroup(Long groupId, User user){
		this.groupKey = new GroupKey(groupId, user.getId());
	}
	public AppGroup(GroupKey groupKey, User user) {
		this.groupKey = groupKey;
		this.user = user;
	}
/* ****** ゲッター、セッター *************/
	public GroupKey getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(GroupKey groupKey) {
		this.groupKey = groupKey;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
/* ****** その他 *************/	
	@Override
	public int hashCode() {
		int hash = 5;
		hash = 13 * hash + Objects.hashCode(this.groupKey);
		hash = 13 * hash + Objects.hashCode(this.user);
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
		if (!Objects.equals(this.user, other.user)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "AppGroup{" + "groupKey=" + groupKey + ", user=" + user + '}';
	}
	
	
}
