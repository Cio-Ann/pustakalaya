package cgr.cgfsdam.pustakalaya.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cgr.cgfsdam.pustakalaya.model.users.Usuario;

@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

		Usuario findByUsername(String username);

		Usuario findByEmail(String email);
}
