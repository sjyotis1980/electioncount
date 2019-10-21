/**
 * 
 */
package com.jyoti.ElectionCount.batch;



import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jyoti.ElectionCount.model.BloomFilterManager;
import com.jyoti.ElectionCount.model.Candidates;

/**
 * @author JyotiKumar
 *
 */
@Component
public class Processor implements ItemProcessor<Candidates, Candidates> {

	@Autowired
	private BloomFilterManager bloomFilterManager;
	
	@Override
	public Candidates process(Candidates candidates) throws Exception {
		// TODO Auto-generated method stub
		Candidates transformed = null;
		if (bloomFilterManager.getBloomFilter().mightContain(candidates.getVoterId())) {
			
			transformed = new Candidates(candidates.getVoterId(), candidates.getCandidateId());
		
		}
		return transformed;
	}

}
