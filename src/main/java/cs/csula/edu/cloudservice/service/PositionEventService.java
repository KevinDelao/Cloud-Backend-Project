package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.dto.positionEvent.PositionEventPostDto;
import cs.csula.edu.cloudservice.entity.event.PositionEvent;
import cs.csula.edu.cloudservice.repository.DeviceRepository;
import cs.csula.edu.cloudservice.repository.GameSessionRepository;
import cs.csula.edu.cloudservice.repository.PositionEventRepository;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PositionEventService {

  private final PositionEventRepository positionEventRepository;
  private final DeviceRepository deviceRepository;
  private final GameSessionRepository gameSessionRepository;
  private final ModelMapper modelMapper;

  public PositionEventService(PositionEventRepository positionEventRepository,
      DeviceRepository deviceRepository, GameSessionRepository gameSessionRepository,
      ModelMapper modelMapper) {
    this.positionEventRepository = positionEventRepository;
    this.modelMapper = modelMapper;
    this.deviceRepository = deviceRepository;
    this.gameSessionRepository = gameSessionRepository;
  }


  public PositionEvent createPositionEvent(PositionEventPostDto positionEventPostDto) {
    UUID deviceID = UUID.fromString(positionEventPostDto.getDeviceID());
    UUID gameID = UUID.fromString(positionEventPostDto.getGameID());
    PositionEvent positionEvent = modelMapper.map(positionEventPostDto, PositionEvent.class);

    positionEvent.setDevice(deviceRepository.findById(deviceID).get());
    positionEvent.setGameSession(gameSessionRepository.findById(gameID).get());
    return positionEventRepository.save(positionEvent);
  }
}
