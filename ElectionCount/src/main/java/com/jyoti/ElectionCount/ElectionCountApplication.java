package com.jyoti.ElectionCount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jyoti.ElectionCount.model.Candidates;





@SpringBootApplication
@EnableJpaRepositories({"com.jyoti.ElectionCount.repository"})
public class ElectionCountApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ElectionCountApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(ElectionCountApplication.class);
		
	}

}
