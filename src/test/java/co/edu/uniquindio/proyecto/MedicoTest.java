package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.admin.RegistroTratamientoDTO;
import co.edu.uniquindio.proyecto.dto.medico.*;
import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;
import co.edu.uniquindio.proyecto.servicios.interfaces.MedicoServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class MedicoTest {

    @Autowired
    private MedicoServicio medicoServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasPendientes(){

        List<ItemCitaMedicoDTO> citasMedico;

        try {
            citasMedico = medicoServicio.listarCitasPendientes(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(2, citasMedico.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void atenderCita(){

        List<RegistroTratamientoDTO> registroTratamientoDTOList = new ArrayList<>();
        registroTratamientoDTOList.add(new RegistroTratamientoDTO(3,"Tomar 1 cada 8 horas", Medicamento.ASPIRINA));

        int codigoConsulta;

        AtencionMedicoDTO atencionMedicoDTO = new AtencionMedicoDTO(7,"Mareos", "Presión alta", "El paciente debe tomar 20 minutos de descanso cada 3 horas", registroTratamientoDTOList);

        try {
            codigoConsulta = medicoServicio.atenderCita(atencionMedicoDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotEquals(0, codigoConsulta);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitaPaciente(){

        List<ItemConsultaMedicoPacienteDTO> listaConsultas;

        try {
            listaConsultas = medicoServicio.listarCitaPaciente(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(1, listaConsultas.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void agendarDiaLibre(){
        DiaLibreDTO diaLibreDTO = new DiaLibreDTO(1, LocalDate.of(2023,10,5));

        int codigoDiaLibre;

        try {
            codigoDiaLibre = medicoServicio.agendarDiaLibre(diaLibreDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotEquals(0, codigoDiaLibre);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasRealizadas(){
        List<ItemConsultaMedicoPacienteDTO> listarCitasRealizadas;

        try {
            listarCitasRealizadas = medicoServicio.listarCitasRealizadasMedico(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(2, listarCitasRealizadas.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void generarFactura(){

        int codigoFactura;

        try {
            codigoFactura = medicoServicio.generarFactura(6);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotEquals(0, codigoFactura);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void mostrarDetalleFactura(){

        DetalleFacturaDTO detalleFacturaDTO ;

        try {
            detalleFacturaDTO = medicoServicio.mostrarDetalleFactura(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals("Consulta de rutina", detalleFacturaDTO.concepto());
    }

}
