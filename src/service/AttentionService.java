package service;

public class AttentionService {
    private long lastFaceDetectedTime = System.currentTimeMillis();
    private final long thresholdMillis = 300;

    public void updateDetection(boolean faceFound) {
        if (faceFound) {
            lastFaceDetectedTime = System.currentTimeMillis();
        }
    }

    public boolean isUserDistracted() {
        return (System.currentTimeMillis() - lastFaceDetectedTime) > thresholdMillis;
    }
}