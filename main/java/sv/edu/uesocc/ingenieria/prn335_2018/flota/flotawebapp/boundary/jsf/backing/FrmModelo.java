package sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.boundary.jsf.backing;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.LazyDataModel;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.Marca;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.Modelo;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.datos.definicion.TipoVehiculo;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.boundary.jsf.AbstractFrmDataModel;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.AbstractFacade;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.MarcaFacade;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.ModeloFacade;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.ModeloParteFacade;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.ParteFacade;
import sv.edu.uesocc.ingenieria.prn335_2018.flota.flotawebapp.control.TipoVehiculoFacade;

/**
 * BackingBean para el formulario de mantenimiento de Modelo y ParteModelo
 * @author jcpenya
 */
@Named
@ViewScoped
public class FrmModelo extends AbstractFrmDataModel<Modelo> implements Serializable {

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
    protected List<TipoVehiculo> listaTipoVehiculo;
    protected List<Marca> listaMarca;
    protected int tabSeleccionado = 0;
    private FrmModeloParte frmModeloParte;

    @PostConstruct
    @Override
    public void inicializar() {
        super.inicializar(); 
        try {
            this.listaMarca = marcaFacade.findAll();
        } catch (Exception ex) {
            this.listaMarca = Collections.EMPTY_LIST;
        }
        try {
            this.listaTipoVehiculo = tipoVehiculoFacade.findAll();
        } catch (Exception ex) {
            this.listaTipoVehiculo = Collections.EMPTY_LIST;
        }
    }

    @Override
    public Object clavePorDatos(Modelo object) {
        if (object != null) {
            return object.getIdModelo();
        }
        return null;
    }

    @Override
    public Modelo datosPorClave(String rowkey) {
        if (rowkey != null && !rowkey.trim().isEmpty()) {
            try {
                return this.getModelo().getWrappedData().stream().filter(r -> r.getIdModelo().toString().compareTo(rowkey) == 0).collect(Collectors.toList()).get(0);
            } catch (Exception ex) {

            }
        }
        return null;
    }

    @Override
    public AbstractFacade getFacade() {
        return modeloFacade;
    }

    @Override
    public void crearNuevo() {
        this.registro = new Modelo();
        this.tabSeleccionado = 0;
    }

    @Override
    public void btnEliminarHandler(ActionEvent ae) {
        super.btnEliminarHandler(ae); 
        this.tabSeleccionado = 0;
    }

    @Override
    public void btnCancelarHandler(ActionEvent ae) {
        super.btnCancelarHandler(ae); 
        this.tabSeleccionado = 0;
    }

    @Override
    public LazyDataModel<Modelo> getModelo() {
        return super.getModelo(); 
    }
/**
 * Maneja el cambio entre las pestañas del Tab con los detalles, 
 * con el fin de instanciar el formulario para Partes
 * @param tce 
 */
    public void tabChangeHandler(TabChangeEvent tce) {
        this.frmModeloParte = null;
        if (tce != null && tce.getTab() != null && tce.getTab().getTitle() != null) {
            if (tce.getTab().getTitle().compareToIgnoreCase("partes") == 0) {
                this.frmModeloParte = new FrmModeloParte(modeloParteFacade, modeloFacade, parteFacade, this.registro);
            }
        }
    }
/**
 * Propiedad sintetica para cambiar de Marca
 * @return 
 */
    public Integer getIdMarcaSeleccionada() {
        if (this.registro != null && this.registro.getIdMarca() != null) {
            return this.registro.getIdMarca().getIdMarca();
        }
        return null;
    }
/**
 * Propiedad sintetica para cambiar de Marca
 * @param idMarca 
 */
    public void setIdMarcaSeleccionada(Integer idMarca) {
        if (this.registro != null && this.listaMarca != null) {
            try {
                this.registro.setIdMarca(this.listaMarca.stream().filter(m -> m.getIdMarca().compareTo(idMarca) == 0).collect(Collectors.toList()).get(0));
            } catch (Exception ex) {

            }
        }
    }
/**
 * Propiedad sintentica para cambiar el tipo de vehiculo
 * @return 
 */
    public Integer getIdTipoVehiculoSeleccionado() {
        if (this.registro != null && this.registro.getIdTipoVehiculo() != null) {
            return this.registro.getIdTipoVehiculo().getIdTipoVehiculo();
        }
        return null;
    }
/**
 * Propiedad sintentica para cambiar el tipo de vehiculo
 * @param idTipoSeleccionado 
 */
    public void setIdTipoVehiculoSeleccionado(Integer idTipoSeleccionado) {
        if (this.registro != null && this.listaTipoVehiculo != null) {
            try {
                this.registro.setIdTipoVehiculo(this.listaTipoVehiculo.stream().filter(r -> r.getIdTipoVehiculo().compareTo(idTipoSeleccionado) == 0).collect(Collectors.toList()).get(0));
            } catch (Exception ex) {

            }
        }
    }

    public List<TipoVehiculo> getListaTipoVehiculo() {
        return listaTipoVehiculo;
    }

    public void setListaTipoVehiculo(List<TipoVehiculo> listaTipoVehiculo) {
        this.listaTipoVehiculo = listaTipoVehiculo;
    }

    public List<Marca> getListaMarca() {
        return listaMarca;
    }

    public void setListaMarca(List<Marca> listaMarca) {
        this.listaMarca = listaMarca;
    }

    public int getTabSeleccionado() {
        return tabSeleccionado;
    }

    public void setTabSeleccionado(int tabSeleccionado) {
        this.tabSeleccionado = tabSeleccionado;
    }

    public FrmModeloParte getFrmModeloParte() {
        return frmModeloParte;
    }
}
