package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.dto.user.UserPostDto;
import cs.csula.edu.cloudservice.entity.user.User;
import cs.csula.edu.cloudservice.exception.NotFoundException;
import cs.csula.edu.cloudservice.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  private final ModelMapper modelMapper;

  private static final String USER_NOT_EXISTS = "User with username %s does not exist";

  public UserService(UserRepository userRepository, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
  }

  public User createUser(UserPostDto userPostDto) {
    User user = modelMapper.map(userPostDto, User.class);
    return userRepository.save(user);
  }

  public User getUser(UUID id) {
    User user = userRepository.findById(id).get();
    return user;
  }

  public User getUser(String username) {
    Optional<User> userOpt = userRepository.getUserByUsernameEquals(username);

    return userOpt.orElseThrow(() -> new NotFoundException(String.format(USER_NOT_EXISTS, username)));
  }
}
