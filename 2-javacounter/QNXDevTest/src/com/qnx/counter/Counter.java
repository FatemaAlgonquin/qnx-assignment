package com.qnx.counter;
/**
 * Represents counter thread
 * @author fatema
 *
 */

public class Counter implements Runnable{

	int limit;
	CountCallBack cc;

	public Counter(int limit, CountCallBack cc){
		this.limit = limit;
		this.cc = cc;
	}

	private void count(){
		for(int i = 0; i<= limit; i++){
			cc.reportCount(i);
		}
	}

	@Override
	public void run() {
		try{
			count();
		}
		catch(Exception e){

		}
	}

}
