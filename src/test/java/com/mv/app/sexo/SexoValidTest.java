package com.mv.app.sexo;

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
import com.mv.app.model.Sexo;
import com.mv.app.service.sexo.SexoService;

@SpringBootTest
@AutoConfigureMockMvc
public class SexoValidTest {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SexoService sexoService;
   
    
    @Test
    void deveFiltrarSexoPorId() throws Exception {
    	
    	mockMvc.perform( MockMvcRequestBuilders
    		      .get("/sexo/{id}", 1L)
    		      .accept(MediaType.APPLICATION_JSON))
    		      .andDo(print())
    		      .andExpect(status().isOk())
    		      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }
    
    @Test
    void deveListarSexos() throws Exception {
    	mockMvc.perform( MockMvcRequestBuilders
  		      .get("/sexo/sexos")
  		      .accept(MediaType.APPLICATION_JSON))
  		      .andDo(print())
  		      .andExpect(status().isOk());
  		     
    }
    
    @Test
    void deveAdicionarSexo() throws Exception {
    	
    	Sexo sexo = new Sexo();
    		sexo.setId(4L);
    		sexo.setDescricao("TESTE");
    	
    	mockMvc.perform(post("/sexo/sexo")
    	        .contentType("application/json")
    	        .content(objectMapper.writeValueAsString(sexoService.add(sexo))))
    	        .andExpect(status().isOk());
	
    	assertThat(sexo.getId() == 4L);
    	assertThat(sexo.getDescricao() == "TESTE");
    }


}
