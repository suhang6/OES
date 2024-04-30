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

public class T implements ActionListener {
	JFrame frame;
	JPanel panel_b;// 按钮面板
	JButton jb_q;// 管理题库按钮
	JButton jb_p;// 管理组卷按钮
	JButton jb_s;// 查看学生成绩按钮
	JButton jb_pw;// 更改密码按钮

	JPanel p;// 功能面板,以下3面板函数为其子面板

	JPanel p_q() // 管理题目面板
	{
		JPanel panel_s = new JPanel();
		panel_s.setBackground(Color.orange);
		panel_s.setLayout(new BorderLayout(1, 1));
		// 顶部信息条
		panel_s.add(BorderLayout.NORTH, new JLabel("管理题目"));
		// 中部表格面板
		JScrollPane scrollPane = new JScrollPane();
		String[][] playerInfo = null;
		try {
			playerInfo = sql.show.all("question");
		} catch (ClassNotFoundException | SQLException e) {
		} // usershow是sql函数
		String Names[] = { "题号", "题目", "A", "B", "C", "D", "答案", "题型", "科目" };// 创建一个只有表头的表格模型
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
				JTextField no = new JTextField("题号");
				pjtx.add(no);
				JTextField con = new JTextField("内容");
				pjtx.add(con);
				JTextField a = new JTextField("A");
				pjtx.add(a);
				JTextField b = new JTextField("B");
				pjtx.add(b);
				JTextField c = new JTextField("C");
				pjtx.add(c);
				JTextField d = new JTextField("D");
				pjtx.add(d);
				JTextField an = new JTextField("答案");
				pjtx.add(an);
				JTextField type = new JTextField("题型");
				pjtx.add(type);
				JTextField cname = new JTextField("科目");
				pjtx.add(cname);
				ad.add(pjtx);
				JButton qr = new JButton("确认");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						try {
							sql.add.question(new String(no.getText()), new String(con.getText()),
									new String(a.getText()), new String(b.getText()), new String(c.getText()),
									new String(d.getText()), new String(an.getText()), new String(type.getText()),
									new String(cname.getText()));
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
				JTextField no = new JTextField("题号");
				pjtx.add(no);
				JTextField con = new JTextField("内容");
				pjtx.add(con);
				JTextField a = new JTextField("A");
				pjtx.add(a);
				JTextField b = new JTextField("B");
				pjtx.add(b);
				JTextField c = new JTextField("C");
				pjtx.add(c);
				JTextField d = new JTextField("D");
				pjtx.add(d);
				JTextField an = new JTextField("答案");
				pjtx.add(an);
				JTextField type = new JTextField("题型");
				pjtx.add(type);
				JTextField cname = new JTextField("科目");
				pjtx.add(cname);
				up.add(pjtx);
				JButton qr = new JButton("确认");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						try {
							sql.delete.delete("question", new String(no.getText()));
							sql.add.question(new String(no.getText()), new String(con.getText()),
									new String(a.getText()), new String(b.getText()), new String(c.getText()),
									new String(d.getText()), new String(an.getText()), new String(type.getText()),
									new String(cname.getText()));
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
							sql.delete.delete("question", new String(jtf.getText()));
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

	JPanel p_p()// 管理组卷面板
	{
		JPanel panel_s = new JPanel();
		panel_s.setBackground(Color.red);
		panel_s.setLayout(new BorderLayout(1, 1));
		panel_s.add(BorderLayout.NORTH, new JLabel("管理组卷"));
		// 表格面板
		JScrollPane scrollPane = new JScrollPane();
		String[][] playerInfo = null;
		try {
			playerInfo = sql.show.all("paper");// usershow是sql函数
		} catch (ClassNotFoundException | SQLException e) {
		}
		String Names[] = { "组卷号", "组卷名", "科目" };
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
				JTextField no = new JTextField("组卷号");
				pjtx.add(no);
				JTextField name = new JTextField("名字");
				pjtx.add(name);
				JTextField cname = new JTextField("科目");
				pjtx.add(cname);
				ad.add(pjtx);
				JButton qr = new JButton("确认");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						try {
							sql.add.paper(no.getText(), name.getText(), cname.getText());
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
				JTextField no = new JTextField("组卷号");
				pjtx.add(no);
				JTextField name = new JTextField("名字");
				pjtx.add(name);
				JTextField cname = new JTextField("科目");
				pjtx.add(cname);
				up.add(pjtx);
				JButton qr = new JButton("确认");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						try {
							sql.delete.delete("paper", new String(no.getText()));
							sql.add.paper(no.getText(), name.getText(), cname.getText());
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
							sql.delete.delete("paper", new String(jtf.getText()));
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

		// 组卷题目更改按钮及其功能
		JButton form = new JButton("组卷题目更改");
		form.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.print("form");
				JFrame form = new JFrame("更改");
				form.setLayout(new BorderLayout(1, 1));
				form.setSize(500, 100);
				form.setLocationRelativeTo(null);
				form.setResizable(false);

				JLabel jl1 = new JLabel("输入要修改的组卷的信息");
				form.add(BorderLayout.NORTH, jl1);
				// 信息文本框面板
				JTextField no = new JTextField("组卷号");
				form.add(BorderLayout.CENTER, no);
				JButton qr = new JButton("确认");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						form.setVisible(false);
						String[][] playerInfo = null;
						try {
							playerInfo = sql.show.select(
									"SELECT form.qno,qcontent,a,b,c,d,qanswer,qtype,cname FROM form,question WHERE form.qno=question.qno AND pno='"
											+ no.getText() + "'");
						} catch (ClassNotFoundException | SQLException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}

						// 子界面
						JFrame show = new JFrame(no.getText() + "试卷题目组成");
						show.setLayout(new BorderLayout(1, 1));
						show.setSize(500, 500);
						show.setLocationRelativeTo(null);
						show.setResizable(false);
						// 表格面板
						JScrollPane scrollPane = new JScrollPane();
						String Names[] = { "题号", "题目", "A", "B", "C", "D", "答案", "题型", "科目" };
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

						show.add(BorderLayout.CENTER, scrollPane);
						// 底部按钮板
						JPanel panel_pf = new JPanel();
						// 添加按钮及其功能
						JButton adds = new JButton("添加");
						adds.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e1) {
								System.out.print("add");
								JFrame ad = new JFrame("添加");
								ad.setLayout(new BorderLayout(1, 1));
								ad.setSize(500, 300);
								ad.setLocationRelativeTo(null);
								ad.setResizable(false);
								// 顶部信息窗
								JLabel jl1 = new JLabel("输入添加的信息");
								ad.add(BorderLayout.NORTH, jl1);

								String[][] playerInfo = null;
								try {
									playerInfo = sql.show.select(
											"SELECT qno,qcontent,a,b,c,d,qanswer,qtype,question.cname FROM paper,question WHERE paper.cname=question.cname AND pno='"
													+ no.getText() + "'");
								} catch (ClassNotFoundException | SQLException e) {
									// TODO 自动生成的 catch 块
									e.printStackTrace();
								}
								// 表格面板
								JScrollPane scrollPane = new JScrollPane();
								String Names[] = { "题号", "题目", "A", "B", "C", "D", "答案", "题型", "科目" };
								// 创建一个只有表头的表格模型
								DefaultTableModel defaultTableModel = new DefaultTableModel(null, Names);
								// 将playerInfo中非空元素插入表中
								for (String[] x : playerInfo) {
									if (x[0] != null) {
										defaultTableModel.addRow(x);
									}
								}

								ad.add(scrollPane);

								JTable table = new JTable(defaultTableModel);
								// 设置单元格中的文字居中 非表头单元格
								DefaultTableCellRenderer r = new DefaultTableCellRenderer();
								r.setHorizontalAlignment(JLabel.CENTER);
								table.setDefaultRenderer(Object.class, r);
								scrollPane.setViewportView(table);

								// 底部文本框+按钮面板
								JPanel panel_ad = new JPanel();
								JTextField qno = new JTextField("题号");
								panel_ad.add(qno);
								JButton qr = new JButton("确认");
								panel_ad.add(qr);
								ad.add(BorderLayout.SOUTH, panel_ad);
								qr.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e1) {
										try {
											sql.add.form(no.getText(), qno.getText());
										} catch (ClassNotFoundException | SQLException e) {
											e.printStackTrace();
										}
									}
								});

								ad.setVisible(true);

							}
						});
						panel_pf.add(adds);
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

								JLabel jl1 = new JLabel("输入被更改的题号与更改后的题号");
								up.add(BorderLayout.NORTH, jl1);
								// 信息文本框面板
								JPanel pjtx = new JPanel();
								JTextField qno1 = new JTextField("组卷号");
								pjtx.add(qno1);
								JTextField qno2 = new JTextField("名字");
								pjtx.add(qno2);
								up.add(pjtx);
								JButton qr = new JButton("确认");
								qr.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e1) {
										try {
											sql.delete.deleteword("DELETE FROM form WHERE pno='" + no.getText()
													+ "' AND qno='" + qno1.getText() + "'");
											sql.add.form(no.getText(), qno2.getText());
										} catch (ClassNotFoundException | SQLException e) {
											e.printStackTrace();
										}
									}
								});
								up.add(BorderLayout.SOUTH, qr);
								up.setVisible(true);
							}
						});
						panel_pf.add(up);
						// 删除按钮及其功能
						JButton delete = new JButton("删除");
						delete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e2) {
								JFrame delete = new JFrame("删除");
								delete.setLayout(new BorderLayout(1, 1));
								delete.setSize(500, 100);
								delete.setLocationRelativeTo(null);
								delete.setResizable(false);

								JLabel jl1 = new JLabel("输入删除的题号");
								delete.add(BorderLayout.NORTH, jl1);

								JTextField qno = new JTextField("题号");
								delete.add(qno);

								JButton qr = new JButton("确认");
								qr.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e1) {
										try {
											sql.delete.deleteword("DELETE FROM form WHERE pno='" + no.getText()
													+ "' AND qno='" + qno.getText() + "'");
										} catch (ClassNotFoundException | SQLException e) {
											e.printStackTrace();
										}
									}
								});
								delete.add(BorderLayout.SOUTH, qr);
								delete.setVisible(true);
							}
						});
						panel_pf.add(delete);

						show.add(BorderLayout.SOUTH, panel_pf);
						show.setVisible(true);

					}

				});
				form.add(BorderLayout.SOUTH, qr);
				form.setVisible(true);
			}
		});
		panel_s_b.add(form);

		panel_s.add(BorderLayout.SOUTH, panel_s_b);

		return panel_s;
	};// 组卷面板

	JPanel p_s()// 查看学生成绩
	{
		JPanel panel_s = new JPanel();
		panel_s.setBackground(Color.GREEN);
		panel_s.setLayout(new BorderLayout(1, 1));
		panel_s.add(BorderLayout.NORTH, new JLabel("学生成绩"));
		// 表格面板
		JScrollPane scrollPane = new JScrollPane();
		String[][] playerInfo = null;
		try {
			playerInfo = sql.show.all("s_g");// usershow是sql函数
		} catch (ClassNotFoundException | SQLException e) {
		}
		String Names[] = { "学生号", "学生名", "班级", "组卷名", "科目", "成绩" };
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

		JTextField clas = new JTextField("班级");
		panel_s_b.add(clas);

		JTextField cou = new JTextField("科目");
		panel_s_b.add(cou);

		JButton adds = new JButton("按班级或科目查询");
		panel_s_b.add(adds);
		adds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				String[][] playerInfo = null;
				try {
					if (clas.getText().equals("班级") && cou.getText().equals("科目")) {
						playerInfo = sql.show.all("s_g");// usershow是sql函数
					}
					if (!clas.getText().equals("班级") && cou.getText().equals("科目")) {
						playerInfo = sql.show.select("SELECT * FROM S_G WHERE sclass='" + clas.getText() + "'");
					}
					if (clas.getText().equals("班级") && !cou.getText().equals("科目")) {
						playerInfo = sql.show.select("SELECT * FROM S_G WHERE cname='" + cou.getText() + "'");
					}
					if (!clas.getText().equals("班级") && !cou.getText().equals("科目")) {
						playerInfo = sql.show.select("SELECT * FROM S_G WHERE sclass='" + clas.getText()
								+ "' AND cname='" + cou.getText() + "'");
					}

				} catch (ClassNotFoundException | SQLException e) {
				}
				// 子界面
				JFrame show = new JFrame(clas.getText() + cou.getText() + "学生成绩");
				show.setLayout(new BorderLayout(1, 1));
				show.setSize(500, 500);
				show.setLocationRelativeTo(null);
				show.setResizable(false);

				// 表格面板
				JScrollPane scrollPane = new JScrollPane();
				String Names[] = { "学生号", "学生名", "班级", "组卷名", "科目", "成绩" };
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

				show.add(BorderLayout.CENTER, scrollPane);
				show.setVisible(true);

			}
		});

		panel_s.add(BorderLayout.SOUTH, panel_s_b);
		return panel_s;
	};// 组卷面板

	T(String no)// 构造函数
	{
		// 窗口设置
		frame = new JFrame("教师操作平台");
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout(2, 2));

		// 按钮面板
		panel_b = new JPanel();
		panel_b.setLayout(new GridLayout(5, 1, 20, 20));
		JLabel jl1 = new JLabel("教师界面");
		jl1.setOpaque(true); // 设置背景颜色必须先将它设置为不透明的，默认透明
		jl1.setBackground(Color.GRAY);
		jb_q = new JButton("管理题目信息");
		jb_p = new JButton("管理组卷信息");
		jb_s = new JButton("查看学生成绩");
		jb_pw = new JButton("更改密码");
		panel_b.add(jl1);
		panel_b.add(jb_q);
		panel_b.add(jb_p);
		panel_b.add(jb_s);
		panel_b.add(jb_pw);
		jb_q.addActionListener(this);
		jb_p.addActionListener(this);
		jb_s.addActionListener(this);
		jb_pw.addActionListener(new ActionListener() {
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
							sql.update.up("update teacher set Tpassword ='" + new String(pw.getText())
									+ "' where Tno ='" + no + "'");
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					}
				});
				up.add(BorderLayout.SOUTH, qr);
				up.setVisible(true);
			}
		});
		frame.add(BorderLayout.WEST, panel_b);

		// 功能面板
		p = new JPanel();
		p.setLayout(new BorderLayout(1, 1));
		frame.add(BorderLayout.CENTER, p);

		// 初始界面

		p.add(new JLabel("欢迎你,教师" + no));

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		// 选择访问的用户表
		if (e.getSource() == jb_q) {
			p.removeAll();
			p.add(p_q());
			p.setVisible(false);
			p.setVisible(true);
			System.out.println("q");

		}
		if (e.getSource() == jb_p) {
			p.removeAll();
			p.add(p_p());
			p.setVisible(false);
			p.setVisible(true);
			System.out.println("p");
		}
		if (e.getSource() == jb_s) {
			p.removeAll();
			p.add(p_s());
			p.setVisible(false);
			p.setVisible(true);
			System.out.println("s");
		}

	}

	public static void main(String args[]) {
		new T("1");// 运行
	}
}
