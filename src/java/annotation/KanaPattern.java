package annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author 岡本　侑貴
 */
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = KanaValidator.class)
public @interface KanaPattern {

	String message() default "{KanaPattern}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	String charaType() default "かな";
}
