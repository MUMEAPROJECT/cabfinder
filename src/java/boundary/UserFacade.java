/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.CabUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author PTamang
 */
@Stateless
public class UserFacade extends AbstractFacade<CabUser> implements UserFacadeLocal {
    @PersistenceContext(unitName = "CabFinderPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(CabUser.class);
    }
    
}
