package twitterx.simplificado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twitterx.simplificado.entities.User;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
}
