package by.samsolutions.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import by.samsolutions.dto.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object>
{
	@Override
	public void initialize(final PasswordMatches passwordMatches)
	{

	}

	@Override
	public boolean isValid(final Object o, final ConstraintValidatorContext constraintValidatorContext)
	{
		UserDto userDto = (UserDto) o;

		return userDto.getPassword().equals(userDto.getConfirmPassword());
	}
}
