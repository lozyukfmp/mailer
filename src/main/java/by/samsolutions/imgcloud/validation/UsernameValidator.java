package by.samsolutions.imgcloud.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsernameValidator implements ConstraintValidator<ValidUsername, String>
{

	@Value("${validation.username}")
	private String regexp;

	@Override
	public void initialize(final ValidUsername validPassword)
	{

	}

	@Override
	public boolean isValid(final String username, final ConstraintValidatorContext constraintValidatorContext)
	{
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(username);

		return matcher.matches();
	}
}
