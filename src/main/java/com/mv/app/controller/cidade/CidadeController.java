package com.mv.app.controller.cidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mv.app.model.Cidade;
import com.mv.app.repository.CidadeRepository;
import com.mv.app.service.cidade.CidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cidade")
@Api(value = "Cidade")
public class CidadeController {
	
	@Autowired
	CidadeService cidadeService;
	
	private CidadeRepository cidadeRepository;
	
	CidadeController(CidadeRepository cidadeRepository) {
	       this.cidadeRepository = cidadeRepository;
	   }
	

	@ApiOperation(value = "Listar todos os cidades cadastrados")
	@GetMapping(value = "/cidades")
	public List<Cidade> getCidade(){
	   return cidadeService.buscarTodos();
	}
	
	
	@ApiOperation(value = "Listar cidade pelo id")
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Cidade> findById(@PathVariable long id){
		
		Cidade obj = cidadeService.buscarPorId(id);
		
		 return 	ResponseEntity.ok(obj);
	}
	
	
	@ApiOperation(value = "Cadastrar cidade")
	@PostMapping("/cidade") // OK
	public Cidade salvar(@RequestBody Cidade cidade){
	   return cidadeService.add(cidade);
	}
	
	
	@ApiOperation(value = "Editar cidade")
	@PutMapping(value="/{id}")
	public ResponseEntity<Cidade> update(@PathVariable("id") long id,
	                                      @RequestBody Cidade cidade) {
	   return cidadeRepository.findById(id)
	           .map(record -> {
	               record.setNome(cidade.getNome());
	               Cidade updated = cidadeRepository.save(record);
	               return ResponseEntity.ok().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@ApiOperation(value = "Deletar cidade")
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity <?> delete(@PathVariable long id) {
	   return cidadeRepository.findById(id)
	           .map(record -> {
	        	   cidadeRepository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	
	@ApiOperation(value = "Listar cidade pelo id do estado")
	@GetMapping(value = "/cidadePorEstado/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Cidade> listarCidadesPorEstado(@PathVariable("id") Long id) {
        return cidadeService.buscarPorEstado(id);
    }


}
