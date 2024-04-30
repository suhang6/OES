package ����;

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

		frame = new JFrame("��½����");
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new GridLayout(7, 1));
		// ��ѡ���
		panel = new JPanel();
		jrb1 = new JRadioButton("����Ա", true);
		panel.add(jrb1);
		jrb2 = new JRadioButton("ѧ��");
		panel.add(jrb2);
		jrb3 = new JRadioButton("��ʦ");
		panel.add(jrb3);
		frame.add(panel);
		// jrb1.addActionListener(this);
		// jrb2.addActionListener(this);
		// jrb3.addActionListener(this);
		// ��ӵ�ѡ��
		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
		bg.add(jrb3);

		JLabel jl1 = new JLabel("�˺�");
		frame.add(jl1);
		jtf = new JTextField("");
		frame.add(jtf);

		JLabel jl2 = new JLabel("����");
		frame.add(jl2);
		jpf = new JPasswordField("");
		frame.add(jpf);
		// ��½��ť
		jb = new JButton("��½");
		frame.add(jb);
		jb.addActionListener(this);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		// ѡ����ʵ��û���
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
		// �жϵ�½�Ƿ�ɹ�
		if (e.getSource() == jb) {
			String no = new String(jtf.getText());// ��ȡ�˺�
			System.out.print(no);
			String pw = new String(jpf.getPassword());// ��ȡ����
			System.out.print(pw);

			try {
				d = sql.userlogin(no, pw, x);// ����sql.java�ļ��е�½����,�ɹ�Ϊ1
			} catch (ClassNotFoundException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}

			// System.out.print(d);
			if (d == 1) {
				frame.dispose();// �رյ�½����

				if (x == "administrator")
					new A(no);// �������Ա����
				else if (x == "student")
					new S(no);// ����ѧ��ҳ��
				else if (x == "teacher")
					new T(no);// �����ʦҳ��

			} else if (d == 0) {
				frame.dispose();// �رչ���Ա��½����
				JDialog jd1 = new JDialog(frame, "", true);// ��½����ʾ����
				jd1.setSize(100, 50);
				jd1.setLocationRelativeTo(null);
				jd1.setResizable(false);

				JLabel jl1 = new JLabel("��½ʧ��,�����µ�½");
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
