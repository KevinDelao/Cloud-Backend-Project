package cs.csula.edu.cloudservice.controller;

import cs.csula.edu.cloudservice.entity.user.User;
import cs.csula.edu.cloudservice.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConstants.VERSION + PathConstants.USER)
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public User createUser(User user) {
    return userService.createUser(user);
  }
}
