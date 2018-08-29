package com.qnx.counter;
/**
 * CallBack interface to report count value by counter
 * @author fatema
 *
 */

public interface CountCallBack {
	/**
	 * Report a count value. This thread will wait until all the counters/threads report their counter value.
	 * @param count
	 */
	public void reportCount(int count);

}
