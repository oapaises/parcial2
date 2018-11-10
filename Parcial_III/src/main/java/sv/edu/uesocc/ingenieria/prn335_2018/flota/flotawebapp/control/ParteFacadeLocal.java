/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control;

import java.util.List;
import javax.ejb.Local;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.Parte;

/**
 *
 * @author christian
 */
@Local
public interface ParteFacadeLocal {

    void create(Parte parte);

    void edit(Parte parte);

    void remove(Parte parte);

    Parte find(Object id);

    List<Parte> findAll();

    List<Parte> findRange(int[] range);

    int count();
    
}
