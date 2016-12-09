package by.samsolutions.dto;

import org.hibernate.validator.constraints.NotEmpty;

import by.samsolutions.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto implements BaseDto
{

	@NotEmpty
	@ValidEmail
	private String email;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String secondName;

	@NotEmpty
	private String thirdName;

	private String imageUrl;

	private String username;

}
