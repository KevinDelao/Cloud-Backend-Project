package cs.csula.edu.cloudservice.controller;


import cs.csula.edu.cloudservice.dto.positionEvent.PositionEventPostDto;
import cs.csula.edu.cloudservice.entity.event.PositionEvent;
import cs.csula.edu.cloudservice.exception.EntityNotProcessableException;
import cs.csula.edu.cloudservice.service.PositionEventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(PathConstants.VERSION + PathConstants.PositionEvent)
public class PositionEventController {

  private final PositionEventService positionEventService;


  public PositionEventController(PositionEventService positionEventService) {
    this.positionEventService = positionEventService;

  }

  @PostMapping
  public PositionEvent createPositionEvent(@RequestBody PositionEventPostDto positionEventPostDto) {
    try {
      return positionEventService.createPositionEvent(positionEventPostDto);
    } catch (EntityNotProcessableException ex) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), ex);
    }
  }
}
