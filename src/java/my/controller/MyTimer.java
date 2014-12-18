/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.controller;

import entities.Driver;
import entities.Location;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author Santosh
 */

@Stateless
public class MyTimer {

    DriverController dc;
    
    public MyTimer(){
        dc = new DriverController();
    }

    public void driverController(DriverController dc) {
        this.dc = dc;
    }
    
    @Schedule(minute = "*/1", persistent = false)
    public void resetCurrentLocation(){
        System.out.println("Scheduler..");
        //dc.getDriver().setCurrentLocation(new Location());
        //System.out.println("Scheduler called.." + dc.getDriver().getCurrentLocation().getLat());
//        driver.getCurrentLocation().setLat(0);
//        driver.getCurrentLocation().setLon(0);
//        driver.getCurrentLocation().setStreet("");
    }
}
