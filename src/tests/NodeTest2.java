package tests;

import browserhistory.DoublyLinkedList;
import browserhistory.Node;

public class NodeTest2 {
	public static void main(String[] args) {
		
		DoublyLinkedList list = new DoublyLinkedList();
		
		Node google = new Node("https://google.com", "Google");
		Node youtube = new Node("https://youtube.com", "Youtube");
		Node github = new Node("https://github.com", "Github");
		Node udemy = new Node("https://udemy.com", "Udemy");
		Node coursera = new Node("https://coursera.com", "Coursera");
		
		list.addLast(google);
		list.addLast(youtube);
		list.addLast(github);
		list.addLast(udemy);
		list.addLast(coursera);
		
		System.out.println("Initial size: " + list.getSize());
		
		Node current = list.getHead();
		while(current!=null) {
			System.out.println(current);
			current = current.getNext();
		}
		System.out.println("\n \n");
		Node removed = list.removeNode(youtube);
		System.out.println("Test 1: Remove from middle");
		System.out.println("Removed: " + removed);
		System.out.println("Google's next: "+ google.getNext().getTitle());
		System.out.println("Github's prev: " + github.getPrev().getTitle());
		System.out.println("Size: " + list.getSize());
		System.out.println("\n \n");
		
		System.out.println("Test 2: Remove first node");
		
		removed = list.removeNode(google);
		System.out.println("Removed: " + removed.getTitle());
        System.out.println("New head: " + list.getHead().getTitle());
        System.out.println("New head previous: " + list.getHead().getPrev());
        System.out.println("Size: " + list.getSize());
        System.out.println("\n \n \n");
      

        System.out.println("Test 3: Remove last node");
        removed = list.removeNode(coursera);
        System.out.println("Removed: " + removed.getTitle());
        System.out.println("New tail: " + list.getTail().getTitle());
        System.out.println("New tail next: " + list.getTail().getNext());
        System.out.println("Size: " + list.getSize());
        System.out.println("\n \n \n");

        Node external = new Node("https://example.com", "Example");

        removed = list.removeNode(external);

        System.out.println("Test 4: Remove external node");
        System.out.println("Removed: " + removed);
        System.out.println("Size: " + list.getSize());
        System.out.println("\n \n \n");

        list.clear();

        System.out.println("Test 5: Clear list");
        System.out.println("Head: " + list.getHead());
        System.out.println("Tail: " + list.getTail());
        System.out.println("Size: " + list.getSize());
	}
}
