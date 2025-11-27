package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Rol;
import com.example.demo.model.Usuario;
import com.example.demo.repository.RolRepository;
import com.example.demo.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepository rolRepo;

    @GetMapping("/login")
    public String loginForm(Model model) {
        return "usuario/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String correo,
                        @RequestParam String contrasena,
                        HttpSession session,
                        Model model) {

        Usuario u = usuarioService.login(correo, contrasena);

        if (u == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            return "usuario/login";
        }

        session.setAttribute("usuario", u);
        return "redirect:/usuario/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("roles", rolRepo.findAll());
        return "usuario/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String nombres,
                           @RequestParam String apellidos,
                           @RequestParam String dni,
                           @RequestParam String correo,
                           @RequestParam String contrasena) {

        Rol rolCliente = rolRepo.findById(2).orElse(null);

        Usuario u = new Usuario();
        u.setNombres(nombres);
        u.setApellidos(apellidos);
        u.setDni(dni);
        u.setCorreo(correo);
        u.setContrasena(contrasena);
        u.setRol(rolCliente);

        usuarioService.registrar(u);

        return "redirect:/usuario/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/usuario/login";
    }
}
