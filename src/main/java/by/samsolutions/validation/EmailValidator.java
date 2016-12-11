package by.samsolutions.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator implements ConstraintValidator<ValidEmail, String>
{

	@Value("${validation.email}")
	private String regexp;

	@Override
	public void initialize(final ValidEmail constraintAnnotation)
	{

	}

	@Override
	public boolean isValid(final String email, final ConstraintValidatorContext context)
	{
		return (validateEmail(email));
	}

	private boolean validateEmail(final String email)
	{
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
