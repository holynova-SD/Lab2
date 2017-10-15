package Actions;
import java.sql.DriverManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class delete extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String execute() throws SQLException, ClassNotFoundException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_holynovalab2?useUnicode=true&characterEncoding=utf8&useSSL=false", "y3w5nzkyxx", "l40wjlk24ik4l14xzhz0hxikhhh0zk425kwhl04l");
			//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookDB?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "holynova");
			String sql = "delete from Book where Title = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.execute();
			stmt.close();
			con.close();
		}catch(Exception e){
            e.printStackTrace();
        }
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter outjs = response.getWriter();
		outjs.print("<script>alert('删除成功！');window.history.go(-2);</script>");
		return null;
	}
}
