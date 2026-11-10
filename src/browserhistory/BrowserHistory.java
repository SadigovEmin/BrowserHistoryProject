package browserhistory;

public class BrowserHistory {
	
	private DoublyLinkedList history;
	private Node currentPage;
	
	private static final int MAX_CAPACITY = 50;
	
	public BrowserHistory(String homepageUrl, String homepageTitle) {
		history = new DoublyLinkedList();
		Node homePageNode = new Node(homepageUrl, homepageTitle);
		history.addLast(homePageNode);
		currentPage = homePageNode;
	} 
	
	public void visit(String url, String title) {
		while(currentPage.getNext() != null) {
			history.removeNode(currentPage.getNext());
		}
		Node newPage = new Node(url, title);
			
		history.addLast(newPage);
			
		currentPage = newPage;
		if(history.getSize() > MAX_CAPACITY) history.removeFirst(); //Capacity requirement
			
		
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
	
	public void currentPage() {
		if(currentPage == null) {
			System.out.println("No current page. ");
			return;
		}
		System.out.println("\nCurrent Page Information");
		System.out.println("----------------------------");
		System.out.println("URL: " + currentPage.getUrl());
		System.out.println("Title: " + currentPage.getTitle());
		System.out.println("Visit Time: " + currentPage.getVisitTime());
	}
	
	public int getHistorySize() {
		return history.getSize();
	}
	

}
