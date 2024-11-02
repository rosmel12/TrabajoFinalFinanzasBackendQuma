package org.example.trabajofinalfinanzasbackend.controller;

import org.example.trabajofinalfinanzasbackend.dtos.UserDto;
import org.example.trabajofinalfinanzasbackend.dtos.UsuarioDto;
import org.example.trabajofinalfinanzasbackend.model.Usuario;
import org.example.trabajofinalfinanzasbackend.security.JwtResponse;
import org.example.trabajofinalfinanzasbackend.security.JwtTokenUtil;
import org.example.trabajofinalfinanzasbackend.serviceimplements.JwtUserDetailsService;
import org.example.trabajofinalfinanzasbackend.servicesinterfaces.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quma")
@CrossOrigin()
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/register")
    public Integer createUsuario(@RequestBody UsuarioDto usuario) throws Exception {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Usuario user = modelMapper.map(usuario, Usuario.class);
            return usuarioService.agregarUsuario(user);
        } catch(Exception e) {
            throw new Exception("Error al insertar usuario");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticateUser(@RequestBody UserDto user) throws Exception {
        authenticateUser(user.getUsername(), user.getPassword());
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    private void authenticateUser(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch(DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        }catch(BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

    }

    @GetMapping("/cliente/usuario/id/{username}")
    public UsuarioDto usuarioCliente(@PathVariable String username){
            ModelMapper modelMapper = new ModelMapper();
            Usuario usuarios = usuarioService.buscarUsuario(username);
            return modelMapper.map(usuarios, UsuarioDto.class)      ;
    }
}
