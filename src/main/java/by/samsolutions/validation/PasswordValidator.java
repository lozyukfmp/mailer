package by.samsolutions.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String>
{
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";

	@Override
	public void initialize(final ValidPassword validPassword)
	{

	}

	@Override
	public boolean isValid(final String password, final ConstraintValidatorContext constraintValidatorContext)
	{

		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);

		return matcher.matches();
	}
}
