package com.mv.app.controller.estado;

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

import com.mv.app.model.Estado;
import com.mv.app.repository.EstadoRepository;
import com.mv.app.service.estado.EstadoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/estado")
@Api(value = "Estado")
public class EstadoController {
	
	@Autowired
	EstadoService estadoService;
	
	private EstadoRepository estadoRepository;
	
	EstadoController(EstadoRepository estadoRepository) {
	       this.estadoRepository = estadoRepository;
	   }
	

	@ApiOperation(value = "Listar todos os estados cadastrados")
	@GetMapping(value = "/estados")
	public List<Estado> getEstado(){
	   return estadoService.buscarTodos();
	}
	
	
	@ApiOperation(value = "Listar estado pelo id")
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Estado> findById(@PathVariable long id){
		Estado obj = estadoService.buscarPorId(id);
		
		 return 	ResponseEntity.ok(obj);
	}
	
	
	@ApiOperation(value = "Cadastrar estado")
	@PostMapping("/estado") // OK
	public Estado salvar(@RequestBody Estado estado){
	   return estadoService.add(estado);
	}
	
	
	@ApiOperation(value = "Editar estado")
	@PutMapping(value="/{id}")
	public ResponseEntity<Estado> update(@PathVariable("id") long id,
	                                      @RequestBody Estado estado) {
	   return estadoRepository.findById(id)
	           .map(record -> {
	               record.setNome(estado.getNome());
	               Estado updated = estadoRepository.save(record);
	               return ResponseEntity.ok().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@ApiOperation(value = "Deletar estado")
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity <?> delete(@PathVariable long id) {
	   return estadoRepository.findById(id)
	           .map(record -> {
	        	   estadoRepository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}

}
