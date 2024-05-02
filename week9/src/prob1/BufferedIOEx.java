package prob1;

import java.io.*;
import java.util.Scanner;

public class BufferedIOEx {

	public static void main(String[] args) {
		System.out.println("학번 : 22140038, 이름 : 임지민\n");
		
		FileReader fin = null;
		int c;
		try {
			fin = new FileReader("c:\\Temp\\test2.txt");
			BufferedOutputStream out = new BufferedOutputStream(System.out, 5);
			while ((c = fin.read()) != -1) {
				out.write(c);
			}
			new Scanner(System.in).nextLine();
			out.flush();
			fin.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
