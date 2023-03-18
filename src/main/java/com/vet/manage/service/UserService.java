package com.vet.manage.service;

import com.vet.manage.model.dto.Role;
import com.vet.manage.model.dto.SearchTerm;
import com.vet.manage.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Service for User
 * author: Abasibiangake
 */
public interface UserService {

    public Optional<User> findById(Integer id);

    public Optional<User> findUserByUsername(String username);

    public List<User> findAll();

    public List<User> searchUsersForAdminRole(SearchTerm searchTerm);

    public List<User> findByRoleNot(Role role);

    public List<User> findByRole(Role role);

    public void saveAllUser(List<Integer> ids, String status);

    public void saveOrUpdate(User user);

    public void changeStatusById(Integer id, boolean status);

    public void updatePassword(User user);
}
