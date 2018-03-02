/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 
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
		boolean hiraganaCheck = value.matches("^[ぁ-ん]+$");
		boolean katakanaCheck = value.matches("^[ァ-ヶー]+$");

		if(charaType.equals("かな")) return hiraganaCheck;
		if(charaType.equals("カナ")) return katakanaCheck;
		return false;
	}

}
