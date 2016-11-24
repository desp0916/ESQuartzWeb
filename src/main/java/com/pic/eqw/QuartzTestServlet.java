package com.pic.eqw;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

@WebServlet(name = "QuartzTestServlet", urlPatterns = {"/quartztest.view"})
public class QuartzTestServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {

		// 列出所有 jobs：
		// https://www.mkyong.com/java/how-to-list-all-jobs-in-the-quartz-scheduler/

		Scheduler scheduler;
		try {
			scheduler = new StdSchedulerFactory().getScheduler();

			// // define the job and tie it to our HelloJob class
			// JobDetail job = newJob(HelloJob.class)
			// .withIdentity("job1", "group1").build();
			//
			// // Trigger the job to run now, and then repeat every 40 seconds
			// Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
			// .startNow().withSchedule(simpleSchedule()
			// .withIntervalInSeconds(10).repeatForever())
			// .build();
			//
			// scheduler.scheduleJob(job, trigger);
			// ----------------------------------
//			JobStateExample example = new JobStateExample();
//			example.run(scheduler);
			System.out.println(
					"QuartzServlet###################################");

			Thread.sleep(3000);
			for (String groupName : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler
						.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();

					// get job's trigger
					List<Trigger> triggers = (List<Trigger>) scheduler
							.getTriggersOfJob(jobKey);
					Date nextFireTime = triggers.get(0).getNextFireTime();

					System.out.println(
							"[jobName] : " + jobName + " [groupName] : "
									+ jobGroup + " - " + nextFireTime);
//					 scheduler.deleteJob(jobKey);
				}
			}

		} catch (SchedulerException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp);
	}
}
