package id.ac.uin.forgotpassword.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.ac.uin.forgotpassword.entity.PasswordResetToken;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
}
