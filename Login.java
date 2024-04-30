package 界面;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class Login implements ActionListener {
	private static String x = "";
	private static int d = 0;
	JFrame frame;
	JPanel panel;
	JRadioButton jrb1;
	JRadioButton jrb2;
	JRadioButton jrb3;
	JTextField jtf;
	JPasswordField jpf;
	JButton jb;

	Login() {

		frame = new JFrame("登陆界面");
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new GridLayout(7, 1));
		// 单选面板
		panel = new JPanel();
		jrb1 = new JRadioButton("管理员", true);
		panel.add(jrb1);
		jrb2 = new JRadioButton("学生");
		panel.add(jrb2);
		jrb3 = new JRadioButton("老师");
		panel.add(jrb3);
		frame.add(panel);
		// jrb1.addActionListener(this);
		// jrb2.addActionListener(this);
		// jrb3.addActionListener(this);
		// 添加单选组
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
		bg.add(jrb3);

		JLabel jl1 = new JLabel("账号");
		frame.add(jl1);
		jtf = new JTextField("");
		frame.add(jtf);

		JLabel jl2 = new JLabel("密码");
		frame.add(jl2);
		jpf = new JPasswordField("");
		frame.add(jpf);
		// 登陆按钮
		jb = new JButton("登陆");
		frame.add(jb);
		jb.addActionListener(this);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		// 选择访问的用户表
		if (jrb1.isSelected()) {
			x = "administrator";
			System.out.println("A");
		} else if (jrb2.isSelected()) {
			x = "student";
			System.out.println("S");
		} else if (jrb3.isSelected()) {
			System.out.println("T");
			x = "teacher";
		}
		// 判断登陆是否成功
		if (e.getSource() == jb) {
			String no = new String(jtf.getText());// 获取账号
			System.out.print(no);
			String pw = new String(jpf.getPassword());// 获取密码
			System.out.print(pw);

			try {
				d = sql.userlogin(no, pw, x);// 调用sql.java文件中登陆函数,成功为1
			} catch (ClassNotFoundException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}

			// System.out.print(d);
			if (d == 1) {
				frame.dispose();// 关闭登陆窗口

				if (x == "administrator")
					new A(no);// 进入管理员界面
				else if (x == "student")
					new S(no);// 进入学生页面
				else if (x == "teacher")
					new T(no);// 进入教师页面

			} else if (d == 0) {
				frame.dispose();// 关闭管理员登陆窗口
				JDialog jd1 = new JDialog(frame, "", true);// 登陆中提示窗口
				jd1.setSize(100, 50);
				jd1.setLocationRelativeTo(null);
				jd1.setResizable(false);

				JLabel jl1 = new JLabel("登陆失败,请重新登陆");
				jd1.add(jl1);
				jd1.setVisible(true);
				new Login();
			}

		}

	}

	public static void main(String args[]) {
		new Login();
	}

}
