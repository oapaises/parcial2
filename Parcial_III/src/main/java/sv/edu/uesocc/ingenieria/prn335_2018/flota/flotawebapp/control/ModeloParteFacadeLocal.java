/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control;

import java.util.List;
import javax.ejb.Local;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.ModeloParte;

/**
 *
 * @author christian
 */
@Local
public interface ModeloParteFacadeLocal {

    void create(ModeloParte modeloParte);

    void edit(ModeloParte modeloParte);

    void remove(ModeloParte modeloParte);

    ModeloParte find(Object id);

    List<ModeloParte> findAll();

    List<ModeloParte> findRange(int[] range);

    int count();
    
}
