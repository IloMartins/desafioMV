package com.mv.app.service.cidade;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mv.app.controller.cliente.ClienteController;
import com.mv.app.model.Cidade;
import com.mv.app.repository.CidadeRepository;
import com.mv.app.service.GenericService;


@Service
public class CidadeService implements GenericService<Cidade> {
	 
	@Autowired
	 private CidadeRepository cidadeRepository;
	
	private static Logger logger = LoggerFactory.getLogger(ClienteController.class);

   public Cidade add(Cidade cidade) {
	   logger.info("Cidade salva com sucesso");
		return cidadeRepository.save(cidade);
	}
	   
	@Override
	public void excluir(Long id) {
		cidadeRepository.deleteById(id);
		 logger.info("Cidade excluída com sucesso");
	}

	@Override
	public Cidade buscarPorId(Long id) {
		
		Optional<Cidade> resultado =  cidadeRepository.findById(id);
		   Cidade obj = resultado.orElse(null);
		   logger.info("Cidade com id"+ id +"encontrado");
		   
		   return obj;
	}

	@Override
	public List<Cidade> buscarTodos() {
		
		 List<Cidade> resultado =  cidadeRepository.findAll();
		   logger.info("Lista carregada"+ resultado +" com sucesso");
		   
		return cidadeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public List<Cidade> buscarPorEstado(Long id) {
		List<Cidade> resultado =  cidadeRepository.findByEstado(id);
		 logger.info("Lista"+ resultado  +"exibida");
	    return cidadeRepository.findByEstado(id);
	}

}
