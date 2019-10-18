/**
 * 
 */
package com.jyoti.ElectionCount.service.impl;

import java.util.Map;

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
	public void save(Map<String, String> map) {
		// TODO Auto-generated method stub
		candidatesRepository.save(toEntity(map));
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

	private Candidates toEntity(Map<String, String> map) {
		Candidates candidates = new Candidates();
		for (Map.Entry<String, String> entry : map.entrySet()) {

			candidates.setVoterId(entry.getKey());
			candidates.setCandidateId(entry.getValue());

		}
		return candidates;
	}

}
