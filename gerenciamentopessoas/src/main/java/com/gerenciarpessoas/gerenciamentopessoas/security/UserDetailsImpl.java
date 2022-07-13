package com.gerenciarpessoas.gerenciamentopessoas.security;

import java.util.Collection;
import java.util.List;

import com.gerenciarpessoas.gerenciamentopessoas.model.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class UserDetailsImpl implements UserDetails {

    private String userName;
    private String password;

    // autoriza todos os privilegios de usuarios
    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(UsuarioModel usuario) {
        this.userName = usuario.getUsuario();
        this.password = usuario.getSenha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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
        return true;
    }
}
