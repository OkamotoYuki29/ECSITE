package beans;

import java.io.Serializable;
import javax.enterprise.context.Dependent;

@Dependent
public class MakeText implements Serializable {

	public	String getRegistText(String name, String url){

		String CR = System.getProperty("line.separator");
		StringBuilder buf = new StringBuilder();
		buf.append(name).append("様").append(CR);
		buf.append("ご登録ありがとうございます。").append(CR);
		buf.append("以下のURLにアクセスいただけますと、登録完了となります。").append(CR);
		buf.append(url).append(CR);

		return buf.toString();
	}
}
