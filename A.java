package 界面;

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
	JPanel panel_b;// 按钮面板
	JButton jb_s;// 管理学生按钮
	JButton jb_t;
	JButton jb_p;// 更改密码按钮
	JPanel p;// 功能面板,以下3面板函数为其子面板

	JPanel p_s() // 管理学生面板
	{
		JPanel panel_s = new JPanel();
		panel_s.setBackground(Color.orange);
		panel_s.setLayout(new BorderLayout(1, 1));
		// 顶部信息条
		panel_s.add(BorderLayout.NORTH, new JLabel("管理学生"));
		// 中部表格面板
		JScrollPane scrollPane = new JScrollPane();
		String[][] playerInfo = null;
		try {
			playerInfo = sql.show.all("student");
		} catch (ClassNotFoundException | SQLException e) {
		} // usershow是sql函数
		String Names[] = { "学号", "姓名", "密码", "班级", "性别" };// 创建一个只有表头的表格模型
		DefaultTableModel defaultTableModel = new DefaultTableModel(null, Names);
		// 将playerInfo中非空元素插入表中
		for (String[] x : playerInfo) {
			if (x[0] != null) {
				defaultTableModel.addRow(x);
			}
		}
		JTable table = new JTable(defaultTableModel);
		// 设置单元格中的文字居中 非表头单元格
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		scrollPane.setViewportView(table);
		panel_s.add(BorderLayout.CENTER, scrollPane);
		// 底部按钮板
		JPanel panel_s_b = new JPanel();
		// 添加按钮及其功能
		JButton adds = new JButton("添加");
		adds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.print("add");
				JFrame ad = new JFrame("添加");
				ad.setLayout(new BorderLayout(1, 1));
				ad.setSize(500, 100);
				ad.setLocationRelativeTo(null);
				ad.setResizable(false);

				JLabel jl1 = new JLabel("输入添加的信息");
				ad.add(BorderLayout.NORTH, jl1);
				// 信息文本框面板
				JPanel pjtx = new JPanel();
				JTextField no = new JTextField("学号");
				pjtx.add(no);
				JTextField name = new JTextField("名字");
				pjtx.add(name);
				JTextField pw = new JTextField("密码");
				pjtx.add(pw);
				JTextField clas = new JTextField("班级");
				pjtx.add(clas);
				JTextField sex = new JTextField("性别");
				pjtx.add(sex);
				ad.add(pjtx);
				JButton qr = new JButton("确认");
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
		// 更改按钮及其功能
		JButton up = new JButton("更改");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.print("up");
				JFrame up = new JFrame("更改");
				up.setLayout(new BorderLayout(1, 1));
				up.setSize(500, 100);
				up.setLocationRelativeTo(null);
				up.setResizable(false);

				JLabel jl1 = new JLabel("输入修改后的全部的信息");
				up.add(BorderLayout.NORTH, jl1);
				// 信息文本框面板
				JPanel pjtx = new JPanel();
				JTextField no = new JTextField("学号");
				pjtx.add(no);
				JTextField name = new JTextField("名字");
				pjtx.add(name);
				JTextField pw = new JTextField("密码");
				pjtx.add(pw);
				JTextField clas = new JTextField("班级");
				pjtx.add(clas);
				JTextField sex = new JTextField("性别");
				pjtx.add(sex);
				up.add(pjtx);
				JButton qr = new JButton("确认");
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
		// 删除按钮及其功能
		JButton delete = new JButton("删除");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				JFrame delete = new JFrame("删除");
				delete.setLayout(new BorderLayout(1, 1));
				delete.setSize(500, 100);
				delete.setLocationRelativeTo(null);
				delete.setResizable(false);

				JLabel jl1 = new JLabel("输入删除的号码");
				delete.add(BorderLayout.NORTH, jl1);

				JTextField jtf = new JTextField("");
				delete.add(jtf);

				JButton qr = new JButton("确认");
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

	JPanel p_t()// 管理教师面板
	{
		JPanel panel_s = new JPanel();
		panel_s.setBackground(Color.red);
		panel_s.setLayout(new BorderLayout(1, 1));
		panel_s.add(BorderLayout.NORTH, new JLabel("管理教师"));
		// 表格面板
		JScrollPane scrollPane = new JScrollPane();
		String[][] playerInfo = null;
		try {
			playerInfo = sql.show.all("teacher");// usershow是sql函数
		} catch (ClassNotFoundException | SQLException e) {
		}
		String Names[] = { "教师号", "姓名", "密码" };
		// 创建一个只有表头的表格模型
		DefaultTableModel defaultTableModel = new DefaultTableModel(null, Names);
		// 将playerInfo中非空元素插入表中
		for (String[] x : playerInfo) {
			if (x[0] != null) {
				defaultTableModel.addRow(x);
			}
		}
		JTable table = new JTable(defaultTableModel);
		// 设置单元格中的文字居中 非表头单元格
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		scrollPane.setViewportView(table);
		panel_s.add(BorderLayout.CENTER, scrollPane);
		//
		// 底部按钮板
		JPanel panel_s_b = new JPanel();
		// 添加按钮及其功能
		JButton adds = new JButton("添加");
		adds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.print("add");
				JFrame ad = new JFrame("添加");
				ad.setLayout(new BorderLayout(1, 1));
				ad.setSize(500, 100);
				ad.setLocationRelativeTo(null);
				ad.setResizable(false);

				JLabel jl1 = new JLabel("输入添加的信息");
				ad.add(BorderLayout.NORTH, jl1);
				// 信息文本框面板
				JPanel pjtx = new JPanel();
				JTextField no = new JTextField("教师号");
				pjtx.add(no);
				JTextField name = new JTextField("名字");
				pjtx.add(name);
				JTextField pw = new JTextField("密码");
				pjtx.add(pw);
				ad.add(pjtx);
				JButton qr = new JButton("确认");
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
		// 更改按钮及其功能
		JButton up = new JButton("更改");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.print("up");
				JFrame up = new JFrame("更改");
				up.setLayout(new BorderLayout(1, 1));
				up.setSize(500, 100);
				up.setLocationRelativeTo(null);
				up.setResizable(false);

				JLabel jl1 = new JLabel("输入修改后的全部的信息");
				up.add(BorderLayout.NORTH, jl1);
				// 信息文本框面板
				JPanel pjtx = new JPanel();
				JTextField no = new JTextField("教师号");
				pjtx.add(no);
				JTextField name = new JTextField("名字");
				pjtx.add(name);
				JTextField pw = new JTextField("密码");
				pjtx.add(pw);
				up.add(pjtx);
				JButton qr = new JButton("确认");
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
		// 删除按钮及其功能
		JButton delete = new JButton("删除");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				JFrame delete = new JFrame("删除");
				delete.setLayout(new BorderLayout(1, 1));
				delete.setSize(500, 100);
				delete.setLocationRelativeTo(null);
				delete.setResizable(false);

				JLabel jl1 = new JLabel("输入删除的号码");
				delete.add(BorderLayout.NORTH, jl1);

				JTextField jtf = new JTextField("");
				delete.add(jtf);

				JButton qr = new JButton("确认");
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
	};// 教师面板

	JPanel p_p(String no) // 修改密码面板
	{
		JPanel p = new JPanel();

		JButton up = new JButton("更改");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.print("up");
				JFrame up = new JFrame("更改");
				up.setLayout(new BorderLayout(1, 1));
				up.setSize(500, 100);
				up.setLocationRelativeTo(null);
				up.setResizable(false);
				JLabel jl1 = new JLabel("输入您的新与密码");
				up.add(BorderLayout.NORTH, jl1);
				JPanel pjtx = new JPanel();
				JTextField pw = new JTextField("密码");
				pjtx.add(pw);
				up.add(pjtx);
				JButton qr = new JButton("确认");
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

	A(String no)// 构造函数
	{
		// 窗口设置
		frame = new JFrame("管理员操作平台");
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout(2, 2));

		// 按钮面板
		panel_b = new JPanel();
		panel_b.setLayout(new GridLayout(4, 1, 20, 20));
		JLabel jl1 = new JLabel("管理员界面");
		jl1.setOpaque(true); // 设置背景颜色必须先将它设置为不透明的，默认透明
		jl1.setBackground(Color.GRAY);
		jb_s = new JButton("管理学生信息");
		jb_t = new JButton("管理教师信息");
		jb_p = new JButton("更改密码");
		panel_b.add(jl1);
		panel_b.add(jb_s);
		panel_b.add(jb_t);
		panel_b.add(jb_p);
		jb_s.addActionListener(this);
		jb_t.addActionListener(this);
		jb_p.addActionListener(new ActionListener() {// 交卷按钮
			public void actionPerformed(ActionEvent e1) {
				p.removeAll();
				p.add(p_p(no));
				p.setVisible(false);
				p.setVisible(true);
				System.out.println("p");
			}
		});
		frame.add(BorderLayout.WEST, panel_b);

		// 功能面板
		p = new JPanel();
		p.setLayout(new BorderLayout(1, 1));
		frame.add(BorderLayout.CENTER, p);

		// 初始界面

		p.add(new JLabel("欢迎你,管理员" + no));

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		// 选择访问的用户表
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
		new A("1");// 运行
	}
}
