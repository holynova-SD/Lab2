package Actions;
import java.sql.DriverManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class add_book extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String isbn;
	private String title;
	private Integer authorID;
	private String publisher;
	private String publishDate;
	private String price;
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthorID(Integer authorID) {
		this.authorID = authorID;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public boolean isDouble(String str){
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");  
	    return pattern.matcher(str).matches(); 
	}
	
	public String execute() throws SQLException, ClassNotFoundException, IOException {
		if(isbn == "" || title == "" || publisher == "" || publishDate == "" || price == "") {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter outjs = response.getWriter();
			outjs.print("<script>alert('所有信息必填！');window.history.back(-1);</script>");
			return null;
		}
		else if(isDouble(price) == false){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter outjs = response.getWriter();
			outjs.print("<script>alert('price必为浮点数！');window.history.back(-1);</script>");
			return null;
		}
		else {
			boolean is_exist = false;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_holynovalab2?useUnicode=true&characterEncoding=utf8&useSSL=false", "y3w5nzkyxx", "l40wjlk24ik4l14xzhz0hxikhhh0zk425kwhl04l");
				//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookDB?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "holynova");
				Statement sta_Book = con.createStatement(); 
				ResultSet rSet_Book = sta_Book.executeQuery("SELECT ISBN FROM Book");
				String sql = "INSERT INTO Book " + "(ISBN,Title,AuthorID,Publisher,PublishDate,Price) VALUES(?,?,?,?,?,?)";
				float f_price = Float.parseFloat(price);
				while(rSet_Book.next()) {
					if(rSet_Book.getString("ISBN").equals(isbn)) {
						is_exist = true;
						break;
					}
				}
				rSet_Book.close();
				sta_Book.close();
				if(is_exist == false) {
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, isbn);
					pst.setString(2, title);
					pst.setInt(3, authorID);
					pst.setString(4, publisher);
					pst.setString(5, publishDate);
					pst.setFloat(6, f_price);
					pst.execute();
					pst.close();
					con.close();
				}
				else {
					con.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			if(is_exist == false) {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter outjs = response.getWriter();
				outjs.print("<script>alert('插入成功！');window.history.back(-1);</script>");
				return null;
			}
			else {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter outjs = response.getWriter();
				outjs.print("<script>alert('该书已存在！');window.history.back(-1);</script>");
				return null;
			}
		}
	}
}
