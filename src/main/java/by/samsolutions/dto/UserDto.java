package by.samsolutions.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class UserDto {

    @NotEmpty
    @Size(min = 5, max = 15)
    private String username;

    @NotEmpty
    @Size(min = 8, max = 15)
    private String password;
    private String confirmPassword;

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

}
