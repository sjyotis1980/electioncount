/**
 * 
 */
package com.jyoti.ElectionCount.service;

import com.jyoti.ElectionCount.model.Candidates;

/**
 * @author JyotiKumar
 *
 */
public interface CandidateServices {

	void save(Candidates candidates);

	String getCandidate(String voterId);

	Integer getVotingCount(String candidateId);

}
