package cs.csula.edu.cloudservice.dto.gameSession;

import cs.csula.edu.cloudservice.entity.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

public class GameSessionPostDto
{


    private String userID;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;


    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


}
