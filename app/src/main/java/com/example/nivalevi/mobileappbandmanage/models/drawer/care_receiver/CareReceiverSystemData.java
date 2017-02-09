package com.example.nivalevi.mobileappbandmanage.models.drawer.care_receiver;

/**
 * Created by nivalevi on 09/02/2017.
 */
public class CareReceiverSystemData {
    private String name;
    private int patientRoom;
    private Boolean isNeedCare;
    private String systemId;
    private long DbItemId;
    private int timeZone;
    private String imageURL;
    private String kitId;

    private Long lastActionMillis;      //  NOT INIT IN THE CONSTRUCTOR


    public CareReceiverSystemData(long DbItemId, String name, String systemId, Boolean isNeedCare, int patientRoom, int timeZone, String imageURL) {
        this.name = name;
        this.patientRoom = patientRoom;
        this.isNeedCare = isNeedCare;
        this.systemId = systemId;
        this.DbItemId = DbItemId;
        this.timeZone = timeZone;
        this.imageURL = imageURL;
        this.kitId = null;
    }

    public CareReceiverSystemData(long DbItemId, String name, String systemId, Boolean isNeedCare, int patientRoom, int timeZone, String imageURL, String kitId) {
        this(DbItemId, name, systemId, isNeedCare, patientRoom, timeZone, imageURL);
        this.kitId = kitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystemId() {
        return systemId;
    }

    public Boolean isNeedCare() {
        return isNeedCare;
    }

    public void setIsNeedCare(Boolean isNeedCare) {
        this.isNeedCare = isNeedCare;
    }

    public int getPatientRoom() {
        return patientRoom;
    }

    public int getTimeZone() {
        return timeZone;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getKitId() { return kitId; }

    public void setKitId(String kitId) { this.kitId = kitId; }

    public Long getLastActionMillis() {
        return lastActionMillis;
    }

    public void setLastActionMillis(Long lastActionMillis) {
        this.lastActionMillis = lastActionMillis;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}
