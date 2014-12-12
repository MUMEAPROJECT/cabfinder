/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.controller;

import boundary.LocationFacadeLocal;
import entities.Location;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author PTamang
 */
@Named
@RequestScoped
public class LocationController {
    
    private Location location;
    @EJB
    private LocationFacadeLocal lfacade;
    
    LocationController(){
        location = new Location();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
}
