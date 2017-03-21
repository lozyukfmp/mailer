package by.samsolutions.imgcloud.dto;

import org.hibernate.validator.constraints.NotEmpty;

import by.samsolutions.imgcloud.validation.PasswordMatches;
import by.samsolutions.imgcloud.validation.ValidPassword;
import by.samsolutions.imgcloud.validation.ValidUsername;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PasswordMatches
public class UserDto implements BaseDto
{
	@ValidUsername
	@NotEmpty
	private String username;

	@ValidPassword
	@NotEmpty
	private String password;

	@NotEmpty
	private String confirmPassword;

	private boolean enabled;
	private boolean admin;
	private UserProfileDto profile;

}