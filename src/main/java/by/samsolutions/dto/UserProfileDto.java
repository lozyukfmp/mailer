package by.samsolutions.dto;

import by.samsolutions.validation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.NotEmpty;

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
