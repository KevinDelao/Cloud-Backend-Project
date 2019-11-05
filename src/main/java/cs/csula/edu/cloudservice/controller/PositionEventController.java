package cs.csula.edu.cloudservice.controller;


import cs.csula.edu.cloudservice.dto.positionEvent.PositionEventPostDto;
import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.entity.event.PositionEvent;
import cs.csula.edu.cloudservice.entity.gamesession.GameSession;
import cs.csula.edu.cloudservice.service.DeviceService;
import cs.csula.edu.cloudservice.service.PositionEventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping(PathConstants.VERSION + PathConstants.PositionEvent)
public class PositionEventController
{
    private final PositionEventService positionEventService;


    public PositionEventController(PositionEventService positionEventService)
    {
        this.positionEventService = positionEventService;

    }

    @PostMapping

    public PositionEvent createPositionEvent(HttpServletRequest request, PositionEventPostDto positionEventPostDto)
    {
        UUID deviceID = UUID.fromString(request.getParameter("deviceID"));
        UUID gameID = UUID.fromString(request.getParameter("gameID"));
        positionEventService.createPositionEvent(positionEventPostDto);
        
        Device device = positionEventService.getDeviceByID(deviceID);
        GameSession gameSession = positionEventService.getGameSessionByID(gameID);
        positionEventPostDto.setDevice(device);
        positionEventPostDto.setGameSession(gameSession);

        return positionEventService.createPositionEvent(positionEventPostDto);
    }
}
