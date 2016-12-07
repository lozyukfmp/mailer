package by.samsolutions.dto;

import org.hibernate.validator.constraints.NotEmpty;

import by.samsolutions.validation.PasswordMatches;
import by.samsolutions.validation.ValidPassword;
import by.samsolutions.validation.ValidUsername;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PasswordMatches
public class UserDto
{
	@ValidUsername
	@NotEmpty
	private String username;

	@ValidPassword
	@NotEmpty
	private String password;

	@NotEmpty
	private String confirmPassword;

	private UserProfileDto userProfileDto;

}