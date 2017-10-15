package Actions;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.opensymphony.xwork2.ActionSupport;

public class details extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ArrayList<String>> detail_result;
	private String title;
	
	public ArrayList<ArrayList<String>> getDetail_result() {
		return detail_result;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String execute() throws SQLException, ClassNotFoundException {
		detail_result = new ArrayList<ArrayList<String>>();
		try {
			int id = 0;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_holynovalab2?useUnicode=true&characterEncoding=utf8&useSSL=false", "y3w5nzkyxx", "l40wjlk24ik4l14xzhz0hxikhhh0zk425kwhl04l");
			//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookDB?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "holynova");
			Statement sta_Author = con.createStatement(); 
			Statement sta_Book = con.createStatement(); 
			ResultSet rSet_Author = sta_Author.executeQuery("SELECT * FROM Author"); 
			ResultSet rSet_Book = sta_Book.executeQuery("SELECT * FROM Book");
			while(rSet_Book.next()) {
				if(rSet_Book.getString("Title").equals(title)) {
					ArrayList<String> Book_details = new ArrayList<String>();
					Book_details.add(rSet_Book.getString("ISBN"));
					Book_details.add(rSet_Book.getString("Title"));
					Book_details.add(Integer.toString(rSet_Book.getInt("AuthorID")));
					Book_details.add(rSet_Book.getString("Publisher"));
					Book_details.add(rSet_Book.getString("PublishDate"));
					Book_details.add(Float.toString(rSet_Book.getFloat("Price")));
					id = rSet_Book.getInt("AuthorID");
					detail_result.add(Book_details);
					break;
				}
			}
			while(rSet_Author.next()) {
				if(rSet_Author.getInt("AuthorID") == id) {
					ArrayList<String> Author_details = new ArrayList<String>();
					Author_details.add(Integer.toString(id));
					Author_details.add(rSet_Author.getString("Name"));
					Author_details.add(Integer.toString(rSet_Author.getInt("Age")));
					Author_details.add(rSet_Author.getString("Country"));
					detail_result.add(Author_details);
					break;
				}
			}
			rSet_Author.close();
			rSet_Book.close();
			sta_Author.close();
			sta_Book.close();
			con.close();
		}catch(Exception e){
            e.printStackTrace();
        }
		return SUCCESS;
	}
}
