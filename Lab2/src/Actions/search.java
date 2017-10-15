package Actions;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.opensymphony.xwork2.ActionSupport;

public class search extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ArrayList<String>> search_result;
	private String name;
	
	public ArrayList<ArrayList<String>> getSearch_result() {
		return search_result;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String execute() throws SQLException, ClassNotFoundException {
		
		int num = 0;
		search_result = new ArrayList<ArrayList<String>>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_holynovalab2?useUnicode=true&characterEncoding=utf8&useSSL=false", "y3w5nzkyxx", "l40wjlk24ik4l14xzhz0hxikhhh0zk425kwhl04l");
			//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookDB?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "holynova");
			Statement sta_Author = con.createStatement(); 
			Statement sta_Book = con.createStatement(); 
			ResultSet rSet_Author = sta_Author.executeQuery("SELECT AuthorID,Name FROM Author"); 
			int id;
			while(rSet_Author.next()) {
				if(rSet_Author.getString("Name").equals(name)) {
					num++;
					id = rSet_Author.getInt("AuthorID");
					ArrayList<String> Book_by_author = new ArrayList<String>();
					Book_by_author.add(name);
					Book_by_author.add(Integer.toString(id));
					ResultSet rSet_Book = sta_Book.executeQuery("SELECT Title,AuthorID FROM Book");
					while(rSet_Book.next()) {
						if(rSet_Book.getInt("AuthorID") == id) {
							Book_by_author.add(rSet_Book.getString("Title"));
						}
					}
					rSet_Book.close();
					search_result.add(Book_by_author);
				}
			}
			rSet_Author.close();
			sta_Author.close();
			sta_Book.close();
			con.close();
		}catch(Exception e){
            e.printStackTrace();
        }
		
		if(num == 0) {
			return ERROR;
		}
		else {
			return SUCCESS;
		}
		
	}
}
