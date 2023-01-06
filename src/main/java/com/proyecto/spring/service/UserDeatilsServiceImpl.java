package com.proyecto.spring.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto.spring.model.Usuario;

@Service
public class UserDeatilsServiceImpl implements UserDetailsService {

	@Autowired
	private IUsuarioService iUsuarioService;

	@Autowired
	HttpSession session;

	private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Este es el usuario");
		Optional<Usuario> optionalUser = iUsuarioService.findByEmail(username);
		if (optionalUser.isPresent()) {
			log.info("Este es el id: {}", optionalUser.get().getId());
			session.setAttribute("idUsuario", optionalUser.get().getId());
			Usuario usuario = optionalUser.get();
			return User.builder().username(usuario.getNombre())
					.password(usuario.getPassword())
					.roles(usuario.getTipo()).build();
		}else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}

	}

}
