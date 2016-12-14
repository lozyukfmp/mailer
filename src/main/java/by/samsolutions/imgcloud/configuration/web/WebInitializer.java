package by.samsolutions.imgcloud.configuration.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import by.samsolutions.imgcloud.configuration.root.SpringRootConfiguration;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return new Class[] {SpringRootConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class[] {SpringWebConfig.class};
	}

	@Override
	protected String[] getServletMappings()
	{
		return new String[] {"/"};
	}

}
