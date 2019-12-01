package cs.csula.edu.cloudservice.dto.positionEvent;

import javax.validation.constraints.NotEmpty;

public class PositionEventPostDto {

  private int x;
  private int y;
  private int z;

  @NotEmpty
  private String deviceId;

  @NotEmpty
  private String gameSessionId;

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
