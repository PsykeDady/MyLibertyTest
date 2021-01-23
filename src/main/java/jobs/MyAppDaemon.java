package jobs;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import jobs.impl.MyJobTest;
import psykeco.ioeasier.io.DebugPrint;
import psykeco.littlejonh.LittleJonH;
import utils.MyAppConstant;
import utils.MyAppProperties;


@Startup 
@Singleton
public class MyAppDaemon{
	@Resource 
	private TimerService timerService;

	private DebugPrint dp;

	private IMyJobs jobs[]={
		new MyJobTest()
	};

	/**
	 * run commands
	 */
	@PostConstruct 
	public void daemonRC() {
		System.out.println("\n\n####Daemon load...####\n\n");

		dp=new DebugPrint(MyAppProperties.getInstance().getValue(MyAppConstant.DEBUG_LOG),true);
		dp.debug_mode=true;
		DebugPrint.global_mode=MyAppProperties.getInstance().getValue(MyAppConstant.DEBUG_GLOBAL).equals("1");

		dp.println("SERVER START NOW: "+LocalDateTime.now());
		
		nextTimer(1*60+0L);

	}
	
	@Timeout
	public void loop(Timer t){

		System.out.println("\n\n####TIMER TICK####\n\n");

		dp.println("SERVER LOOP TICK ON: "+LocalDateTime.now());

		for (IMyJobs job : jobs ){
			if(!job.busy()) job.task();
		}

		LittleJonH cron=new LittleJonH("* * * * *");
	
		// nextTimer(1*60);
		nextCalendar(cron.nextT());
		
	}


	public void nextCalendar(LocalDateTime l){

		ScheduleExpression schedule = new ScheduleExpression();

		schedule.year(l.getYear());
		schedule.month(l.getMonth().getValue());
		schedule.dayOfMonth(l.getDayOfMonth());
		schedule.hour(l.getHour());
		schedule.minute((l.getMinute()+2)%60);

		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setPersistent(false);

		timerService.createCalendarTimer(schedule, timerConfig);

		
	}

	public void nextTimer(long second){
		long seconds=second*1000;
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setPersistent(false);
		timerService.createSingleActionTimer(seconds, timerConfig) ;
	}

	

	@PreDestroy 
	public void daemonDestroy() {
		System.out.println("\n\n####Daemon stop...####\n\n");

		dp.println("SERVER STOP NOW :"+LocalDateTime.now());
			
	}

}