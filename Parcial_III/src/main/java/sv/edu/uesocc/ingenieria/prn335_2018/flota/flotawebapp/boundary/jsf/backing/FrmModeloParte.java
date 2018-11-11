/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.boundary.jsf.backing;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.Modelo;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.ModeloParte;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.Parte;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.boundary.jsf.AbstractFrmDataModel;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.AbstractFacade;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.MarcaFacade;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.ModeloFacade;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.ModeloParteFacade;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.ParteFacade;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.TipoVehiculoFacade;

/**
 *
 * @author christian
 */
public class FrmModeloParte extends AbstractFrmDataModel<ModeloParte> implements Serializable{

    public FrmModeloParte(ModeloParteFacade modeloParteFacade, ModeloFacade modeloFacade, ParteFacade parteFacade, Modelo registro){
    }

    @Inject
    ModeloFacade modeloFacade;
    @Inject
    MarcaFacade marcaFacade;
    @Inject
    TipoVehiculoFacade tipoVehiculoFacade;
    @Inject
    ParteFacade parteFacade;
    @Inject
    ModeloParteFacade modeloParteFacade;
    List<Parte> listaParte;
    //public ModeloParte modePar = new ModeloParte();

    @PostConstruct
    @Override
    public void inicializar() {
        super.inicializar();
        try {
            this.listaParte = parteFacade.findAll();
        } catch (Exception ex) {
            this.listaParte = Collections.EMPTY_LIST;
        }
    }

    @Override
    public LazyDataModel<ModeloParte> getModelo() {
        return super.getModelo();
    }

    @Override
    public Object clavePorDatos(ModeloParte object) {
        if (object != null) {
            return object.getIdModeloParte();

        }
        return null;

    }

    @Override
    public ModeloParte datosPorClave(String rowkey) {
        if (rowkey != null && !rowkey.trim().isEmpty()) {
            try {
                return this.getModelo().getWrappedData().stream().filter(r -> r.getIdModeloParte().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);
            } catch (Exception ex) {

            }
        }
        return null;

    }
    /**
     * Propiedad sintetica para cambiar de Parte
     *
     * @return
     */
    public Integer getIdParteSeleccionada() {
        if (this.registro != null && this.registro.getIdParte() != null) {
            return this.registro.getIdParte().getIdParte();
        }
        return null;
    }

    /**
     * Propiedad sintetica para cambiar de Parte
     *
     * @param idParte
     */
    public void setIdParteSeleccionada(Integer idParte) {
        if (this.registro != null && this.listaParte != null) {
            try {
                this.registro.setIdParte(this.listaParte.stream().filter(m -> m.getIdParte().compareTo(idParte) == 0).collect(Collectors.toList()).get(0));
            } catch (Exception ex) {

            }
        }
    }

    @Override
    public AbstractFacade<ModeloParte> getFacade() {
        return modeloParteFacade;
    }

    @Override
    public void crearNuevo() {
        this.registro = new ModeloParte();

    }

    public List<Parte> getListaParte() {
        return listaParte;
    }

    public void setListaParte(List<Parte> listaParte) {
        this.listaParte = listaParte;
    }
    
    
}
