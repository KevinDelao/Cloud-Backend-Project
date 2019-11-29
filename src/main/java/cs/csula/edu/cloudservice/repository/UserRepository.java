package cs.csula.edu.cloudservice.repository;

import cs.csula.edu.cloudservice.entity.user.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> getUserByUsernameEquals(String username);
}
