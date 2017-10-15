package Actions;
import com.opensymphony.xwork2.ActionSupport;

public class deliver_aid extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer aid;
	
	public Integer getAid() {
		return aid;
	}
	
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
	public String execute() {
		return SUCCESS;
	}
}
