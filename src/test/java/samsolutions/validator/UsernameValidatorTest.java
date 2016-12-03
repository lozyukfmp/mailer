package samsolutions.validator;

import javax.validation.ConstraintValidatorContext;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import by.samsolutions.validation.UsernameValidator;

public class UsernameValidatorTest
{
	private static UsernameValidator usernameValidator;
	private static ConstraintValidatorContext context;

	private String[] validUsernameList   = {"arTem234", "bla_blA2", "ArTeM1"};
	private String[] invalidUsernameList= {"arT2", "sd@fb1", "AAA12$"};

	@BeforeClass
	public static void initData()
	{
		usernameValidator = new UsernameValidator();
		context = Mockito.mock(ConstraintValidatorContext.class);
	}

	@Test
	public void ValidUsernameTest()
	{
		for (String username : validUsernameList)
		{
			boolean valid = usernameValidator.isValid(username, context);
			Assert.assertEquals(true, valid);
		}
	}

	@Test
	public void InValidPasswordTest()
	{
		for (String username : invalidUsernameList)
		{
			boolean valid = usernameValidator.isValid(username, context);
			Assert.assertEquals(false, valid);
		}
	}
}
