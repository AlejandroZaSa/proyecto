package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.clinica.EpsDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemTratamientoDTO;
import co.edu.uniquindio.proyecto.dto.clinica.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClinicaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicaServicioImpl implements ClinicaServicio {
    @Override
    public List<EstadoPqrs> cargarListaEstadosPqrs() {
        return null;
    }

    @Override
    public List<TipoSangre> cargarTiposSangre() {
        return null;
    }

    @Override
    public List<EpsDTO> cargarEps() {
        return null;
    }

    @Override
    public List<Medicamento> cargarMedicamentos() {
        return null;
    }

    @Override
    public List<Ciudad> cargarCiudades() {
        return null;
    }

    @Override
    public List<MensajeDTO> mostrarHistorialMensajesPqrs(int codigoPqrs) throws Exception {
        return null;
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

    }

    @Override
    public void cambiarPassword(int codigoUsuario, String nuevaPassword) throws Exception {

    }

    @Override
    public List<ItemTratamientoDTO> verTratamiento(int codigoConsulta) {
        return null;
    }
}
