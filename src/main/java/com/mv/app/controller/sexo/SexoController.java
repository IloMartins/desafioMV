package com.mv.app.controller.sexo;

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

import com.mv.app.model.Sexo;
import com.mv.app.repository.SexoRepository;
import com.mv.app.service.sexo.SexoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/sexo")
@Api(value = "Sexo")
public class SexoController {
	
	@Autowired
	SexoService sexoService;
	
	private SexoRepository sexoRepository;

	SexoController(SexoRepository sexoRepository) {
	       this.sexoRepository = sexoRepository;
	   }
	

	@ApiOperation(value = "Listar todos os sexos cadastrados")
	@GetMapping(value = "/sexos")
	public List<Sexo> getSexo(){
	   return sexoService.buscarTodos();
	}
	
	
	@ApiOperation(value = "Listar sexo pelo id")
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Sexo> findById(@PathVariable long id){
	   return sexoRepository.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.notFound().build());
	}
	
	
	@ApiOperation(value = "Cadastrar sexo")
	@PostMapping("/sexo") // OK
	public Sexo salvar(@RequestBody Sexo sexo){
	   return sexoService.add(sexo);
	}
	
	
	@ApiOperation(value = "Editar sexo")
	@PutMapping(value="/{id}")
	public ResponseEntity<Sexo> update(@PathVariable("id") long id,
	                                      @RequestBody Sexo sexo) {
	   return sexoRepository.findById(id)
	           .map(record -> {
	               record.setDescricao(sexo.getDescricao());
	               Sexo updated = sexoRepository.save(record);
	               return ResponseEntity.ok().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@ApiOperation(value = "Deletar sexo")
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity <?> delete(@PathVariable long id) {
	   return sexoRepository.findById(id)
	           .map(record -> {
	        	   sexoRepository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	

}
