package by.samsolutions.configuration.root;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("by.samsolutions.*")
@PropertySource(value = {"classpath:/common/common.properties", "classpath:/validation/regexp.properties"})
public class SpringRootConfiguration
{

}
