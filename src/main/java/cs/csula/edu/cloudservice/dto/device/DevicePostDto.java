package cs.csula.edu.cloudservice.dto.device;

public class DevicePostDto {

  private String name;

  private String userId;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
