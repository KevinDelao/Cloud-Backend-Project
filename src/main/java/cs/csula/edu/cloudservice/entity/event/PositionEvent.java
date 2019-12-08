package cs.csula.edu.cloudservice.entity.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PositionEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID event_id;

  private int x;
  private int y;
  private int z;

  @ManyToOne
  @JoinColumn
  @JsonIgnore
  private Device device;

  @ManyToOne
  @JoinColumn
  @JsonIgnore
  private GameSession gameSession;

  public UUID getId() {
    return event_id;
  }

  public void setId(UUID id) {
    this.event_id = id;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getZ() {
    return z;
  }

  public void setZ(int z) {
    this.z = z;
  }

  public Device getDevice() {
    return device;
  }

  public void setDevice(Device device) {
    this.device = device;
  }

  public GameSession getGameSession() {
    return gameSession;
  }

  public void setGameSession(GameSession gameSession) {
    this.gameSession = gameSession;
  }
}
