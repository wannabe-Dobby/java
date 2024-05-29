package prob2and3;

import javax.swing.*;
import java.awt.*;

public class TabbedJava extends JFrame {
	public TabbedJava() {
		setTitle("자바란?");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		JTabbedPane pane = createTabbedPane();
		c.add(pane, BorderLayout.CENTER);
		setSize(500,300);
		setVisible(true);
	}
	
	private JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane(JTabbedPane.LEFT);
		pane.addTab("자바의 특성", new MyPanel(1));
		pane.addTab("자바의 장단점", new MyPanel(2));
		pane.addTab("자바의 역사", new MyPanel(3));
		return pane;	
	}
	class MyPanel extends JPanel {
		public MyPanel(int no) {
			JTextArea ta = new JTextArea(10, 30);
			if (no == 1) {
				// ta.append("자바의 특성은 \n");
				// ta.append("바이트코드이다.\n");
				ta.append("자바의 주요 특성 중 하나는 객체 지향 프로그래밍(OOP) 패러다임을 따른다는 점입니다.\n"
						+ "이는 코드의 재사용성과 유지보수성을 높이는 데 큰 도움을 줍니다.\n"
						+ "또한, 자바는 강력한 메모리 관리 기능을 제공하며, 가비지 컬렉션을 통해 개발자가 메모리 관리를 직접 하지 않아도 됩니다.\n"
						+ "멀티스레딩을 지원하여 동시에 여러 작업을 처리할 수 있는 점도 중요한 특징입니다.");
			}
			else if(no == 2) {
				// ta.append("자바의 장단점은 \n");
				// ta.append("플랫폼 독립적이다.\n");
				ta.append("자바의 장점으로는 플랫폼 독립성과 강력한 커뮤니티 지원을 들 수 있습니다.\n"
						+ "또한, 자바는 다양한 라이브러리와 프레임워크를 제공하여 개발자가 다양한 애플리케이션을 쉽게 개발할 수 있도록 돕습니다.\n"
						+ "보안 기능도 잘 갖추어져 있어, 네트워크 애플리케이션 개발에 적합합니다.\n"
						+ "자바는 다양한 도구와 IDE(통합 개발 환경)를 지원하여 개발 생산성을 높여줍니다.");
			}
			else {
				// ta.append("자바의 역사는\n");
				// ta.append("C로부터 시작되어.....\n");
				ta.append("자바는 오랜 역사를 가지고 있으며, 꾸준히 발전해왔습니다.\n"
						+ "1990년대 초반, 제임스 고슬링과 그의 팀이 가전제품을 제어할 목적으로 개발을 시작했으나,\n"
						+ "인터넷의 급속한 성장과 함께 자바는 웹 애플리케이션 개발의 표준으로 자리잡았습니다.\n"
						+ "이후 지속적인 업데이트와 커뮤니티의 기여로 자바는 현재도 널리 사용되는 프로그래밍 언어 중 하나입니다.");
			}
			add(ta);
		}
	}

	public static void main(String [] args) {
		new TabbedJava();
	}
} 