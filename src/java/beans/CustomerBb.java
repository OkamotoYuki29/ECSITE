package beans;

import entity.TempCustomer;
import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import util.SHA256Encoder;
import util.Token;

@Named
@ConversationScoped
public class CustomerBb extends CustomerSuperBb implements Serializable{
	@Inject
	Conversation conv;

/** *************(ユーザー登録)***************************************/
/* ******（content-1）**************************************/
	public String goto1_forRegist(){
		if(conv.isTransient()) conv.begin();
		editable = true;
		return "/customer/info1.xhtml?faces-redirect=true?";
	}
/* ******（content-2）**************************************/
	public String goto2(){
		/* トークンを暗号化 */
		String criptoToken = cripto.encrypt(id, mail, passwd);
		/* パスワードを暗号化 */
		String criptoPasswd = getEncodedPw(passwd);
		/* DBへ登録 */
		TempCustomer tempCustomer = new TempCustomer(id, criptoPasswd, name, mail, criptoToken);
		tempCustomerDb.create(tempCustomer);
		/* 本登録URL付mail送信 */
		sendMail();

		return "/customer/info2.xhtml?faces-redirect=true";
	}
	/**
	 * mail送信
	 */
	private void sendMail(){
		token = Token.generateToken();
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
/* ******（content-3）**************************************/
	public String goto3(){
		System.out.println("token:" + token);
		//Tokenチェック
		/**
		 * Tokenが存在するかチェック
		 */
		//true
		/**
		 * 仮登録DBから削除して、本登録DBに登録
		 */
		return "/customer/info3.xhtml?faces-redirect=true";
		//false
		//return "認証エラーページ";
	}
/** ****(ユーザー情報表示・変更)***************************************/
/* ******（content-1）**************************************/
	public String goto1_forDispEdit(){
		if(conv.isTransient()) conv.begin();
		editable = false;
		displayable = true;
		return "/customer/info1.xhtml?faces-redirect=true";
	}
/* ******（end）**************************************/
	public String end_forDispEdit(){
		conv.end();
		// 変更DB処理
		return null;
	}
}
