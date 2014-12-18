/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.controller;

import entities.Driver;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Santosh
 */
@Stateless
public class MyTimer {

    Driver d;

    public MyTimer() {
    }

//    public MyTimer(Driver dc) {
//        d = dc;
//    }

//    public void driverController(Driver dc) {
//        this.d = dc;
//    }
//    
    //@Schedule(minute = "*/1", persistent = false)
    @Schedule(second = "1", minute = "*/1", hour = "*", persistent = false)
    public void resetCurrentLocation() {
//        try {
//            System.out.println("Scheduler..");
//            if (d != null) {
//                d.setCurrentLocation(new Location());
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        System.out.println("Scheduler..");
        //ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
       // Map<String, Object> sessionMap = externalContext.getSessionMap();
       // DriverController d = (DriverController) sessionMap.get("driverController");
        //System.out.println("Scheduler.new.");
        //d.getDriver().setCurrentLocation(new Location());
        //System.out.println("Scheduler called.." + dc.getDriver().getCurrentLocation().getLat());
//        driver.getCurrentLocation().setLat(0);
//        driver.getCurrentLocation().setLon(0);
//        driver.getCurrentLocation().setStreet("");
    }
}
