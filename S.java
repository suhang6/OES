package 界面;

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
	JPanel panel_b;// 按钮面板
	JButton jb_e;// 我的考试按钮
	JButton jb_pw;// 更改密码按钮
	JPanel p;// 功能面板,以下3面板函数为其子面板
	String no;

	JPanel p_exam(String sno)// 我的考试面板
	{
		JPanel panel_e = new JPanel();
		panel_e.setBackground(Color.red);
		panel_e.setLayout(new BorderLayout(1, 1));
		Calendar c = Calendar.getInstance();
		panel_e.add(BorderLayout.NORTH, new JLabel("我的考试    			" + c.get(Calendar.HOUR_OF_DAY) + ":"
				+ c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND)));
		// 表格面板
		JScrollPane scrollPane = new JScrollPane();
		String[][] playerInfo = null;
		try {
			playerInfo = sql.show.select("SELECT * FROM P_E WHERE sno='" + sno + "'");// usershow是sql函数
		} catch (ClassNotFoundException | SQLException e) {
		}
		String Names[] = { "学号", "组卷号", "科目", "组卷名", "考试时间", "成绩" };
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
		panel_e.add(BorderLayout.CENTER, scrollPane);
		//
		// 底部按钮板
		JPanel panel_e_b = new JPanel();
		// 开始考试按钮及其功能
		JButton adds = new JButton("开始考试");
		adds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				new exam(sno, "1");
			}
		});
		panel_e_b.add(adds);

		panel_e.add(BorderLayout.SOUTH, panel_e_b);

		return panel_e;
	};// 组卷面板

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

				JLabel jl1 = new JLabel("输入您的新密码");
				up.add(BorderLayout.NORTH, jl1);
				JPanel pjtx = new JPanel();
				JTextField pw = new JTextField("密码");
				pjtx.add(pw);
				up.add(pjtx);
				JButton qr = new JButton("确认");
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

	S(String no)// 构造函数
	{
		// 窗口设置
		frame = new JFrame("学生操作平台");
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout(2, 2));

		// 按钮面板
		panel_b = new JPanel();
		panel_b.setLayout(new GridLayout(4, 1, 20, 20));
		JLabel jl1 = new JLabel("学生界面");
		jl1.setOpaque(true); // 设置背景颜色必须先将它设置为不透明的，默认透明
		jl1.setBackground(Color.GRAY);

		jb_e = new JButton("我的考试");
		jb_pw = new JButton("更改密码");
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

		// 功能面板
		p = new JPanel();
		p.setLayout(new BorderLayout(1, 1));
		frame.add(BorderLayout.CENTER, p);

		// 初始界面

		p.add(new JLabel("欢迎你,学生" + no));

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String args[]) {
		new S("006");// 运行
	}
}
