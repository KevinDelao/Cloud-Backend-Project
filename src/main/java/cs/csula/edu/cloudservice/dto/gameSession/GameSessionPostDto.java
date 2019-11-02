package cs.csula.edu.cloudservice.dto.gameSession;

import cs.csula.edu.cloudservice.entity.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

public class GameSessionPostDto
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    /*
    @ManyToOne
    @JoinColumn
    private User user;
    */
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }


}
