package id.ac.uin.forgotpassword.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.ac.uin.forgotpassword.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}