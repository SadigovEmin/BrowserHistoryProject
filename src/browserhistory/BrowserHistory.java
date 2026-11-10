package browserhistory;

public class BrowserHistory {
	
	private DoublyLinkedList history;
	private Node currentPage;
	
	private static final int MAX_CAPACITY = 50;
	
	public BrowserHistory(String homepage) {
		history = new DoublyLinkedList();
		Node homePageNode = new Node(homepage, homepage);
		history.addLast(homePageNode);
		currentPage = homePageNode;
	} 
	
	public void visit(String url) {
		while(currentPage.getNext() != null) {
			history.removeNode(currentPage.getNext());
		}
		Node newPage = new Node(url, url);
			
		history.addLast(newPage);
			
		currentPage = newPage;
		if(history.getSize() > MAX_CAPACITY) history.removeFirst();
			
		
	}
	
	public void display_history() {
		Node current = history.getHead();
		while(current != null) {
			if(current == currentPage) {
				System.out.print("[<]");
			}
			System.out.print(current.getTitle() + " -> ");
			current = current.getNext();
		}
		System.out.println("NULL");
	}
	
	public String back(int steps) {
		if(steps < 0) {
			throw new IllegalArgumentException("Steps cannot be negative!");
			
		}
		for (int i = 0; i < steps; i++) {
			if(currentPage.getPrev() == null) {
				break;
			}
			currentPage = currentPage.getPrev();
		}
		return currentPage.getUrl();
	}
	
	public String forward(int steps) {
		if(steps < 0) {
			throw new IllegalArgumentException("Steps cannot be negative!");
		}
		for(int i = 0; i < steps; i++) {
			if(currentPage.getNext() == null) {
				break;
			}
			currentPage = currentPage.getNext();
		
		}
		return currentPage.getUrl();
	}
	
	public int getHistorySize() {
		return history.getSize();
	}
	

}
