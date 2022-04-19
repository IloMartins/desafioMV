package com.mv.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mv.app.model.Cidade;


@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query("select c from Cidade c where c.estado.id = :estado order by c.nome")
    public List<Cidade> findByEstado(@Param("estado")Long estado);
}

