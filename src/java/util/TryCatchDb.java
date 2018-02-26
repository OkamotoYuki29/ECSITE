package util;

import java.util.List;

/**
 * SuperDbクラスのメソッドをtry-catchしたクラス
 * @author 岡本　侑貴
 * @param <T> 
 */
public class TryCatchDb<T> extends SuperDb{
	
	public TryCatchDb(Class entityClass) {
		super(entityClass);
	}

	
	public	void add(T obj){		//新規登録
		try{
			create(obj);
		}catch(Exception e){ e.printStackTrace();}
	}
	public	void update(T obj){		//更新
		try{
			edit(obj);
		}catch(Exception e){}
		
	}
	public	void delete(T obj){		//削除
		try{
			remove(obj);
		}catch(Exception e){}
	}
	public	T search(Object id){	//主キー検索
		return	(T)find(id);
	}
	
	public List<T> getAll() {		//全件検索
		return	findAll();
	}
	public List<T> getRange(int[] range) {		//配列で範囲を限定した主キー検索
		return	findRange(range);
	}

	public int dataCount() {		//総件数を返す
		return	count();
	}
}
