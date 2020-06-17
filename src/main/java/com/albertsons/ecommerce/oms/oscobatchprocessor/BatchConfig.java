package com.albertsons.ecommerce.oms.oscobatchprocessor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rajni Kanth Tupakula
 */
@Component
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired private StepBuilderFactory stepBuilderFactory;

    public Job helloJob() {
        return jobBuilderFactory
                .get("job2")
                .start(stepBuilderFactory.get("job2step1")
                        .tasklet(new Tasklet(){
                            @Override
                            public RepeatStatus execute(
                                    StepContribution contribution,
                                    ChunkContext chunkContext) throws Exception {
                                System.out.println("Here we can add business logic to process job");
                                return RepeatStatus.FINISHED;
                            }
                        }).build()).build();
    }
}
