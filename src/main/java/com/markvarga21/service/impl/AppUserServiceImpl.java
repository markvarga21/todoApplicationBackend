package com.markvarga21.service.impl;

import com.markvarga21.entity.AppUser;
import com.markvarga21.entity.Role;
import com.markvarga21.exception.InvalidRoleException;
import com.markvarga21.exception.InvalidUserCredentials;
import com.markvarga21.exception.RoleNotFoundException;
import com.markvarga21.exception.UserNotFoundException;
import com.markvarga21.repository.AppUserRepository;
import com.markvarga21.repository.RoleRepository;
import com.markvarga21.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    private final AppUserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOptional = this.userRepository.findByUserName(username);
        if (userOptional.isEmpty()) {
            log.error("User not found with username '{}'", username);
            throw new UserNotFoundException(String.format("Username '%s' not found!", username));
        }
        log.info("User '{}' found!", username);
        var user = userOptional.get();
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles()
            .forEach(role ->
                    authorities.add(new SimpleGrantedAuthority(role.getName()))
            );
        return new User(
                user.getUserName(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving user {}", user);
        String userName = user.getUserName();
        if (userNameAlreadyExists(userName)) {
            throw new InvalidUserCredentials(String.format("Username '%s' is already in use!", userName));
        }
        return this.userRepository.save(user);
    }

    private boolean userNameAlreadyExists(String userName) {
        return this.userRepository
                .findByUserName(userName)
                .isPresent();
    }

    private boolean roleNameAlreadyExists(String roleName) {
        return this.roleRepository
                .findByName(roleName)
                .isPresent();
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role {}", role);
        String roleName = role.getName();
        if (roleNameAlreadyExists(roleName)) {
            throw new InvalidRoleException(String.format("Role'%s' already exists!", roleName));
        }
        return this.roleRepository.save(role);
    }

    @Override
    public void addRoleToAppUser(String userName, String roleName) {
        log.info("Adding role {} to user {}", roleName, userName);
        var userOptional  = this.userRepository.findByUserName(userName);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(String.format("User not found with name: %s", userName));
        }
        var roleOptional = this.roleRepository.findByName(roleName);
        if (roleOptional.isEmpty()) {
            throw new RoleNotFoundException(String.format("Role not found with name: %s", roleName));
        }
        userOptional
                .get()
                .getRoles()
                .add(roleOptional.get());
    }

    @Override
    public AppUser getUser(String userName) {
        log.info("Getting user {}", userName);
        var userOptional = this.userRepository.findByUserName(userName);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(String.format("User not found with username '%s'", userName));
        }
        return userOptional.get();
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Getting all users");
        var users = this.userRepository.findAll();
        if (users.isEmpty()) {
            return List.of();
        }
        return users;
    }
}
