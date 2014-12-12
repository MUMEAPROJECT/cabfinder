/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.controller;

import boundary.UserFacadeLocal;
import entities.CabUser;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author PTamang
 */
@Named
@RequestScoped
public class UserController {
    
    private CabUser user;
    private String testString;
    
    @EJB
    private UserFacadeLocal ufacade;
    
    public UserController(){
        user = new CabUser();
    }

    public CabUser getUser() {
        return user;
    }

    public void setUser(CabUser user) {
        this.user = user;
    }
    
    public void checkCredentials(){
        
    }
}
