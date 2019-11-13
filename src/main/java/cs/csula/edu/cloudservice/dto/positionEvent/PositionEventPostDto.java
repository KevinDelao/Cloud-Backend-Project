package cs.csula.edu.cloudservice.dto.positionEvent;

import cs.csula.edu.cloudservice.entity.device.Device;
import cs.csula.edu.cloudservice.entity.gamesession.GameSession;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PositionEventPostDto
{
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    private int x;
    private int y;
    private int z;

    private String deviceID;

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    private Device device;

    private String gameID;
    private GameSession gameSession;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }


}
