package samsolutions.validator;

import javax.validation.ConstraintValidatorContext;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import by.samsolutions.validation.PasswordValidator;

public class PasswordValidatorTest
{
	private static PasswordValidator passwordValidator;
	private static ConstraintValidatorContext context;

	private String[] validPasswords   = {"arTem234", "blablA2", "ArTeM1"};
	private String[] invalidPasswords = {"arT2", "sdfb", "AAA1s"};

	@BeforeClass
	public static void initData()
	{
		passwordValidator = new PasswordValidator();
		context = Mockito.mock(ConstraintValidatorContext.class);
	}

	@Test
	public void ValidPasswordTest()
	{
		for (String password : validPasswords)
		{
			boolean valid = passwordValidator.isValid(password, context);
			Assert.assertEquals(true, valid);
		}
	}

	@Test
	public void InValidPasswordTest()
	{
		for (String password : invalidPasswords)
		{
			boolean valid = passwordValidator.isValid(password, context);
			Assert.assertEquals(false, valid);
		}
	}
}
