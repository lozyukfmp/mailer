package by.samsolutions.configuration.seсurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthSuccessHandler authSuccessHandler;

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	protected void configure(final HttpSecurity http) throws Exception
	{

		http.authorizeRequests()
		    .antMatchers("/user*","/profile*")
		    .access("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
		    .antMatchers("/admin*")
		    .access("hasRole('ROLE_ADMIN')")
		    .and()
		    .formLogin()
		    .loginPage("/login-page")
		    .loginProcessingUrl("/login")
		    .successHandler(authSuccessHandler)
		    .usernameParameter("username")
		    .passwordParameter("password")
		    .and()
		    .exceptionHandling()
		    .accessDeniedPage("/accessDenied")
		    .and()
		    .csrf()
		    .disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}
