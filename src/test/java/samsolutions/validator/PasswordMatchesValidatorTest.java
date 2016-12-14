package samsolutions.validator;

import javax.validation.ConstraintValidatorContext;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import by.samsolutions.imgcloud.dto.UserDto;
import by.samsolutions.imgcloud.validation.PasswordMatchesValidator;

public class PasswordMatchesValidatorTest
{
	private static PasswordMatchesValidator   passwordMatchesValidator;
	private static ConstraintValidatorContext context;

	@BeforeClass
	public static void initData()
	{
		passwordMatchesValidator = new PasswordMatchesValidator();
		context = Mockito.mock(ConstraintValidatorContext.class);
	}

	@Test
	public void ValidMatcherTest()
	{
		UserDto validDto = new UserDto();
		validDto.setPassword("artem");
		validDto.setConfirmPassword("artem");

		boolean valid = passwordMatchesValidator.isValid(validDto, context);
		Assert.assertEquals(true, valid);
	}

	@Test
	public void InValidPasswordTest()
	{
		UserDto invalidDto = new UserDto();
		invalidDto.setPassword("artem");
		invalidDto.setConfirmPassword("lozyuk");

		boolean valid = passwordMatchesValidator.isValid(invalidDto, context);
		Assert.assertEquals(false, valid);
	}
}
