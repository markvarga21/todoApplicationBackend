package com.markvarga21.controller;

import com.markvarga21.entity.AppUser;
import com.markvarga21.entity.Role;
import com.markvarga21.service.AppUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers() {
        return new ResponseEntity<>(
                this.userService.getUsers(),
                HttpStatus.OK
        );
    }

    @PostMapping("/save")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) {
        return new ResponseEntity<>(
                this.userService.saveUser(user),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role) {
        return new ResponseEntity<>(
                this.userService.saveRole(role),
                HttpStatus.CREATED
        );
    }

    @GetMapping("role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        this.userService.addRoleToAppUser(
                form.getUserName(),
                form.getRoleName()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

@Data
class RoleToUserForm {
    private String userName;
    private String roleName;
}
