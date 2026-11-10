package browserhistory;
import java.time.LocalDateTime;

// this node will store page information & metadata
public class Node {
	
	private String url;
	private String title;
	private LocalDateTime visitTime;
	
	private Node prev;
	private Node next;
	
	public Node(String url, String title) {
		this.url = url;
		this.title = title;
		this.visitTime = LocalDateTime.now();
		
		this.prev=null;
		this.next=null;
	}
	public Node(String url, String title, LocalDateTime visitTime) {
		this.url = url;
		this.title = title;
		this.visitTime = visitTime;
		
		this.prev=null;
		this.next=null;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public LocalDateTime getVisitTime() {
		return this.visitTime;
	}
	
	public Node getPrev() {
		return this.prev;
	}
	
	public Node getNext() {
		return this.next;
	}
	//since the metadata of node shouldnt be changed I am not creating setters for them
	public void setPrev(Node prev ) {
		this.prev = prev;
		
	}
	
	public void setNext(Node next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return "Url:  " + this.url +
					" \ntitle: " + this.title 
					+ " \ntimestamp visited: " + this.visitTime;
	}

}
