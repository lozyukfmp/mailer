package samsolutions.validator;

import javax.validation.ConstraintValidatorContext;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import by.samsolutions.validation.EmailValidator;

public class EmailValidatorTest
{
	private static EmailValidator             emailValidator;
	private static ConstraintValidatorContext context;

	private String[] validEmails   = {"arTem234@mail.com", "blablA2@mail.ru", "ArTeM1@gmail.com"};
	private String[] invalidEmails = {"arT2.com", "sdfb.sdfs", "AAA1s.sfsdf"};

	@BeforeClass
	public static void initData()
	{
		emailValidator = new EmailValidator();
		context = Mockito.mock(ConstraintValidatorContext.class);
	}

	@Test
	public void ValidPasswordTest()
	{
		for (String email : validEmails)
		{
			boolean valid = emailValidator.isValid(email, context);
			Assert.assertEquals(true, valid);
		}
	}

	@Test
	public void InValidPasswordTest()
	{
		for (String email : invalidEmails)
		{
			boolean valid = emailValidator.isValid(email, context);
			Assert.assertEquals(false, valid);
		}
	}
}
