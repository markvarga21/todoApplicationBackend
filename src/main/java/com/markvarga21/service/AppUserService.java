package com.markvarga21.service;

import com.markvarga21.entity.AppUser;
import com.markvarga21.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppUserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToAppUser(String userName, String roleName);
    AppUser getUser(String userName);
    List<AppUser> getUsers();
}
