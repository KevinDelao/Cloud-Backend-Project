package cs.csula.edu.cloudservice.repository;

import cs.csula.edu.cloudservice.entity.event.PositionEvent;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionEventRepository extends JpaRepository<PositionEvent, UUID> {

}
