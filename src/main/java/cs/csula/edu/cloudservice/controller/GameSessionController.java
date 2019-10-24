package cs.csula.edu.cloudservice.controller;

import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import cs.csula.edu.cloudservice.service.GameSessionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConstants.VERSION + PathConstants.GameSession)
public class GameSessionController
{
   private final GameSessionService gameSessionService;
    public GameSessionController(GameSessionService gameSessionService)
    {
        this.gameSessionService = gameSessionService;
    }
    @PostMapping
    public GameSession createGameSession(GameSession gameSession)
    {
        return gameSessionService.createGameSession(gameSession);
    }

}
