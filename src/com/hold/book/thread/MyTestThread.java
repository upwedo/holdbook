package com.hold.book.thread;

public class MyTestThread implements Runnable{
	private String name;
    public MyTestThread(String name){
    	this.name=name;
    }
	@Override
	public void run() {
		 System.out.println(name + " is running.");
	}

}
