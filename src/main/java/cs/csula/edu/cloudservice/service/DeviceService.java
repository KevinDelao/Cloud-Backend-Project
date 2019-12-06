package cs.csula.edu.cloudservice.service;


import cs.csula.edu.cloudservice.dto.device.DevicePostDto;
import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.entity.user.User;
import cs.csula.edu.cloudservice.exception.ConflictException;
import cs.csula.edu.cloudservice.exception.EntityNotProcessableException;
import cs.csula.edu.cloudservice.exception.NotFoundException;
import cs.csula.edu.cloudservice.repository.DeviceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DeviceService {

  private static final String DEVICE_ALREADY_EXISTS = "Device with name %s already exists";
  private static final String DEVICE_NOT_FOUND = "Device with name %s not found";
  private static final String DEVICE_USER_NOT_FOUND = "Device cannot be linked to non-existent user with id %s.";



  private final DeviceRepository deviceRepository;
  private final UserService userService;
  private final ModelMapper modelMapper;
  private List<DevicePostDto> deviceList;
  public DeviceService(DeviceRepository deviceRepository, UserService userService,
      ModelMapper modelMapper) {
    this.deviceRepository = deviceRepository;
    this.userService = userService;
    this.modelMapper = modelMapper;
    deviceList = new ArrayList<DevicePostDto>();
  }

  public Device createDevice(DevicePostDto devicePostDto) {
    validateDevice(devicePostDto.getName());
    Device device = modelMapper.map(devicePostDto, Device.class);
    device.setUser(getUser(devicePostDto.getUserId()));
    return deviceRepository.save(device);
  }
  private User getUser(String userId) {
    UUID userUuid;
    try {
      userUuid = UUID.fromString(userId);
    } catch (IllegalArgumentException ex) {
      throw new EntityNotProcessableException(String.format(DEVICE_USER_NOT_FOUND, userId));
    }

    try {
      return userService.getUserByUsername(userUuid);
    } catch (NotFoundException ex) {
      throw new EntityNotProcessableException(String.format(DEVICE_USER_NOT_FOUND, userId));
    }
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

  public List<Device> getAll()
  {
//    //remove any previous elements
//    deviceList.clear();
//    for (int i = 0; i < deviceRepository.findAll().size(); i++) {
//      DevicePostDto userPost = modelMapper.map(deviceRepository.findAll().get(i), DevicePostDto.class);
//      deviceList.add(userPost);
//    }
    return deviceRepository.findAll();
  }
}
