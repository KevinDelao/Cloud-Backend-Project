package cs.csula.edu.cloudservice.serviceTests;
import cs.csula.edu.cloudservice.dto.device.DevicePostDto;
import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.service.DeviceService;
import cs.csula.edu.cloudservice.service.UserService;
import org.junit.jupiter.api.Test;

import cs.csula.edu.cloudservice.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
class ServiceTests {


    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private  UserService userService;

    @Mock
    private  ModelMapper modelMapper;

    private DeviceService deviceService;

    @BeforeEach
    void initUseCase()
    {
        deviceService = new DeviceService(deviceRepository,userService,modelMapper);
    }

    @Test
    void savedDeviceNotNullName()
    {
        DevicePostDto devicePostDto = new DevicePostDto();
        devicePostDto.setName("Kevin");
        devicePostDto.setUserId("1234");
        Device savedDevice = deviceService.createDevice(devicePostDto);
        assertNotNull(savedDevice.getName());
    }

}