/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.controller;

import boundary.DriverFacadeLocal;
import entities.Driver;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

@Stateful
@Named("login")
@SessionScoped
public class UserLogin {

    private String username;
    private String password;
    
    private Driver driver;
    @EJB
    private DriverFacadeLocal dfacade;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String driverLogin(){
        try {
            driver = dfacade.checkCredential(username, password);
            return "dashboard";
        } catch (NoResultException | NonUniqueResultException e) {
            FacesMessage msg = new FacesMessage(" Invalid Username and Password. ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "driver_login";
        }
    }
}
