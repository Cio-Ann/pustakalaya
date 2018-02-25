package cgr.cgfsdam.pustakalaya.service.users;

import cgr.cgfsdam.pustakalaya.model.users.Usuario;

public interface UsuarioService {
	
	/**
	 * Gets a Usuario instance by its username field.
	 * 
	 * @param username String to search in username field.
	 * @return Usuario object matching username field or null.
	 */
	public Usuario findByUsername(String username);
	/**
	 * Gets a Usuario instance by its email field
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
