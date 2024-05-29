import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TodoItem {	// 할 일 목록을 만들기 위한 항목
	private String task;	// task
	private boolean isComplete;		// task 완료 여부
	
	public TodoItem(String task) {
		this.task = task;
		this.isComplete = false;
	}
	
	public boolean isComplete() {	// task 완료 여부 반환
		return isComplete;
	}
	
	public void setComplete(boolean Complete) {		// task 완료 여부 바꿀 수 있음 
		isComplete = Complete;
	}
	
	public String getTask() {		// task 반환
		return task;
	}
}

public class TodoList extends JFrame {	// task List 프로그램 만들기
	private DefaultListModel<TodoItem> model;
	private JList<TodoItem> todoList;
	private JButton add_button, remove_button, complete_button;
	private JTextField task_field;
	private ImageIcon checked_icon, unchecked_icon;
	
	public TodoList() {
		setTitle("[ My Todo List ]");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
        checked_icon = resizeIcon(new ImageIcon("images/checked.png"), 20, 20);		// 체크박스 아이콘
        unchecked_icon = resizeIcon(new ImageIcon("images/unchecked.png"), 20, 20);		// 빈 체크박스 아이콘
       
		model = new DefaultListModel<>();
        todoList = new JList<>(model);
		task_field = new JTextField(20);
		add_button = new JButton("추가");
		remove_button = new JButton("제거");
		complete_button = new JButton("완료");
		
		
		add_button.addActionListener(new ActionListener() {		// '추가' 버튼
			public void actionPerformed(ActionEvent e) {
				String task = task_field.getText();
				if (!task.isEmpty()) {		// task 필드가 비어있어 있지 않으면 새로운 task 추가 후 task 필드를 다시 빈칸으로 만들기
					model.addElement(new TodoItem(task));
					task_field.setText("");
				}
			}
		});
		
		remove_button.addActionListener(new ActionListener() {		// '제거' 버튼
			public void actionPerformed(ActionEvent e) {
				int index = todoList.getSelectedIndex();
				if (index != -1) {		// 선택된 항목이 있으면 '제거'
					model.remove(index);
				}
			}
		});
		
		complete_button.addActionListener(new ActionListener() {	// '완료' 버튼
			public void actionPerformed(ActionEvent e) {
				int index = todoList.getSelectedIndex();
				if (index != -1) {		// 선택된 항목이 있으면 그 항목을을 완료 항목에 추가 후 다시 그리기
					TodoItem item = model.getElementAt(index);
					item.setComplete(!item.isComplete());
					todoList.repaint();
				}
			}
		});

        todoList.setCellRenderer(new DefaultListCellRenderer() {	// task를 완료해서 완료 버튼을 누를 경우 체크박스, 빈 체크박스가 번갈아가며 뜨도록 해주기
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof TodoItem) {
                    TodoItem item = (TodoItem) value;
                    label.setText(item.getTask());
                    if (item.isComplete()) {	// 완료되었다면 체크박스 아이콘, 완료되지 않았다면 빈 체크박스 아이콘이 뜨도록 해주기
                        label.setIcon(checked_icon);
                    } 
                    else {
                        label.setIcon(unchecked_icon);
                    }
                }
                return label;
            }
        });
                
		JPanel toppenel = new JPanel();		// 상단 패널은 task 필드와 '추가' 버튼으로 구성
		toppenel.add(task_field);
		toppenel.add(add_button);
		
		JPanel bottompenel = new JPanel();		// 하단 패널은 '제거' 버튼과 '완료'버튼으로 구성
		bottompenel.add(remove_button);
		bottompenel.add(complete_button);
		
		add(toppenel, BorderLayout.NORTH);
		add(new JScrollPane(todoList), BorderLayout.CENTER);	// 상단 패널과 하단 패널 사이에는 스크롤바가 있는 todo 리스트 패널을 더함
		add(bottompenel, BorderLayout.SOUTH);
		
		setVisible(true);
	}

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {	// 아이콘의 크기를 조절하기
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
	
	public static void main(String[] args) {
		new TodoList();
	}
}