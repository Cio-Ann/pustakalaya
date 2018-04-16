package cgr.cgfsdam.pustakalaya.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.users.Usuario;

/**
 * Interfaz del repositorio de usuario.
 *
 * @author CGR-Casa
 */
@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	/**
	 * Busca un usuario por username.
	 * 
	 * @param username String username del usuario.
	 * @return Usuario con la coincidencia o <code>null</code> en caso contrario
	 */
	Usuario findByUsername(String username);

	/**
	 * Busca un ususario por su email.
	 * 
	 * @param email String email del usuario
	 * @return Usuario con la coincidencia o <code>null</code> en caso contrario
	 */
	Usuario findByEmail(String email);
}
