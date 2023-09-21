package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.clinica.EpsDTO;
import co.edu.uniquindio.proyecto.dto.clinica.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.clinica.TratamientoDTO;
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

    void enviarLinkRecuperacion(String email) throws Exception;

    void cambiarPassword(int codigoUsuario, String nuevaPassword) throws Exception;

    List<TratamientoDTO> verTratamiento(int codigoConsulta);

}
