package com.company;

public class Network {
    private int maxNumberOfConnection;
    private int totalNumberOfConnection;
    private String threadName;
    private String deviceName;

    public Network(String threadName,String deviceName) {
        this.threadName=threadName;
        this.deviceName=deviceName;
    }
    public Network(){}
//    public void setThreadName(String threadName) {
//        this.threadName = threadName;
//    }
    public String getThreadName() {
        return threadName;
    }

//    public void setDeviceName(String deviceName) {
//        this.deviceName = deviceName;
//    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setMaxNumberOfConnection(int maxNumberOfConnection) {
        this.maxNumberOfConnection = maxNumberOfConnection;
    }

    public int getMaxNumberOfConnection() {
        return maxNumberOfConnection;
    }

    public void setTotalNumberOfConnection(int totalNumberOfConnection) {
        this.totalNumberOfConnection = totalNumberOfConnection;
    }

    public int getTotalNumberOfConnection() {
        return totalNumberOfConnection;
    }
}
