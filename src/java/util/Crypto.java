package util;

import java.io.Serializable;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES暗号/CBCモードによる暗号処理クラス
 * @author 川場隆　kawaba@tk-webs.com
 */
public class Crypto implements Serializable {
    private Cipher encrypter, decrypter;

    /* コンストラクタ */
    public Crypto(byte[] secretKey, byte[] ivs) throws Exception{
        IvParameterSpec iv = new IvParameterSpec(ivs);    // 暗号化時のスタートブロック用の初期値を作成

        SecretKeySpec key = new SecretKeySpec(secretKey, "AES"); // 暗号方式＋解読キーのセットを作成
        encrypter = Cipher.getInstance("AES/CBC/PKCS5Padding");  // 暗号方式と生成方式などを指定して暗号器を作成
        encrypter.init(Cipher.ENCRYPT_MODE, key, iv);            // 暗号器を暗号化モードにセットする

        decrypter = Cipher.getInstance("AES/CBC/PKCS5Padding");  // もうひとつ、暗号器を作成しておく
        decrypter.init(Cipher.DECRYPT_MODE, key, iv);            // 暗号器を復号モードにセットする
    }

    /* 暗号化処理を実行するメソッド */
    public String encrypt(String text) throws Exception {
        byte[] crypto = encrypter.doFinal(text.getBytes()); // 暗号化する
        byte[] str64 = Base64.getEncoder().encode(crypto);  // 表示できるように文字の配列に変換する
        return new String(str64);                           // さらに文字列にしておく
    }
    /* 復号化処理を実行するメソッド */
    public String decrypt(String str64) throws Exception {
        byte[] str = Base64.getDecoder().decode(str64);     // 暗号文字列を元のバイナリに戻す
        byte[] text = decrypter.doFinal(str);               // 復号化する
        return new String(text);                            // 文字列に変換して返す
    }
}
