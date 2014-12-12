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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author PTamang
 */
@ManagedBean(name = "driverController")
@RequestScoped
public class DriverController implements Serializable {
    
    private Driver driver;
    @EJB
    private DriverFacadeLocal dfacade;
    
    private String repass;
    
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
    
    public void checkCredentials() {
        
    }
    
    public void signUp() {
        dfacade.create(driver);
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
    
    public String index(){
        return "index";
    }
}
