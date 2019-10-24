package cs.csula.edu.cloudservice.service;

import cs.csula.edu.cloudservice.entity.event.PositionEvent;
import cs.csula.edu.cloudservice.repository.PositionEventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PositionEventService
{
    private final PositionEventRepository positionEventRepository;
    private final ModelMapper modelMapper;
    public PositionEventService(PositionEventRepository positionEventRepository, ModelMapper modelMapper) {
        this.positionEventRepository = positionEventRepository;
        this.modelMapper = modelMapper;
    }

    public PositionEvent createPositionEvent(PositionEvent positionEvent)
    {
        PositionEvent positionEvent1 = modelMapper.map(positionEvent, PositionEvent.class);
        return positionEventRepository.save(positionEvent1);
    }
}
