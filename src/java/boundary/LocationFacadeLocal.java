/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entities.Location;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PTamang
 */
@Local
public interface LocationFacadeLocal {

    void create(Location location);

    void edit(Location location);

    void remove(Location location);

    Location find(Object id);

    List<Location> findAll();

    List<Location> findRange(int[] range);

    int count();
    
}
