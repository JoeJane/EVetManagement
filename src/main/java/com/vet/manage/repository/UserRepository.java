package com.vet.manage.repository;

import com.vet.manage.model.dto.Role;
import com.vet.manage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository for User
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT e FROM User as e WHERE (:inputString is null or e.lastName like %:inputString% " +
            "or e.firstName like %:inputString% or e.username like %:inputString% " +
            "or e.address.street like %:inputString% or e.address.city like %:inputString% " +
            "or concat(e.address.province, '') like %:inputString% or e.address.postalCode like %:inputString% " +
            "or e.phoneNumber like %:inputString% " +
            "or concat(e.role, '') like %:inputString% " +
            "or e.address.country like %:inputString% or e.gender like %:inputString% ) and e.deleted = :status " +
            "and e.role IN :roles"
    )
    public List<User> findUser(String inputString, Boolean status, List<Role> roles);

    @Query(value = "SELECT e FROM User as e WHERE (:inputString is null or e.lastName like %:inputString% " +
            "or e.firstName like %:inputString% or e.username like %:inputString% " +
            "or e.address.street like %:inputString% or e.address.city like %:inputString% " +
            "or concat(e.address.province, '') like %:inputString% or e.address.postalCode like %:inputString% " +
            "or e.phoneNumber like %:inputString% " +
            "or concat(e.role, '') like %:inputString% " +
            "or e.address.country like %:inputString% or e.gender like %:inputString% ) " +
            "and e.role IN :roles"
    )
    public List<User> findUser(String inputString, List<Role> roles);

    public Optional<User> findUserByUsername(String username);

    public List<User> findByRoleNot(Role role);

    public List<User> findByRole(Role role);
}
