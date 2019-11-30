package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.dto.gameSession.GameSessionPostDto;
import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import cs.csula.edu.cloudservice.entity.user.User;
import cs.csula.edu.cloudservice.exception.EntityNotProcessableException;
import cs.csula.edu.cloudservice.exception.NotFoundException;
import cs.csula.edu.cloudservice.repository.GameSessionRepository;
import java.util.Map;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GameSessionService {

  private static final String GAME_SESSION_NOT_FOUND = "Game session with id %s not found.";
  private static final String GAME_SESSION_USER_NOT_FOUND = "Game session cannot be linked to non-existent user with id %s.";

  private final UserService userService;
  private final GameSessionRepository gameSessionRepository;
  private final ModelMapper modelMapper;

  public GameSessionService(GameSessionRepository gameSessionRepository,
      UserService userService, ModelMapper modelMapper) {
    this.gameSessionRepository = gameSessionRepository;
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  public GameSession updateGameSession(String id, Map<String, Object> update) {
    GameSession gameSession = getGameSession(id);

    for (String key : update.keySet()) {
      switch (key) {
        case "calibrationStage":
          gameSession.setCalibrationStage((String) update.get(key));
        case "score":
          gameSession.setScore((int) update.get(key));
        case "rounds":
          gameSession.setRounds((int) update.get(key));
        case "wordsCorrect":
          gameSession.setWordsCorrect((int) update.get(key));
        case "totalWords":
          gameSession.setTotalWords((int) update.get(key));
        case "totalWrongWords":
          gameSession.setTotalWrongWords((int) update.get(key));
        case "objectsHit":
          gameSession.setObjectsHit((int) update.get(key));
        case "baseline":
          gameSession.setBaseline((int) update.get(key));
        case "speed":
          gameSession.setSpeed((float) update.get(key));
        default:
      }
    }

    return gameSessionRepository.save(gameSession);
  }

  private GameSession getGameSession(String gameId) {
    UUID gameUuid;

    try {
      gameUuid = UUID.fromString(gameId);
    } catch (IllegalArgumentException ex) {
      throw new NotFoundException(String.format(GAME_SESSION_NOT_FOUND, gameId));
    }

    return gameSessionRepository.findById(gameUuid)
        .orElseThrow(() -> new NotFoundException(String.format(GAME_SESSION_NOT_FOUND, gameId)));
  }

  public GameSession createGameSession(GameSessionPostDto gameSessionPostDto) {
    GameSession gameSession = modelMapper.map(gameSessionPostDto, GameSession.class);
    gameSession.setUser(getUser(gameSessionPostDto.getUserId()));
    return gameSessionRepository.save(gameSession);
  }

  private User getUser(String userId) {
    UUID userUuid;
    try {
      userUuid = UUID.fromString(userId);
    } catch (IllegalArgumentException ex) {
      throw new EntityNotProcessableException(String.format(GAME_SESSION_USER_NOT_FOUND, userId));
    }

    try {
      return userService.getUserByUsername(userUuid);
    } catch (NotFoundException ex) {
      throw new EntityNotProcessableException(String.format(GAME_SESSION_USER_NOT_FOUND, userId));
    }
  }
}
