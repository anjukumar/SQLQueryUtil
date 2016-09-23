import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Query {

	public void query() throws FileNotFoundException {
		String s, r;
		Scanner sc = new Scanner(System.in);
		// PrintWriter writer = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			System.out.println("Enter your SQL statement");
			s = sc.nextLine();
			String sql = s;
			System.out.println("Would you like to  save in a file? y or n");
			r = sc.nextLine();
			rs = stmt.executeQuery(sql);

			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			String intoWriter = "";
			PrintWriter writer = null;
			while (rs.next()) {

				for (int i = 1; i <= numberOfColumns; i++) {

					if (r.equalsIgnoreCase("n"))
					{
			
						System.out.println(rsmd.getColumnName(i) + ": " + rs.getString(i));
					}
					if (r.equalsIgnoreCase("y")) {

						intoWriter += rsmd.getColumnName(i) + ": " + rs.getString(i);
						try {
							writer = new PrintWriter("data.txt", "UTF-8");
							writer.println(intoWriter);
							writer.close();
							System.out.println("Data written in data.txt");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
