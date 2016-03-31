/**
 * 		이 클래스는 eml 컨버터의 메인 화면 클래스이다. 
 * 		
 * */
package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.EmlConverter;
import util.FileFinder;
import util.TimeCheck;

public class MainView extends InitView implements ActionListener,Runnable{

	FileFinder finder;	// 파일탐색기 클래스
	EmlConverter converter;	//파일변환 클래스
	List<String> nameList = new ArrayList<String>();
	TimeCheck timeCheck ;
	
	public MainView() {		
		
		converter = new EmlConverter();	//컨버팅 유틸  		
		
		///상단 컨테이너
		this.add(p_north,BorderLayout.NORTH);
		p_north.add(p_north1);
		p_north.add(p_north2);
		p_north.add(p_north3);
		p_north.setLayout(new GridLayout(3,0));
		p_north1.add(la_filePath);
		p_north1.add(t_filePath);

		p_north2.add(la_copyPath);
		p_north2.add(t_copyPath);
		
		p_north3.add(la_fileCount);
		p_north3.add(t_fileCount);
		p_north3.add(la_fileSize);
		p_north3.add(t_fileSize);		
		p_north3.add(la_curTime);
		p_north3.add(t_curTime);
		
		t_fileCount.setText("0 개");	//파일 갯수 필드 초기화
		t_fileSize.setText("0 MB");		//파일 용량 필드 초기화
		
		//textField에 입력하지 못하도록
		t_fileSize.setEditable(false);			
		t_fileCount.setEditable(false);	
		t_filePath.setEditable(false);	
		t_copyPath.setEditable(false);			
		t_curTime.setEditable(false);	
		
		///중앙 컨테이너 
		this.add(p_center,BorderLayout.CENTER);
		
		///중앙을 그리드로 나눈다. 
		p_center.setLayout(new GridLayout(0,2));
		p_center.add(p_center1);
		p_center.add(p_center2);

		p_center1.add(s_west);

		p_center2.add(s_east);		
		
		a_west.setEditable(false);
		a_east.setEditable(false);
		
		///아래쪽 컨테이너 
		this.add(p_south,BorderLayout.SOUTH);
		p_south.add(p_south1);
		
		///버튼을 붙인다. 
		p_south.add(bt_find);
		p_south.add(bt_convert);
		p_south.add(bt_exit);
		
		//버튼에 생명을 단다. 
		bt_find.addActionListener(this);
		bt_convert.addActionListener(this);
		bt_exit.addActionListener(this);
		
		this.setTitle("eml 변환기 ----------------- ver 0.3 제작자 : 이상훈 ");
		this.setResizable(false);		//사이즈 고정 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	//종료시 자바 프로세스 종료
		this.setSize(530,400);		//현재 프레임의 사이즈 
		this.setVisible(true);
		
	}
	
	/**
	 * @Override
	 * @param ActionEvent  
	 *  버튼을 누를시 작동하는 액션 메서드 
	 */
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource(); 		
		//파일 찾기 
		if(obj.equals(bt_find)) {
			fileOpenWork();	//파일을 열고 경로,이름을 가져온다.
		}
		//변환 
		else if(obj.equals(bt_convert)) {
			if(a_west.getText().length() <= 0 ){
				JOptionPane.showMessageDialog(getParent(), "파일을 선택해 주세요.");
				return;
			}
			timeCheck = new TimeCheck("",true);
			//	파일 쓰기 스레드 실행
			Thread th = new Thread( this );
			th.start();
			
		}
		//종료
		else if(obj.equals(bt_exit)) {
			System.exit(0);
		}
		
	}
	
	/**
	 * 		파일을 열어서 이름을가져오고, 텍스트영역에 심는 작업을
	 * 		하는 메서드
	 * */
	public void fileOpenWork() {
		int fileCount = 0;
		finder = new FileFinder();
		///경로 가져오기
		t_filePath.setText(finder.getFilePath());
		t_copyPath.setText(finder.getFilePath()+"EmlFiles"+File.separator);
		///파일명 가져오기
		
		nameList = finder.getFileNames();
		
		///파일명을 각각의 file 객체에 담는다
		for( String fileName : nameList ){
			//가져온 파일 이름을 textArea 에 심는다.
			fileCount++;
			a_west.setText(a_west.getText()+fileName+"\n");
			a_east.setText("");
		}
		t_fileCount.setText(fileCount+" 개");
		///파일경로와 파일명 리스트를 변환기에 넣는다.  
		converter.getFileInfo(finder.getFilePath(),nameList);
		t_fileSize.setText(finder.getFileSize()+" MB");
		
		JOptionPane.showMessageDialog(getParent(), fileCount+" 개 의 파일을 모두 불러왔습니다.");
	}			
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MainView();
	}

	@Override
	public void run() {
		boolean flag = false;
		// TODO Auto-generated method stub
		converter.getFileParser(t_copyPath.getText());
		/// 복사 경로와 원본파일이 존재할때 작동하도록함
		if(t_copyPath != null || 0 < t_copyPath.getText().length())
			flag = converter.getFileParser(t_copyPath.getText());
		///	복사가 성공하였을경우 파일명을 오른쪽 textarea로 옮긴다.
		if( flag ) {
			t_curTime.setText( timeCheck.toString() );
			a_east.setText(a_west.getText());
			a_west.setText("");
			JOptionPane.showMessageDialog(getParent(), "변환이 성공 하였습니다.");
			
		}else{
			JOptionPane.showMessageDialog(getParent(), "변환이 실패 하였습니다.");
		}
		
	}


}








