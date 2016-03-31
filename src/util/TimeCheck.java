/**
 * 		�� Ŭ������ ���ø����̼� �󿡼� ������ ����ð��� ����ϴ� Ŭ�����̴�.   
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
		/// Stop �޼��带 ȣ�������� �ð� ������ �����Ѵ�. 
		delayTime = System.nanoTime() - startTime; 
	}
	
	public void changeMessage( String message , 
			boolean threadFlag, boolean resetFlag ) {
		
/*		StringBuffer threadName = new StringBuffer();
		this.threadFlag = threadFlag; 
		
		if( threadFlag ) {
			//// �������� �̸��� �����´�. 
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
		.append("��");
		
		return currentName.toString();
	}

	
	public static void main(String[] args) {
		
		TimeCheck check = new TimeCheck("Class.method",true);
		
		System.out.println(check);
	}
} 



