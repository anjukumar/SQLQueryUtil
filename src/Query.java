import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

	public void query() throws FileNotFoundException, UnsupportedEncodingException {
		String s, r, t,name;
		Scanner sc = new Scanner(System.in);

		// PrintWriter writer = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		PrintWriter writer = null;
		writer = new PrintWriter(new FileOutputStream ("SQLQueries.txt", true));
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			System.out.println("To write a new SQL press (1) to use existing SQL (2) ");

			int o=sc.nextInt();
			//			sc.nextLine();

			if(o==1)
			{
				System.out.println("Enter your SQL statement");
				s = sc.nextLine();
				String sql = s;
				System.out.println("Save this query? y or n");
				t = sc.nextLine();
				System.out.println("Name of this query: ");
				name = sc.nextLine();

				String intoWriter = "";


				if(t.equalsIgnoreCase("y"))
				{
					writer.println(name +"|"+ s);
					writer.flush();
				}
				System.out.println("Would you like to  save in a file? y or n");
				r = sc.nextLine();
				rs = stmt.executeQuery(sql);

				ResultSetMetaData rsmd = rs.getMetaData();
				int numberOfColumns = rsmd.getColumnCount();			
				while (rs.next()) 
				{

					for (int i = 1; i <= numberOfColumns; i++) {

						if (r.equalsIgnoreCase("n"))
						{

							System.out.println(rsmd.getColumnName(i) + ": " + rs.getString(i)+"\n");
						}
						if (r.equalsIgnoreCase("y")) {

							intoWriter += "\n" +rsmd.getColumnName(i) + ": " + rs.getString(i);
							try {
								writer = new PrintWriter("data.txt", "UTF-8");


							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}

					writer.println(intoWriter);
					writer.flush();
					writer.close();
					System.out.println("Data written.");


				}
				try
				{
					rs.close();
					stmt.close();
					con.close();
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}

			}
			if(o==2)
			{
				int x=1;
				File file = new File("/home/oracle/workspace/Password/SQLQueries.txt");
				Scanner sc1 = new Scanner(file);
				File file2 = new File("/home/oracle/workspace/Password/SQLQueries.txt");
				Scanner sc2 = new Scanner(file);
				sc1.useDelimiter("\\|");
				sc2.useDelimiter("\\|");
				System.out.println("The saved queries are as follows " );
				while (sc1.hasNextLine())
				{


					String i = sc1.next();
					String q = sc1.nextLine();
					System.out.print(x+ "." +i+"\n"); 
					x++;		
				}
				System.out.println("Select option" );
				int o1 =sc.nextInt();
				sc.nextLine();
				int y =1;
				String q;
				while(sc2.hasNextLine())
				{		
					sc2.next();
					if(y==o1)
					{
						q = sc2.next();
					}
					else 
						sc2.next();
				}


			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

		}
	}
}
