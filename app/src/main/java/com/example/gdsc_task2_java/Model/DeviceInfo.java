package com.example.gdsc_task2_java.Model;

public class DeviceInfo{
    public String nameDevice;
    public String model;
    public int sdkVersion;
    public String apkVersion;

    public DeviceInfo(String nameDevice, String model, int sdkVersion, String apkVersion) {
        this.nameDevice = nameDevice;
        this.model = model;
        this.sdkVersion = sdkVersion;
        this.apkVersion = apkVersion;
    }
}
