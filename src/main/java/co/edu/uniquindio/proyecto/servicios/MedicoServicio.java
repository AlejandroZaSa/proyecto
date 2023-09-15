package co.edu.uniquindio.proyecto.servicios;

public interface MedicoServicio {

    void listarCitasPendientes();

    void atenderCita();

    void listarCitaPaciente();//historial medico

    void agendarDiaLibre();

    void listarCitasRealizadasMedico();
}
