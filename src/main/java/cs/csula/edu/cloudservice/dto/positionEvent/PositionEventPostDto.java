package cs.csula.edu.cloudservice.dto.positionEvent;

import javax.validation.constraints.NotEmpty;

public class PositionEventPostDto {

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
