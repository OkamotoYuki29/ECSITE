package beans;

import db.CustomerDb;
import db.TempCustomerDb;
import entity.AppGroupId;
import entity.Customer;
import entity.TempCustomer;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import net.tkxtools.MailSender;
public class CustomerSuperBb implements Serializable{

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
		/* token */
		protected String token;
		/* 暗号化パスワード */

	  /* ***(宛先情報) ************/
		/* 宛名 */
		@Size(min = 1)
		protected String addressee;
		/* 宛名かな */
		protected String addresseeaKana;
		/* 郵便番号 */
		@Digits(integer = 7, fraction = 0)
		protected Integer postal;
		/* 住所 */
		@Size(min = 1)
		protected String address;
		/* 連絡先番号 */
		@Size(min = 10, max = 11)
		protected String number;

	  /* ***(その他) ************/
		/* フォームの編集可否 */
		protected boolean editable;
		/* フォームの編集可否を変更できるかの可否
			新規登録はfalse,マイページはtrue	*/
		protected boolean displayable;
		/* 既存ユーザーリスト */
		protected List<Customer> customerList;
		/* 仮ユーザーリスト */
		protected List<TempCustomer> tempCustomerList;

	/* *****（データベース処理）*******************************/
		@EJB
		protected CustomerDb customerDb;		// 顧客データベース
		@EJB
		protected TempCustomerDb tempCustomerDb;	//仮登録顧客データベース
	/* *****（ユーティリティのインジェクト）********************/
		@Inject
		protected transient Logger log;		// ロガー
		@EJB
		protected MailSender sender;		//電子メールユーティリティ
		@Inject
		protected MakeText text;			//メール本文を作成する
		@Inject
		protected CriptoExecute cripto;		//トークンを暗号化する
	/* *****（初期化）******************************************/
		@PostConstruct
		public void init(){
			groupId = AppGroupId.TEMPORARY_USER;		//仮ユーザー
			/* 現在CUSTOMERテーブルが空のためコメントアウト
			customerList = customerDb.getAll();
			*/
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
		public boolean isEditable() {
			return editable;
		}
		public void setEditable(boolean editable) {
			this.editable = editable;
		}
		public List<Customer> getCustomerList() {
			return customerList;
		}
		public void setCustomerList(List<Customer> customerList) {
			this.customerList = customerList;
		}
		public boolean isDisplayable() {
			return displayable;
		}
		public void setDisplayable(boolean displayable) {
			this.displayable = displayable;
		}
		public String getAddresseeaKana() {
			return addresseeaKana;
		}
		public void setAddresseeaKana(String addresseeaKana) {
			this.addresseeaKana = addresseeaKana;
		}
		public MailSender getSender() {
			return sender;
		}
		public void setSender(MailSender sender) {
			this.sender = sender;
		}
		public MakeText getText() {
			return text;
		}
		public void setText(MakeText text) {
			this.text = text;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public TempCustomerDb getTempCustomerDb() {
			return tempCustomerDb;
		}
		public void setTempCustomerDb(TempCustomerDb tempCustomerDb) {
			this.tempCustomerDb = tempCustomerDb;
		}
		public CriptoExecute getCripto() {
			return cripto;
		}
		public void setCripto(CriptoExecute cripto) {
			this.cripto = cripto;
		}
		public List<TempCustomer> getTempCustomerList() {
			return tempCustomerList;
		}
		public void setTempCustomerList(List<TempCustomer> tempCustomerList) {
			this.tempCustomerList = tempCustomerList;
		}
}
