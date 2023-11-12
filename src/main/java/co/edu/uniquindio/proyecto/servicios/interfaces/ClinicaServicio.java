package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.clinica.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemTratamientoDTO;
import co.edu.uniquindio.proyecto.dto.clinica.EpsDTO;
import co.edu.uniquindio.proyecto.dto.clinica.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.enums.*;

import java.util.List;

public interface ClinicaServicio {

    List<EstadoPqrs> cargarListaEstadosPqrs();

    List<Especialidad> cargarEspecialidades();

    List<TipoSangre> cargarTiposSangre();

    List<EpsDTO> cargarEps();

    List<Medicamento> cargarMedicamentos();

    List<Ciudad> cargarCiudades();

    List<MensajeDTO> mostrarHistorialMensajesPqrs(int codigoPqrs) throws Exception;

    void enviarLinkRecuperacion(String email) throws Exception;

    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception;

    List<ItemTratamientoDTO> verTratamiento(int codigoConsulta) throws Exception;

}
