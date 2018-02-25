package cgr.cgfsdam.pustakalaya.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.users.Role;

/**
 * Interfaz del repositorio de roles.
 *
 * @author CGR-Casa
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
	/**
	 * Busca un rol por su nombre.
	 * 
	 * @param role String nombre del rol
	 * @return Role si existe uno con ese nombre o <code>null</code> en caso contrario
	 */
	Role findByRole(String role);
}
