package cn.nicemorning.flower.database.dao;

/**
 * Created by Nicemorning on 14-Mar-18.
 * In package cn.nicemorning.flower.database
 */

public class DandelionModel {
    private int pointX;
    private int pointY;
    private int portOffset;
    private int landOffset;

    public DandelionModel(int pointX, int pointY, int portOffset, int landOffset) {
        this.pointX = pointX;
        this.pointY = pointY;
        this.portOffset = portOffset;
        this.landOffset = landOffset;
    }

    public int getPointX() {
        return pointX;
    }

    public void setPointX(int pointX) {
        this.pointX = pointX;
    }

    public int getPointY() {
        return pointY;
    }

    public void setPointY(int pointY) {
        this.pointY = pointY;
    }

    public int getPortOffset() {
        return portOffset;
    }

    public void setPortOffset(int portOffset) {
        this.portOffset = portOffset;
    }

    public int getLandOffset() {
        return landOffset;
    }

    public void setLandOffset(int landOffset) {
        this.landOffset = landOffset;
    }
}
