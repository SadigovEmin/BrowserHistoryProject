package browserhistory;

public class NodeTest {
	public static void main(String[] args) {
		Node first = new Node("https://github.com/", "Github");
		Node second = new Node("https://google.com", "Google");
		
		first.setNext(second);
		second.setPrev(first);
		
		System.out.println(first.getTitle());
		System.out.println(first.getUrl());
		System.out.println(first.getVisitTime());
		
		System.out.println(first.getNext().getTitle());
		System.out.println(first.getNext().getPrev().getNext().getUrl());
		System.out.println(second.getVisitTime());
	}
}
