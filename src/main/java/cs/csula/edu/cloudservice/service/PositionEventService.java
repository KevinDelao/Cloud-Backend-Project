package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.dto.positionEvent.PositionEventPostDto;
import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.entity.event.PositionEvent;
import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import cs.csula.edu.cloudservice.repository.DeviceRepository;
import cs.csula.edu.cloudservice.repository.GameSessionRepository;
import cs.csula.edu.cloudservice.repository.PositionEventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PositionEventService
{
    private final PositionEventRepository positionEventRepository;
    private final DeviceRepository deviceRepository;
    private final GameSessionRepository gameSessionRepository;
    private final ModelMapper modelMapper;

    public PositionEventService(PositionEventRepository positionEventRepository,DeviceRepository deviceRepository,GameSessionRepository gameSessionRepository, ModelMapper modelMapper) {
        this.positionEventRepository = positionEventRepository;
        this.modelMapper = modelMapper;
        this.deviceRepository = deviceRepository;
        this.gameSessionRepository=gameSessionRepository;
    }

    public Device getDeviceByID(UUID deviceID)
    {
        Device device = deviceRepository.findById(deviceID).get();
        return device;
    }
    public GameSession getGameSessionByID(UUID gameID)
    {
        GameSession gameSession = gameSessionRepository.findById(gameID).get();
        return gameSession;
    }
    public PositionEvent createPositionEvent(PositionEventPostDto positionEventPostDto)
    {
        PositionEvent positionEvent = modelMapper.map(positionEventPostDto, PositionEvent.class);
        return positionEventRepository.save(positionEvent);
    }
}
