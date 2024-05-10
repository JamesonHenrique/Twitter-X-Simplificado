package twitterx.simplificado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import twitterx.simplificado.entities.User;

import java.util.UUID;

public interface TweetRepository extends JpaRepository<User, UUID> {
}
