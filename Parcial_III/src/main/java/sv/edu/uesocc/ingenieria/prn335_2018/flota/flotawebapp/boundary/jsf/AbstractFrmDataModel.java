/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.boundary.jsf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.event.ActionEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.AbstractFacade;

/**
 *
 * @author christian
 */
public abstract class AbstractFrmDataModel<T> {

    public T registro;

    public abstract Object clavePorDatos(T object);

    public abstract T datosPorClave(String rowKey);

    public abstract AbstractFacade<T> getFacade();
    List<T> List = new ArrayList<>();
    public LazyDataModel<T> modelo;

    public void llenarLista(List<T> Lista) {
        if (getFacade().findAll() != null) {
            Lista = getFacade().findAll();
        } else {
            Lista = Collections.EMPTY_LIST;
        }
    }

    public void crearNuevo() {
        if (getFacade() != null && registro != null) {
            try {
                getFacade().create(registro);
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    public void btnEliminarHandler(ActionEvent ae) {
        if (getFacade() != null && registro != null) {
            try {
                getFacade().remove(registro);
            } catch (Exception e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    public void btnCancelarHandler(ActionEvent ae) {

    }

    public LazyDataModel<T> Fodelo() {
        try {
            modelo = new LazyDataModel<T>() {
                @Override
                public Object getRowKey(T object) {
                    return clavePorDatos(object);
                }

                @Override
                public T getRowData(String rowKey) {
                    return datosPorClave(rowKey);
                }

                @Override
                public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    List<T> salida = new ArrayList<>();
                    try {
                        if (getFacade() != null) {
                            this.setRowCount(getFacade().count());
                            salida = getFacade().findRange(first, pageSize);
                        }
                    } catch (Exception e) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
                    }
                    return salida;
                }
            };
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        return null;
    }


//    public List<T> listar(int first, int pageSize, String sortField, boolean asc) {
//        try {
//
//            if (getFacadeLocal() != null) {
//                List = getFacadeLocal().findRange(first, pageSize);
//            }
//        } catch (Exception e) {
//            System.out.println("Excepcion" + e.getMessage());
//        }
//
//        if (sortField != null) {
//            if (asc == true) {
//                Lista.addAll(List);
//            } else {
//
//            }
//        }
//        return Lista;
//
//    }
//
    public void inicializar() {
//        this.modelo();
        this.llenarLista(List);
    }

    public T getRegistro() {
        return registro;
    }

    public void setRegistro(T registro) {
        this.registro = registro;
    }

    public LazyDataModel<T> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<T> modelo) {
        this.modelo = modelo;
    }

   
    
}
