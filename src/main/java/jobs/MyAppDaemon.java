package jobs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
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


@Startup 
@Singleton
public class MyAppDaemon{
	@Resource 
	private TimerService timerService;

	private static MyAppDaemon instance;
	
	/**
	 * run commands
	 */
	@PostConstruct 
	public void daemonRC() {
		System.out.println("\n\n####Daemon load...####\n\n");

		File f=new File("daemonRC");

		try (PrintWriter pw=new PrintWriter(f)){
			
			System.out.println("start log file in "+f.getAbsolutePath());
			pw.append("TEST"+LocalDateTime.now());
			
		} catch (Exception e) {}
		
		instance=this;

		nextTimer(5*60);

	}


	public static MyAppDaemon getInstance() {return instance;}
	
	@Timeout
	public void loop(Timer t){

		System.out.println("\n\n####TIMER TICK####\n\n");

		File f=new File("daemonLoop");

		try (
			FileWriter fr = new FileWriter(f, true);
			BufferedWriter br = new BufferedWriter(fr)
		){
			br.write("Scrittura "+LocalDateTime.now());
		}catch (Exception e) {}

	
		nextTimer(5*60);
		
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

		File f=new File("daemonSTOP");

		try (PrintWriter pw=new PrintWriter(f)) {
			
			System.out.println("stop log file in "+f.getAbsolutePath());
			pw.append("TEST"+LocalDateTime.now());

			
		}catch (Exception e) {}
	}

}