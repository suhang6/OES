package ����;

import java.sql.*;

//sql��غ���
public class sql {
	private static String user = "root";// mysql��¼��
	private static String pass = "";// mysql��¼���루д�Լ�֮ǰ���õģ�
	private static String url = "jdbc:mysql://localhost:3306/oes";

	public static class show {

		// ����sql���ķ��ؽ��
		// sql;
		public static String[][] select(String sqlword) throws SQLException, ClassNotFoundException {
			System.out.println(sqlword);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			PreparedStatement pstmt = con.prepareStatement(sqlword);
			// ��ȡ����
			ResultSet rs = pstmt.executeQuery();
			int sample = 0;
			while (rs.next())
				sample++;
			// ��������
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colcount = rsmd.getColumnCount();// ȡ��ȫ������
			// ��ֵ
			String[][] out = new String[sample][colcount];
			rs = pstmt.executeQuery();// �ر���Ҫ������ȡ����ȫ��0����Ϊִ�������while(rs.next())��ResultSet������±���ָ��0��
			// API��������ResultSet�����Statement����رա�����ִ�л������Ӷ����������л�ȡ��һ�����ʱ��ResultSet�����Զ��رա�
			for (int i = 0; rs.next(); i++) {
				for (int j = 0; j < colcount; j++) {
					out[i][j] = rs.getString(j + 1);
				}
			}
			rs.close();
			pstmt.close();
			con.close();
			return out;
		}

		// ���ر��ȫ�����ݺ���
		// selecct * from table;
		public static String[][] all(String table) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "select * from " + table;
			PreparedStatement pstmt = con.prepareStatement(sql);
			// ��ȡ����
			ResultSet rs = pstmt.executeQuery();
			int sample = 0;
			while (rs.next())
				sample++;
			// ��������
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colcount = rsmd.getColumnCount();// ȡ��ȫ������
			// ��ֵ
			String[][] out = new String[sample][colcount];
			rs = pstmt.executeQuery();// �ر���Ҫ������ȡ����ȫ��0����Ϊִ�������while(rs.next())��ResultSet������±���ָ��0��
			// API��������ResultSet�����Statement����رա�����ִ�л������Ӷ����������л�ȡ��һ�����ʱ��ResultSet�����Զ��رա�
			for (int i = 0; rs.next(); i++) {
				for (int j = 0; j < colcount; j++) {
					out[i][j] = rs.getString(j + 1);
				}
			}
			rs.close();
			pstmt.close();
			con.close();
			return out;
		}

		// ������������������
		// select * from table where restriction;
		public static String[][] restricted(String table, String restriction)
				throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "select * from " + table + " where " + restriction;
			PreparedStatement pstmt = con.prepareStatement(sql);
			// ��ȡ����
			ResultSet rs = pstmt.executeQuery();
			int sample = 0;
			while (rs.next())
				sample++;
			// ��������
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colcount = rsmd.getColumnCount();// ȡ��ȫ������
			// ��ֵ
			String[][] out = new String[sample][colcount];
			rs = pstmt.executeQuery();// �ر���Ҫ������ȡ����ȫ��0����Ϊִ�������while(rs.next())��ResultSet������±���ָ��0��
			// API��������ResultSet�����Statement����رա�����ִ�л������Ӷ����������л�ȡ��һ�����ʱ��ResultSet�����Զ��رա�
			for (int i = 0; rs.next(); i++) {
				for (int j = 0; j < colcount; j++) {
					out[i][j] = rs.getString(j + 1);
				}
			}
			rs.close();
			pstmt.close();
			con.close();
			return out;
		}

	}

	public static class update {
		// �޸����
		//
		public static int up(String sqlword) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = sqlword;
			PreparedStatement ptmt = con.prepareStatement(sql);

			try {
				ptmt.executeUpdate();
				System.out.println(ptmt.executeUpdate(sql));
				// update������ʧ��ʱ������,������Ӱ������ptmt.executeUpdate(sql)Ϊ1����0���ж�����Ƿ�ִ�гɹ�
				if (ptmt.executeUpdate(sql) > 0) // ����0����Ӱ��
				{
					System.out.println("��ӳɹ�");
					ptmt.close();
					con.close();
					return 1;
				} else // ptmt.executeUpdate(sql)==0
				{
					System.out.println("���ʧ��");
					ptmt.close();
					con.close();
					return 0;
				}
			} catch (Exception e1) {
				System.out.println("���ʧ��");
				ptmt.close();
				con.close();
				return 0;
			}

		}

		// �޸���Ŀ
		//
		public static int question(String Qno, String Qcontent, String A, String B, String C, String D, String Qanswer,
				String Qtype, String Cname) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "update question set Qcontent='" + Qcontent + "',A='" + A + "',B ='" + B + "',C='" + C
					+ "',D='" + D + "',Qanswer='" + Qanswer + "',Qtype='" + Qtype + "',Cname='" + Cname + "'where Qno='"
					+ Qno + "'";
			PreparedStatement ptmt = con.prepareStatement(sql);

			try {

				// update������ʧ��ʱ������,������Ӱ������ptmt.executeUpdate(sql)Ϊ1����0���ж�����Ƿ�ִ�гɹ�
				if (ptmt.executeUpdate() == 1) // ����0����Ӱ��
				{
					System.out.println("�޸���Ŀ�ɹ�");
					ptmt.close();
					con.close();
					return 1;
				} else // ptmt.executeUpdate(sql)==0
				{
					System.out.println("��Ŀ���޸�,�޸�ʧ��");
					ptmt.close();
					con.close();
					return 0;
				}
			} catch (Exception e1) {
				System.out.println("�޸�ʧ��");
				ptmt.close();
				con.close();
				return 0;
			}
		}

		// ���뿼�� ����
		//
		public static int grade(String sno, String pno, int grade) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "update exam set egrade = " + grade + " where sno = '" + sno + "' and pno = '" + pno + "'";
			PreparedStatement ptmt = con.prepareStatement(sql);

			try {
				ptmt.executeUpdate();
				System.out.println(ptmt.executeUpdate(sql));
				// update������ʧ��ʱ������,������Ӱ������ptmt.executeUpdate(sql)Ϊ1����0���ж�����Ƿ�ִ�гɹ�
				if (ptmt.executeUpdate(sql) > 0) // ����0����Ӱ��
				{
					System.out.println("��ӳɼ��ɹ�");
					ptmt.close();
					con.close();
					return 1;
				} else // ptmt.executeUpdate(sql)==0
				{
					System.out.println("��ӳɼ�ʧ��");
					ptmt.close();
					con.close();
					return 0;
				}
			} catch (Exception e1) {
				System.out.println("���ʧ��");
				ptmt.close();
				con.close();
				return 0;
			}

		}

	}

	public static class add {
		// ����û�
		// insert into table+ (xno,xpassword)values(username,pw);
		public static int user(String table, String userno, String username, String pw)
				throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);

			String x = null;// �����ױ꣬�����ڱ���Ϊadministrator�ı��н��в��ң��ױ�Ϊ��a�������롰no���ϳɡ�ano���Թ���ѯ
			if (table == "administrator")
				x = "a";
			if (table == "student")
				x = "s";
			if (table == "teacher")
				x = "t";
			if (x == null)
				System.out.print("�ױ�ʧ��");
			String sql = "insert into " + table + " (" + x + "no," + x + "name," + x + "password)values(?,'" + username
					+ "',?)";
			PreparedStatement ptmt = con.prepareStatement(sql);
			ptmt.setString(1, userno);
			ptmt.setString(2, pw);
			try {
				ptmt.execute();
				System.out.println("����û��ɹ���");
				ptmt.close();
				con.close();
				return 1;
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("�û����Ѿ�����,����û�ʧ��");
				ptmt.close();
				con.close();
				return 0;
			}

		}

		// ��������Ŀ
		// insert into question values(Qno,Qcontent,A,B,C,D,Qanswer,Qtype,Cname)
		public static int question(String Qno, String Qcontent, String A, String B, String C, String D, String Qanswer,
				String Qtype, String Cname) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "insert into question  values ('" + Qno + "','" + Qcontent + "','" + A + "','" + B + "','" + C
					+ "','" + D + "','" + Qanswer + "','" + Qtype + "','" + Cname + "')";
			PreparedStatement ptmt = con.prepareStatement(sql);
			try {
				ptmt.execute();
				System.out.println("��Ŀ��ӳɹ�");
				ptmt.close();
				con.close();
				return 1;
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("��Ŀ���Ѿ�����,���ʧ��");
				ptmt.close();
				con.close();
				return 0;
			}

		}

		// ���ѧ��
		// insert into student values(Sno,Sname,Spw,Sclass,Ssex)
		public static int student(String Sno, String Sname, String Spw, String Sclass, String Ssex)
				throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "insert into student values('" + Sno + "','" + Sname + "','" + Spw + "','" + Sclass + "','"
					+ Ssex + "')";
			PreparedStatement ptmt = con.prepareStatement(sql);
			try {
				ptmt.execute();
				System.out.println("���ѧ���ɹ���");
				ptmt.close();
				con.close();
				return 1;
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("ѧ�����Ѿ�����,����û�ʧ��");
				ptmt.close();
				con.close();
				return 0;
			} catch (Exception e2) {
				System.out.println("�Ա����Ϊ�л�Ů");
				ptmt.close();
				con.close();
				return 0;
			}

		}

		// ������
		// insert into paper values(pno,Cname)
		public static int paper(String pno, String pname, String Cname) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "insert into paper  values ('" + pno + "','" + pname + "','" + Cname + "')";
			PreparedStatement ptmt = con.prepareStatement(sql);
			try {
				ptmt.execute();
				System.out.println("�Ծ���ӳɹ�");
				ptmt.close();
				con.close();
				return 1;
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("�Ծ���Ѿ����ڻ���Ӳ�����Ҫ��,���ʧ��");
				ptmt.close();
				con.close();
				return 0;
			}

		}

		// ����������Ŀ
		// insert into paper values(pno,qno)
		public static int form(String pno, String qno) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "insert into form  values ('" + pno + "','" + qno + "')";
			PreparedStatement ptmt = con.prepareStatement(sql);
			try {
				ptmt.execute();
				System.out.println("����������Ŀ�ɹ�");
				ptmt.close();
				con.close();
				return 1;
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("����Ŀ���Ѿ��������Ծ����Ӳ�����Ҫ��,���ʧ��");
				ptmt.close();
				con.close();
				return 0;
			}

		}

	}

	// ��½����
	// select xno,xpassword from table where xno=? and xpassword=?;
	public static int userlogin(String username, String password, String table)
			throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/oes";
		Connection con = DriverManager.getConnection(url, user, pass);
		// ����sql���
		String x = null;// �����ױ꣬�����ڱ���Ϊadministrator�ı��н��в��ң��ױ�Ϊ��a�������롰no���ϳɡ�ano���Թ���ѯ
		if (table == "administrator")
			x = "a";
		if (table == "student")
			x = "s";
		if (table == "teacher")
			x = "t";
		if (x == null)
			System.out.print("�ױ�ʧ��");
		String sql = "select " + x + "no," + x + "password from " + table + " where " + x + "no=? and " + x
				+ "password=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, username);
		ptmt.setString(2, password);
		ResultSet rs = ptmt.executeQuery();
		// �ӵ�¼�û��������˺�����������ѯ�����ݿ�����Ƿ������ͬ���˺�����
		if (rs.next()) {
			System.out.println("��¼�ɹ���");
			rs.close();
			ptmt.close();
			con.close();
			return 1;
		} else {
			System.out.println("�������������");
			rs.close();
			ptmt.close();
			con.close();
			return 0;
		}

	}

	public static class delete {
		public static int deleteword(String sql) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			PreparedStatement ptmt = con.prepareStatement(sql);
			try {
				// ptmt.execute();
				if (ptmt.executeUpdate(sql) > 0) // ����0����Ӱ��
				{
					System.out.println("ɾ���ɹ�");
					ptmt.close();
					con.close();
					return 1;
				} else // ptmt.executeUpdate(sql)==0
				{
					System.out.println("ɾ�����󲻴���");
					ptmt.close();
					con.close();
					return 0;
				}
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("ɾ��ʧ��");
				ptmt.close();
				con.close();
				return 0;
			}

		}

		public static int delete(String table, String xno) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "delete from " + table + " where " + table.charAt(0) + "no ='" + xno + "'";
			PreparedStatement ptmt = con.prepareStatement(sql);
			try {
				// ptmt.execute();
				if (ptmt.executeUpdate(sql) > 0) // ����0����Ӱ��
				{
					System.out.println("ɾ���ɹ�");
					ptmt.close();
					con.close();
					return 1;
				} else // ptmt.executeUpdate(sql)==0
				{
					System.out.println("ɾ�����󲻴���");
					ptmt.close();
					con.close();
					return 0;
				}
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("ɾ��ʧ��");
				ptmt.close();
				con.close();
				return 0;
			}

		}
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(userlogin("1", "123", "administrator"));

		// add.user("administrator","7", "lis","111");
		// add.question("103"," 1+1", "", "", "", "", "2", "���", "����");
		// add.student("08", "lis", "111", "222", "��");
		// add.paper("3", "��");
		// add.form("3", "03");

		for (String x[] : show.all("paper")) {
			for (String y : x)
				System.out.printf(y + " ");
			System.out.println("");
		}

		// for(String x[]:show.restricted("student", "sno='001'"))
		// {
		// for(String y:x)System.out.printf(y+" ");
		// System.out.println("");
		// }

		// for(String x[]:show.select("select * from teacher"))
		// {
		// for(String y:x)System.out.printf(y+" ");
		// System.out.println("");
		// }

		// update.grade("001", "4", 100);
		// update.question("100", "1+10", "", "", "", "", "11", "���", "����");
		// update.up("update exam set egrade = 20 where sno = 002 and pno = 1");
		// delete("student","08");

	}
}
