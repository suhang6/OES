package ����;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class S {
	JFrame frame;
	JPanel panel_b;// ��ť���
	JButton jb_e;// �ҵĿ��԰�ť
	JButton jb_pw;// �������밴ť
	JPanel p;// �������,����3��庯��Ϊ�������
	String no;

	JPanel p_exam(String sno)// �ҵĿ������
	{
		JPanel panel_e = new JPanel();
		panel_e.setBackground(Color.red);
		panel_e.setLayout(new BorderLayout(1, 1));
		Calendar c = Calendar.getInstance();
		panel_e.add(BorderLayout.NORTH, new JLabel("�ҵĿ���    			" + c.get(Calendar.HOUR_OF_DAY) + ":"
				+ c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND)));
		// ������
		JScrollPane scrollPane = new JScrollPane();
		String[][] playerInfo = null;
		try {
			playerInfo = sql.show.select("SELECT * FROM P_E WHERE sno='" + sno + "'");// usershow��sql����
		} catch (ClassNotFoundException | SQLException e) {
		}
		String Names[] = { "ѧ��", "����", "��Ŀ", "�����", "����ʱ��", "�ɼ�" };
		// ����һ��ֻ�б�ͷ�ı��ģ��
		DefaultTableModel defaultTableModel = new DefaultTableModel(null, Names);
		// ��playerInfo�зǿ�Ԫ�ز������
		for (String[] x : playerInfo) {
			if (x[0] != null) {
				defaultTableModel.addRow(x);
			}
		}
		JTable table = new JTable(defaultTableModel);
		// ���õ�Ԫ���е����־��� �Ǳ�ͷ��Ԫ��
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		scrollPane.setViewportView(table);
		panel_e.add(BorderLayout.CENTER, scrollPane);
		//
		// �ײ���ť��
		JPanel panel_e_b = new JPanel();
		// ��ʼ���԰�ť���书��
		JButton adds = new JButton("��ʼ����");
		adds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				new exam(sno, "1");
			}
		});
		panel_e_b.add(adds);

		panel_e.add(BorderLayout.SOUTH, panel_e_b);

		return panel_e;
	};// ������

	JPanel p_p(String no) // �޸��������
	{
		JPanel p = new JPanel();

		JButton up = new JButton("����");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.print("up");
				JFrame up = new JFrame("����");
				up.setLayout(new BorderLayout(1, 1));
				up.setSize(500, 100);
				up.setLocationRelativeTo(null);
				up.setResizable(false);

				JLabel jl1 = new JLabel("��������������");
				up.add(BorderLayout.NORTH, jl1);
				JPanel pjtx = new JPanel();
				JTextField pw = new JTextField("����");
				pjtx.add(pw);
				up.add(pjtx);
				JButton qr = new JButton("ȷ��");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						try {
							sql.update.up("update student set spassword ='" + new String(pw.getText())
									+ "' where sno ='" + no + "'");
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					}
				});
				up.add(BorderLayout.SOUTH, qr);
				up.setVisible(true);
			}
		});
		p.add(up);

		return p;

	}

	S(String no)// ���캯��
	{
		// ��������
		frame = new JFrame("ѧ������ƽ̨");
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout(2, 2));

		// ��ť���
		panel_b = new JPanel();
		panel_b.setLayout(new GridLayout(4, 1, 20, 20));
		JLabel jl1 = new JLabel("ѧ������");
		jl1.setOpaque(true); // ���ñ�����ɫ�����Ƚ�������Ϊ��͸���ģ�Ĭ��͸��
		jl1.setBackground(Color.GRAY);

		jb_e = new JButton("�ҵĿ���");
		jb_pw = new JButton("��������");
		panel_b.add(jl1);

		panel_b.add(jb_e);
		panel_b.add(jb_pw);

		jb_e.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				p.removeAll();
				System.out.print(no);
				p.add(p_exam(no));
				p.setVisible(false);
				p.setVisible(true);
				System.out.println("e");

			}
		});
		jb_pw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {

				p.removeAll();
				p.add(p_p(no));
				p.setVisible(false);
				p.setVisible(true);
				System.out.println("pw");
			}
		});
		frame.add(BorderLayout.WEST, panel_b);

		// �������
		p = new JPanel();
		p.setLayout(new BorderLayout(1, 1));
		frame.add(BorderLayout.CENTER, p);

		// ��ʼ����

		p.add(new JLabel("��ӭ��,ѧ��" + no));

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String args[]) {
		new S("006");// ����
	}
}
