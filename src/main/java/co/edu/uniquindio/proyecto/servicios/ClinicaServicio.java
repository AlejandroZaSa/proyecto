package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.EpsDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;

import java.util.List;

public interface ClinicaServicio {

    List<EstadoPqrs> cargarListaEstadosPqrs() throws Exception;

    List<TipoSangre> cargarTiposSangre() throws Exception;

    List<EpsDTO> cargarEps() throws Exception;

    List<Medicamento> cargarMedicamentos() throws Exception;

    List<Ciudad> cargarCiudades() throws Exception;

    List<MensajeDTO> mostrarHistorialMensajesPqrs(int codigoPqrs) throws Exception;

}
