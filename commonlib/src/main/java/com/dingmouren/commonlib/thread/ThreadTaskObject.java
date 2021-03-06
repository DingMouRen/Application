/**
 * Title: ThreadTaskObject.java
 * Description: 
 * Copyright: Copyright (c) 2013-2015 luoxudong.com
 * Company: 个人
 * Author: 罗旭东 (hi@luoxudong.com)
 * Date: 2017年4月20日 下午7:05:58
 * Version: 1.0
 */
package com.dingmouren.commonlib.thread;


import java.util.concurrent.ExecutorService;

/**
 *
 */
public abstract class ThreadTaskObject implements Runnable {
	private ExecutorService mExecutorService = null;
	
	private String mPoolName = null;
	
	public ThreadTaskObject() {
		init();
    }

	public ThreadTaskObject(String poolName) {
		mPoolName = poolName;
		init();
    }
	
	private void init() {
		mExecutorService = ThreadManager.Builder.cached().name(mPoolName).builder();
	}
	
	public void start() {
		mExecutorService.execute(this);
	}
}
