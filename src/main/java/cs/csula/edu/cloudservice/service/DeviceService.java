package cs.csula.edu.cloudservice.service;


import cs.csula.edu.cloudservice.dto.device.DevicePostDto;
import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.entity.user.User;
import cs.csula.edu.cloudservice.exception.EntityNotProcessableException;
import cs.csula.edu.cloudservice.repository.DeviceRepository;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

  private static final String USER_NOT_FOUND = "User with id %s does not exist.";

  private final DeviceRepository deviceRepository;
  private final UserService userService;
  private final ModelMapper modelMapper;

  public DeviceService(DeviceRepository deviceRepository, UserService userService, ModelMapper modelMapper) {
    this.deviceRepository = deviceRepository;
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  public Device createDevice(DevicePostDto devicePostDto) {
    Device device = modelMapper.map(devicePostDto, Device.class);
    device.setUser(getUser(devicePostDto.getUserId()));
    return deviceRepository.save(device);
  }

  private User getUser(String userId) {
    UUID userUuid;
    try {
      userUuid = UUID.fromString(userId);
    } catch (IllegalArgumentException ex) {
      throw new EntityNotProcessableException(String.format(USER_NOT_FOUND, userId));
    }

    User user = userService.getUser(userUuid);

    if (user == null) {
      throw new EntityNotProcessableException(String.format(USER_NOT_FOUND, userId));
    }

    return user;
  }
}
