package beans;

import entity.AppGroup;
import entity.AppGroupId;
import entity.Customer;
import static entity.Customer_.destination;
import entity.Destination;
import entity.GroupKey;
import entity.TempCustomer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import util.SHA256Encoder;
import util.Token;

@Named
@ConversationScoped
public class CustomerBb extends CustomerSuperBb implements Serializable{
	@Inject
	Conversation conv;

/** *************(ユーザー登録)***************************************/
/* ******（infot1-1）**************************************/
	public String goto1_1_forRegist(){
		if(conv.isTransient()) conv.begin();
		editable = true;
		return "/customer/info1-1.xhtml?faces-redirect=true?";
	}
/* ******（info1-2）**************************************/
	public String goto1_2(){
		/* トークンを生成 */
		token = Token.generateToken();
		/* トークンを暗号化 */
		String criptoToken = cripto.encrypt(id, mail, token);
		/* パスワードを暗号化 */
		String criptoPasswd = getEncodedPw(passwd);
		/* DBへ登録 */
		TempCustomer tempCustomer = new TempCustomer(id, criptoPasswd, name, mail, criptoToken);
		tempCustomerDb.create(tempCustomer);
		/* 本登録URL付mail送信 */
		sendMail();
		conv.end();
		return "/customer/info1-2.xhtml?faces-redirect=true";
	}
	/**
	 * mail送信
	 */
	private void sendMail(){
		String url = "http://localhost:8080/ecsite/faces/customer/mailAttest.xhtml?token=" + token;
		sender.send(mail, "本登録のご案内", text.getRegistText(name, url));
	}
	/**
	 * パスワードを暗号化
	 */
	private String getEncodedPw(String passwd) {
		SHA256Encoder encoder = new SHA256Encoder();
		return encoder.encodePassword(passwd);
	}
/* ******（info1-3）**************************************/
	public String goto1_3(){
		if(conv.isTransient()) conv.begin();
		System.out.println("token:" + token);
		//Tokenチェック
		TempCustomer temp = tokenChk();
		//同じトークンがなかった場合
		if(temp == null){
			facesErrorMsg("認証できませんでした。再度登録をお願いします。");
			return "/customer/error.xhtml?faces-redirect=true";
		}
		//本登録して、仮登録データを削除
		customerRegist(temp);
		return "/customer/info1-3.xhtml?faces-redirect=true";
	}
	/**
	 * TempCustomerテーブルのトークンを一つずつそれぞれのキー(id,mail)で復号し、<br/>
	 * URL記載のトークンと同じの場合、そのTempCustomerを返すメソッド<br/>
	 * @return トークンチェックの結果得られたTempCustomer
	 */
	private TempCustomer tokenChk(){
		//仮ユーザーリストの取得
		tempCustomerList = em.createNamedQuery(TempCustomer.All, TempCustomer.class).getResultList();
		//トークンを復号
		for(TempCustomer temp : tempCustomerList){
			String decriptoToken = cripto.decript(temp.getId(), temp.getMail(), temp.getToken());
			//トークンの整合性チェック
			if(token.equals(decriptoToken)){
				return temp;
			}
		} return null;
	}
	/**
	 * 仮登録情報をフィールドにセットし、本登録するメソッド
	 * @param temp 仮登録情報
	 */
	private void customerRegist(TempCustomer temp){
		id = temp.getId();	passwd = temp.getPasswd(); name = temp.getName(); mail = temp.getMail();
		GroupKey key = new GroupKey(AppGroupId.USER, id);
		AppGroup group = new AppGroup(key, null);
		List<Destination> list = new ArrayList<>();
		Customer cusotmer = new Customer(id, passwd, name, mail, group, list);
		group.setCustomer(cusotmer);
		customerDb.add(cusotmer);
		tempCustomerDb.delete(temp);
	}
/* ******（info2_1）**************************************/
	public String goto2_1(){
		editable = true;
		return "/customer/info2-1.xhtml?faces-redirect=true";
	}
/* ******（info2_2）**************************************/
	public String goto2_2(){
		addDestination();
		conv.end();
		return "/customer/info2-2.xhtml?faces-redirect=true";
	}
	private void addDestination(){
		TypedQuery<Customer> q = em.createNamedQuery(Customer.FIND, Customer.class);
		q.setParameter("valueOfId", id);
		Customer customer = q.getSingleResult();
		Destination destination = new Destination(addressee, postal, address, number, customer);
		customer.getDestination().add(destination);
		customerDb.update(customer);
	}

/** ****(ユーザー情報表示・変更)***************************************/
/* ******（regist1-1）**************************************/
	public String goto1_1_forDispEdit(){
		if(conv.isTransient()) conv.begin();
		editable = false;
		displayable = true;
		return "/customer/info1-1.xhtml?faces-redirect=true";
	}
/* ******（end）**************************************/
	public String end_forDispEdit(){
		conv.end();
		// 変更DB処理
		return null;
	}
}
