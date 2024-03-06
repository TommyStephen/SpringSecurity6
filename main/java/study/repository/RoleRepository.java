package study.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	 Role findByName(String name);
}
