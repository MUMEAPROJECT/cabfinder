/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.UserType;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author PTamang
 */
@Stateless
public class UserTypeFacade extends AbstractFacade<UserType> implements UserTypeFacadeLocal {
    @PersistenceContext(unitName = "CabFinderPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserTypeFacade() {
        super(UserType.class);
    }
    
}
