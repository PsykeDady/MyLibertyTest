package jobs.impl;

import java.util.concurrent.atomic.AtomicBoolean;

import jobs.IMyJobs;

public class MyJobTest implements IMyJobs{

	private AtomicBoolean busy=new AtomicBoolean(false);

	@Override
	public boolean busy() {
		return busy.get();
	}

	@Override
	public void task() {
		busy.set(true);
		
		System.out.println("#### MyJobTest TICK ####");

		busy.set(false);
	}
	
}
