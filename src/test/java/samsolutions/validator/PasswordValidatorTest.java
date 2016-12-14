package samsolutions.validator;

import javax.validation.ConstraintValidatorContext;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.samsolutions.validation.PasswordValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PasswordValidatorTest
{
	private static ConstraintValidatorContext context;
	@Autowired
	private        PasswordValidator          passwordValidator;
	private String[] validPasswords   = {"arTem234"/*, "blablA2", "ArTeM1"*/};
	private String[] invalidPasswords = {"arT2", "sdfb", "AAA1s"};

	@BeforeClass
	public static void initData()
	{
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

	@Configuration
	@PropertySource(value = "classpath:/validation/regexp.properties")
	static class PasswordValidatorConfiguration
	{

		@Bean
		public PasswordValidator passwordValidator()
		{
			return new PasswordValidator();
		}
	}
}
