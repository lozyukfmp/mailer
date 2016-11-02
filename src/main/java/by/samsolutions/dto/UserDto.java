package by.samsolutions.dto;

import by.samsolutions.validation.PasswordMatches;
import by.samsolutions.validation.ValidEmail;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@PasswordMatches
public class UserDto {

    @NotEmpty
    @Size(min = 5, max = 25)
    private String username;

    @NotEmpty
    @Size(min = 8, max = 15)
    private String password;

    @NotEmpty
    @Size(min = 8, max = 15)
    private String confirmPassword;

    @NotEmpty
    @ValidEmail
    private String email;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String secondName;

    @NotEmpty
    private String thirdName;

}
