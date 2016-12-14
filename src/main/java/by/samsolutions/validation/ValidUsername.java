package by.samsolutions.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
@Documented
public @interface ValidUsername
{
	String message() default "Invalid username";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
