package beans;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import util.Token;

@Named
@SessionScoped
public class CustomerBb extends CustomerSuperBb implements Serializable{

	/* *****(ユーザー登録)******************/
	/* content-1 */
	public String goto1_forRegist(){
		editable = true;
		return "/customer/info1.xhtml?faces-redirect=true?";
	}
	/* content-2 */
	public String goto2(){
		// 認証用のワンタイムトークン付のURL作成
		token = Token.generateToken();
		String url = "http://localhost:8080/ecsite/faces/customer/info3.xhtml?token=" + token;
		sender.send(mail, "本登録のご案内", text.getRegistText(name, url));
		return "/customer/info2.xhtml?faces-redirect=true";
	}
	public String goto4(){
		//Tokenチェック
		
		return "/customer/info4.xhtml?faces-redirect=true";
	}

	/* *****(ユーザー情報表示・変更)******************/
	/* content-1 */
	public String goto1_forDispEdit(){
		editable = false;
		displayable = true;
		return "/customer/info1.xhtml?faces-redirect=true";
	}
	/* end */
	public String end_forDispEdit(){
		// 変更DB処理
		return null;
	}
}
