package cs.csula.edu.cloudservice.entity.gamesession;

import cs.csula.edu.cloudservice.entity.event.PositionEvent;
import cs.csula.edu.cloudservice.entity.user.User;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class GameSession {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDateTime;

  @OneToMany(mappedBy = "gameSession")
  private List<PositionEvent> positionEvents;

  @ManyToOne
  @JoinColumn
  private User user;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Date getCreationDateTime() {
    return creationDateTime;
  }

  public void setCreationDateTime(Date creationDateTime) {
    this.creationDateTime = creationDateTime;
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
