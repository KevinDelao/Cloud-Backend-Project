package cs.csula.edu.cloudservice.dto.gameSession;

import cs.csula.edu.cloudservice.entity.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

public class GameSessionPostDto
{


    private String userID;
    private User user;
    String calibrationStage;
    int score;

    int rounds;
    int wordsCorrect;
    int totalWords;

    int totalWrongWords;
    int objectsHit;

    int baseline;
    float speed;

    public String getCalibrationStage() {
        return calibrationStage;
    }

    public void setCalibrationStage(String calibrationStage) {
        this.calibrationStage = calibrationStage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getWordsCorrect() {
        return wordsCorrect;
    }

    public void setWordsCorrect(int wordsCorrect) {
        this.wordsCorrect = wordsCorrect;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public void setTotalWords(int totalWords) {
        this.totalWords = totalWords;
    }

    public int getTotalWrongWords() {
        return totalWrongWords;
    }

    public void setTotalWrongWords(int totalWrongWords) {
        this.totalWrongWords = totalWrongWords;
    }

    public int getObjectsHit() {
        return objectsHit;
    }

    public void setObjectsHit(int objectsHit) {
        this.objectsHit = objectsHit;
    }

    public int getBaseline() {
        return baseline;
    }

    public void setBaseline(int baseline) {
        this.baseline = baseline;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

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
