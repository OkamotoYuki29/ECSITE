package util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
/**
 * バイナリファイルの入力と出力を行うクラスメソッド<br/>
 * @author 
 */
public class BinFileUtil {
	static final Logger log = Logger.getLogger(BinFileUtil.class.getName());

	
	/**
	 * バイナリファイルの全内容を読みだしてバイト配列として返す<br/>
	 * 使用例： byte[] img = getBinary("/resources/picture.jpg");<br/>
	 * 
     * @param fpath		入力元のバイナリファイルのURL<br/>
	 *					アプリケーションルートからの相対パスで指定する（/resources/～）
     * @return	byte[]型のバイナリデータ
     */
	public static byte[] getBinary(String fpath){
    	String	filePath	=	getRealPath(fpath);		// 絶対パスに変換
		Path	path		=	Paths.get(filePath);	// Pathオブジェクトを作成
		byte[]	binaryData = null;
		try {
			binaryData	= Files.readAllBytes(path);		// 全データを読み出す
		} catch (IOException ex) {
			log.severe("◆"+ex.toString());
		}
		return	binaryData;
    }
	/**
	 * バイナリデータをファイルに書き出す<br/>
	 * 使用例： putBinary(data, "/resources/images/picture.jpg");<br/>
	 * 
     * @param binaryData   出力するbyte[]型のバイナリデータ
	 * @param outFile		出力先のファイルURL<br/>
	 *						アプリケーションルートからの相対パスで指定する（/resources/～）
     */
	public	static	void	putBinary(byte[] binaryData, String outFile){
    	String	filePath	=	getRealPath(outFile);		// 絶対パスに変換
		Path	path		=	Paths.get(filePath);		// Pathオブジェクトを作成
		try {
			Files.write(path, binaryData);					// 全データを書き出す
		} catch (IOException ex) {
			log.severe("◆"+ex.toString());
		}
	}
	/**
	 * 画像ファイルをサーバーのimagesディレクトリに保存するメソッド<br/>
	 * 使用例：	uploadImage(pic);<br/>
	 * 
	 * @param pic xhtmlより選択されたPart型のデータ
	 * 
	 * @return url String型の画像ファイルのURL
	 */
	public static String uploadImage(Part pic) {
		try{
			InputStream in = pic.getInputStream();					// ストリームを得る
			String filepath = getRealPath("resources/images");		// 保存するディレクトリのパス
			String filename = pic.getSubmittedFileName();			// ファイル名を得る
			Files.copy(in, new File(filepath, filename).toPath());	//ファイルとして保管
			String url = "resources/images" + filename;
			return url;
		} catch(IOException ex){
			log.severe("◆"+ex.toString());
		} return null;
	}
	public static byte[] partToBinary(Part file){
		byte[] data = new byte[(int) file.getSize()];   // byte配列を作成
		try {
			InputStream in = file.getInputStream();     // ストリームからbyte配列
			in.read(data);                              // に、入力する
		} catch (IOException ex) {
		  log.severe("◆"+ex.toString());
		}
		return data;
	}
	/**
	 * I/Oで使用する絶対パスを求める
	 * @param path	アプリケーションルートのリソースからの相対パス（/resources/～）
	 * @return		絶対パス
	 */
	public	static String getRealPath(String path){
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		return  ctx.getRealPath(path);
	}
}

