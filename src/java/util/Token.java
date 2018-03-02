package util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.enterprise.context.Dependent;

@Dependent
public class Token {
  private static final int TOKEN_LENGTH = 16;//16*2=32バイト

  //32バイトのCSRFトークンを作成
  public static String getToken() {
    byte token[] = new byte[TOKEN_LENGTH];
    StringBuilder buf = new StringBuilder();
    SecureRandom random = null;

    try {
      random = SecureRandom.getInstance("SHA1PRNG");
      random.nextBytes(token);

      for (int i = 0; i < token.length; i++) {
        buf.append(String.format("%02x", token[i]));
      }

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    return buf.toString();
  }
}
