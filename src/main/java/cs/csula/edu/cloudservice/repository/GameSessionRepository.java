package cs.csula.edu.cloudservice.repository;

import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSessionRepository extends JpaRepository<GameSession, UUID> {

}
