package cs.csula.edu.cloudservice.service;


import cs.csula.edu.cloudservice.entity.device.Device;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import cs.csula.edu.cloudservice.repository.DeviceRepository;

@Service
public class DeviceService
{
    private final DeviceRepository deviceRepository;
    private final ModelMapper modelMapper;
    public DeviceService(DeviceRepository deviceRepository, ModelMapper modelMapper) {
        this.deviceRepository = deviceRepository;
        this.modelMapper = modelMapper;
    }
    public Device createDevice(Device device)
    {
        Device device1 = modelMapper.map(device, Device.class);
        return deviceRepository.save(device1);
    }


}
