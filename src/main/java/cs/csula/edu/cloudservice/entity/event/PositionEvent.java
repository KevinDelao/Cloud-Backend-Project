package cs.csula.edu.cloudservice.entity.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
  @JsonProperty("positionEventId")
  private UUID id;

  private float x;
  private float y;
  private float z;
  private float xRot;
  private float yRot;
  private float zRot;
  private float xRotVel;
  private float yRotVel;
  private float zRotVel;
  public float getxRot() {
    return xRot;
  }

  public void setxRot(float xRot) {
    this.xRot = xRot;
  }

  public float getyRot() {
    return yRot;
  }

  public void setyRot(float yRot) {
    this.yRot = yRot;
  }

  public float getzRot() {
    return zRot;
  }

  public void setzRot(float zRot) {
    this.zRot = zRot;
  }

  public float getxRotVel() {
    return xRotVel;
  }

  public void setxRotVel(float xRotVel) {
    this.xRotVel = xRotVel;
  }

  public float getyRotVel() {
    return yRotVel;
  }

  public void setyRotVel(float yRotVel) {
    this.yRotVel = yRotVel;
  }

  public float getzRotVel() {
    return zRotVel;
  }

  public void setzRotVel(float zRotVel) {
    this.zRotVel = zRotVel;
  }

  @ManyToOne
  @JoinColumn
  @JsonIgnore
  private Device device;

  @ManyToOne
  @JoinColumn
  @JsonIgnore
  private GameSession gameSession;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public float getZ() {
    return z;
  }

  public void setZ(float z) {
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
