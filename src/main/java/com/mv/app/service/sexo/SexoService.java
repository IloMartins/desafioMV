package com.mv.app.service.sexo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mv.app.model.Sexo;
import com.mv.app.repository.SexoRepository;
import com.mv.app.service.GenericService;


@Service
public class SexoService implements GenericService<Sexo> {

	@Autowired
    private SexoRepository sexoRepository;

 
    
    public Sexo add(Sexo sexo) {
		return sexoRepository.save(sexo);
	}

    @Override
    public void excluir(Long id) {
        sexoRepository.deleteById(id);
    }

    @Override
    public Sexo buscarPorId(Long id) {
        return sexoRepository.findById(id).get();
    }

    @Override
    public List<Sexo> buscarTodos() {
        return sexoRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
    

}
