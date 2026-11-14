package browserhistory;

public class DoublyLinkedList {
	
	private Node head;
	private Node tail;
	private int size;
	
	public void addLast(Node newNode) {
		
		if(head == null) {
			head = newNode;
			tail = newNode;
		}else {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
		size++;
	}
	
	public Node removeFirst() {
		if(head == null) {
			return null;
		}
		Node removedNode = head;
		
		if(head == tail) {
			head = null;
			tail = null;
		}else {
			head = head.getNext();
			head.setPrev(null);
		}
		removedNode.setNext(null);
		removedNode.setPrev(null);
		
		size--;
		
		return removedNode;
	}
	
	public Node removeLast() {
		
		if(tail==null) {
			return null;
		}
		
		Node removedNode = tail;
		
		if(head == tail) {
			head = null;
			tail = null;
		}else {
			tail = tail.getPrev();
			tail.setNext(null);
		}
		
		removedNode.setPrev(null);
		removedNode.setNext(null);
		
		size--;
		
		return removedNode;
		
		
	}
	
	public Node removeNode(Node node) {
		if(node == null || head == null) return null;
		Node current = head;
		boolean found = false;
		
		while(current != null) {
			if(current == node) {
				found = true;
				break;
			}
			current = current.getNext();
		}
		
		if(!found) return null;
		
		if(node == head) {
			head = node.getNext();
			
			if(head != null) {
				head.setPrev(null);
			}
			
		}
		
		if(node == tail) {
			tail = node.getPrev();
			
			if(tail != null) {
				tail.setNext(null);
			}
		}
		
		if(node.getPrev() != null) {
			node.getPrev().setNext(node.getNext());
		}
		
		if(node.getNext() != null) {
			node.getNext().setPrev(node.getPrev());
		}
		
		node.setPrev(null);
		node.setNext(null);
		
		size--;
		return node;
	}
	
	public void clear() {
		Node current = head;
		while(current != null) {
			Node nextNode = current.getNext();
			
			current.setPrev(null);
			current.setNext(null);
			current = nextNode;
		}
		head = null;
		tail = null;
		size = 0;
	}
	
	public void display() {
		if(head == null) {
			System.out.println("History is empty.");
			return;
		}
		Node current = head;
		while(current != null) {
			System.out.print(current.getTitle() + " -> ");
			current = current.getNext();
		}
		System.out.println("NULL");
	}
	
	
	public int getSize() {
		return size;
	}
	
	public Node getHead() {
		return head;
	}
	public Node getTail() {
		return tail;
	}
	

	public void insertAt(int index, Node newNode) {

	    if (index < 0 || index > size) {
	        throw new IndexOutOfBoundsException("Invalid index: " + index);
	    }
	
	    if (newNode == null) {
	        throw new IllegalArgumentException("Node cannot be null.");
	    }
	
	    Node check = head;
	
	    while (check != null) {
	        if (check == newNode) {
	            throw new IllegalArgumentException("Node already exists in the list.");
	        }
	
	        check = check.getNext();
	    }
	
	    if (index == size) {
	        addLast(newNode);
	        return;
	    }
	
	    Node current = head;
	
	    for (int i = 0; i < index; i++) {
	        current = current.getNext();
	    }
	
	    Node previous = current.getPrev();
	
	    newNode.setPrev(previous);
	    newNode.setNext(current);
	
	    if (previous == null) {
	        head = newNode;
	    } else {
	        previous.setNext(newNode);
	    }
	
	    current.setPrev(newNode);
	
	    size++;
	}

	

}
