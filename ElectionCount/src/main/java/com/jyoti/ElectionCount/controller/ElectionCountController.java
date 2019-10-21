/**
 * 
 */
package com.jyoti.ElectionCount.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyoti.ElectionCount.model.DataNotFoundException;
import com.jyoti.ElectionCount.service.CandidateServices;

/**
 * @author JyotiKumar
 *
 */
@RestController
@RequestMapping("/api/test")
public class ElectionCountController {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	
	@Autowired
	private CandidateServices candidateServices;

	@GetMapping("/candidateData/{voterId}")
	public ResponseEntity<String> candidateData(@PathVariable(name = "voterId") String voterId)
			throws DataNotFoundException {
		String resultMsg = candidateServices.getCandidate(voterId);
		if (StringUtils.isEmpty(resultMsg))
			throw new DataNotFoundException("Invalid Voter");

		return new ResponseEntity<String>(resultMsg, HttpStatus.OK);

	}

	@GetMapping("/votingCount/{candidateId}")
	public ResponseEntity<Integer> votingCount(@PathVariable(name = "candidateId") String candidateId)
			throws DataNotFoundException {
		Integer resultMsg = candidateServices.getVotingCount(candidateId);
		if (resultMsg == 0)
			throw new DataNotFoundException("Invalid Candidate");

		return new ResponseEntity<Integer>(resultMsg, HttpStatus.OK);
	}
	
    @GetMapping("/load")
    public BatchStatus load() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException{
    
    	Map<String, JobParameter> maps = new HashMap<>();
    	maps.put("time", new JobParameter(System.currentTimeMillis()));
    	
    	JobParameters parameters = new JobParameters(maps);
    	JobExecution jobExecution = jobLauncher.run(job, parameters); 
    	
    	System.out.println("jobExecution: "+jobExecution.getStatus());
    	
    	System.out.println("Batch is Running....");
    	while(jobExecution.isRunning()){
    		System.out.println("....");
    	}
    	
    	return jobExecution.getStatus();
    }

}
