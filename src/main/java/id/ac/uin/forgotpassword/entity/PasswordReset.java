package id.ac.uin.forgotpassword.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

import id.ac.uin.forgotpassword.validator.PasswordConfirmation;

@Data
@PasswordConfirmation(password = "password", passwordConfirmation = "passwordConfirmation", message = "PASSWORD_NOT_EQUAL")
public class PasswordReset {
    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordConfirmation;
    @NotEmpty
    private String token;
}

