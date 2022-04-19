package com.mv.app.cliente;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.mv.app.model.Cliente;
import com.mv.app.model.Sexo;
import com.mv.app.service.cidade.CidadeService;
import com.mv.app.service.cliente.ClienteService;
import com.mv.app.service.sexo.SexoService;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteValidTest {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private SexoService sexoService;
    
    @Autowired
    private CidadeService cidadeService;
    
    @Test
    void deveFiltrarClientePorId() throws Exception {
    	
    	mockMvc.perform( MockMvcRequestBuilders
    		      .get("/cliente/{id}", 5L)
    		      .accept(MediaType.APPLICATION_JSON))
    		      .andDo(print())
    		      .andExpect(status().isOk())
    		      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(5L));
    }
    
    @Test
    void deveListarClientes() throws Exception {
    	mockMvc.perform( MockMvcRequestBuilders
  		      .get("/cliente/clientes")
  		      .accept(MediaType.APPLICATION_JSON))
  		      .andDo(print())
  		      .andExpect(status().isOk());
  		     
    }
    
    @Test
    void deveAdicionarCliente() throws Exception {
    	
    	Sexo sexo = sexoService.buscarPorId(1L);
    	Cidade cidade = cidadeService.buscarPorId(1L);
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    	LocalDate dt1 = LocalDate.parse("15/03/1990", formatter);

    	mockMvc.perform( MockMvcRequestBuilders
    		      .post("/cliente/cliente")
    		      .content(asJsonString(new Cliente(null,"JOSEFA",sexo,dt1,32,cidade)))
    		      .contentType(MediaType.APPLICATION_JSON)
    		      .accept(MediaType.APPLICATION_JSON))
    		      .andExpect(status().isOk())
    		      .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());	

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
      }
    
    @Test
    public void deveAtualizarNomeDoCliente() throws Exception 
    {
    	Sexo sexo = sexoService.buscarPorId(1L);
    	Cidade cidade = cidadeService.buscarPorId(1L);
    	
    	mockMvc.perform( MockMvcRequestBuilders
          .put("/cliente/{id}", 3L)
          .content(asJsonString(new Cliente(3L,"ILO MARTINS",sexo,LocalDate.of(1990, 06, 22),32,cidade)))
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.nomeCompleto").value("ILO MARTINS"));
         
    }
    
    @Test
    public void deveDeletarCliente() throws Exception 
    {
    	mockMvc.perform( MockMvcRequestBuilders.delete("/cliente/{id}", 7L) )
            .andExpect(status().isNotFound());
    }

}
