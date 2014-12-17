/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.controller;

import boundary.LocationFacadeLocal;
import entities.Location;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.naming.InitialContext;

/**
 *
 * @author PTamang
 */
@ManagedBean(name = "locationController")
@SessionScoped
public class LocationController implements Serializable{
    
    private Location location;
    @EJB
    private LocationFacadeLocal lfacade;
    
    public LocationController(){
        location = new Location();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    
    
}
