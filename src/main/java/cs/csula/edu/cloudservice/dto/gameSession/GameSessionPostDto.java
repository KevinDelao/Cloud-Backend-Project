package cs.csula.edu.cloudservice.dto.gameSession;

import javax.validation.constraints.NotEmpty;

public class GameSessionPostDto {

  @NotEmpty
  private String userID;
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

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }


}
