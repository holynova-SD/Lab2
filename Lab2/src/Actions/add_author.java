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
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class add_author extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String aid;
	private String name;
	private String age;
	private String country;
	
	public void setAid(String aid) {
		this.aid = aid;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public void setCountry(String country) {
		this.country = country;
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
		if(aid == "" || name == "" || age == "" || country == "") {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter outjs = response.getWriter();
			outjs.print("<script>alert('所有信息必填！');window.history.back(-1);</script>");
			return null;
		}
		else if(isNumeric(aid) == false ||isNumeric(age) == false){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter outjs = response.getWriter();
			outjs.print("<script>alert('AuthorID 或 age 必为整数！');window.history.back(-1);</script>");
			return null;
		}
		else {
			boolean is_exist = false;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_holynovalab2?useUnicode=true&characterEncoding=utf8&useSSL=false", "y3w5nzkyxx", "l40wjlk24ik4l14xzhz0hxikhhh0zk425kwhl04l");
				//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookDB?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "holynova");
				Statement sta_Author = con.createStatement(); 
				ResultSet rSet_Author = sta_Author.executeQuery("SELECT AuthorID FROM Author");
				String sql = "INSERT INTO Author " + "(AuthorID,Name,Age,Country) VALUES(?,?,?,?)";
				int int_aid = Integer.parseInt(aid);
				int int_age = Integer.parseInt(age);
				while(rSet_Author.next()) {
					if(rSet_Author.getInt("AuthorID") == int_aid) {
						is_exist = true;
						break;
					}
				}
				rSet_Author.close();
				sta_Author.close();
				if(is_exist == false) {
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, int_aid);
					pst.setString(2, name);
					pst.setInt(3, int_age);
					pst.setString(4, country);
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
				outjs.print("<script>alert('该作者已存在！');window.history.back(-1);</script>");
				return null;
			}
		}
	}
}
