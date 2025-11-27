package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.RolRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private RolRepository rolRepo;

    @Override
    public Usuario registrar(Usuario usuario) {
        return repo.save(usuario);
    }

    @Override
    public Usuario login(String correo, String contrasena) {
        Usuario u = repo.findByCorreo(correo);
        if(u != null && u.getContrasena().equals(contrasena)) {
            return u;
        }
        return null;
    }

    @Override
    public List<Usuario> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        Optional<Usuario> u = repo.findById(id);
        return u.orElse(null);
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        return repo.save(usuario);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}

