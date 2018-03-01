package beans;

import db.CustomerDb;
import entity.AppGroupId;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerSuperBb {
	
	/* *****（変数）********************************************/
	
	  /* ***(ユーザー情報) ************/
		/* ID */
		@Size(min = 1, max = 8) @Pattern(regexp = "^\\w+$")
		protected String id;
		/* パスワード */
		@Size(min = 8, max = 16) @Pattern(regexp = "^\\w+$")
		protected String passwd;
		/* 氏名 */
		@Size(min = 1)
		protected String name;
		/* メールアドレス */
		@Pattern(regexp = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$")
		protected String mail;
		/* グループID */
		protected AppGroupId groupId;
		
		/* *****（データベース処理）*******************************/
		@EJB
		protected CustomerDb customerDb;		// 顧客データベース
	/* *****（ユーティリティのインジェクト）********************/
		@Inject
		protected transient Logger log;		// ロガー
	/* *****（初期化）******************************************/
		@PostConstruct
		public void init(){
			groupId = AppGroupId.USER;		//ユーザー登録画面では実質的にfinal
		}
		
	/* *****（getter setter）******************************************/
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
		public AppGroupId getGroupId() {
			return groupId;
		}
		public void setGroupId(AppGroupId groupId) {
			this.groupId = groupId;
		}
		public CustomerDb getCustomerDb() {
			return customerDb;
		}
		public void setCustomerDb(CustomerDb customerDb) {
			this.customerDb = customerDb;
		}
		public Logger getLog() {
			return log;
		}
		public void setLog(Logger log) {
			this.log = log;
		}
}
