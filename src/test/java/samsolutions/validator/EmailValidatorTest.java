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

import by.samsolutions.validation.EmailValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class EmailValidatorTest
{
	@Configuration
	@PropertySource(value = "classpath:/validation/regexp.properties")
	static class EmailValidatorConfiguration {

		@Bean
		public EmailValidator emailValidator()
		{
			return new EmailValidator();
		}
	}

	@Autowired
	private EmailValidator             emailValidator;
	private static ConstraintValidatorContext context;

	private String[] validEmails   = {"arTem234@mail.com", "blablA2@mail.ru", "ArTeM1@gmail.com"};
	private String[] invalidEmails = {"arT2.com", "sdfb.sdfs", "AAA1s.sfsdf"};

	@BeforeClass
	public static void initData()
	{
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
