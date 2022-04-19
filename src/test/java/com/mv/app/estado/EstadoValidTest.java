package com.mv.app.estado;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mv.app.model.Estado;
import com.mv.app.service.estado.EstadoService;

@SpringBootTest
@AutoConfigureMockMvc
public class EstadoValidTest {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private EstadoService estadoService;
    
    @Test
    void deveFiltrarEstadoPorId() throws Exception {
    	
    	mockMvc.perform( MockMvcRequestBuilders
    		      .get("/estado/{id}", 1L)
    		      .accept(MediaType.APPLICATION_JSON))
    		      .andDo(print())
    		      .andExpect(status().isOk())
    		      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }
    
    @Test
    void deveListarEstados() throws Exception {
    	mockMvc.perform( MockMvcRequestBuilders
  		      .get("/estado/estados")
  		      .accept(MediaType.APPLICATION_JSON))
  		      .andDo(print())
  		      .andExpect(status().isOk());
  		     
    }
    
    @Test
    void deveAdicionarEstado() throws Exception {
    	
    	Estado estado = new Estado();
    		estado.setId(4L);
    		estado.setNome("AMAZONAS");
    		    	
    	mockMvc.perform(post("/estado/estado")
    	        .contentType("application/json")
    	        .content(objectMapper.writeValueAsString(estadoService.add(estado))))
    	        .andExpect(status().isOk());
	
    	assertThat(estado.getId() == 4L);
    	assertThat(estado.getNome() == "AMAZONAS");
    }

}
