/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.boundary.jsf.backing;

import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.Modelo;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.ModeloParte;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.boundary.jsf.AbstractFrmDataModel;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.AbstractFacade;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.ModeloFacade;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.ModeloParteFacade;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.ParteFacade;

/**
 *
 * @author christian
 */
class FrmModeloParte extends AbstractFrmDataModel<ModeloParte>{
    public FrmModeloParte(ModeloParteFacade modeloParteFacade, ModeloFacade modeloFacade, ParteFacade parteFacade, Modelo registro ) {
    }

    @Override
    public Object clavePorDatos(ModeloParte object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ModeloParte datosPorClave(String rowKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractFacade<ModeloParte> getFacade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
