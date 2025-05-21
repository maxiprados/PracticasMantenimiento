//Pedro Scarpati Sundblad y Máximo Prados Meléndez - Grupo C
package com.uma.example.springuma.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.example.springuma.model.Imagen;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.Paciente;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.File;
import java.time.Duration;
import java.util.Calendar;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImagenControllerWebTestClient {

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    private WebTestClient client;

    @PostConstruct
    public void setupClient() {
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
    @DisplayName("Subida de imagen con WebTestClient funciona correctamente")
    public void subirFotoRetorna201_conWebTestClient() throws Exception {
        // Paso 1: Crear médico
        Medico medico = createMedico(1L, "14253678S", "Pepe Viyuela", "Cirujano");
        client.post().uri("/medico")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(medico)
                .exchange()
                .expectStatus().isCreated();

        // Paso 2: Crear paciente
        Paciente paciente = createPaciente(1L, "Carmen Machi", "78965412L", "10/02/26", medico, 50);
        client.post().uri("/paciente")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(paciente)
                .exchange()
                .expectStatus().isCreated();

        // Paso 3: Subir imagen
        File imagen = new File("./src/test/resources/healthy.png");
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(imagen));
        builder.part("paciente", new ByteArrayResource("{\"id\":1}".getBytes()) {
            @Override
            public String getFilename() {
                return "paciente.json";
            }
        }).header("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        client.post()
                .uri("/imagen")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()
                .expectStatus().is2xxSuccessful();
    }
}

