package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Usuario;

public interface UsuarioService {
    Usuario registrar(Usuario usuario);
    Usuario login(String correo, String contrasena);
    List<Usuario> listarTodos();
    Usuario buscarPorId(Integer id);
    Usuario actualizar(Usuario usuario);
    void eliminar(Integer id);
}
