package by.samsolutions.imgcloud.configuration.seсurity;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication
					authentication)
					throws IOException, ServletException
	{
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted())
		{
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(final Authentication authentication)
	{
		String url;

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = authorities.stream().map(authority -> authority.getAuthority()).collect(Collectors.toList());

		if (isAdmin(roles))
		{
			url = "/admin";
		}
		else if (isUser(roles))
		{
			url = "/user";
		}
		else
		{
			url = "/accessDenied";
		}

		return url;
	}

	private boolean isAdmin(final List<String> roles)
	{
		return roles.contains("ROLE_ADMIN");
	}

	private boolean isUser(final List<String> roles)
	{
		return roles.contains("ROLE_USER");
	}

	protected RedirectStrategy getRedirectStrategy()
	{
		return redirectStrategy;
	}

	public void setRedirectStrategy(final RedirectStrategy redirectStrategy)
	{
		this.redirectStrategy = redirectStrategy;
	}
}
