package ����;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;

public class exam {

	private JFrame frame;
	JScrollPane scrollPane = new JScrollPane();

	JTree tree;
	ButtonGroup group[];// ÿ�ⰴť���Ӧһ��group
	String[][] Qselect = { { "ѡ��", "A", "B", "C", "D", "��" } };
	String[][] Qfill = { { "���", "��" } };

	public exam(String sno, String pno) {

		try {
			Qselect = sql.show.select("SELECT qcontent,a,b,c,d,qanswer FROM selectq where pno='" + pno + "'");
			Qfill = sql.show.select("SELECT qcontent,qanswer FROM fillq where pno='" + pno + "';");

		} catch (ClassNotFoundException | SQLException e2) {
			// TODO �Զ����ɵ� catch ��
			e2.printStackTrace();
		}

		frame = new JFrame("���Խ���");
		frame.setBounds(100, 100, 609, 471);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel_t = new JPanel();// ��д�����
		panel_t.setBounds(0, 0, 700, 419);
		frame.getContentPane().add(panel_t);
		panel_t.setLayout(null);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(0, 0, 594, 409);
		panel_t.add(splitPane);

		JLabel lab;
		JRadioButton jRadioButton[][];
		JPanel panel = new JPanel();// ��Ŀ���

		// ѡ��
		group = new ButtonGroup[Qselect.length];
		jRadioButton = new JRadioButton[group.length][4];// 4����ѡ
		// JPanel panel = new JPanel(new GridLayout(6, 1, 0, 0));
		for (int i = 0; i < group.length; i++) {
			JPanel p = new JPanel(new GridLayout(5, 1, 0, 0));
			lab = new JLabel(i + 1 + "��" + Qselect[i][0]);
			group[i] = new ButtonGroup();
			p.add(lab);
			for (int j = 0; j < jRadioButton[i].length; j++) {
				jRadioButton[i][j] = new JRadioButton(Qselect[i][j + 1]);
				group[i].add(jRadioButton[i][j]);
				p.add(jRadioButton[i][j]);
			}
			panel.add(p);
		}
		scrollPane.setViewportView(panel);

		// listener.setButtonGroup(group);
		// �ж�
		JTextField[] jtx = new JTextField[Qfill.length];
		panel.setLayout(new GridLayout(group.length + jtx.length + 1, 1, 0, 0));
		for (int i = 0; i < jtx.length; i++) {
			JPanel p = new JPanel(new GridLayout(3, 1, 0, 0));
			lab = new JLabel(i + 1 + "��" + Qfill[i][0]);
			// lab.addActionListener(listener);
			p.add(lab);
			jtx[i] = new JTextField("");
			p.add(jtx[i]);

			// jRadioButton[j].addActionListener(listener);

			panel.add(p);
		}
		scrollPane.setViewportView(panel);
		JPanel panel_south = new JPanel();
		JButton jButton = new JButton("����");// ����ť
		panel_south.add(jButton);
		jButton.addActionListener(new ActionListener() {// ����ť
			public void actionPerformed(ActionEvent e1) {
				int correct = 0;
				// ��ѡ�����
				System.out.println(group.length);
				for (int i = 0; i < group.length; i++) {
					try {
						String res;
						if (jRadioButton[i][0].isSelected()) {
							res = "A";
						} else if (jRadioButton[i][1].isSelected()) {
							res = "B";
						} else if (jRadioButton[i][2].isSelected()) {
							res = "C";
						} else if (jRadioButton[i][3].isSelected()) {
							res = "D";
						} else {
							res = "null";
						}

						if (Qselect[i][5].equals(res)) {
							correct += 1;
						}
						System.out.println(res + Qselect[i][5] + correct);
					} catch (Exception e21) {
						// TODO: handle exception
						i++;
					}
				}
				// ��������
				System.out.println(jtx.length);
				for (int i = 0; i < jtx.length; i++) {
					try {

						if (Qfill[i][1].equals(jtx[i].getText())) {
							correct += 1;
						}
						System.out.println(jtx[i].getText() + Qfill[i][1] + correct);
					} catch (Exception e21) {
						// TODO: handle exception
						i++;
					}
				}
				int mark = 100 * correct / (jtx.length + group.length);
				JOptionPane.showMessageDialog(null, "�ɼ�:" + mark, "�ɼ�", JOptionPane.WARNING_MESSAGE);
				System.out.println(sno + pno + mark);
				try {
					sql.update.grade(sno, pno, mark);// �޸ķ���
				} catch (ClassNotFoundException | SQLException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
		});
		panel.add(panel_south);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		splitPane.setRightComponent(scrollPane);
		splitPane.setLeftComponent(null);
		frame.setVisible(true);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new exam("001", "1");

	}
}
