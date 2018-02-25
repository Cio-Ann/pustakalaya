package cgr.cgfsdam.pustakalaya.service.users.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cgr.cgfsdam.pustakalaya.model.users.Role;
import cgr.cgfsdam.pustakalaya.model.users.Usuario;
import cgr.cgfsdam.pustakalaya.repository.users.RoleRepository;
import cgr.cgfsdam.pustakalaya.repository.users.UsuarioRepository;
import cgr.cgfsdam.pustakalaya.service.users.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}
	
	@Override
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public void saveUsuario(Usuario usuario) {
		usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
		usuario.setActive(1);
		if (usuario.getRoles() == null) {
			Role userRole = roleRepository.findByRole("LECTOR");
			usuario.setRoles(new HashSet<Role>(Arrays.asList(userRole)));			
		}
		
		if (usuario.getFechaAlta() == null) {
			usuario.setFechaAlta(new Date());
		}
		
		if (usuario.getFechaVigor() == null) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, 1);
			usuario.setFechaVigor(cal.getTime());
		}

		usuarioRepository.save(usuario);
	}

}
