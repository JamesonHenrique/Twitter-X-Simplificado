package twitterx.simplificado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import twitterx.simplificado.entities.Role;


import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, Long> {
Role findByName(String name);
}
