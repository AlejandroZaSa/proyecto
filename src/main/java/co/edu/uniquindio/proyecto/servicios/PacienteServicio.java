package co.edu.uniquindio.proyecto.servicios;

public interface PacienteServicio {

    void registrarse();

    void editarPerfil();

    void eliminarCuenta();

    void enviarLinkRecuperacion();

    void cambiarPassword();

    void agendarCita();

    void crearPqrs();

    void listarPqrsPaciente();

    void responderPqrs();

    void listarCitasPaciente();

    void filtrarCitasPorFecha();

    void filtrarCitasPorMedico();

    void verDetalleCita();
}
