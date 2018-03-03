package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.enterprise.context.Dependent;

@Dependent
public class FileUtil {
    public static void writeBytes(byte[] b, String path) {
        try(ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(path))){
            o.write(b);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static byte[] readBytes(String path) {
        byte[] b = new byte[16];
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))){
            in.read(b, 0,16);
        }catch(Exception e){
            e.printStackTrace();
        }
        return  b;
    }
    /* テスト */
    public static void main(String[] args){
        byte[] b = "abcdefghijklmn".getBytes();
        FileUtil.writeBytes(b, "vi_byte");
        byte[] bb = FileUtil.readBytes("vi_byte");
        System.out.println(new String(bb));
    }
}
