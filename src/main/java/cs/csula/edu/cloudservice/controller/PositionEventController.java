package cs.csula.edu.cloudservice.controller;


import cs.csula.edu.cloudservice.dto.positionEvent.PositionEventPostDto;
import cs.csula.edu.cloudservice.entity.event.PositionEvent;

import cs.csula.edu.cloudservice.service.PositionEventService;
import org.springframework.web.bind.annotation.*;


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

    public PositionEvent createPositionEvent(@RequestBody PositionEventPostDto positionEventPostDto)
    {


        return positionEventService.createPositionEvent(positionEventPostDto);
    }
}
