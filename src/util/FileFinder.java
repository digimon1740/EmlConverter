/**
 * 		이 클래스는 파일탐색기 하여 
 * 		필요한 파일 경로 , 파일명을 추출하는 클래스이다.
 * 		.mail 파일만 가져오도록 필터링 되어있다. 
 * */
package util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileFinder extends JFileChooser{

	FileNameExtensionFilter extFilter;	//확장자 필터링 클래스 
	String filePath = "";	
	static final String DECIMAL_PATTERN = "#####.##";
	
	public FileFinder() {
		//객체 초기화 및 필터링 확장자 입력 
		extFilter = new FileNameExtensionFilter(".mail Files","mail");
		//파일필터 장착
		this.setFileFilter(extFilter);
		//파일을 여러개 선택할 수 있도록 함
		this.setMultiSelectionEnabled(true);
		
		//탐색기 실행 
		this.showOpenDialog(null);
	}
	
	/**
	 * 이 메서드는 선택된 파일의 경로를 가져온다.
	 * */
	public String getFilePath() {
		filePath = this.getSelectedFile().getPath();
		return filePath.substring(0, filePath.lastIndexOf(File.separator)+1);  
	}
	
	/**
	 * 	선택한 파일의 용량을 가져오는 메서드
	 * 	한개한개 파일의 값을 토탈사이즈에 저장하고
	 * 	메가바이트 단위로 나누어 반환한다.
	 * */
	public double getFileSize() {
		double totSize = 0;	//합계 용량 
		long curSize = 0;	//현재 파일 용량
		DecimalFormat df = null;  
		df = new DecimalFormat(DECIMAL_PATTERN);
		
		File[] files = this.getSelectedFiles();
		for(File file : files) {	
			curSize = file.length();		
			totSize += curSize;
		}
		///소수점 둘째 자리까지 출력되도록 파싱
		totSize = Double.parseDouble(
				df.format((double)totSize/(1024*1024))
				);
		return totSize;
	}
	
	/**
	 * 	이 메서드는 선택된 파일들의 이름을 추출해서
	 *  리턴시키는 메서드 이다.
	 * */ 
	public List<String> getFileNames() {
		File[] files = this.getSelectedFiles();
		List<String> list = new ArrayList<String>();
		for(File file : files) {	
			list.add(file.getName());
		} 
		
		return list;
	}
	
	
	
	
}




