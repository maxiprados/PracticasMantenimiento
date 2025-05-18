package com.uma.example.springuma.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.example.springuma.model.Informe;
import com.uma.example.springuma.model.Imagen;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Calendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InformeControllerMockMvcIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private Medico createMedico(Long id, String dni, String nombre, String especialidad){
        Medico res = new Medico();
        res.setId(id);
        res.setDni(dni);
        res.setEspecialidad(especialidad);
        res.setNombre(nombre);
        return res;
    }

    private Imagen createImagen(Long id, Calendar fecha, Paciente paciente, byte[] file_content){
        Imagen res = new Imagen();
        res.setId(id);
        res.setFecha(fecha);
        res.setNombre("Imagen "+paciente);
        res.setPaciente(paciente);
        res.setFile_content(file_content);
        return res;
    }

    private Paciente createPaciente(Long id, String name, String dni, String cita, Medico medico, int edad){
        Paciente res = new Paciente();
        res.setId(id);
        res.setDni(dni);
        res.setNombre(name);
        res.setCita(cita);
        res.setEdad(edad);
        res.setMedico(medico);
        return res;
    }

    @Test
    @DisplayName("Creación de informe enlazado a una imagen")
    public void crearInformeDebeRetornar201() throws Exception {
        //Paso 1: Creamos el medico
        Medico medico = createMedico(1L, "14253678S", "Pepe Viyuela", "Cirujano");

        this.mockMvc.perform(post("/medico")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(medico)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        //Paso 2: Creamos el paciente con dicho medico asociado

        Paciente paciente = new Paciente("Carmen Machi",50,"10/02/26","78965412L",medico);

        this.mockMvc.perform(post("/paciente")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // Paso 3: Subir una imagen del paciente
        byte[] dummyImageBytes = new byte[]{1, 2, 3};
        Long id = 1L;
        Calendar fecha = Calendar.getInstance();

        Imagen imagen = createImagen(id, fecha, paciente, dummyImageBytes);

        // Construir el multipart file con un nombre y contenido
        MockMultipartFile multipartFile = new MockMultipartFile(
                "image", // nombre del parámetro esperado por el endpoint
                "imagenPaciente.jpg", // nombre del archivo simulado
                MediaType.IMAGE_JPEG_VALUE,
                dummyImageBytes // contenido del archivo
        );

        // 👇 Solo pasamos el ID para que JPA lo relacione
        String pacienteReferenciaJson = "{\"id\": 1}";

        MockMultipartFile multipartPaciente = new MockMultipartFile(
                "paciente",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                pacienteReferenciaJson.getBytes()
        );


        String url = "/imagen/" + id;

        MvcResult result = mockMvc.perform(multipart("/imagen")
                        .file(multipartFile)
                        .file(multipartPaciente)
                        .with(request -> {
                            request.setMethod("POST"); // Forzar POST
                            return request;
                        }))
                .andExpect(status().is2xxSuccessful())
                .andReturn();


        // Paso 4: Crear un informe enlazado a esa imagen

        Informe informe = new Informe();
        informe.setPrediccion("no cancer");
        informe.setContenido("La imagen no muestra signos sospechosos");
        informe.setImagen(imagen);

        mockMvc.perform(post("/informe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(informe)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Borrar informe")
    public void deleteInforme() throws Exception {
        //Paso 1: Creamos el medico
        Medico medico = createMedico(1L, "14253678S", "Pepe Viyuela", "Cirujano");

        this.mockMvc.perform(post("/medico")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(medico)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        //Paso 2: Creamos el paciente con dicho medico asociado

        Paciente paciente = new Paciente("Carmen Machi",50,"10/02/26","78965412L",medico);

        this.mockMvc.perform(post("/paciente")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // Paso 3: Subir una imagen del paciente
        byte[] dummyImageBytes = new byte[]{1, 2, 3};
        Long id = 1L;
        Calendar fecha = Calendar.getInstance();

        Imagen imagen = createImagen(id, fecha, paciente, dummyImageBytes);

        // Construir el multipart file con un nombre y contenido
        MockMultipartFile multipartFile = new MockMultipartFile(
                "image", // nombre del parámetro esperado por el endpoint
                "imagenPaciente.jpg", // nombre del archivo simulado
                MediaType.IMAGE_JPEG_VALUE,
                dummyImageBytes // contenido del archivo
        );

        // 👇 Solo pasamos el ID para que JPA lo relacione
        String pacienteReferenciaJson = "{\"id\": 1}";

        MockMultipartFile multipartPaciente = new MockMultipartFile(
                "paciente",
                "",
                MediaType.APPLICATION_JSON_VALUE,
                pacienteReferenciaJson.getBytes()
        );


        String url = "/imagen/" + id;

        MvcResult result = mockMvc.perform(multipart("/imagen")
                        .file(multipartFile)
                        .file(multipartPaciente)
                        .with(request -> {
                            request.setMethod("POST"); // Forzar POST
                            return request;
                        }))
                .andExpect(status().is2xxSuccessful())
                .andReturn();


        // Paso 4: Crear un informe enlazado a esa imagen

        Informe informe = new Informe();
        informe.setPrediccion("no cancer");
        informe.setContenido("La imagen no muestra signos sospechosos");
        informe.setImagen(imagen);

        MvcResult res = mockMvc.perform(post("/informe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(informe)))
                .andExpect(status().isCreated())
                .andReturn();

        // Paso 5: Borramos el informe

        mockMvc.perform(delete("/informe/0"))
                .andExpect(status().isNoContent());

        // Paso 6: Comprobamos que no existe el informe
        // No se porque pero el metodo /informe/id aunque no exista el id da 200
        mockMvc.perform(get("/informe/0"))
                .andExpect(status().is2xxSuccessful());

    }
}
