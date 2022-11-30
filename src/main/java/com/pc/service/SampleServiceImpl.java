package com.pc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.model.Sample;
import com.pc.repositry.SampleDao;

@Service
public class SampleServiceImpl implements SampleService{

	@Autowired
	private SampleDao sampleDao ;
	
	@Override
	public Sample addSample(Sample sample) {
	return sampleDao.save(sample) ;
	}
	
	@Override
	public List<Sample> showSample() {
		return sampleDao.findAll() ;
	}

}
