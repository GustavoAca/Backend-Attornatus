package com.gerenciarpessoas.gerenciamentopessoas.security;


import com.gerenciarpessoas.gerenciamentopessoas.model.UsuarioModel;
import com.gerenciarpessoas.gerenciamentopessoas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<UsuarioModel> usuario = repository.findByUsuario(userName);

        usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " Usuario nao encontrado"));

        return usuario.map(UserDetailsImpl::new).get();
    }


}
