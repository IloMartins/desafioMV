package com.mv.app.cidade;

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
import com.mv.app.model.Cidade;
import com.mv.app.service.cidade.CidadeService;

@SpringBootTest
@AutoConfigureMockMvc
public class CidadeValidTest {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private CidadeService cidadeService;
    
    @Test
    void deveFiltrarCidadePorId() throws Exception {
    	
    	mockMvc.perform( MockMvcRequestBuilders
    		      .get("/cidade/{id}", 1L)
    		      .accept(MediaType.APPLICATION_JSON))
    		      .andDo(print())
    		      .andExpect(status().isOk())
    		      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }
    
    @Test
    void deveListarCidades() throws Exception {
    	mockMvc.perform( MockMvcRequestBuilders
  		      .get("/cidade/cidades")
  		      .accept(MediaType.APPLICATION_JSON))
  		      .andDo(print())
  		      .andExpect(status().isOk());
  		     
    }
    
    @Test
    void deveAdicionarCidade() throws Exception {
    	
    	Cidade cidade = new Cidade();
    		cidade.setId(4L);
    		cidade.setNome("MANAUS");
    	
    	mockMvc.perform(post("/cidade/cidade")
    	        .contentType("application/json")
    	        .content(objectMapper.writeValueAsString(cidadeService.add(cidade))))
    	        .andExpect(status().isOk());
	
    	assertThat(cidade.getId() == 4L);
    	assertThat(cidade.getNome() == "MANAUS");
    }

}
