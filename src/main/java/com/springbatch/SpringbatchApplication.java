package com.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class SpringbatchApplication {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job deliverPackageJob(){
		return this.jobBuilderFactory.get("deliverPackageJob").start(packageItemStep()).build();
	}

	public Step packageItemStep() {
		return this.stepBuilderFactory.get("packageItemStep").tasklet((contribution, chunkContext) -> {
			System.out.println("Item has been packaged");
			return RepeatStatus.FINISHED;
		}).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbatchApplication.class, args);
	}

}
