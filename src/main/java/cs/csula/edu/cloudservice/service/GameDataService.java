package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.dto.gameData.GameDataPostDto;
import cs.csula.edu.cloudservice.entity.gamedata.GameData;
import cs.csula.edu.cloudservice.repository.GameDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GameDataService
{
    private final GameDataRepository gameDataRepository;
    private final ModelMapper modelMapper;

    public GameDataService(GameDataRepository gameDataRepository, ModelMapper modelMapper) {
        this.gameDataRepository = gameDataRepository;
        this.modelMapper = modelMapper;
    }

    public GameData createGameData(GameDataPostDto gameDataPostDto) {
        GameData gameData = modelMapper.map(gameDataPostDto, GameData.class);
        return gameDataRepository.save(gameData);
    }
}
