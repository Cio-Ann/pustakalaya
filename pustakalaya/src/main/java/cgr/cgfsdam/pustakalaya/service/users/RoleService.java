package cgr.cgfsdam.pustakalaya.service.users;

import java.util.List;

import cgr.cgfsdam.pustakalaya.model.users.Role;

/**
 * Interfaz del servicio de la entidad Role
 *
 * @author CGR-Casa
 */
public interface RoleService {

	/**
	 * Encuentra un Role por su nombre.
	 * 
	 * @param role String nombre del role a buscar
	 * @return Role objeto
	 */
	public Role findByRole(String role);

	/**
	 * Devuelve una lista con todos los roles de base de datos.
	 * 
	 * @return List<Role> todos los Role
	 */
	public List<Role> findAll();

}
