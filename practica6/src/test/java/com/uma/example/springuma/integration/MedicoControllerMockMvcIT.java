package com.uma.example.springuma.integration;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.uma.example.springuma.integration.base.AbstractIntegration;
import com.uma.example.springuma.model.Medico;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class MedicoControllerMockMvcIT extends AbstractIntegration {

    @Autowired
	private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private Medico createMedico(Long id,String dni,String nombre,String especialidad){
        Medico res = new Medico();
        res.setId(id);
        res.setDni(dni);
        res.setEspecialidad(especialidad);
        res.setNombre(nombre);
        return res;
    }




    @Test
    @DisplayName("Creacion de un medico en la base de datos")
	void createMedicoPost_isObtainedWithGet() throws Exception {
        Medico medico = this.createMedico(1L, "14253678S", "Pepe Viyuela", "Cirujano");
        
        // Introduce un medico en la base de datos
        this.mockMvc.perform(post("/medico")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().isCreated())
        .andExpect(status().is2xxSuccessful());

        // Obtenemos el medico correctamente

        MvcResult mvcResult = this.mockMvc.perform(get("/medico/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.id").value("1"))
        .andExpect(jsonPath("$.dni").value("14253678S"))
        .andExpect(jsonPath("$.especialidad").value("Cirujano"))
        .andExpect(jsonPath("$.nombre").value("Pepe Viyuela"))
        .andReturn();        


    }

    @Test
    @DisplayName("Edicion de medico correcta")
    void updateMedico_cambiarDatos() throws Exception{

        Medico medico = this.createMedico(1L, "14253678S", "Pepe Viyuela", "Cirujano");

        // Introduce un medico en la base de datos
        this.mockMvc.perform(post("/medico")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().isCreated())
        .andExpect(status().is2xxSuccessful());
        
        
        
        //Editamos los datos del medico
        medico.setNombre("Paco Leon");

        this.mockMvc.perform(put("/medico")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().is2xxSuccessful());

        //Comprobamos que obtenemos al medico con los datos actualizados

        MvcResult mvcResult = this.mockMvc.perform(get("/medico/1"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.id").value("1"))
        .andExpect(jsonPath("$.dni").value("14253678S"))
        .andExpect(jsonPath("$.especialidad").value("Cirujano"))
        .andExpect(jsonPath("$.nombre").value("Paco Leon"))
        .andReturn();  

    }


    @Test
    @DisplayName("Eliminacion correcta de un medico")
    void deleteMedico_ReturnsVacio() throws Exception{

        Medico medico = this.createMedico(1L, "14253678S", "Pepe Viyuela", "Cirujano");

        //Lo insertamos en la bd
        this.mockMvc.perform(post("/medico")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().isCreated())
        .andExpect(status().is2xxSuccessful());

        //Eliminamos al medico y intentamos obtenerlo

        this.mockMvc.perform(delete("/medico/1"))
        .andExpect(status().is2xxSuccessful());


        MvcResult mvcResult = this.mockMvc.perform(get("/medico/1"))
        .andExpect(status().is5xxServerError())
        .andExpect(content().contentType("application/json"))
        .andReturn(); 

    }
 


    @Test
    @DisplayName("Buscar Medico por su DNI")
    void getMedicoByDni_ReturnsMedicoInsertado() throws Exception{

        Medico medico = this.createMedico(1L, "14253678S", "Pepe Viyuela", "Cirujano");

        //Lo insertamos en la bd
        this.mockMvc.perform(post("/medico")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().isCreated())
        .andExpect(status().is2xxSuccessful());

        //Lo intentamos extraer por su dni
        this.mockMvc.perform(get("/medico/dni/14253678S"))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.id").value("1"))
        .andExpect(jsonPath("$.dni").value("14253678S"))
        .andExpect(jsonPath("$.especialidad").value("Cirujano"))
        .andExpect(jsonPath("$.nombre").value("Pepe Viyuela"));
        



    }




} 