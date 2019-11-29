package cs.csula.edu.cloudservice.dto.device;

import javax.validation.constraints.NotEmpty;

public class DevicePostDto {

  @NotEmpty
  private String name;

  @NotEmpty
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
