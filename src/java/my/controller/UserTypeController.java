/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.controller;

import boundary.UserTypeFacadeLocal;
import entities.UserType;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author PTamang
 */
@Named
@RequestScoped
public class UserTypeController {
    
    private UserType utype;
    @EJB
    private UserTypeFacadeLocal utypefacade;
    
    public UserTypeController(){
        utype = new UserType();
    }

    public UserType getUtype() {
        return utype;
    }

    public void setUtype(UserType utype) {
        this.utype = utype;
    }
}
