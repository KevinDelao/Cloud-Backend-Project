package cs.csula.edu.cloudservice.dto.user;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class UserPostDto {

  private UUID id;
  @NotEmpty
  private String username;

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
