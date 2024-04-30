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

public class T implements ActionListener {
	JFrame frame;
	JPanel panel_b;// ��ť���
	JButton jb_q;// ������ⰴť
	JButton jb_p;// �������ť
	JButton jb_s;// �鿴ѧ���ɼ���ť
	JButton jb_pw;// �������밴ť

	JPanel p;// �������,����3��庯��Ϊ�������

	JPanel p_q() // ������Ŀ���
	{
		JPanel panel_s = new JPanel();
		panel_s.setBackground(Color.orange);
		panel_s.setLayout(new BorderLayout(1, 1));
		// ������Ϣ��
		panel_s.add(BorderLayout.NORTH, new JLabel("������Ŀ"));
		// �в�������
		JScrollPane scrollPane = new JScrollPane();
		String[][] playerInfo = null;
		try {
			playerInfo = sql.show.all("question");
		} catch (ClassNotFoundException | SQLException e) {
		} // usershow��sql����
		String Names[] = { "���", "��Ŀ", "A", "B", "C", "D", "��", "����", "��Ŀ" };// ����һ��ֻ�б�ͷ�ı��ģ��
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
				JTextField no = new JTextField("���");
				pjtx.add(no);
				JTextField con = new JTextField("����");
				pjtx.add(con);
				JTextField a = new JTextField("A");
				pjtx.add(a);
				JTextField b = new JTextField("B");
				pjtx.add(b);
				JTextField c = new JTextField("C");
				pjtx.add(c);
				JTextField d = new JTextField("D");
				pjtx.add(d);
				JTextField an = new JTextField("��");
				pjtx.add(an);
				JTextField type = new JTextField("����");
				pjtx.add(type);
				JTextField cname = new JTextField("��Ŀ");
				pjtx.add(cname);
				ad.add(pjtx);
				JButton qr = new JButton("ȷ��");
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
				JTextField no = new JTextField("���");
				pjtx.add(no);
				JTextField con = new JTextField("����");
				pjtx.add(con);
				JTextField a = new JTextField("A");
				pjtx.add(a);
				JTextField b = new JTextField("B");
				pjtx.add(b);
				JTextField c = new JTextField("C");
				pjtx.add(c);
				JTextField d = new JTextField("D");
				pjtx.add(d);
				JTextField an = new JTextField("��");
				pjtx.add(an);
				JTextField type = new JTextField("����");
				pjtx.add(type);
				JTextField cname = new JTextField("��Ŀ");
				pjtx.add(cname);
				up.add(pjtx);
				JButton qr = new JButton("ȷ��");
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

	JPanel p_p()// ����������
	{
		JPanel panel_s = new JPanel();
		panel_s.setBackground(Color.red);
		panel_s.setLayout(new BorderLayout(1, 1));
		panel_s.add(BorderLayout.NORTH, new JLabel("�������"));
		// ������
		JScrollPane scrollPane = new JScrollPane();
		String[][] playerInfo = null;
		try {
			playerInfo = sql.show.all("paper");// usershow��sql����
		} catch (ClassNotFoundException | SQLException e) {
		}
		String Names[] = { "����", "�����", "��Ŀ" };
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
				JTextField no = new JTextField("����");
				pjtx.add(no);
				JTextField name = new JTextField("����");
				pjtx.add(name);
				JTextField cname = new JTextField("��Ŀ");
				pjtx.add(cname);
				ad.add(pjtx);
				JButton qr = new JButton("ȷ��");
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
				JTextField no = new JTextField("����");
				pjtx.add(no);
				JTextField name = new JTextField("����");
				pjtx.add(name);
				JTextField cname = new JTextField("��Ŀ");
				pjtx.add(cname);
				up.add(pjtx);
				JButton qr = new JButton("ȷ��");
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

		// �����Ŀ���İ�ť���书��
		JButton form = new JButton("�����Ŀ����");
		form.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.print("form");
				JFrame form = new JFrame("����");
				form.setLayout(new BorderLayout(1, 1));
				form.setSize(500, 100);
				form.setLocationRelativeTo(null);
				form.setResizable(false);

				JLabel jl1 = new JLabel("����Ҫ�޸ĵ�������Ϣ");
				form.add(BorderLayout.NORTH, jl1);
				// ��Ϣ�ı������
				JTextField no = new JTextField("����");
				form.add(BorderLayout.CENTER, no);
				JButton qr = new JButton("ȷ��");
				qr.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e1) {
						form.setVisible(false);
						String[][] playerInfo = null;
						try {
							playerInfo = sql.show.select(
									"SELECT form.qno,qcontent,a,b,c,d,qanswer,qtype,cname FROM form,question WHERE form.qno=question.qno AND pno='"
											+ no.getText() + "'");
						} catch (ClassNotFoundException | SQLException e) {
							// TODO �Զ����ɵ� catch ��
							e.printStackTrace();
						}

						// �ӽ���
						JFrame show = new JFrame(no.getText() + "�Ծ���Ŀ���");
						show.setLayout(new BorderLayout(1, 1));
						show.setSize(500, 500);
						show.setLocationRelativeTo(null);
						show.setResizable(false);
						// ������
						JScrollPane scrollPane = new JScrollPane();
						String Names[] = { "���", "��Ŀ", "A", "B", "C", "D", "��", "����", "��Ŀ" };
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

						show.add(BorderLayout.CENTER, scrollPane);
						// �ײ���ť��
						JPanel panel_pf = new JPanel();
						// ��Ӱ�ť���书��
						JButton adds = new JButton("���");
						adds.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e1) {
								System.out.print("add");
								JFrame ad = new JFrame("���");
								ad.setLayout(new BorderLayout(1, 1));
								ad.setSize(500, 300);
								ad.setLocationRelativeTo(null);
								ad.setResizable(false);
								// ������Ϣ��
								JLabel jl1 = new JLabel("������ӵ���Ϣ");
								ad.add(BorderLayout.NORTH, jl1);

								String[][] playerInfo = null;
								try {
									playerInfo = sql.show.select(
											"SELECT qno,qcontent,a,b,c,d,qanswer,qtype,question.cname FROM paper,question WHERE paper.cname=question.cname AND pno='"
													+ no.getText() + "'");
								} catch (ClassNotFoundException | SQLException e) {
									// TODO �Զ����ɵ� catch ��
									e.printStackTrace();
								}
								// ������
								JScrollPane scrollPane = new JScrollPane();
								String Names[] = { "���", "��Ŀ", "A", "B", "C", "D", "��", "����", "��Ŀ" };
								// ����һ��ֻ�б�ͷ�ı��ģ��
								DefaultTableModel defaultTableModel = new DefaultTableModel(null, Names);
								// ��playerInfo�зǿ�Ԫ�ز������
								for (String[] x : playerInfo) {
									if (x[0] != null) {
										defaultTableModel.addRow(x);
									}
								}

								ad.add(scrollPane);

								JTable table = new JTable(defaultTableModel);
								// ���õ�Ԫ���е����־��� �Ǳ�ͷ��Ԫ��
								DefaultTableCellRenderer r = new DefaultTableCellRenderer();
								r.setHorizontalAlignment(JLabel.CENTER);
								table.setDefaultRenderer(Object.class, r);
								scrollPane.setViewportView(table);

								// �ײ��ı���+��ť���
								JPanel panel_ad = new JPanel();
								JTextField qno = new JTextField("���");
								panel_ad.add(qno);
								JButton qr = new JButton("ȷ��");
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

								JLabel jl1 = new JLabel("���뱻���ĵ��������ĺ�����");
								up.add(BorderLayout.NORTH, jl1);
								// ��Ϣ�ı������
								JPanel pjtx = new JPanel();
								JTextField qno1 = new JTextField("����");
								pjtx.add(qno1);
								JTextField qno2 = new JTextField("����");
								pjtx.add(qno2);
								up.add(pjtx);
								JButton qr = new JButton("ȷ��");
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
						// ɾ����ť���书��
						JButton delete = new JButton("ɾ��");
						delete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e2) {
								JFrame delete = new JFrame("ɾ��");
								delete.setLayout(new BorderLayout(1, 1));
								delete.setSize(500, 100);
								delete.setLocationRelativeTo(null);
								delete.setResizable(false);

								JLabel jl1 = new JLabel("����ɾ�������");
								delete.add(BorderLayout.NORTH, jl1);

								JTextField qno = new JTextField("���");
								delete.add(qno);

								JButton qr = new JButton("ȷ��");
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
	};// ������

	JPanel p_s()// �鿴ѧ���ɼ�
	{
		JPanel panel_s = new JPanel();
		panel_s.setBackground(Color.GREEN);
		panel_s.setLayout(new BorderLayout(1, 1));
		panel_s.add(BorderLayout.NORTH, new JLabel("ѧ���ɼ�"));
		// ������
		JScrollPane scrollPane = new JScrollPane();
		String[][] playerInfo = null;
		try {
			playerInfo = sql.show.all("s_g");// usershow��sql����
		} catch (ClassNotFoundException | SQLException e) {
		}
		String Names[] = { "ѧ����", "ѧ����", "�༶", "�����", "��Ŀ", "�ɼ�" };
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

		JTextField clas = new JTextField("�༶");
		panel_s_b.add(clas);

		JTextField cou = new JTextField("��Ŀ");
		panel_s_b.add(cou);

		JButton adds = new JButton("���༶���Ŀ��ѯ");
		panel_s_b.add(adds);
		adds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				String[][] playerInfo = null;
				try {
					if (clas.getText().equals("�༶") && cou.getText().equals("��Ŀ")) {
						playerInfo = sql.show.all("s_g");// usershow��sql����
					}
					if (!clas.getText().equals("�༶") && cou.getText().equals("��Ŀ")) {
						playerInfo = sql.show.select("SELECT * FROM S_G WHERE sclass='" + clas.getText() + "'");
					}
					if (clas.getText().equals("�༶") && !cou.getText().equals("��Ŀ")) {
						playerInfo = sql.show.select("SELECT * FROM S_G WHERE cname='" + cou.getText() + "'");
					}
					if (!clas.getText().equals("�༶") && !cou.getText().equals("��Ŀ")) {
						playerInfo = sql.show.select("SELECT * FROM S_G WHERE sclass='" + clas.getText()
								+ "' AND cname='" + cou.getText() + "'");
					}

				} catch (ClassNotFoundException | SQLException e) {
				}
				// �ӽ���
				JFrame show = new JFrame(clas.getText() + cou.getText() + "ѧ���ɼ�");
				show.setLayout(new BorderLayout(1, 1));
				show.setSize(500, 500);
				show.setLocationRelativeTo(null);
				show.setResizable(false);

				// ������
				JScrollPane scrollPane = new JScrollPane();
				String Names[] = { "ѧ����", "ѧ����", "�༶", "�����", "��Ŀ", "�ɼ�" };
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

				show.add(BorderLayout.CENTER, scrollPane);
				show.setVisible(true);

			}
		});

		panel_s.add(BorderLayout.SOUTH, panel_s_b);
		return panel_s;
	};// ������

	T(String no)// ���캯��
	{
		// ��������
		frame = new JFrame("��ʦ����ƽ̨");
		frame.setSize(700, 500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout(2, 2));

		// ��ť���
		panel_b = new JPanel();
		panel_b.setLayout(new GridLayout(5, 1, 20, 20));
		JLabel jl1 = new JLabel("��ʦ����");
		jl1.setOpaque(true); // ���ñ�����ɫ�����Ƚ�������Ϊ��͸���ģ�Ĭ��͸��
		jl1.setBackground(Color.GRAY);
		jb_q = new JButton("������Ŀ��Ϣ");
		jb_p = new JButton("���������Ϣ");
		jb_s = new JButton("�鿴ѧ���ɼ�");
		jb_pw = new JButton("��������");
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

		// �������
		p = new JPanel();
		p.setLayout(new BorderLayout(1, 1));
		frame.add(BorderLayout.CENTER, p);

		// ��ʼ����

		p.add(new JLabel("��ӭ��,��ʦ" + no));

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {
		// ѡ����ʵ��û���
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
		new T("1");// ����
	}
}
