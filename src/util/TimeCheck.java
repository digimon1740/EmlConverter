/**
 * 		이 클래스는 어플리케이션 상에서 로직의 수행시간을 기록하는 클래스이다.   
 * */
package util;

import java.text.DecimalFormat;

public class TimeCheck {

	long startTime; 
	long delayTime = 0;
	StringBuffer currentName = new StringBuffer(); 
	boolean threadFlag= false;
	DecimalFormat df;
	static final String pattern = "#####.##";
	
	private TimeCheck() {
	}
	
	public TimeCheck( boolean threadFlag ) {
		changeMessage("",true,true);
	}
	
	public TimeCheck( String message ) {	
		changeMessage(message,false,true);
	}
	
	public TimeCheck( String message, boolean threadFlag ) {
		changeMessage(message,threadFlag,true);
	}
	
	public void start() {
		startTime = System.nanoTime();
		delayTime = 0;
	}
	
	public void stop() {
		/// Stop 메서드를 호출함으로 시간 측정을 중지한다. 
		delayTime = System.nanoTime() - startTime; 
	}
	
	public void changeMessage( String message , 
			boolean threadFlag, boolean resetFlag ) {
		
/*		StringBuffer threadName = new StringBuffer();
		this.threadFlag = threadFlag; 
		
		if( threadFlag ) {
			//// 쓰레드의 이름을 가져온다. 
			threadName.append("ThreadName = ")
			.append( Thread.currentThread().getName() );
		}
		
		currentName.append("[").append(message)
		.append(threadName).append("] ");
		*/
		if(resetFlag) {
			start();
		}
		
	} 
	
	public double getElapsedMS() {
		if( delayTime == 0 ) stop(); 
		return delayTime/1000000000.0;
	}
	
	public double getElapsedNano() {
		if( delayTime == 0 ) stop();
		return delayTime;
	}
	
	public String toString() {
		if( delayTime == 0 ) stop();
		df = new DecimalFormat(pattern);
		currentName
		.append(Double.parseDouble( df.format(
				delayTime/1000000000.0
				)))
		.append("초");
		
		return currentName.toString();
	}

	
	public static void main(String[] args) {
		
		TimeCheck check = new TimeCheck("Class.method",true);
		
		System.out.println(check);
	}
} 



