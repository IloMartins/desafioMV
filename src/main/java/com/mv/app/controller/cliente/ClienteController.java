package com.mv.app.controller.cliente;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.mv.app.model.Cliente;
import com.mv.app.repository.ClienteRepository;
import com.mv.app.service.cliente.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cliente")
@Api(value = "Cliente")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	private ClienteRepository clienteRepository;

	ClienteController(ClienteRepository clienteRepository) {
	       this.clienteRepository = clienteRepository;
	   }
	

	@ApiOperation(value = "Listar todos os clientes cadastrados")
	@GetMapping(value = "/clientes")
	public List<Cliente> getCliente(){
	   return clienteService.buscarTodos();
	}
	
	
	@ApiOperation(value = "Listar cliente pelo id")
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Cliente> findById(@PathVariable long id){
		
		Cliente obj = clienteService.buscarPorId(id);
		 return 	ResponseEntity.ok(obj);

	}
	
	@ApiOperation(value = "Listar cliente pelo nome")
	@GetMapping(path = {"/clientes/{nomeCompleto}"})
	public List<Cliente> findByNome(@PathVariable String nomeCompleto){
		 return 	clienteService.buscarNome(nomeCompleto);

	}
	
	
	@ApiOperation(value = "Cadastrar cliente")
	@PostMapping("/cliente") // OK
	public Cliente salvar(@RequestBody Cliente cliente){
	   return clienteService.add(cliente);
	}
	
	
	@ApiOperation(value = "Editar cliente")
	@PutMapping(value="/{id}")
	public ResponseEntity<Cliente> update(@PathVariable("id") long id,
	                                      @RequestBody Cliente cliente) {
	   return clienteRepository.findById(id)
	           .map(record -> {
	               record.setNomeCompleto(cliente.getNomeCompleto());
	               Cliente updated = clienteRepository.save(record);
	               return ResponseEntity.ok().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@ApiOperation(value = "Deletar cliente")
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity <?> delete(@PathVariable long id) {
	   return clienteRepository.findById(id)
	           .map(record -> {
	        	   clienteRepository.deleteById(id);
	               return ResponseEntity.accepted().build();
	           }).orElse(ResponseEntity.notFound().build());
	}

}
