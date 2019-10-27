package cs.csula.edu.cloudservice.controller;

import cs.csula.edu.cloudservice.dto.device.DevicePostDto;
import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.service.DeviceService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConstants.VERSION + PathConstants.Device)
public class DeviceController
{
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public Device createDevice(DevicePostDto devicePostDto) {
        return deviceService.createDevice(devicePostDto);
    }
}
