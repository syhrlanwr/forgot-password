package id.ac.uin.forgotpassword.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.ac.uin.forgotpassword.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}