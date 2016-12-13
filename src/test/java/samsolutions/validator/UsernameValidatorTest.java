package samsolutions.validator;

import javax.validation.ConstraintValidatorContext;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.samsolutions.validation.UsernameValidator;
import samsolutions.configuration.ValidatorTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ValidatorTestConfiguration.class)
public class UsernameValidatorTest
{
	@Autowired
	private UsernameValidator          usernameValidator;
	private static ConstraintValidatorContext context;

	private String[] validUsernameList   = {"arTem234", "bla_blA2", "ArTeM1"};
	private String[] invalidUsernameList = {"arT2", "sd@fb1", "AAA12$"};

	@BeforeClass
	public static void initData()
	{
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
