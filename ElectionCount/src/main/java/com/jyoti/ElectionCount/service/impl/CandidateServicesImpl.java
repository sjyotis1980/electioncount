/**
 * 
 */
package com.jyoti.ElectionCount.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.jyoti.ElectionCount.model.Candidates;

import com.jyoti.ElectionCount.repository.CandidatesRepository;
import com.jyoti.ElectionCount.service.CandidateServices;

/**
 * @author JyotiKumar
 *
 */
@Service
public class CandidateServicesImpl implements CandidateServices {

	@Autowired
	private CandidatesRepository candidatesRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jyoti.ElectionCount.service.CandidateServices#save(com.jyoti.
	 * ElectionCount.model.Candidates)
	 */
	@Override
	public void save(Candidates candidates) {
		// TODO Auto-generated method stub
		candidatesRepository.save(candidates);
	}

	@Override
	public String getCandidate(String voterId) {
		// TODO Auto-generated method stub
		return candidatesRepository.findByVoterId(voterId);
	}

	@Override
	public Integer getVotingCount(String candidateId) {
		// TODO Auto-generated method stub
		return candidatesRepository.findByCandidateId(candidateId);
	}

	
}
