package cgr.cgfsdam.pustakalaya.service.users.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgr.cgfsdam.pustakalaya.model.users.Role;
import cgr.cgfsdam.pustakalaya.repository.users.RoleRepository;
import cgr.cgfsdam.pustakalaya.service.users.RoleService;

/**
 * Implementación del servicio de Role.
 *
 * @author CGR-Casa
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByRole(String role) {

		return roleRepository.findByRole(role);
	}

	@Override
	public List<Role> findAll() {

		return roleRepository.findAll();
	}

}
