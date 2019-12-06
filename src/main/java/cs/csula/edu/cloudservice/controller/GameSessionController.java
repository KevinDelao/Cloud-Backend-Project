package cs.csula.edu.cloudservice.controller;

import cs.csula.edu.cloudservice.dto.gameSession.GameSessionPostDto;
import cs.csula.edu.cloudservice.entity.event.PositionEvent;
import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import cs.csula.edu.cloudservice.exception.EntityNotProcessableException;
import cs.csula.edu.cloudservice.exception.NotFoundException;
import cs.csula.edu.cloudservice.service.GameSessionService;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(PathConstants.VERSION + PathConstants.GAME_SESSION)
public class GameSessionController {

  private final GameSessionService gameSessionService;

  public GameSessionController(GameSessionService gameSessionService) {
    this.gameSessionService = gameSessionService;
  }

  @PutMapping("/{id}")
  public GameSession updateGameSession(@PathVariable String id,
      @RequestBody Map<String, Object> update) {
    try {
      return gameSessionService.updateGameSession(id, update);
    } catch (NotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GameSession createGameSession(@Valid @RequestBody GameSessionPostDto gameSessionPostDto) {
    try {
      return gameSessionService.createGameSession(gameSessionPostDto);
    } catch (EntityNotProcessableException ex) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), ex);
    }
  }

  @GetMapping("/{id}/position-event")
  public List<PositionEvent> getGamePositionEvents(@PathVariable UUID id) {
    try {
      return gameSessionService.getGameEvents(id);
    } catch (NotFoundException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }
  }
}
