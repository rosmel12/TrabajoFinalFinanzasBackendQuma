package org.example.trabajofinalfinanzasbackend.serviceimplements;
import org.example.trabajofinalfinanzasbackend.model.Usuario;
import org.example.trabajofinalfinanzasbackend.repositories.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    private JwtUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUsuarioByUsername(username);
        if(usuario==null){
            throw new UsernameNotFoundException("este usuario "+username+" no existe");
        }
       List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
       grantedAuthorities.add(new SimpleGrantedAuthority(usuario.getRol()));
      return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(),true, true, true, true, grantedAuthorities);
    }
}
