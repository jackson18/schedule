package com.qijiabin.schedule;

/**
 * ========================================================
 * 日 期：2016年10月21日 上午11:04:01
 * 版 本：1.0.0
 * 类说明：
 * TODO
 * ========================================================
 * 修订日期     修订人    描述
 */
public abstract class ScheduleTask implements Runnable {
	
	protected final Thread thread;
	private Object lock = new Object();
	private volatile boolean isNotified;
	private volatile boolean isStoped;
	
	
	public ScheduleTask(String name) {
        this.thread = new Thread(this, name);
    }
	
	public void start() {
		this.thread.start();
	}
	
	public void stop() {
		this.isStoped = true;
	}
	
	@Override
	public void run() {
		while (!isStoped) {
			doRun();
		}
		doEnd();
	}
	
	public void pause() {
		synchronized (lock) {
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				isNotified = false;
			}
		}
	}
	
	public void pause(long milliseconds) {
		synchronized (lock) {
			try {
				lock.wait(milliseconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				isNotified = false;
			}
		}
	}
	
	public void wakeup() {
		synchronized (lock) {
			if (!isNotified) {
				isNotified = true;
				lock.notify();
			}
		}
	}
	
	public boolean isStop() {
		return this.isStoped;
	}
	
	protected abstract void doRun();
	
	protected abstract void doEnd();

}

