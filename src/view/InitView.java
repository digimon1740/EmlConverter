package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InitView extends JFrame {
	
	JPanel p_north,p_north1,p_north2,p_north3;					//북쪽영역 
	JPanel p_center,p_center1,p_center2;	//중앙영역
	JPanel p_south,p_south1; 					//남쪽영역
	JButton bt_find,bt_convert,bt_exit;		//하단 버튼
	JTextField t_filePath,t_copyPath,t_fileCount,t_fileSize,t_curTime;						
	JLabel la_filePath,la_copyPath,la_fileCount,la_fileSize,la_curTime;
	JTextArea a_west,a_east;
	JScrollPane s_west,s_east;
	
	public InitView() {
		///객체 초기화
		initPanel();
		initTextArea();
		initScrollPane();
		initBtn();
		initTextField();
		initLabel();
	}
	
	public void initTextArea() {
		a_west = new JTextArea(17,22);
		a_east = new JTextArea(17,22);
	}
	
	public void initScrollPane() {
		s_west = new JScrollPane(a_west);
		s_east = new JScrollPane(a_east);
	}
	
	public void initBtn() {
		bt_find = new JButton("파일 찾기"); 
		bt_convert = new JButton("eml로 변환");
		bt_exit = new JButton("종료");
	}
	
	public void initPanel() {
		p_north = new JPanel();
		p_north1 = new JPanel();
		p_north2 = new JPanel();
		p_north3 = new JPanel();
		p_center = new JPanel();
		p_center1 = new JPanel();
		p_center2 = new JPanel();
		p_south = new JPanel();
		p_south1 = new JPanel();
	}
	
	public void initTextField() {
		t_filePath = new JTextField(30);
		t_copyPath = new JTextField(30);
		t_fileCount = new JTextField(5);
		t_fileSize = new JTextField(10);
		t_curTime = new JTextField(10);
	}
	
	public void initLabel() {
		la_filePath = new JLabel("파일 경로 : ");
		la_copyPath = new JLabel("복사 경로 : ");
		la_fileCount = new JLabel("파일 갯수 : ");
		la_fileSize = new JLabel("용량 : ");
		la_curTime = new JLabel("경과시간 : ");
	}



}
