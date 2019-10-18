/**
 * 
 */
package com.jyoti.ElectionCount.model;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author JyotiKumar
 *
 */
@Component
public class BloomFilterManager {
	
	private int expectedInsertions = 500000000;

	private double ffp = 0.01;
	
	private BloomFilter<String> bloomFilter;
	
	public BloomFilterManager(){
		this.bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), expectedInsertions, ffp);
	}
	
//	public void populate(List<VoterID> voterIDs){
//		
//	}
	
	public BloomFilter<String> getBloomFilter(){
		return bloomFilter;
	}

}
