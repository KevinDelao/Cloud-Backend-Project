package cs.csula.edu.cloudservice.dto.positionEvent;

public class PositionEventPostDto {

  private int x;
  private int y;
  private int z;

  private String deviceId;

  private String gameId;

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

  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }
}
