package cs.csula.edu.cloudservice.repository;

import cs.csula.edu.cloudservice.entity.gamedata.GameData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GameDataRepository extends JpaRepository<GameData, UUID> {
}
