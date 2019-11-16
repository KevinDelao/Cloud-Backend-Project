package cs.csula.edu.cloudservice.controller;

import cs.csula.edu.cloudservice.dto.gameData.GameDataPostDto;
import cs.csula.edu.cloudservice.entity.gamedata.GameData;
import cs.csula.edu.cloudservice.service.GameDataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConstants.VERSION + PathConstants.GameData)
public class GameDataController
{
    private final GameDataService gameDataService;

    public GameDataController(GameDataService gameDataService) {
        this.gameDataService = gameDataService;
    }

    @PostMapping
    public GameData createUser(@RequestBody GameDataPostDto gameDataPostDto)
    {
        return gameDataService.createGameData(gameDataPostDto);
    }

}
