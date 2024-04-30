package ����;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class A implements ActionListener {
	JFrame frame;
	JPanel panel_b;// ��ť���
	JButton jb_s;// ����ѧ����ť
	JButton jb_t;
	JButton jb_p;// �������밴ť
	JPanel p;// �������,����3��庯��Ϊ�������

	JPanel p_s() // ����ѧ�����
	{
		JPanel panel_s = new JPanel();
		panel_s.setBackground(Color.orange);
		panel_s.setLayout(new BorderLayout(1, 1));
		// ������Ϣ��
		panel_s.add(BorderLayout.NORTH, new JLabel("����ѧ��"));
		// �в�������
		JScrollPane scrollPane = new JScrollPane();
		String[][] playerInfo = null;
		try {
			playerInfo = sql.show.all("student");
		} catch (ClassNotFoundException | SQLException e) {
		} // usershow��sql����
		String Names[] = { "ѧ��", "����", "����", "�༶", "�Ա�" };// ����һ��ֻ�б�ͷ�ı��ģ��
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
		panel_s.add(BorderLayout.CENTER, scrollPane);
		// �ײ���ť��
		JPanel panel_s_b = new JPanel();
		// ��Ӱ�ť���书��
		JButton adds = new JButton("���");
		adds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.print("add");
				JFrame ad = new JFrame("���");
				ad.setLayout(new BorderLayout(1, 1));
				ad.setSize(500, 100);
				ad.setLocationRelativeTo(null);
				ad.setResizable(false);

				JLabel jl1 = new JLabel("������ӵ���Ϣ");
				ad.add(BorderLayout.NORTH, jl1);
				// ��Ϣ�ı������
				JPanel pjtx = new JPanel();
				JTextField no = new JTextField("ѧ��");
				pjtx.add(no);
				JTextField name = new JTextField("����");
				pjtx.add(name);
				JTextField pw = new JTextField("����");
				pjtx.add(pw);
				JTextField clas = new JTextField("�༶");
				pjtx.add(clas);
				JTextField sex = new JTextField("�Ա�");
				pjtx.add(sex);
				ad.add(pjtx);
				JButton qr = new JButton("ȷ��");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						try {
							sql.add.student(new String(no.getText()), new String(name.getText()),
									new String(pw.getText()), new String(clas.getText()), new String(sex.getText()));
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					}
				});
				ad.add(BorderLayout.SOUTH, qr);
				ad.setVisible(true);
			}
		});
		panel_s_b.add(adds);
		// ���İ�ť���书��
		JButton up = new JButton("����");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.print("up");
				JFrame up = new JFrame("����");
				up.setLayout(new BorderLayout(1, 1));
				up.setSize(500, 100);
				up.setLocationRelativeTo(null);
				up.setResizable(false);

				JLabel jl1 = new JLabel("�����޸ĺ��ȫ������Ϣ");
				up.add(BorderLayout.NORTH, jl1);
				// ��Ϣ�ı������
				JPanel pjtx = new JPanel();
				JTextField no = new JTextField("ѧ��");
				pjtx.add(no);
				JTextField name = new JTextField("����");
				pjtx.add(name);
				JTextField pw = new JTextField("����");
				pjtx.add(pw);
				JTextField clas = new JTextField("�༶");
				pjtx.add(clas);
				JTextField sex = new JTextField("�Ա�");
				pjtx.add(sex);
				up.add(pjtx);
				JButton qr = new JButton("ȷ��");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						try {
							sql.delete.delete("student", new String(no.getText()));
							sql.add.student(new String(no.getText()), new String(name.getText()),
									new String(pw.getText()), new String(clas.getText()), new String(sex.getText()));
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					}
				});
				up.add(BorderLayout.SOUTH, qr);
				up.setVisible(true);
			}
		});
		panel_s_b.add(up);
		// ɾ����ť���书��
		JButton delete = new JButton("ɾ��");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				JFrame delete = new JFrame("ɾ��");
				delete.setLayout(new BorderLayout(1, 1));
				delete.setSize(500, 100);
				delete.setLocationRelativeTo(null);
				delete.setResizable(false);

				JLabel jl1 = new JLabel("����ɾ���ĺ���");
				delete.add(BorderLayout.NORTH, jl1);

				JTextField jtf = new JTextField("");
				delete.add(jtf);

				JButton qr = new JButton("ȷ��");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						try {
							sql.delete.delete("student", new String(jtf.getText()));
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					}
				});
				delete.add(BorderLayout.SOUTH, qr);
				delete.setVisible(true);
			}
		});
		panel_s_b.add(delete);

		panel_s.add(BorderLayout.SOUTH, panel_s_b);

		return panel_s;
	}

	JPanel p_t()// �����ʦ���
	{
		JPanel panel_s = new JPanel();
		panel_s.setBackground(Color.red);
		panel_s.setLayout(new BorderLayout(1, 1));
		panel_s.add(BorderLayout.NORTH, new JLabel("�����ʦ"));
		// ������
		JScrollPane scrollPane = new JScrollPane();
		String[][] playerInfo = null;
		try {
			playerInfo = sql.show.all("teacher");// usershow��sql����
		} catch (ClassNotFoundException | SQLException e) {
		}
		String Names[] = { "��ʦ��", "����", "����" };
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
		panel_s.add(BorderLayout.CENTER, scrollPane);
		//
		// �ײ���ť��
		JPanel panel_s_b = new JPanel();
		// ��Ӱ�ť���书��
		JButton adds = new JButton("���");
		adds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.print("add");
				JFrame ad = new JFrame("���");
				ad.setLayout(new BorderLayout(1, 1));
				ad.setSize(500, 100);
				ad.setLocationRelativeTo(null);
				ad.setResizable(false);

				JLabel jl1 = new JLabel("������ӵ���Ϣ");
				ad.add(BorderLayout.NORTH, jl1);
				// ��Ϣ�ı������
				JPanel pjtx = new JPanel();
				JTextField no = new JTextField("��ʦ��");
				pjtx.add(no);
				JTextField name = new JTextField("����");
				pjtx.add(name);
				JTextField pw = new JTextField("����");
				pjtx.add(pw);
				ad.add(pjtx);
				JButton qr = new JButton("ȷ��");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						try {
							sql.add.user("teacher", new String(no.getText()), new String(name.getText()),
									new String(pw.getText()));
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					}
				});
				ad.add(BorderLayout.SOUTH, qr);
				ad.setVisible(true);
			}
		});
		panel_s_b.add(adds);
		// ���İ�ť���书��
		JButton up = new JButton("����");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.print("up");
				JFrame up = new JFrame("����");
				up.setLayout(new BorderLayout(1, 1));
				up.setSize(500, 100);
				up.setLocationRelativeTo(null);
				up.setResizable(false);

				JLabel jl1 = new JLabel("�����޸ĺ��ȫ������Ϣ");
				up.add(BorderLayout.NORTH, jl1);
				// ��Ϣ�ı������
				JPanel pjtx = new JPanel();
				JTextField no = new JTextField("��ʦ��");
				pjtx.add(no);
				JTextField name = new JTextField("����");
				pjtx.add(name);
				JTextField pw = new JTextField("����");
				pjtx.add(pw);
				up.add(pjtx);
				JButton qr = new JButton("ȷ��");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						try {
							sql.delete.delete("teacher", new String(no.getText()));
							sql.add.user("teacher", new String(no.getText()), new String(name.getText()),
									new String(pw.getText()));
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					}
				});
				up.add(BorderLayout.SOUTH, qr);
				up.setVisible(true);
			}
		});
		panel_s_b.add(up);
		// ɾ����ť���书��
		JButton delete = new JButton("ɾ��");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				JFrame delete = new JFrame("ɾ��");
				delete.setLayout(new BorderLayout(1, 1));
				delete.setSize(500, 100);
				delete.setLocationRelativeTo(null);
				delete.setResizable(false);

				JLabel jl1 = new JLabel("����ɾ���ĺ���");
				delete.add(BorderLayout.NORTH, jl1);

				JTextField jtf = new JTextField("");
				delete.add(jtf);

				JButton qr = new JButton("ȷ��");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						try {
							sql.delete.delete("teacher", new String(jtf.getText()));
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					}
				});
				delete.add(BorderLayout.SOUTH, qr);
				delete.setVisible(true);
			}
		});
		panel_s_b.add(delete);

		panel_s.add(BorderLayout.SOUTH, panel_s_b);

		return panel_s;
	};// ��ʦ���

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
				JLabel jl1 = new JLabel("����������������");
				up.add(BorderLayout.NORTH, jl1);
				JPanel pjtx = new JPanel();
				JTextField pw = new JTextField("����");
				pjtx.add(pw);
				up.add(pjtx);
				JButton qr = new JButton("ȷ��");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						try {
							sql.update.up("update administrator set Apassword ='" + new String(pw.getText())
									+ "' where Ano ='" + no + "'");
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

	A(String no)// ���캯��
	{
		// ��������
		frame = new JFrame("����Ա����ƽ̨");
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout(2, 2));

		// ��ť���
		panel_b = new JPanel();
		panel_b.setLayout(new GridLayout(4, 1, 20, 20));
		JLabel jl1 = new JLabel("����Ա����");
		jl1.setOpaque(true); // ���ñ�����ɫ�����Ƚ�������Ϊ��͸���ģ�Ĭ��͸��
		jl1.setBackground(Color.GRAY);
		jb_s = new JButton("����ѧ����Ϣ");
		jb_t = new JButton("�����ʦ��Ϣ");
		jb_p = new JButton("��������");
		panel_b.add(jl1);
		panel_b.add(jb_s);
		panel_b.add(jb_t);
		panel_b.add(jb_p);
		jb_s.addActionListener(this);
		jb_t.addActionListener(this);
		jb_p.addActionListener(new ActionListener() {// ����ť
			public void actionPerformed(ActionEvent e1) {
				p.removeAll();
				p.add(p_p(no));
				p.setVisible(false);
				p.setVisible(true);
				System.out.println("p");
			}
		});
		frame.add(BorderLayout.WEST, panel_b);

		// �������
		p = new JPanel();
		p.setLayout(new BorderLayout(1, 1));
		frame.add(BorderLayout.CENTER, p);

		// ��ʼ����

		p.add(new JLabel("��ӭ��,����Ա" + no));

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		// ѡ����ʵ��û���
		if (e.getSource() == jb_s) {
			p.removeAll();
			p.add(p_s());
			p.setVisible(false);
			p.setVisible(true);
			System.out.println("s");

		}
		if (e.getSource() == jb_t) {
			p.removeAll();
			p.add(p_t());
			p.setVisible(false);
			p.setVisible(true);
			System.out.println("t");
		}

	}

	public static void main(String args[]) {
		new A("1");// ����
	}
}
