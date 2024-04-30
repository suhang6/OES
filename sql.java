package 界面;

import java.sql.*;

//sql相关函数
public class sql {
	private static String user = "root";// mysql登录名
	private static String pass = "";// mysql登录密码（写自己之前设置的）
	private static String url = "jdbc:mysql://localhost:3306/oes";

	public static class show {

		// 返回sql语句的返回结果
		// sql;
		public static String[][] select(String sqlword) throws SQLException, ClassNotFoundException {
			System.out.println(sqlword);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			PreparedStatement pstmt = con.prepareStatement(sqlword);
			// 获取行数
			ResultSet rs = pstmt.executeQuery();
			int sample = 0;
			while (rs.next())
				sample++;
			// 输入列数
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colcount = rsmd.getColumnCount();// 取得全部列数
			// 赋值
			String[][] out = new String[sample][colcount];
			rs = pstmt.executeQuery();// 特别重要，否则取到的全是0。因为执行上面的while(rs.next())后，ResultSet对象的下标已指到0。
			// API：当生成ResultSet对象的Statement对象关闭、重新执行或用来从多个结果的序列获取下一个结果时，ResultSet对象将自动关闭。
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

		// 返回表格全部内容函数
		// selecct * from table;
		public static String[][] all(String table) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "select * from " + table;
			PreparedStatement pstmt = con.prepareStatement(sql);
			// 获取行数
			ResultSet rs = pstmt.executeQuery();
			int sample = 0;
			while (rs.next())
				sample++;
			// 输入列数
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colcount = rsmd.getColumnCount();// 取得全部列数
			// 赋值
			String[][] out = new String[sample][colcount];
			rs = pstmt.executeQuery();// 特别重要，否则取到的全是0。因为执行上面的while(rs.next())后，ResultSet对象的下标已指到0。
			// API：当生成ResultSet对象的Statement对象关闭、重新执行或用来从多个结果的序列获取下一个结果时，ResultSet对象将自动关闭。
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

		// 带条件返回所有内容
		// select * from table where restriction;
		public static String[][] restricted(String table, String restriction)
				throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "select * from " + table + " where " + restriction;
			PreparedStatement pstmt = con.prepareStatement(sql);
			// 获取行数
			ResultSet rs = pstmt.executeQuery();
			int sample = 0;
			while (rs.next())
				sample++;
			// 输入列数
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colcount = rsmd.getColumnCount();// 取得全部列数
			// 赋值
			String[][] out = new String[sample][colcount];
			rs = pstmt.executeQuery();// 特别重要，否则取到的全是0。因为执行上面的while(rs.next())后，ResultSet对象的下标已指到0。
			// API：当生成ResultSet对象的Statement对象关闭、重新执行或用来从多个结果的序列获取下一个结果时，ResultSet对象将自动关闭。
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
		// 修改语句
		//
		public static int up(String sqlword) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = sqlword;
			PreparedStatement ptmt = con.prepareStatement(sql);

			try {
				ptmt.executeUpdate();
				System.out.println(ptmt.executeUpdate(sql));
				// update语句更新失败时不报错,需用受影响行数ptmt.executeUpdate(sql)为1或者0来判断语句是否执行成功
				if (ptmt.executeUpdate(sql) > 0) // 多于0行受影响
				{
					System.out.println("添加成功");
					ptmt.close();
					con.close();
					return 1;
				} else // ptmt.executeUpdate(sql)==0
				{
					System.out.println("添加失败");
					ptmt.close();
					con.close();
					return 0;
				}
			} catch (Exception e1) {
				System.out.println("添加失败");
				ptmt.close();
				con.close();
				return 0;
			}

		}

		// 修改题目
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

				// update语句更新失败时不报错,需用受影响行数ptmt.executeUpdate(sql)为1或者0来判断语句是否执行成功
				if (ptmt.executeUpdate() == 1) // 多于0行受影响
				{
					System.out.println("修改题目成功");
					ptmt.close();
					con.close();
					return 1;
				} else // ptmt.executeUpdate(sql)==0
				{
					System.out.println("题目已修改,修改失败");
					ptmt.close();
					con.close();
					return 0;
				}
			} catch (Exception e1) {
				System.out.println("修改失败");
				ptmt.close();
				con.close();
				return 0;
			}
		}

		// 传入考试 分数
		//
		public static int grade(String sno, String pno, int grade) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "update exam set egrade = " + grade + " where sno = '" + sno + "' and pno = '" + pno + "'";
			PreparedStatement ptmt = con.prepareStatement(sql);

			try {
				ptmt.executeUpdate();
				System.out.println(ptmt.executeUpdate(sql));
				// update语句更新失败时不报错,需用受影响行数ptmt.executeUpdate(sql)为1或者0来判断语句是否执行成功
				if (ptmt.executeUpdate(sql) > 0) // 多于0行受影响
				{
					System.out.println("添加成绩成功");
					ptmt.close();
					con.close();
					return 1;
				} else // ptmt.executeUpdate(sql)==0
				{
					System.out.println("添加成绩失败");
					ptmt.close();
					con.close();
					return 0;
				}
			} catch (Exception e1) {
				System.out.println("添加失败");
				ptmt.close();
				con.close();
				return 0;
			}

		}

	}

	public static class add {
		// 添加用户
		// insert into table+ (xno,xpassword)values(username,pw);
		public static int user(String table, String userno, String username, String pw)
				throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);

			String x = null;// 创建首标，例如在表名为administrator的表中进行查找，首标为“a”，可与“no”合成“ano”以供查询
			if (table == "administrator")
				x = "a";
			if (table == "student")
				x = "s";
			if (table == "teacher")
				x = "t";
			if (x == null)
				System.out.print("首标失败");
			String sql = "insert into " + table + " (" + x + "no," + x + "name," + x + "password)values(?,'" + username
					+ "',?)";
			PreparedStatement ptmt = con.prepareStatement(sql);
			ptmt.setString(1, userno);
			ptmt.setString(2, pw);
			try {
				ptmt.execute();
				System.out.println("添加用户成功！");
				ptmt.close();
				con.close();
				return 1;
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("用户号已经存在,添加用户失败");
				ptmt.close();
				con.close();
				return 0;
			}

		}

		// 添加题库题目
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
				System.out.println("题目添加成功");
				ptmt.close();
				con.close();
				return 1;
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("题目号已经存在,添加失败");
				ptmt.close();
				con.close();
				return 0;
			}

		}

		// 添加学生
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
				System.out.println("添加学生成功！");
				ptmt.close();
				con.close();
				return 1;
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("学生号已经存在,添加用户失败");
				ptmt.close();
				con.close();
				return 0;
			} catch (Exception e2) {
				System.out.println("性别必须为男或女");
				ptmt.close();
				con.close();
				return 0;
			}

		}

		// 添加组卷
		// insert into paper values(pno,Cname)
		public static int paper(String pno, String pname, String Cname) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "insert into paper  values ('" + pno + "','" + pname + "','" + Cname + "')";
			PreparedStatement ptmt = con.prepareStatement(sql);
			try {
				ptmt.execute();
				System.out.println("试卷添加成功");
				ptmt.close();
				con.close();
				return 1;
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("试卷号已经存在或添加不符号要求,添加失败");
				ptmt.close();
				con.close();
				return 0;
			}

		}

		// 向组卷添加题目
		// insert into paper values(pno,qno)
		public static int form(String pno, String qno) throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			String sql = "insert into form  values ('" + pno + "','" + qno + "')";
			PreparedStatement ptmt = con.prepareStatement(sql);
			try {
				ptmt.execute();
				System.out.println("向组卷添加题目成功");
				ptmt.close();
				con.close();
				return 1;
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("此题目号已经存在于试卷或添加不符号要求,添加失败");
				ptmt.close();
				con.close();
				return 0;
			}

		}

	}

	// 登陆函数
	// select xno,xpassword from table where xno=? and xpassword=?;
	public static int userlogin(String username, String password, String table)
			throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/oes";
		Connection con = DriverManager.getConnection(url, user, pass);
		// 生成sql语句
		String x = null;// 创建首标，例如在表名为administrator的表中进行查找，首标为“a”，可与“no”合成“ano”以供查询
		if (table == "administrator")
			x = "a";
		if (table == "student")
			x = "s";
		if (table == "teacher")
			x = "t";
		if (x == null)
			System.out.print("首标失败");
		String sql = "select " + x + "no," + x + "password from " + table + " where " + x + "no=? and " + x
				+ "password=?";
		PreparedStatement ptmt = con.prepareStatement(sql);
		ptmt.setString(1, username);
		ptmt.setString(2, password);
		ResultSet rs = ptmt.executeQuery();
		// 从登录用户给出的账号密码来检测查询在数据库表中是否存在相同的账号密码
		if (rs.next()) {
			System.out.println("登录成功！");
			rs.close();
			ptmt.close();
			con.close();
			return 1;
		} else {
			System.out.println("姓名或密码错误！");
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
				if (ptmt.executeUpdate(sql) > 0) // 多于0行受影响
				{
					System.out.println("删除成功");
					ptmt.close();
					con.close();
					return 1;
				} else // ptmt.executeUpdate(sql)==0
				{
					System.out.println("删除对象不存在");
					ptmt.close();
					con.close();
					return 0;
				}
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("删除失败");
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
				if (ptmt.executeUpdate(sql) > 0) // 多于0行受影响
				{
					System.out.println("删除成功");
					ptmt.close();
					con.close();
					return 1;
				} else // ptmt.executeUpdate(sql)==0
				{
					System.out.println("删除对象不存在");
					ptmt.close();
					con.close();
					return 0;
				}
			} catch (SQLIntegrityConstraintViolationException e1) {
				System.out.println("删除失败");
				ptmt.close();
				con.close();
				return 0;
			}

		}
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(userlogin("1", "123", "administrator"));

		// add.user("administrator","7", "lis","111");
		// add.question("103"," 1+1", "", "", "", "", "2", "填空", "高数");
		// add.student("08", "lis", "111", "222", "男");
		// add.paper("3", "数");
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
		// update.question("100", "1+10", "", "", "", "", "11", "填空", "高数");
		// update.up("update exam set egrade = 20 where sno = 002 and pno = 1");
		// delete("student","08");

	}
}
