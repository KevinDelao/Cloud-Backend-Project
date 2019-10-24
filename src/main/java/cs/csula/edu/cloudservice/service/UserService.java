package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.dto.user.UserPostDto;
import cs.csula.edu.cloudservice.entity.user.User;
import cs.csula.edu.cloudservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
<<<<<<< HEAD
public class UserService
{
=======
public class UserService {
>>>>>>> 11067afd8969226b935dd16838067d48faf66ff2

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
