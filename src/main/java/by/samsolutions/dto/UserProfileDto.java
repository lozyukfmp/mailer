package by.samsolutions.dto;

import by.samsolutions.validation.ValidEmail;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UserProfileDto {

    @NotEmpty
    @ValidEmail
    private String email;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String secondName;

    @NotEmpty
    private String thirdName;

    private String username;

}
