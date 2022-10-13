package com.codej.serviceImp;

import com.codej.model.Cliente;
import com.codej.model.Rol;
import com.codej.repository.IClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IClienteRepository clienteRepository;

    //logger
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);


    public UsuarioService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByEmail(username);

        if (cliente == null) {
            logger.error("Error en el login: no existe el usuario '" + username + "' en el sistema!");
            throw new UsernameNotFoundException("Error en el login: no existe el usuario '" + username + "' " +
                    "en el sistema!");
        }

        List<GrantedAuthority> authorities = cliente.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        for (Rol rol : cliente.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        return new User(cliente.getUsername(), cliente.getPassword(), cliente.isEnabled(),
                true, true, true, authorities);
    }

}
