package samsolutions.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import by.samsolutions.validation.EmailValidator;
import by.samsolutions.validation.PasswordValidator;
import by.samsolutions.validation.UsernameValidator;

@Configuration
@PropertySource(value = "classpath:/validation/regexp.properties")
public class ValidatorTestConfiguration
{

	@Bean
	public EmailValidator emailValidator()
	{
		return new EmailValidator();
	}

	@Bean
	public PasswordValidator passwordValidator()
	{
		return new PasswordValidator();
	}

	@Bean
	public UsernameValidator usernameValidator()
	{
		return new UsernameValidator();
	}
}