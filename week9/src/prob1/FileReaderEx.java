package prob1;

import java.io.*;

public class FileReaderEx {

	public static void main(String[] args) {
		System.out.println("학번 : 22140038, 이름 : 임지민\n");
		
		FileReader fin = null;
		try {
			fin = new FileReader("c:\\windows\\system.ini");
			int c;
			while ((c = fin.read()) != -1) {
				System.out.print((char)c);
			}
			fin.close();
		}
		catch (IOException e) {
			System.out.println("입출력 오류");
		}
	}
}