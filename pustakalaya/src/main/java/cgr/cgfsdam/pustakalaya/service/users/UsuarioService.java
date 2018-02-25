package cgr.cgfsdam.pustakalaya.service.users;

import cgr.cgfsdam.pustakalaya.model.users.Usuario;

/**
 * Interfaz del servicio de la entidad Usuario.
 *
 * @author CGR-Casa
 */
public interface UsuarioService {
	
	/**
	 * Recupera un Usuario por su username.
	 * 
	 * @param username String nick del usuario a buscar
	 * @return Usuario objeto coincidente o <code>null</code> en caso contrario
	 */
	public Usuario findByUsername(String username);
	/**
	 * Recupera un Usuario por su email
	 * 
	 * @param email email to search.
	 * @return Usuario object matching email field or null.
	 */
	public Usuario findByEmail(String email);
	/**
	 * Saves a user on repository.
	 * 
	 * @param usuario Usuario instance to save.
	 */
	public void saveUsuario(Usuario usuario);

}
