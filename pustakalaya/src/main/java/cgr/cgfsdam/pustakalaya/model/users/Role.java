package cgr.cgfsdam.pustakalaya.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad que representa un rol de usuario.
 * Se almacena en la tabla role.
 *
 * @author CGR-Casa
 */
@Entity
@Table(name="role")
public class Role {
	
	/**
	 * Identificador del rol.
	 */
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/**
	 * Nombre del rol.
	 */
	@Column(name="role", unique=true)
	private String role;
	
	/**
	 * Descripción del rol.
	 */
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Role() { }

	public Role(String role, String description) {
		this.role = role;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + ", description=" + description + "]";
	}

}
