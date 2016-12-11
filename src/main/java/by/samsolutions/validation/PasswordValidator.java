package by.samsolutions.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator implements ConstraintValidator<ValidPassword, String>
{

	@Value("${validation.password}")
	private String regexp;

	@Override
	public void initialize(final ValidPassword validPassword)
	{

	}

	@Override
	public boolean isValid(final String password, final ConstraintValidatorContext constraintValidatorContext)
	{

		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(password);

		return matcher.matches();
	}
}
