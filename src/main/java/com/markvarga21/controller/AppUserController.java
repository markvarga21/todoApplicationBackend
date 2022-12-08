package com.markvarga21.controller;

import com.markvarga21.dto.RoleToUserForm;
import com.markvarga21.entity.AppUser;
import com.markvarga21.entity.Role;
import com.markvarga21.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin
public class AppUserController {
    private final AppUserService userService;

    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers() {
        return new ResponseEntity<>(
                this.userService.getUsers(),
                HttpStatus.OK
        );
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) {
        return new ResponseEntity<>(
                this.userService.saveUser(user),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role) {
        return new ResponseEntity<>(
                this.userService.saveRole(role),
                HttpStatus.CREATED
        );
    }

    @PreAuthorize("hasAuthority('SCOPE_admin')")
    @GetMapping("/role/assignToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        this.userService.addRoleToAppUser(
                form.getUserName(),
                form.getRoleName()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
