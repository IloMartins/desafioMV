package com.mv.app.service.cliente;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mv.app.controller.cliente.ClienteController;
import com.mv.app.model.Cliente;
import com.mv.app.repository.ClienteRepository;
import com.mv.app.service.GenericService;


@Service
public class ClienteService implements GenericService<Cliente> {
	
	@Autowired
	private ClienteRepository clienteRepository;

	private static Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	
	 public Cliente add(Cliente cliente) {
		 logger.info("Cliente salvo com sucesso");
			return clienteRepository.save(cliente);
			
	}

	@Override
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
		 logger.info("Cliente excluído com sucesso");
	}

	@Override
	public Cliente buscarPorId(Long id) {
	
	   Optional<Cliente> resultado =  clienteRepository.findById(id);
	   Cliente obj = resultado.orElse(null);
	   logger.info("Cliente com id"+ id +"encontrado");
	   
	   return obj;
	      
	}
	
	
	public List<Cliente> buscarNome(String nome) {
		 List<Cliente> resultado =  clienteRepository.findByNomeCompleto(nome);
		 
		   logger.info("Lista carregada"+ resultado +" com sucesso");
		return clienteRepository.findByNomeCompleto(nome);
	}

	@Override
	public List<Cliente> buscarTodos() {
		 List<Cliente> resultado =  clienteRepository.findAll();
		 
		   logger.info("Lista carregada"+ resultado +" com sucesso");
		return clienteRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

}
