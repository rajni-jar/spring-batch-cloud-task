package com.albertsons.ecommerce.oms.oscobatchprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@SpringBootApplication
@EnableTask
@EnableBatchProcessing
@Slf4j
public class OscoBatchProcessorApplication implements CommandLineRunner {
	@Autowired private BatchConfig batchConfig;
	@Autowired private JobLauncher jobLauncher;

	public static void main(String[] args) {
		SpringApplication.run(OscoBatchProcessorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello from spring cloud task");
		try {
			JobParameters jobParameters = new JobParametersBuilder()
					.addLong("batch job ", System.currentTimeMillis()).toJobParameters();
			Job job = batchConfig.helloJob();
				jobLauncher.run(job, jobParameters);
		} catch (Exception e) {
			log.error("Error handling Batch job : ", e);
		}
	}
}
