package cs.csula.edu.cloudservice.entity.device;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cs.csula.edu.cloudservice.entity.event.PositionEvent;
import cs.csula.edu.cloudservice.entity.user.User;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Device {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID device_id;

  private String name;

  @OneToMany(mappedBy = "device")
  @JsonIgnore
  private List<PositionEvent> positionEvents;

  @ManyToOne
  @JoinColumn
  @JsonIgnore
  private User user;

  public UUID getId() {
    return device_id;
  }

  public void setId(UUID id) {
    this.device_id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<PositionEvent> getPositionEvents() {
    return positionEvents;
  }

  public void setPositionEvents(
      List<PositionEvent> positionEvents) {
    this.positionEvents = positionEvents;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
