package cgr.cgfsdam.pustakalaya.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.users.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRole(String role);
}
