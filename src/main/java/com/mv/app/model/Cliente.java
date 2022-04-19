package com.mv.app.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @Column(columnDefinition = "varchar(100)")
	 private String nomeCompleto;
	 
	 @ManyToOne
	 @JoinColumn(name = "sexo_id")
	 private Sexo sexo;
	 
	 @JsonDeserialize(using = LocalDateDeserializer.class)  
	 @JsonSerialize(using = LocalDateSerializer.class)  
	 @DateTimeFormat(pattern = "dd/MM/yyyy")
	 private LocalDate dataNascimento;
	 
	 @Column(name = "idade_atual")
	 private Integer idade;
	 
	 @ManyToOne
	 @JoinColumn(name = "cidade_id")
	 private Cidade cidade;

}
