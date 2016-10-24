package by.samsolutions.configuration.sequrity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthSuccessHandler authSuccessHandler;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Artem").password("1234").roles("USER");
        auth.inMemoryAuthentication().withUser("Gena").password("123").roles("USER");
        auth.inMemoryAuthentication().withUser("Petia").password("1234").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/user/**").access("hasRole('ROLE_USER')")
                .and()
                    .formLogin().loginPage("/loginPage")
                    .loginProcessingUrl("/login")
                    .successHandler(authSuccessHandler)
                    .usernameParameter("username").passwordParameter("password")
                .and()
                    .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                    .csrf();
    }
}
