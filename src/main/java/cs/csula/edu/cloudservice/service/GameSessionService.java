package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.dto.gameSession.GameSessionPostDto;
import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import cs.csula.edu.cloudservice.entity.user.User;
import cs.csula.edu.cloudservice.repository.GameSessionRepository;
import cs.csula.edu.cloudservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameSessionService
{
    private final UserRepository userRepository;
    private final GameSessionRepository gameSessionRepository;
    private final ModelMapper modelMapper;
    private UUID userID;
    private UUID gameID;

    public GameSessionService(GameSessionRepository gameSessionRepository,UserRepository userRepository, ModelMapper modelMapper) {
        this.gameSessionRepository = gameSessionRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public GameSession updateGameSession(String id, Map<String, Object> update)
    {
        gameID = UUID.fromString(id);
        GameSession gameSession = gameSessionRepository.findById(gameID).get();
        for (String key : update.keySet()) {
            switch (key)
            {
                case "calibrationStage":
                    gameSession.setCalibrationStage((String) update.get(key));
                    break;
                case "score":
                    gameSession.setScore((int) update.get(key));
                    break;
                case "rounds":
                     gameSession.setRounds((int) update.get(key));
                     break;
                case "wordsCorrect":
                     gameSession.setWordsCorrect((int) update.get(key));
                     break;
                case "totalWords":
                     gameSession.setTotalWords((int) update.get(key));
                     break;
                case  "totalWrongWords":
                     gameSession.setTotalWrongWords((int) update.get(key));
                     break;
                case "objectsHit":
                     gameSession.setObjectsHit((int) update.get(key));
                     break;
                case "baseline":
                     gameSession.setBaseline((int) update.get(key));
                     break;
                case "speed":
                     gameSession.setSpeed((float) update.get(key));
                     break;
                default:
            }
        }
        return gameSessionRepository.save(gameSession);
    }
    public GameSession createGameSession(GameSessionPostDto gameSessionPostDto)
    {
        //saves user ID
        userID = UUID.fromString(gameSessionPostDto.getUserID());
        gameSessionPostDto.setUser(userRepository.findById(userID).get());
        GameSession gameSession = modelMapper.map(gameSessionPostDto, GameSession.class);
        return gameSessionRepository.save(gameSession);
    }


}
