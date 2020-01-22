package cs.csula.edu.cloudservice.controller;


import cs.csula.edu.cloudservice.dto.positionEvent.PositionEventPostDto;
import cs.csula.edu.cloudservice.exception.EntityNotProcessableException;
import cs.csula.edu.cloudservice.service.PositionEventService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(PathConstants.VERSION + PathConstants.POSITION_EVENT)
public class PositionEventController {

  private final PositionEventService positionEventService;

  public PositionEventController(PositionEventService positionEventService) {
    this.positionEventService = positionEventService;

  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createPositionEvent(
      @Valid @RequestBody PositionEventPostDto positionEventPostDto) {
    try {
      positionEventService.createPositionEvent(positionEventPostDto);
    } catch (EntityNotProcessableException ex) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), ex);
    }
  }

  @PostMapping("/batch")
  @ResponseStatus(HttpStatus.CREATED)
  public void createPositionEvents(
      @Valid @RequestBody List<PositionEventPostDto> positionEventPostDtos) {
    try {
      positionEventService.createPositionEvents(positionEventPostDtos);
    } catch (EntityNotProcessableException ex) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), ex);
    }
  }
}
