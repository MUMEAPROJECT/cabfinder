/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.CabUser;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PTamang
 */
@Local
public interface UserFacadeLocal {

    void create(CabUser user);

    void edit(CabUser user);

    void remove(CabUser user);

    CabUser find(Object id);

    List<CabUser> findAll();

    List<CabUser> findRange(int[] range);

    int count();
    
}
