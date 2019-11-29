package cs.csula.edu.cloudservice.service;


import cs.csula.edu.cloudservice.dto.device.DevicePostDto;
import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.repository.DeviceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

  private final DeviceRepository deviceRepository;
  private final ModelMapper modelMapper;

  public DeviceService(DeviceRepository deviceRepository, ModelMapper modelMapper) {
    this.deviceRepository = deviceRepository;
    this.modelMapper = modelMapper;
  }

  public Device createDevice(DevicePostDto devicePostDto) {
    Device device = modelMapper.map(devicePostDto, Device.class);
    return deviceRepository.save(device);
  }


}
