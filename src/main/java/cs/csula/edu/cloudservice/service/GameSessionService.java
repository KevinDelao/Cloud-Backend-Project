package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.dto.gameSession.GameSessionPostDto;
import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import cs.csula.edu.cloudservice.entity.user.User;
import cs.csula.edu.cloudservice.repository.GameSessionRepository;
import cs.csula.edu.cloudservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.UUID;

@Service
public class GameSessionService
{
    private final UserRepository userRepository;
    private final GameSessionRepository gameSessionRepository;
    private final ModelMapper modelMapper;
    private UUID userID;
    public GameSessionService(GameSessionRepository gameSessionRepository,UserRepository userRepository, ModelMapper modelMapper) {
        this.gameSessionRepository = gameSessionRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public GameSession createGameSession(GameSessionPostDto gameSessionPostDto)
    {
        userID = UUID.fromString(gameSessionPostDto.getUserID());
        gameSessionPostDto.setUser(userRepository.findById(userID).get());
        GameSession gameSession = modelMapper.map(gameSessionPostDto, GameSession.class);
        return gameSessionRepository.save(gameSession);
    }


}
