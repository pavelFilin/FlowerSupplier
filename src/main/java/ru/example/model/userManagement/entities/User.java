package ru.filin.HavachMVC.model.userManagement.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class User implements UserDetails {
    private long id;

    private String email;

    private String firstName;

    private String lastName;

    private String password;

    private boolean active;

    private String code;

    List<Role> roles = new ArrayList<>();

    public boolean isAdmin() {
        return roles.stream().filter(role -> "ADMIN".equals(role.getName())).count() > 0;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
