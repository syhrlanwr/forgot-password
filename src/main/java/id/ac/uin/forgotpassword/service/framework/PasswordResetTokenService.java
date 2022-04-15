package id.ac.uin.forgotpassword.service.framework;

import id.ac.uin.forgotpassword.entity.PasswordResetToken;

public interface PasswordResetTokenService {
    PasswordResetToken getPasswordResetToken(String token);
    PasswordResetToken savePasswordResetToken(PasswordResetToken passwordResetToken);
}


