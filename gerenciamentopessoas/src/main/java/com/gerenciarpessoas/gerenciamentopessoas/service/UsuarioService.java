package com.gerenciarpessoas.gerenciamentopessoas.service;

import java.nio.charset.Charset;
import java.util.Optional;

import com.gerenciarpessoas.gerenciamentopessoas.model.UsuarioLogin;
import com.gerenciarpessoas.gerenciamentopessoas.model.UsuarioModel;
import com.gerenciarpessoas.gerenciamentopessoas.repository.UsuarioRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Optional<UsuarioModel> cadastrarUsuario(UsuarioModel usuario) {

        // primeiro valida se o usuario é existente
        if (repository.findByUsuario(usuario.getUsuario()).isPresent()) {
            return Optional.empty();
        }

        usuario.setSenha(criptografarSenha(usuario.getSenha()));
        return Optional.of(repository.save(usuario));
    }

    public Optional<UsuarioModel> atualizarUsuario(UsuarioModel usuario){
        if (repository.findById(usuario.getId()).isPresent()){
            usuario.setSenha(criptografarSenha(usuario.getSenha()));

            return Optional.of(repository.save(usuario));
        }
        return Optional.empty();
    }

    public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin){
        Optional<UsuarioModel> usuario = repository.findByUsuario(usuarioLogin.get().getUsuario());

        if (usuario.isPresent()){
            if(compararSenha(usuarioLogin.get().getSenha(), usuario.get().getSenha())){

                usuarioLogin.get().setId(usuario.get().getId());
                usuarioLogin.get().setNome(usuario.get().getNome());
                usuarioLogin.get().setSenha(usuario.get().getSenha());
                usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(),usuarioLogin.get().getSenha()));
            }
        }
        return Optional.empty();
    }

    public String criptografarSenha(String senha) {
        return encoder.encode(senha);
    }

    private boolean compararSenha(String senhaDigitada, String senhaBanco) {
        return encoder.matches(senhaDigitada, senhaBanco);
    }

    private String gerarBasicToken(String usuario, String senha) {
        String token = usuario + ":" + senha;
        byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
        return "BASIC " + new String(tokenBase64);
    }
}
