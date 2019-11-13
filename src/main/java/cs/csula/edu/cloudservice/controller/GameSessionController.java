package cs.csula.edu.cloudservice.controller;

import cs.csula.edu.cloudservice.dto.gameSession.GameSessionPostDto;
import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import cs.csula.edu.cloudservice.service.GameSessionService;
import org.springframework.web.bind.annotation.*;

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
    public GameSession createGameSession(@RequestBody GameSessionPostDto gameSessionPostDto)
    {
        return gameSessionService.createGameSession(gameSessionPostDto);
    }

}
