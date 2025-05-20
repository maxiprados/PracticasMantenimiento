
package com.uma.example.springuma.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.example.springuma.model.Informe;
import com.uma.example.springuma.model.Imagen;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.Paciente;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.Duration;
import java.util.Calendar;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InformeControllerWebTestClientIT {

    @LocalServerPort
    private int port;

    private WebTestClient client;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        client = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .responseTimeout(Duration.ofSeconds(10))
                .build();
    }

    private Medico createMedico(Long id, String dni, String nombre, String especialidad) {
        Medico res = new Medico();
        res.setId(id);
        res.setDni(dni);
        res.setEspecialidad(especialidad);
        res.setNombre(nombre);
        return res;
    }

    private Imagen createImagen(Long id, Calendar fecha, Paciente paciente, byte[] file_content) {
        Imagen res = new Imagen();
        res.setId(id);
        res.setFecha(fecha);
        res.setNombre("Imagen " + paciente);
        res.setPaciente(paciente);
        res.setFile_content(file_content);
        return res;
    }

    private Paciente createPaciente(Long id, String name, String dni, String cita, Medico medico, int edad) {
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
    @DisplayName("Creación de informe enlazado a una imagen real")
    public void crearInformeDebeRetornar201() throws Exception {
        Medico medico = createMedico(1L, "14253678S", "Pepe Viyuela", "Cirujano");

        client.post().uri("/medico")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(medico)
                .exchange()
                .expectStatus().isCreated();

        Paciente paciente = new Paciente("Carmen Machi", 50, "10/02/26", "78965412L", medico);

        client.post().uri("/paciente")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(paciente)
                .exchange()
                .expectStatus().isCreated();

        // Leer la imagen real desde resources
        Path path = Paths.get("src/test/resources/healthy.png");
        byte[] realImageBytes = Files.readAllBytes(path);

        Long id = 1L;
        Calendar fecha = Calendar.getInstance();
        Imagen imagen = createImagen(id, fecha, paciente, realImageBytes);

        // Multipart con imagen real
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new ByteArrayResource(realImageBytes))
                .filename("healthy.png")
                .contentType(MediaType.IMAGE_PNG);
        builder.part("paciente", "{\"id\": " + id + "}")
                .contentType(MediaType.APPLICATION_JSON);

        client.post().uri("/imagen")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()
                .expectStatus().is2xxSuccessful();

        // Crear el informe enlazado
        Informe informe = new Informe();
        informe.setPrediccion("no cancer");
        informe.setContenido("La imagen no muestra signos sospechosos");
        informe.setImagen(imagen);

        client.post().uri("/informe")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(informe)
                .exchange()
                .expectStatus().isCreated();
    }


    @Test
    @DisplayName("Borrar informe")
    public void deleteInforme() throws Exception {
        Medico medico = createMedico(2L, "12312312M", "Kevin Medina", "Cirujano");

        client.post().uri("/medico")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(medico)
                .exchange()
                .expectStatus().isCreated();

        Paciente paciente = new Paciente("David Larrubia", 20, "10/02/26", "12121212V", medico);

        client.post().uri("/paciente")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(paciente)
                .exchange()
                .expectStatus().isCreated();

        // Leer la imagen real desde resources
        Path path = Paths.get("src/test/resources/healthy.png");
        byte[] realImageBytes = Files.readAllBytes(path);

        Long id = 1L;
        Calendar fecha = Calendar.getInstance();
        Imagen imagen = createImagen(id, fecha, paciente, realImageBytes);

        // Multipart con imagen real
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new ByteArrayResource(realImageBytes))
                .filename("healthy.png")
                .contentType(MediaType.IMAGE_PNG);
        builder.part("paciente", "{\"id\": " + id + "}")
                .contentType(MediaType.APPLICATION_JSON);

        client.post().uri("/imagen")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()
                .expectStatus().is2xxSuccessful();


        Informe informe = new Informe();
        informe.setPrediccion("no cancer");
        informe.setContenido("La imagen no muestra signos sospechosos");
        informe.setImagen(imagen);

        client.post().uri("/informe")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(informe)
                .exchange()
                .expectStatus().isCreated();

        client.delete().uri("/informe/0")
                .exchange()
                .expectStatus().isNoContent();

        client.get().uri("/informe/0")
                .exchange()
                .expectStatus().is2xxSuccessful();
    }
}