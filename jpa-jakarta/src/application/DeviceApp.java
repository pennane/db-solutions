package application;

import entity.*;
import dao.*;

public class DeviceApp {

    public static void main(String[] args) {
    	Dao dao = new Dao();
        Device l1 = new Device("Dell Latitude 7490");
        dao.saveDevice(l1);
        System.out.println("Device saved.");
        System.out.println(l1.getDeviceId());
    }
}

