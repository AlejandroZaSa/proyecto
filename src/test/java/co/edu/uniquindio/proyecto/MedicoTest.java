package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.admin.RegistroTratamientoDTO;
import co.edu.uniquindio.proyecto.dto.medico.*;
import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;
import co.edu.uniquindio.proyecto.servicios.interfaces.MedicoServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MedicoTest {

    @Autowired
    private MedicoServicio medicoServicio;

    @Test
    public void listarCitasPendientes(){

        List<ItemCitaMedicoDTO> citasMedico;

        try {
            citasMedico = medicoServicio.listarCitasPendientes(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (citasMedico != null){
            for(ItemCitaMedicoDTO itemCitaMedicoDTO : citasMedico){
                System.out.println(itemCitaMedicoDTO.toString());
            }
        }

    }

    @Test
    public void atenderCita(){

        List<RegistroTratamientoDTO> registroTratamientoDTOList = new ArrayList<>();
        registroTratamientoDTOList.add(new RegistroTratamientoDTO(3,1, "Tomar 1 cada 8 horas", Medicamento.ASPIRINA));

        int codigoCita;

        AtencionMedicoDTO atencionMedicoDTO = new AtencionMedicoDTO(1,"Mareos", "Presión alta", "El paciente debe tomar 20 minutos de descanso cada 3 horas", registroTratamientoDTOList);

        try {
            codigoCita = medicoServicio.atenderCita(atencionMedicoDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(codigoCita);
    }

    @Test
    public void listarCitaPaciente(){

        List<ItemConsultaMedicoPacienteDTO> listaPacientes;

        try {
            listaPacientes = medicoServicio.listarCitaPaciente(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (listaPacientes != null){
            for(ItemConsultaMedicoPacienteDTO itemConsultaMedicoPacienteDTO: listaPacientes){
                System.out.println(itemConsultaMedicoPacienteDTO.toString());
            }
        }
    }

    @Test
    public void agendarDiaLibre(){
        DiaLibreDTO diaLibreDTO = new DiaLibreDTO(1, LocalDate.of(2023,10,5));

        int codigoDiaLibre;

        try {
            codigoDiaLibre = medicoServicio.agendarDiaLibre(diaLibreDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(codigoDiaLibre);
    }


    @Test
    public void listarCitasRealizadas(){
        List<ItemConsultaMedicoPacienteDTO> listarCitasRealizadas;

        try {
            listarCitasRealizadas = medicoServicio.listarCitasRealizadasMedico(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (listarCitasRealizadas != null){
            for (ItemConsultaMedicoPacienteDTO itemConsultaMedicoPacienteDTO: listarCitasRealizadas){
                System.out.println(itemConsultaMedicoPacienteDTO.toString());
            }
        }
    }

    @Test
    public void generarFactura(){
        int codigoFactura;

        try {
            codigoFactura = medicoServicio.generarFactura(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(codigoFactura);
    }

    @Test
    public void mostrarDetalleFactura(){

        DetalleFacturaDTO detalleFacturaDTO ;


        try {
            detalleFacturaDTO = medicoServicio.mostrarDetalleFactura(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        System.out.println(detalleFacturaDTO.toString());

    }

}
