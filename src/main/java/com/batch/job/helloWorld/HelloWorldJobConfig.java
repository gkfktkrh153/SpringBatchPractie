package com.batch.job.helloWorld;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class HelloWorldJobConfig {

    private final JobBuilderFactory jobBuilderFactory; // job을 빌드해주는 빌더를 생성해줌
    private final StepBuilderFactory stepBuilderFactory;

    /**
     * @Bean은 스프링 부트 앱이 실행될 때 싱글톤 객체로 하나가 생성된다.
     * @JobScope 특정 잡에서 해당 스탭이 필요할 때 객체가 생성된다.
     * @StepScope 스탭에서 해당 tasklet이 필요할 때 생성
     */

    @Bean
    public Job helloWorldJob(Step helloWorldStep1){
        return jobBuilderFactory.get("helloWorldJob")
                .incrementer(new RunIdIncrementer()) // 실행 시 매번 다른 ID를 파라미터로 부여
                .start(helloWorldStep1)
                .build();
    }

    @Bean
    @JobScope
    public Step helloWorldStep1(Tasklet helloWorldTasklet){
        return stepBuilderFactory.get("helloWorldStep1")
                .tasklet(helloWorldTasklet)
                .build();
    }

    @Bean
    @StepScope
    public Tasklet helloWorldTasklet(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("Hello World!");
                return RepeatStatus.FINISHED;
            }
        };
    }

}
