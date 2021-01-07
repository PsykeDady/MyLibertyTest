import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup 
@Singleton
public class MyAppDaemon{

	/**
	 * run commands
	 */
	@PostConstruct 
	public void daemonRC() {
		System.out.println("\n\n####Daemon load...####\n\n");

		File f=new File("daemonRC");

		try {
			PrintWriter pw=new PrintWriter(f);
			System.out.println("start log file in "+f.getAbsolutePath());
			pw.append("TEST"+LocalDateTime.now());
			
			pw.close();
		}catch(Exception e ){ return ;}

	}


	@PreDestroy 
	public void daemonDestroy() {
		System.out.println("\n\n####Daemon stop...####\n\n");

		File f=new File("daemonSTOP");

		try {
			PrintWriter pw=new PrintWriter(f);
			System.out.println("stop log file in "+f.getAbsolutePath());
			pw.append("TEST"+LocalDateTime.now());

			pw.close();
		}catch(Exception e ){ return ;}
	}

}