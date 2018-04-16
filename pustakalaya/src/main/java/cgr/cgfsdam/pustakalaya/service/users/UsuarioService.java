package cgr.cgfsdam.pustakalaya.service.users;

import java.util.List;

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
	 * Guarda un usuario en base de datos.
	 * Valida si todos los campos están completados correctamente y rellena los campos obligatorios que falten.
	 * 
	 * @param usuario Usuario instancia a guardar.
	 */
	public void saveUsuario(Usuario usuario);

	/**
	 * Guarda un usuario en base de datos.
	 * Almacena el usuario en base de datos tal y como lo recibe del controlador.
	 * 
	 * @param usuario Usuario instancia a guardar.
	 */
	public void saveRawUsuario(Usuario usuario);

	/**
	 * Recupera todos los usuarios de base de datos.
	 * 
	 * @return List<Usuario> todos los usuarios.
	 */
	public List<Usuario> findAll();

}
