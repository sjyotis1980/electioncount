/**
 * 
 */
package com.jyoti.ElectionCount.service;

import java.io.IOException;
import java.util.Map;

import com.jyoti.ElectionCount.model.Candidates;

/**
 * @author JyotiKumar
 *
 */
public interface CandidateServices {
	
	void save(Map<String,String> map);
	
	String getCandidate(String voterId);
	
	Integer getVotingCount(String candidateId);
	
	

}
