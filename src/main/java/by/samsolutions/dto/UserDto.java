package by.samsolutions.dto;

import by.samsolutions.validation.PasswordMatches;
import by.samsolutions.validation.ValidEmail;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Size;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

}
