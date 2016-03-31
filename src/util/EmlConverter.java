/**
 * 		이 클래스는 .mail파일을 .eml파일로 변환 시켜주는 
 * 		클래스이다. 
 * 		
 * */
package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

public class EmlConverter {

	File[] fileInfo;
	String[] emlList;

	/**
	 * FileFinder 로 찾아온 파일의 경로와 이름을 가져온다.
	 * 
	 * @param filePath
	 * @param nameList
	 */
	public void getFileInfo(String filePath, List<String> nameList) {
		int i = 0;
		emlList = new String[nameList.size()];
		// /파일명 리스트를 가져온다.
		for (String fileName : nameList) {
			emlList[i] = filePath + fileName;
			i++;
		}
	}

	/**
	 * @param copyPath
	 *            가져온 파일명으로 파일의 내용을 읽는다. 읽은뒤 복사될 디렉토리에 폴더를 생성하고, 유효성 검사후 파일의
	 *            확장자를 변경하여 복사한다.
	 * */
	public boolean getFileParser(String copyPath) {

		File cpFileNameEml = null;
		String cpFileName = "";
		String orgFilePath = ""; // .mail 파일 경로 및 파일명
		File copyFilePath = new File(copyPath); // 복사될 디렉토리 경로확인
		copyFilePath.mkdir(); // 복사될 디렉토리 경로에 폴더 생성

		for (int i = 0; i < emlList.length; i++) {
			orgFilePath = emlList[i];
			// /복사될 파일경로를 만들기위해
			// /기존의 경로및 파일명에서 마지막/부터 마지막.까지 자르면 파일명이 추출됨
			cpFileName = copyFilePath
					+ File.separator
					+ emlList[i].substring(
							emlList[i].lastIndexOf(File.separator) + 1,
							emlList[i].lastIndexOf("."));
			try {
				cpFileNameEml = new File(cpFileName + ".eml");
				writeFile(orgFilePath, cpFileNameEml);
				// 원본 경로에 파일이 존재한다면 파일을 버퍼에 담는다.
				// NIO 를 이용한 파일 입출력 메서드
				// fileWrite( orgFilePath,cpFileNameEml );

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return true;

	}

	public void writeFile(String orgFilePath, File cpFileNameEml) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			bis = new BufferedInputStream(new FileInputStream(orgFilePath));
			bos = new BufferedOutputStream(new FileOutputStream(cpFileNameEml));

			byte[] b = new byte[1024 * 8];

			for (int len = 0; (len = bis.read(b)) != -1;) {
				bos.write(b, 0, len);
				bos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null)
					bis.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				if (bos != null)
					bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
