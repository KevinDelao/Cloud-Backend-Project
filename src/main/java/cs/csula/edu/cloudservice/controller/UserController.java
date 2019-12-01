package cs.csula.edu.cloudservice.controller;

import cs.csula.edu.cloudservice.dto.user.UserPostDto;
import cs.csula.edu.cloudservice.entity.user.User;
import cs.csula.edu.cloudservice.exception.NotFoundException;
import cs.csula.edu.cloudservice.service.UserService;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(PathConstants.VERSION + PathConstants.USER)
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public User createUser(@Valid @RequestBody UserPostDto userPostDto) {
    return userService.createUser(userPostDto);
  }

  @GetMapping("/{id}")
  public User getUserById(@PathVariable UUID id) {
    return userService.getUserByUsername(id);
  }

  @GetMapping
  public User getUserByUsername(@RequestParam String username) {
    try {
      return userService.getUserByUsername(username);
    } catch (NotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }
  }
}
