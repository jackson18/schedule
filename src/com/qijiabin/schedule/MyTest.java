package com.qijiabin.schedule;

/**
 * ========================================================
 * 日 期：2016年11月10日 下午1:43:27
 * 版 本：1.0.0
 * 类说明：
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
public class MyTest {
	
	private static ScheduleTask scheduleTask;
	
	public static void start(String name) {
		scheduleTask = new ScheduleTask(name) {
			@Override
			protected void doRun() {
				System.out.println("do my job...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("wait ...");
				pause(2000L);
			}
			
			@Override
			protected void doEnd() {
				System.out.println("my job is over....");
			}
		};
		scheduleTask.start();
	}

	public static void main(String[] args) {
		start("myTask");
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		scheduleTask.stop();
	}

}

