package cs.csula.edu.cloudservice.dto.positionEvent;

public class PositionEventPostDto {

  private int x;
  private int y;
  private int z;

  private String deviceID;

  private String gameID;

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

  public String getDeviceID() {
    return deviceID;
  }

  public void setDeviceID(String deviceID) {
    this.deviceID = deviceID;
  }

  public String getGameID() {
    return gameID;
  }

  public void setGameID(String gameID) {
    this.gameID = gameID;
  }
}
