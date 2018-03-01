package beans;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class CustomerBb extends CustomerSuperBb implements Serializable{
	@Inject
	Conversation conv;
	public String goto_1(){
		if(conv.isTransient()) conv.begin();
		return "/customer/info1.xhtml?faces-redirect=true";
	}
}
