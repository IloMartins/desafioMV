package com.mv.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mv.app.model.Sexo;


@Repository
public interface SexoRepository extends JpaRepository<Sexo, Long> {
	@Query("select c from Sexo c " +
            "where cast(c.id as string) like %:pesquisa% or " +
            "c.descricao like %:pesquisa% "
           )
	public Page<Sexo> findAllTable(@Param("pesquisa")String pesquisa, Pageable pageable);
}
