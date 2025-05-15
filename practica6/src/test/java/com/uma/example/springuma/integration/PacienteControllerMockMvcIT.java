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
import com.uma.example.springuma.model.Paciente;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class PacienteControllerMockMvcIT extends AbstractIntegration{
    
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

    private Paciente createPaciente(Long id,String name,String dni,String cita,Medico medico,int edad){
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
    @DisplayName("Comprobar que se crea un paciente con un medico asociado")
    void savePaciente_ObtengoCorrecto() throws Exception{
        //Primero creo el medico en la bd
        Medico medico = createMedico(1L, "14253678S", "Pepe Viyuela", "Cirujano");

        this.mockMvc.perform(post("/medico")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().isCreated())
        .andExpect(status().is2xxSuccessful());

        //Creamos el paciente con dicho medico asociado

        Paciente paciente = new Paciente("Carmen Machi",50,"10/02/26","78965412L",medico);

        this.mockMvc.perform(post("/paciente")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(paciente)))
        .andExpect(status().isCreated())
        .andExpect(status().is2xxSuccessful());

        //Comprobamos que el paciente asociado al medico sea el creado

        this.mockMvc.perform(get("/paciente/medico/1")
        .contentType("application/json"))
        .andExpect(jsonPath("$",hasSize(1)))
        .andExpect(jsonPath("$[0]").value(paciente));

        


    }


// Asociar y editar pacientes a médicos. Así como el cambio de médico y detección del cambio.
    @Test
    @DisplayName("Asociar médico a paciente y comprobar que se ha asociado correctamente")
	void asociar_pacienteConMedico() throws Exception {
        Medico medico = new Medico();
        medico.setDni("34583485R");
        medico.setNombre("Ramon");
        medico.setEspecialidad("familia");
        medico.setId(1);
        Paciente paciente = new Paciente();
        paciente.setNombre("Pablo");
        paciente.setEdad(20);
        paciente.setCita("17/05/2025");
        paciente.setDni("20843953F");
        paciente.setMedico(medico);
        paciente.setId(1);

        // crea el médico
        this.mockMvc.perform(post("/medico")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico)))
        .andExpect(status().isCreated())
        .andExpect(status().is2xxSuccessful());

        // crea el paciente
        this.mockMvc.perform(post("/paciente")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(paciente)))
        .andExpect(status().isCreated())
        .andExpect(status().is2xxSuccessful());
        
        // obtiene el paciente y comprueba que se ha creado correctamente con el médico asociado
		this.mockMvc.perform(get("/paciente/1"))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$").value(paciente)) // comprueba que el tipo resultante es igual al paciente creado
        .andExpect(jsonPath("$.medico.dni").value(medico.getDni()))
        .andExpect(jsonPath("$.medico.nombre").value(medico.getNombre()))
        .andExpect(jsonPath("$.medico.especialidad").value(medico.getEspecialidad()));
    }
    
    






    @Test
    @DisplayName("Editamos el medico del paciente")
    void updatePaciente_CambioMedicoAsignado() throws Exception {
        
        Medico medico1 = createMedico(1L, "14253678S", "Pepe Viyuela", "Cirujano");

        //Insertamos ambos medicos en la bd
        this.mockMvc.perform(post("/medico")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico1)))
        .andExpect(status().isCreated())
        .andExpect(status().is2xxSuccessful());

 

        //Insertamos el paciente asignandole como medico Pepe Viyuela
        Paciente paciente = createPaciente(1L,"Carmen Machi","78965412L","10/02/26",medico1,50);

        this.mockMvc.perform(post("/paciente")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(paciente)))
        .andExpect(status().isCreated())
        .andExpect(status().is2xxSuccessful());

        //modificamos el medico del paciente

        
        Medico medico2 = createMedico(2L, "54623578L", "Paz Padilla", "Dermatologa");
        this.mockMvc.perform(post("/medico")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(medico2)))
        .andExpect(status().isCreated())
        .andExpect(status().is2xxSuccessful());

        paciente.setMedico(medico2);

        this.mockMvc.perform(put("/paciente")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(paciente)));

        //Obtenemis el nuevo medico del paciente
        this.mockMvc.perform(get("/paciente/medico/2")
        .contentType("application/json"))
        .andExpect(jsonPath("$",hasSize(1)))
        .andExpect(jsonPath("$[0]").value(paciente));

    }





}
