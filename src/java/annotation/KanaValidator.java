/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotation;

import javax.interceptor.Interceptors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import util.Tracer;

/**
 * !!! 使用見合わせ !!!
 * バグが発生のためいったん使用見合わせ
 * @author 岡本　侑貴
 */
public class KanaValidator implements ConstraintValidator<KanaPattern, String>{

	// 属性値("かな" or "カナ")
	private String charaType;
	
	// 初期化
	@Override
	public void initialize(KanaPattern pattern) {
		charaType = pattern.charaType();
	}

	//検証処理
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value == null) return false;
		boolean hiraganaCheck = value.matches("^[\\\\u3040-\\\\u309Fー]+$");
		boolean katakanaCheck = value.matches("^[ァ-ヶー]+$");
			
		if(charaType == "かな") return hiraganaCheck;
		if(charaType == "カナ") return katakanaCheck;
		return false;
	}
	
}
