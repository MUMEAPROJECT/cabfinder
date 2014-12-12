/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.controller;

import boundary.DriverFacadeLocal;
import entities.Driver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author PTamang
 */
@ManagedBean(name = "driverController")
@SessionScoped
public class DriverController implements Serializable {

    private Driver driver;
    @EJB
    private DriverFacadeLocal dfacade;

    private String repass;
    private String username;
    private String password;

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

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public DriverFacadeLocal getDfacade() {
        return dfacade;
    }

    public void setDfacade(DriverFacadeLocal dfacade) {
        this.dfacade = dfacade;
    }

    public DriverController() {
        driver = new Driver();
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String checkCredentials() {
        // Driver result = null;
        try {
            driver = dfacade.checkCredential(username, password);
            return "dashboard";
        } catch (NoResultException | NonUniqueResultException e) {
            FacesMessage msg = new FacesMessage(" Invalid Username and Password. ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "driver_login";
        }
    }

    public String signUp() {
        dfacade.create(driver);
        return "vdriver/driver_login";
    }

    public void uploadAvatar(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        try {
            String filename = "driver/" + event.getFile().getFileName();
            driver.setAvatar(filename);
            copyFile(filename, event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadCabImage(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        try {
            String filename = "cab/" + event.getFile().getFileName();
            driver.setCabImage(filename);
            copyFile(filename, event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyFile(String fileName, InputStream in) {
        String destination = "D:/upload/";
        try {

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String isLoggedIn(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        if (fc.getExternalContext().getSessionMap().get("id") == null) {
            return "main";
        }
        else {
            return "vdriver/dashboard";
        }
    }
}


