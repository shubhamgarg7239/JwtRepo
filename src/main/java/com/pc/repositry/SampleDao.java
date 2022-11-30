package com.pc.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.model.Sample;

@Repository
public interface SampleDao extends JpaRepository<Sample, Integer> {
	
}
