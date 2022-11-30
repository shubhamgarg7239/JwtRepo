package com.pc.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sample {
	@Id
	private Integer id ;
	private String name ;
	private String mobNo ;
	private String password ;
}
