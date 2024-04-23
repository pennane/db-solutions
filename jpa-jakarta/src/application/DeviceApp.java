package application;

import entity.*;
import dao.*;

public class DeviceApp {

    public static void main(String[] args) {
    	Dao dao = new Dao();
        Device l1 = new Device("Dell Latitude 7490");
        Device l2 = new Device("Cool Device 9001");

        dao.save(l1);
        dao.save(l2);

        System.out.println(l1.getDeviceId());
        System.out.println(l2.getDeviceId());

        dao.updateDescription(l2.getDeviceId(), "Cool Device 9002");

        Device l2Again = dao.load(l2.getDeviceId());
        System.out.println(l2Again.getDescription());

        boolean removed= dao.remove(l1.getDeviceId());
        System.out.println("removed l1: "+removed );
    }
}

