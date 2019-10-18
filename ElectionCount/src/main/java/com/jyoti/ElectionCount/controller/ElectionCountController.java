/**
 * 
 */
package com.jyoti.ElectionCount.controller;



import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jyoti.ElectionCount.model.DataGenerator;
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
	private CandidateServices candidateServices;
	
	@GetMapping("/candidateData/{voterId}")
	public ResponseEntity<String> candidateData(@PathVariable(name = "voterId") String voterId) throws DataNotFoundException  {
		String resultMsg  = candidateServices.getCandidate(voterId);
		if(StringUtils.isEmpty(resultMsg))
		  throw new DataNotFoundException("Invalid Voter");
		
		return new ResponseEntity<String>(resultMsg, HttpStatus.OK);

	}
	
	@GetMapping("/votingCount/{candidateId}")
	public ResponseEntity<Integer> votingCount(@PathVariable(name = "candidateId") String candidateId) throws DataNotFoundException  {
		Integer resultMsg  = candidateServices.getVotingCount(candidateId);
		if(resultMsg==0)
			  throw new DataNotFoundException("Invalid Candidate");
			
		return new ResponseEntity<Integer>(resultMsg, HttpStatus.OK);
		
	}

}
