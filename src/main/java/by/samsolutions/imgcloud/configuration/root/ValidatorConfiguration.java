package by.samsolutions.imgcloud.configuration.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/validation/regexp.properties")
@ComponentScan("by.samsolutions.imgcloud.validation.*")
public class ValidatorConfiguration
{
}
