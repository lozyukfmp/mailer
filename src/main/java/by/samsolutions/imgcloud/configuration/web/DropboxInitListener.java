package by.samsolutions.imgcloud.configuration.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DropboxInitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String contextPath = servletContextEvent.getServletContext().getContextPath();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
