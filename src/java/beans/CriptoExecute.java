package beans;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import org.apache.commons.lang3.RandomStringUtils;
import util.Crypto;
import util.FileUtil;

/**
 *
 * @author 岡本　侑貴
 */
@RequestScoped
public class CriptoExecute implements Serializable {
	/**
	 * トークンを暗号化するメソッド
	 * @param id 暗号解読キーや保存するファイルパス名に使用(uniqueのため)<br/>
	 * @param mail 暗号解読キーや保存するファイルパス名に使用(uniqueのため)<br/>
	 * @param token 暗号化するトークン<br/>
	 * @return 暗号化されたトークン
	 */
	public String encrypt(String id, String mail, String token){
		/*------------------------------------------------------------------------------
           データの準備
        --------------------------------------------------------------------------------*/
		byte[] iv  = RandomStringUtils.randomAlphanumeric(16).getBytes();// IV(暗号化時のスタートブロック用の初期値 128ビット固定長）
		String strKey = id + mail +"aoeigfwio";
        byte[] key = strKey.substring(0, 16).getBytes();  // 暗号解読キー(128ビット固定長)
		/*------------------------------------------------------------------------------
           IVと暗号解読キーはファイルに保存しておく
        --------------------------------------------------------------------------------*/
		FileUtil.writeBytes(iv,  id);
        FileUtil.writeBytes(key, mail);
		System.out.println("IV(スタートブロック用の初期値）="+new String(iv)+ "（"+iv.length + "byte）");
        System.out.println("暗号解読キー＝" + new String(key) + "（16byte）");

		 /*------------------------------------------------------------------------------
           暗号化の処理
        --------------------------------------------------------------------------------*/
        String source = token; // 暗号化する文字列
		String result = "";    // 暗号化した結果の文字列
		System.out.println("トークン＝" + token);
        try {                                                                              // Cryptoオブジェクトを生成する
            Crypto c = new Crypto(FileUtil.readBytes(id), FileUtil.readBytes(mail));	   // 解読キーとIVをファイルから読み込む
            result = c.encrypt(source);                                                    // 暗号化した文字列を得る
        } catch (Exception e) {
            e.printStackTrace();
        }
		System.out.println("暗号＝" + result);  // 暗号文字列を表示する
		return result;
	}
	/**
	 * トークンを復元するメソッド
	 * @param id ivとキーを保存したファイルパス指定のため<br/>
	 * @param mail ivとキーを保存したファイルパス指定のため<br/>
	 * @param criptpToken 暗号トークン<br/>
	 * @return 復元されたトークン
	 */
	public String decript(String id, String mail, String criptpToken){
		String result = "";
		try {                                                                              // Cryptoオブジェクトを生成する
            Crypto c2 =new Crypto(FileUtil.readBytes(id), FileUtil.readBytes(mail)); // 解読キーとIVをファイルから読み込む
            result = c2.decrypt(criptpToken);                                                   // 復号化した文字列を得る
        } catch (Exception e) {
            e.printStackTrace();
        }
		return result;
	}

}
