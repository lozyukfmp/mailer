package by.samsolutions.configuration.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/validation/regexp.properties")
@ComponentScan("by.samsolutions.validation.*")
public class ValidatorConfiguration
{
}
