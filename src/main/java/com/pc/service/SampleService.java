package com.pc.service;

import java.util.List;

import com.pc.model.Sample;

public interface SampleService {
	
	public Sample addSample(Sample sample)  ; 
	public List<Sample> showSample() ;
}
