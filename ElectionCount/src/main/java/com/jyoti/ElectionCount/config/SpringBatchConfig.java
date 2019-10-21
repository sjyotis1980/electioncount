/**
 * 
 */
package com.jyoti.ElectionCount.config;





import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;

import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.jyoti.ElectionCount.model.Candidates;
import com.jyoti.ElectionCount.repository.CandidatesRepository;

/**
 * @author JyotiKumar
 *
 */
@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	private CandidatesRepository candidatesRepository;

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
			       StepBuilderFactory stepBuilderFactory,
			       ItemReader<Candidates> itemReader,
			       ItemProcessor<Candidates, Candidates> itemProcessor,
			       RepositoryItemWriter<Candidates> itemWriter){
		
		Step step1 = stepBuilderFactory.get("ETL-file-load")
				.<Candidates, Candidates>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
		
		return jobBuilderFactory.get("ETL-Load")
		       .incrementer(new RunIdIncrementer())
		       .flow(step1)
		       .end()
		       .build();
		       
	}
	
	@Bean
	public FlatFileItemReader<Candidates> flatFileItemReader(@Value("${input}") Resource resource ) {
		
		FlatFileItemReader<Candidates> flatFileItemReader = new FlatFileItemReader<>();
		
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("TXT-Reader");
		//flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}

	private LineMapper<Candidates> lineMapper() {
		// TODO Auto-generated method stub
		DefaultLineMapper<Candidates> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		
		lineTokenizer.setDelimiter(" ");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[]{"voterId", "CandidateId"});
		
		BeanWrapperFieldSetMapper<Candidates> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Candidates.class);
		
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		
		return defaultLineMapper;
	}
	
	@Bean
    RepositoryItemWriter<Candidates> movieRepositoryItemWriter(){

        RepositoryItemWriter<Candidates> repositoryItemWriter = new RepositoryItemWriter<>();
        repositoryItemWriter.setRepository(candidatesRepository);
        repositoryItemWriter.setMethodName("save");
        return repositoryItemWriter;

    }
	
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}
}
