package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.entity.user.User;
import cs.csula.edu.cloudservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }
}
