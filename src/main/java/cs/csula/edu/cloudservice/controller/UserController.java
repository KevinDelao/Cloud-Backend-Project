package cs.csula.edu.cloudservice.controller;

import cs.csula.edu.cloudservice.dto.user.UserPostDto;
import cs.csula.edu.cloudservice.entity.user.User;
import cs.csula.edu.cloudservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(PathConstants.VERSION + PathConstants.USER)
public class UserController
{

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public User createUser(UserPostDto userPostDto)
  {

    return userService.createUser(userPostDto);
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable UUID id)
  {
    return userService.getUser(id);
  }
}
