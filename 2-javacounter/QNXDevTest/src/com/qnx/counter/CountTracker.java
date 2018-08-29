package com.qnx.counter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountTracker implements CountCallBack {
	int limit;
	int noOfCounters;
	int multipleOf;
	int totalCounter;
	int callBackCount;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();


	public CountTracker(int limit, int noOfCounters, int multipleOf){
		this.limit = limit;
		this.noOfCounters = noOfCounters;
		this.multipleOf = multipleOf;

	}

	public void initialize(){
		for(int i = 0; i< noOfCounters; i++){
			Counter counter = new Counter (limit, this);
			Thread t1 = new Thread(counter);
			t1.start();
		}
	}

	@Override
	public void reportCount(int count) {
		lock.lock();
		try{
			callBackCount++;
			totalCounter += count;
			if(callBackCount == noOfCounters){
				if(totalCounter % multipleOf == 0){
					System.out.println(totalCounter);
				}
				cond.signalAll();
				callBackCount = 0;
				totalCounter= 0;
			}
			else{
				cond.await();
			}
			


		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			
			lock.unlock();
		}

	}

}
