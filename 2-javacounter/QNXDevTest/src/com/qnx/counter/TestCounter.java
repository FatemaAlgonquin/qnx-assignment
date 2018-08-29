package com.qnx.counter;

public class TestCounter {

	public static void main(String[] args) {
		CountTracker countTracker = new CountTracker(10000, 10, 1000);
		countTracker.initialize();

	}

}
