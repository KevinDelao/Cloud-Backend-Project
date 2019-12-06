package cs.csula.edu.cloudservice.dto.device;

import javax.validation.constraints.NotEmpty;

public class DevicePostDto {

  @NotEmpty
  private String userId;
  @NotEmpty
  private String name;

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
