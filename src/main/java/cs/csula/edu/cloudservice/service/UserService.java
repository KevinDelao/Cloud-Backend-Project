package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.dto.user.UserPostDto;
import cs.csula.edu.cloudservice.entity.event.PositionEvent;
import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import cs.csula.edu.cloudservice.entity.user.User;
import cs.csula.edu.cloudservice.exception.ConflictException;
import cs.csula.edu.cloudservice.exception.NotFoundException;
import cs.csula.edu.cloudservice.repository.GameSessionRepository;
import cs.csula.edu.cloudservice.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private static final String USER_WITH_ID_NOT_FOUND = "User with id %s does not exist.";
  private static final String USER_WITH_USERNAME_NOT_EXISTS = "User with username %s does not exist.";
  private static final String USER_WITH_USERNAME_EXISTS = "User with username %s already exists.";

  private final UserRepository userRepository;
  private final GameSessionRepository gameSessionRepository;

  private final ModelMapper modelMapper;
  private List<PositionEvent> userEvents;

  public UserService(UserRepository userRepository, ModelMapper modelMapper,
      GameSessionRepository gameSessionRepository) {
    this.userRepository = userRepository;
    this.gameSessionRepository = gameSessionRepository;
    this.modelMapper = modelMapper;
  }

  public User createUser(UserPostDto userPostDto) {
    validateUser(userPostDto.getUsername());
    User user = modelMapper.map(userPostDto, User.class);
    return userRepository.save(user);
  }

  private void validateUser(String username) {
    if (userRepository.countUsersByUsernameEquals(username) > 0) {
      throw new ConflictException(String.format(USER_WITH_USERNAME_EXISTS, username));
    }
  }

  public User getUserByUsername(UUID id) {
    Optional<User> userOpt = userRepository.findById(id);

    return userOpt
        .orElseThrow(() -> new NotFoundException(String.format(USER_WITH_ID_NOT_FOUND, id)));
  }

  public User getUserByUsername(String username) {
    Optional<User> userOpt = userRepository.getUserByUsernameEquals(username);

    return userOpt
        .orElseThrow(
            () -> new NotFoundException(String.format(USER_WITH_USERNAME_NOT_EXISTS, username)));
  }

  public List<User> getAll() {
    return userRepository.findAll();

  }

  public List<GameSession> getGameSessionByUserId(UUID userId)
  {
    Optional<User> userOpt = userRepository.findById(userId);

    if (!userOpt.isPresent()) {
      throw new NotFoundException(String.format(USER_WITH_ID_NOT_FOUND, userId));
    }

    return gameSessionRepository.getAllByUser(userOpt.get());
  }
}