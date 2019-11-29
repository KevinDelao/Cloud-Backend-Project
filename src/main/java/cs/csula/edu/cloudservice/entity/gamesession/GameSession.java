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
  String calibrationStage;
  int score;
  int rounds;
  int wordsCorrect;
  int totalWords;

  int totalWrongWords;
  int objectsHit;

  int baseline;
  float speed;

  public String getCalibrationStage() {
    return calibrationStage;
  }

  public void setCalibrationStage(String calibrationStage) {
    this.calibrationStage = calibrationStage;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getRounds() {
    return rounds;
  }

  public void setRounds(int rounds) {
    this.rounds = rounds;
  }

  public int getWordsCorrect() {
    return wordsCorrect;
  }

  public void setWordsCorrect(int wordsCorrect) {
    this.wordsCorrect = wordsCorrect;
  }

  public int getTotalWords() {
    return totalWords;
  }

  public void setTotalWords(int totalWords) {
    this.totalWords = totalWords;
  }

  public int getTotalWrongWords() {
    return totalWrongWords;
  }

  public void setTotalWrongWords(int totalWrongWords) {
    this.totalWrongWords = totalWrongWords;
  }

  public int getObjectsHit() {
    return objectsHit;
  }

  public void setObjectsHit(int objectsHit) {
    this.objectsHit = objectsHit;
  }

  public int getBaseline() {
    return baseline;
  }

  public void setBaseline(int baseline) {
    this.baseline = baseline;
  }

  public float getSpeed() {
    return speed;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }


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
