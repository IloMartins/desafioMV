package com.mv.app.service.estado;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mv.app.controller.cliente.ClienteController;
import com.mv.app.model.Estado;
import com.mv.app.repository.EstadoRepository;
import com.mv.app.service.GenericService;

@Service
public class EstadoService implements GenericService<Estado> {
	
	@Autowired
	private EstadoRepository estadoRepository;

	private static Logger logger = LoggerFactory.getLogger(ClienteController.class);

	
	
	 public Estado add(Estado estado) {
		  logger.info("Cidade salva com sucesso");
		return estadoRepository.save(estado);
		}


	@Override
	public void excluir(Long id) {
		estadoRepository.deleteById(id);
		  logger.info("Cidade excluída com sucesso");
	}

	@Override
	public Estado buscarPorId(Long id) {
		
		List<Estado> resultado =  estadoRepository.findAll();
		   logger.info("Lista carregada"+ resultado +" com sucesso");
		  return estadoRepository.findById(id).get();
	}

	@Override
	public List<Estado> buscarTodos() {
		 return estadoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

}
