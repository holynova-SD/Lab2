package Actions;
import java.sql.DriverManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class update extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String isbn;
	private String title;
	private String authorID;
	private String publisher;
	private String publishDate;
	private String price;
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthorID(String authorID) {
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
	
	public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
	}
	
	public String execute() throws SQLException, ClassNotFoundException, IOException {
		if(authorID == "" || publisher == "" || publishDate == "" || price == "") {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter outjs = response.getWriter();
			outjs.print("<script>alert('所有信息更新信息必填！');window.history.back(-1);</script>");
			return null;
		}
		else if(isNumeric(authorID) == false) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter outjs = response.getWriter();
			outjs.print("<script>alert('AuthorID必为整数！');window.history.back(-1);</script>");
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
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_holynovalab2?useUnicode=true&characterEncoding=utf8&useSSL=false", "y3w5nzkyxx", "l40wjlk24ik4l14xzhz0hxikhhh0zk425kwhl04l");
				//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookDB?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "holynova");
				String sql = "UPDATE Book SET AuthorID = ?, Publisher = ?, PublishDate = ?, Price = ? WHERE ISBN = ?";
				int int_authorID = Integer.parseInt(authorID);
				float f_price = Float.parseFloat(price);
				
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, int_authorID);
				pst.setString(2, publisher);
				pst.setString(3, publishDate);
				pst.setFloat(4, f_price);
				pst.setString(5, isbn);
				pst.execute();
				pst.close();
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter outjs = response.getWriter();
			outjs.print("<script>alert('修改成功！');window.history.back(-1);</script>");
			return null;
		}
	}
}
