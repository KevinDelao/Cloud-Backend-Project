package cs.csula.edu.cloudservice.dto.positionEvent;

import javax.validation.constraints.NotEmpty;

public class PositionEventPostDto {

  private float x;
  private float y;
  private float z;

  @NotEmpty
  private String deviceId;

  @NotEmpty
  private String gameSessionId;

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

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getGameSessionId() {
    return gameSessionId;
  }

  public void setGameSessionId(String gameSessionId) {
    this.gameSessionId = gameSessionId;
  }
}
