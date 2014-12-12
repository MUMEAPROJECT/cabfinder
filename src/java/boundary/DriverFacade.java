/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Driver;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author PTamang
 */
@Stateless
public class DriverFacade extends AbstractFacade<Driver> implements DriverFacadeLocal {
    @PersistenceContext(unitName = "CabFinderPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DriverFacade() {
        super(Driver.class);
    }

    @Override
    public Driver checkCredential(String username, String password) throws NoResultException, NonUniqueResultException {
       Object d =  em.createNamedQuery("checkCredentials").setParameter("user", username).setParameter("pass", password).getSingleResult();
       if(d != null){
           return (Driver) d;
       }
        return null;
    }
    
}
