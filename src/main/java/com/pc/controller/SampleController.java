package com.pc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pc.model.Sample;
import com.pc.service.SampleService;

@RestController
public class SampleController {
	@Autowired
	private SampleService sampleService ;
	
	@PostMapping("/add")
	public ResponseEntity<Sample> addSampleHandler(@RequestBody Sample sample){
		Sample newSample = sampleService.addSample(sample) ;
		
		return new ResponseEntity<Sample>(newSample, HttpStatus.OK) ;
	}
	
	@GetMapping("/show")
	public ResponseEntity<List<Sample>> showSampleHandler(){
		List<Sample> newSample = sampleService.showSample() ;
		
		return new ResponseEntity<List<Sample>>(newSample, HttpStatus.OK) ;
	}
}
