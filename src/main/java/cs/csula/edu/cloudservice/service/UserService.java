package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.dto.user.UserPostDto;
import cs.csula.edu.cloudservice.entity.user.User;
import cs.csula.edu.cloudservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service



public class UserService {


  private final UserRepository userRepository;

  private final ModelMapper modelMapper;

  public UserService(UserRepository userRepository, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.modelMapper = modelMapper;
  }

  public User createUser(UserPostDto userPostDto) {
    User user = modelMapper.map(userPostDto, User.class);
    return userRepository.save(user);
  }

}
