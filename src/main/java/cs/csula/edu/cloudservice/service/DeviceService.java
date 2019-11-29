package cs.csula.edu.cloudservice.service;


import cs.csula.edu.cloudservice.dto.device.DevicePostDto;
import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.exception.ConflictException;
import cs.csula.edu.cloudservice.exception.NotFoundException;
import cs.csula.edu.cloudservice.repository.DeviceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

  private static final String DEVICE_USER_NOT_FOUND = "DEVICE cannot be linked to non existent user id %s";
  private static final String DEVICE_ALREADY_EXISTS = "DEVICE with name %s already exists";
  private static final String DEVICE_NOT_FOUND = "DEVICE with name %s not found";

  private final DeviceRepository deviceRepository;
  private final UserService userService;
  private final ModelMapper modelMapper;

  public DeviceService(DeviceRepository deviceRepository, UserService userService,
      ModelMapper modelMapper) {
    this.deviceRepository = deviceRepository;
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  public Device createDevice(DevicePostDto devicePostDto) {
    validateDevice(devicePostDto.getName());
    Device device = modelMapper.map(devicePostDto, Device.class);
    return deviceRepository.save(device);
  }

  public Device getDeviceByName(String name) {
    return deviceRepository.getDeviceByName(name)
        .orElseThrow(() -> new NotFoundException(String.format(DEVICE_NOT_FOUND, name)));
  }

  private void validateDevice(String name) {
    if (deviceRepository.countDevicesByName(name) > 0) {
      throw new ConflictException(String.format(DEVICE_ALREADY_EXISTS, name));
    }
  }
}
