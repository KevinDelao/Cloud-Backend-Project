package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.dto.gameSession.GameSessionPostDto;
import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import cs.csula.edu.cloudservice.repository.GameSessionRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

@Service
public class GameSessionService
{
    private final GameSessionRepository gameSessionRepository;
    private final ModelMapper modelMapper;

    public GameSessionService(GameSessionRepository gameSessionRepository, ModelMapper modelMapper) {
        this.gameSessionRepository = gameSessionRepository;
        this.modelMapper = modelMapper;
    }


    public GameSession createGameSession(GameSessionPostDto gameSessionPostDto)
    {
        GameSession gameSession = modelMapper.map(gameSessionPostDto, GameSession.class);
        return gameSessionRepository.save(gameSession);
    }


}
