package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Rol;
import com.example.demo.model.Usuario;
import com.example.demo.repository.RolRepository;
import com.example.demo.service.UsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolRepository rolRepo;

    // Redireccion apenaz empieza
    @GetMapping
    public String root() {
        return "redirect:/login";
    }

    // Formulario de login
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
        return "redirect:/productos"; 
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
    	model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolRepo.findAll());
        return "usuario/register";
    }

    @PostMapping("/register")
    public String register(
    		@Valid @ModelAttribute("usuario") Usuario usuario,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Rol por defecto → Cliente
        Rol rolCliente = rolRepo.findById(2).orElse(null);
        usuario.setRol(rolCliente);
        
        if (result.hasErrors()) {
            model.addAttribute("roles", rolRepo.findAll());
            return "usuario/register";
        }

        usuarioService.registrar(usuario);
        redirectAttributes.addFlashAttribute("success", "Usuario registrado correctamente");
        
        return "redirect:/login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}