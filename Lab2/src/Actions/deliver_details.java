package Actions;
import com.opensymphony.xwork2.ActionSupport;

public class deliver_details extends ActionSupport {
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
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setAuthorID(String authorID) {
		this.authorID = authorID;
	}
	
	public String getAuthorID() {
		return authorID;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	public String getPublishDate() {
		return publishDate;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String execute() {
		return SUCCESS;
	}
}
