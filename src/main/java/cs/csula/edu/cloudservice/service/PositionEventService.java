package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.dto.positionEvent.PositionEventPostDto;
import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.entity.event.PositionEvent;
import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import cs.csula.edu.cloudservice.exception.EntityNotProcessableException;
import cs.csula.edu.cloudservice.repository.DeviceRepository;
import cs.csula.edu.cloudservice.repository.GameSessionRepository;
import cs.csula.edu.cloudservice.repository.PositionEventRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PositionEventService {

  private static final String DEVICE_NOT_FOUND = "Position event could not be linked to non-existent device with id %s.";
  private static final String GAME_SESSION_NOT_FOUND = "Position event could not be linked to non-existent game session with id %s.";

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


  public void createPositionEvent(PositionEventPostDto positionEventPostDto) {
    PositionEvent positionEvent = modelMapper.map(positionEventPostDto, PositionEvent.class);
    positionEvent.setDevice(getDevice(positionEventPostDto.getDeviceId()));
    positionEvent.setGameSession(getGameSession(positionEventPostDto.getGameId()));

    positionEventRepository.save(positionEvent);
  }

  public void createPositionEvents(List<PositionEventPostDto> positionEventPostDtos) {
    if (!positionEventPostDtos.isEmpty()) {
      final PositionEventPostDto positionEventPostDto = positionEventPostDtos.get(0);
      final Device device = getDevice(positionEventPostDto.getDeviceId());
      final GameSession gameSession = getGameSession(positionEventPostDto.getGameId());

      List<PositionEvent> positionEvents = new ArrayList<>();
      modelMapper.map(positionEventPostDtos, positionEvents);

      for (PositionEvent positionEvent : positionEvents) {
        positionEvent.setDevice(device);
        positionEvent.setGameSession(gameSession);
      }

      positionEventRepository.saveAll(positionEvents);
    }
  }

  private Device getDevice(String deviceId) {
    UUID deviceUuid;
    try {
      deviceUuid = UUID.fromString(deviceId);
    } catch (IllegalArgumentException ex) {
      throw new EntityNotProcessableException(String.format(DEVICE_NOT_FOUND, deviceId));
    }

    Device device = deviceRepository.getOne(deviceUuid);

    if (device == null) {
      throw new EntityNotProcessableException(String.format(DEVICE_NOT_FOUND, deviceId));
    }

    return device;
  }

  private GameSession getGameSession(String gameSessionId) {
    UUID gameSessionUuid;
    try {
      gameSessionUuid = UUID.fromString(gameSessionId);
    } catch (IllegalArgumentException ex) {
      throw new EntityNotProcessableException(String.format(GAME_SESSION_NOT_FOUND, gameSessionId));
    }

    GameSession gameSession = gameSessionRepository.getOne(gameSessionUuid);

    if (gameSession == null) {
      throw new EntityNotProcessableException(String.format(GAME_SESSION_NOT_FOUND, gameSessionId));
    }

    return gameSession;
  }
}
