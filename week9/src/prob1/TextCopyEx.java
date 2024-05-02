package prob1;

import java.io.*;

public class TextCopyEx {

	public static void main(String[] args) {
		System.out.println("학번 : 22140038, 이름 : 임지민\n");
		
		File src = new File("c:\\windows\\system.ini");
		File dest = new File("c:\\Temp\\system.txt");
		
		int c;
		try {
			FileReader fr = new FileReader(src);
			FileWriter fw = new FileWriter(dest);
			while ((c = fr.read()) != -1) {
				fw.write((char)c);
			}
			fr.close();
			fw.close();
			System.out.println(src.getPath() + "를 " + dest.getPath() + "로 복사하였습니다.");
		} catch (IOException e) {
			System.out.println("파일 복사 오류");
		}
	}

}
