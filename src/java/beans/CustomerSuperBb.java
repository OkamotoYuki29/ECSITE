package beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerSuperBb {
	
	/* *****（変数）********************************************/
	
	  /* ***(ユーザー情報) ************/
		/* パスワード */
		@Size(min = 8, max = 16) @Pattern(regexp = "\\w+$")
		private String passwd;
		/* 氏名 */
		@Size(min = 1)
		private String name;
		/* メールアドレス */
		@Pattern(regexp = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$")
		private String mail;
		/* グループID */
}
