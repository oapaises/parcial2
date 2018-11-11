/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.boundary.jsf.backing;

import java.io.Serializable;
import java.util.stream.Collectors;
import javax.inject.Inject;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.Modelo;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.ModeloParte;
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
class FrmModeloParte extends AbstractFrmDataModel<ModeloParte> implements Serializable{
    public FrmModeloParte(ModeloParteFacade modeloParteFacade, ModeloFacade modeloFacade, ParteFacade parteFacade, Modelo registro ) {
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

    @Override
    public AbstractFacade<ModeloParte> getFacade() {
        return modeloParteFacade;
    }

    @Override
    public void crearNuevo() {
        this.registro = new ModeloParte();
    }
}
