package cs.csula.edu.cloudservice.entity.user;

import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(unique = true)
  private String username;

  private String firstName;

  private String lastName;

  @OneToMany(mappedBy = "user")
  private List<Device> devices;

  @OneToMany(mappedBy = "user")
  private List<GameSession> gameSessions;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<Device> getDevices() {
    return devices;
  }

  public void setDevices(List<Device> devices) {
    this.devices = devices;
  }

  public List<GameSession> getGameSessions() {
    return gameSessions;
  }

  public void setGameSessions(
      List<GameSession> gameSessions) {
    this.gameSessions = gameSessions;
  }
}
