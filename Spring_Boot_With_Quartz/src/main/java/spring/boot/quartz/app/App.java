package main.java.spring.boot.quartz.app;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.support.CronSequenceGenerator;

import main.java.spring.boot.quartz.job.SampleJob;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import org.quartz.CronTrigger;

import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;

@SpringBootApplication
public class App  implements CommandLineRunner{
	
StdSchedulerFactory stdSchedulerFactory;
	
	Scheduler sched = null;
	
	JobDetail job;
	
	  public static void main(String[] args) throws Exception {

	        //disabled banner, don't want to see the spring logo
	        SpringApplication app = new SpringApplication(App.class);
	        app.setBannerMode(Banner.Mode.OFF);
	        app.run(args);

	    }

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("works");
		stdSchedulerFactory = new StdSchedulerFactory();
		sched = stdSchedulerFactory.getScheduler();
		job = newJob(SampleJob.class)
			    .withIdentity("job1", "group1")
			    .build();
		Trigger trigger = newTrigger().withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule("0/5 * * * * ?")).build();
		sched.scheduleJob(job, trigger);
		sched.start();
	}

}
