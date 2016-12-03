package by.samsolutions.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String>
{
	private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9_]{6,15}$";

	@Override
	public void initialize(final ValidUsername validPassword)
	{

	}

	@Override
	public boolean isValid(final String username, final ConstraintValidatorContext constraintValidatorContext)
	{
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(username);

		return matcher.matches();
	}
}
